CREATE TABLE PLAYER (
  ID                INT NOT NULL AUTO_INCREMENT,
  EMAIL             VARCHAR(256),
  NICK              VARCHAR(100),
  PASSWORDHASH      VARCHAR(256),
  LAST_ACTIVE       DATE,
  PRIMARY KEY (ID)
);

