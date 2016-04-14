drop table PURCHASEDBOOKS;
drop table PURCHASES;

create table PURCHASES (
id INTEGER NOT NULL GENERATED ALWAYS 
    AS IDENTITY (START WITH 1, INCREMENT BY 1),
QUANTITY integer,
TOTALPRICE decimal(19,2)
);

create table PURCHASEDBOOKS (
id integer,
TITLE varchar(30),
AUTHOR varchar(30),
PRICE decimal(19,2),
QUANTITY integer
);