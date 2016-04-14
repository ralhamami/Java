drop table BOOKS;

create table BOOKS (
id INTEGER NOT NULL GENERATED ALWAYS 
    AS IDENTITY (START WITH 1, INCREMENT BY 1),
TITLE varchar(30),
AUTHOR varchar(30),
PRICE decimal(19,2));

insert into BOOKS (TITLE,AUTHOR,PRICE) values('Head First Servelets','Mark',10.95);
insert into BOOKS(TITLE,AUTHOR,PRICE) values('Big C++','John',11.95);
insert into BOOKS (TITLE,AUTHOR,PRICE) values('Intro to Computers','Luke',12.95);
insert into BOOKS(TITLE,AUTHOR,PRICE) values('Java EE','Matt',13.95);