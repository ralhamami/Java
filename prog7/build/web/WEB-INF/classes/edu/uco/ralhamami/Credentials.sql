/* create the following table
   in WSP database (username: app, password: app)
*/

create table USERTABLE (
    ID integer not null generated always as identity
                        (start with 1, increment by 1),
    USERNAME varchar(255),
    PASSWORD char(64), /* SHA256 encryption */
    EMAIL varchar(255),
    primary key (id)
);

create table GROUPTABLE (
    ID integer not null generated always as identity
                        (start with 1, increment by 1),
    GROUPNAME varchar(255),
    USERNAME varchar(255),
    primary key (id)
);

/*
    initial entries
    root (password='ppp'): admingroup,studentgroup
    admin (password='ppp'): admingroup
    john (password='ppp'): studentgroup
*/
insert into USERTABLE (username, password, email)
    values ('root',
        'c4289629b08bc4d61411aaa6d6d4a0c3c5f8c1e848e282976e29b6bed5aeedc7',
        'root@uco.edu');
insert into GROUPTABLE (groupname, username) values ('admingroup', 'root');
insert into GROUPTABLE (groupname, username) values ('customergroup', 'root');

insert into USERTABLE (username, password, email)
    values ('admin',
        'c4289629b08bc4d61411aaa6d6d4a0c3c5f8c1e848e282976e29b6bed5aeedc7',
        'admin@uco.edu');
insert into GROUPTABLE (groupname, username) values ('admingroup', 'admin');

insert into USERTABLE (username, password, email)
    values ('john',
        'c4289629b08bc4d61411aaa6d6d4a0c3c5f8c1e848e282976e29b6bed5aeedc7',
        'john@uco.edu');
insert into GROUPTABLE (groupname, username) values ('customergroup', 'john');