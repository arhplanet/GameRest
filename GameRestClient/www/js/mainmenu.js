/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var mainmenu = {
    // Application Constructor, must be called explisitly
    initialize: function() {
        this.bindEvents();
    },

    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
        //document.addEventListener("backbutton", this.backButtonClick, false);
        //document.addEventListener("pause", yourCallbackFunction, false);
        //document.addEventListener("resume", yourCallbackFunction, false);
        //document.getElementById("newGameButton").addEventListener('click', this.createNewGame(), false);
    },

    backButtonClick: function() {
         // TODO : HAndle this in a sensible way
    },

    onDeviceReady: function() {
        document.getElementById("playername").innerHTML = document.getElementById("playername").innerHTML + " " + localStorage.nick;
        mainmenu.checkIfRegistred();
    },

    checkIfRegistred: function() {
        console.log(JSON.stringify("password: " + localStorage.passwordHash));
    },

    resetAppMemory: function() {
        alert('reset memory');
        localStorage.clear();
        window.location="index.html";
    },

    createNewGame: function() {
        window.location="gamescreen.html";
    },

    quitAppButton: function() {
        navigator.app.exitApp();
    }


};


