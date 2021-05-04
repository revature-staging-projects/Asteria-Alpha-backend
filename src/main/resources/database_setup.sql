
create table Fav_Epic_ref ( 
	user_id int not null,
	img_id int not null,
	constraint favepic_id_pk primary key(user_id,img_id),
	constraint user_id_fk foreign key(user_id) references Users,
	constraint img_d_fk foreign key(img_id) references Fav_Epic_Image
);

create table Fav_Epic_Image (
	id serial,
	title text not null,
	image_date text not null,
	caption text not null,
	constraint fav_epic_pk primary key(id)
);

create table EPIC_Image ( 
	id serial,
	title text not null,
	image_date text not null,
	caption text not null,
	constraint epic_pk primary key(id)
);

create table Fav_Img_Ref (
	user_id int not null,
	img_id int not null,
	constraint comp_id_pk primary key(user_id,img_id),
	constraint user_id_fk foreign key(user_id) references Users,
	constraint img_d_fk foreign key(img_id) references Fav_Image
);

create table Fav_Image (
	id SERIAL,
	url text not null,
	title text not null,
	description text not null,
	constraint fav_img_pk primary key(id)
);


create table Verified(
	user_id int not null,
	verified bool not null default false
);


create table Users (
	id SERIAL,
	username text not null unique,
	password text not null,
	email text unique,
	location text,
	constraint user_pk primary key(id)
);

create table Article(
	id SERIAL,
	title text,
	snippet text,
	url text not null,
	thumbnail_url text,
	constraint article_pk primary key(id)
);

create table Fav_Article ( 
	id SERIAL,
	title text,
	snippet text,
	url text,
	thumbnail_url text,
	constraint fav_article_pk primary key(id)
);

create table Fav_Article_Ref(
	user_id int not null,
	article_id int not null,
	constraint user_id_fk foreign key(user_id) references Users,
	constraint article_id_fk foreign key(article_id) references Fav_Article
);

create table Image(
	id SERIAL,
	url text not null,
	title text not null,
	description text not null,
	constraint img_pk primary key(id)
);

