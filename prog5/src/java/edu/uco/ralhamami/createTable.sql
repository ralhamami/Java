Create Table BOOKS (
TITLE varchar(20), 
AUTHOR varchar(20),
PRICE double);

Create Table Cart(
TITLE varchar(20),
AUTHOR varchar(20),
UNITPRICE double,
QUANTITY integer,
SUBTOTAL double);

Create Table Orders(
ORDERNO integer NOT NULL,
TITLE varchar(20),
AUTHOR varchar(20),
UNITPRICE double,
QUANTITY integer);

INSERT INTO BOOKS (TITLE, AUTHOR, PRICE) VALUES ('The Best Book Ever','Mosa Johnson',37.50);
INSERT INTO BOOKS (TITLE, AUTHOR, PRICE) VALUES ('The Awesome Book','Rajiv Al-Hammami',27.25);
INSERT INTO BOOKS (TITLE, AUTHOR, PRICE) VALUES ('The Not So Good Book','Rayan Alobaid',18.45);
INSERT INTO BOOKS (TITLE, AUTHOR, PRICE) VALUES ('The Worst Book Ever','Zakria Hamdi',.75);