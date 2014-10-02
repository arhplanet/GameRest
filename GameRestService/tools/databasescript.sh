#!/bin/bash
SCRIPTDIR=$(dirname $0)
BASE=$SCRIPTDIR/..
RESOURCEDIRECTORY=src/main/resources
DBSCRIPTS=liquibase
MODULE=.

function create_dir_if_missing() {
    if [ ! -d $1 ]; then
        echo 'creating missing directory:' $1
        mkdir $1
    fi
}

function user_input() {
    text=$1
    default=$2
    read -p "$text [$default]: " val
    [[ -z $val ]] && echo $default || echo $val;
}


function create_sql_file() {
    MODULE=$1
    TIMESTAMP=$( date +%Y-%m-%d_%H_%M_%S )
    create_dir_if_missing $BASE/$MODULE/$RESOURCEDIRECTORY/$DBSCRIPTS
    FILENAME=$TIMESTAMP'_'$JIRA.sql
    FULL_PATH=$BASE/$MODULE/$RESOURCEDIRECTORY/$DBSCRIPTS/$FILENAME
    $(touch $FULL_PATH)
    echo -e "-- $JIRA $DESCRIPTION\n<skriv-SQL-en-her>\n" >> $FULL_PATH
    echo $FILENAME
}

function update_db_changelog() {
    MODULE=${1}
    ID=${2}
    FILENAME=${3}
    CONTEXT=${4}
    FILEPATH=$BASE/$MODULE/$RESOURCEDIRECTORY/db-changelog.xml

    rm -f tmp.xml
    rm -f tmp2.xml    
    touch tmp.xml
    echo '    <changeSet id="'$ID'" author="'$(whoami)'"'$CONTEXT'>' >> tmp.xml
    echo '        <comment>' >> tmp.xml
    echo '            '$DESCRIPTION >> tmp.xml
    echo '        </comment>'>> tmp.xml
    echo '        <sqlFile path="'$DBSCRIPTS'/'$FILENAME'" endDelimiter="//"/>' >> tmp.xml
    echo '    </changeSet>' >> tmp.xml
    
    awk '{if (index($0, "DATABASESCRIPT PLACEHOLDER") != 0) {system("cat tmp.xml"); print $0} else {print $0}}' $FILEPATH > tmp2.xml
    
    rm tmp.xml
    mv tmp2.xml $FILEPATH
}

echo ' 1. Skjemaendring'
echo ' 2. Populering av data (som også kunden skal ha)'
echo '    (Testdata for utvikling legges direkte i schema-populate-utv.sql filen.)'
echo ' Velg: '
read SELECTION


JIRA=$(user_input 'Enter JIRA number (ex JIRA-123):')
DESCRIPTION=$(user_input 'Enter Description:')

case $SELECTION in
    "1")
        CONTEXT=
        ;;
    "2")
        CONTEXT=' context="prod,utv"'
        ;;
esac

ID=$(date +%Y%m%d%H%M%S)

FILENAME=$(create_sql_file $MODULE)
echo "created script: $MODULE/$RESOURCEDIRECTORY/$DBSCRIPTS/$FILENAME"
update_db_changelog $MODULE $ID $FILENAME "$CONTEXT"

