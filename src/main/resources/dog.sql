create table dog (id int(3) not null auto_increment, dname varchar(10) not null, dgender varchar(10) not null, dage int(3), primary key (id));


insert into dog (dname, dgender, dage) values ('D1', 'male', 2);
insert into dog (dname, dgender, dage) values ('D2', 'female', 1);
insert into dog (dname, dgender, dage) values ('D3', 'female', 2);