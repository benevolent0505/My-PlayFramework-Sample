# --- !Ups
create table Users (
  id serial not null primary key,
  name varchar(64) not null,
  email varchar(64) not null unique,
  password_digest varchar(64) not null,
  created_at timestamp not null,
  updated_at timestamp not null
);
# --- !Downs
drop table Users;