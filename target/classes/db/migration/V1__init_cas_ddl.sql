CREATE TABLE ROLE (
    ID serial NOT NULL,
    NAME varchar(255) NOT NULL,
    CONSTRAINT PK_ROLE PRIMARY KEY (ID)
);

CREATE TABLE ACCOUNT (
    ID serial NOT NULL,
    EMAIL varchar(255) UNIQUE  NOT NULL,
    PASSWORD varchar(255) NOT NULL,
    ENABLED boolean default false NOT NULL,
    ROLE_ID int NOT NULL,
    CONSTRAINT PK_ACCOUNT PRIMARY KEY (ID),
    CONSTRAINT FK_ACCOUNT_ROLE FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID)
);

CREATE TABLE CONFIRMATION_TOKEN (
    ID serial NOT NULL,
   	CREATED_DATE TIMESTAMP NOT NULL,
   	TOKEN varchar(255) NOT NULL,
   	ACCOUNT_ID int NOT NULL,
    CONSTRAINT PK_CONFIRMATION_TOKEN PRIMARY KEY (ID),
CONSTRAINT FK_CONFIRMATION_TOKEN_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT (ID)
);

CREATE SEQUENCE ROLE_SEQ START 10 INCREMENT 10 MINVALUE 10 OWNED BY ROLE.ID;
CREATE SEQUENCE ACCOUNT_SEQ START 10 INCREMENT 10 MINVALUE 10 OWNED BY ACCOUNT.ID;
CREATE SEQUENCE CONFIRMATION_TOKEN_SEQ START 10 INCREMENT 10 MINVALUE 10 OWNED BY CONFIRMATION_TOKEN.ID;

INSERT INTO ROLE VALUES(nextval('ROLE_SEQ'), 'USER');
INSERT INTO ROLE VALUES(nextval('ROLE_SEQ'), 'MANAGER');