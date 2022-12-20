drop table if exists users;
create table if not exists users(
	username varchar(255) primary key,
	userpassword varchar(255)
);
drop table if exists userinfo;
create table if not exists userinfo(
	username varchar(255) primary key references users(username),
	name varchar(255),
	addr varchar(255),
	phone varchar(255),
	wechat varchar(255),
	qq varchar(255),
	email varchar(255)
);
drop table if exists itemslost;
create table if not exists itemslost(
	id integer primary key auto_increment,
	name varchar(255),
	`date` date,
	location varchar(255),
	description varchar(255),
	contact varchar(255),
	username varchar(255) references users(username)
);
drop table if exists itemsfound;
create table if not exists itemsfound(
	id integer primary key auto_increment,
	name varchar(255),
	`date` date,
	location varchar(255),
	description varchar(255),
	contact varchar(255),
	username varchar(255) references users(username)
);
drop table if exists itemstaken;
create table if not exists itemstaken(
	id integer primary key auto_increment,
	name varchar(255),
	`date` date,
	status varchar(255),
	location varchar(255),
	description varchar(255),
	contactlost varchar(255) references users(username),
	contactfound varchar(255) references users(username)
);