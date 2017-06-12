create table persistent_logins (

  username varchar(64) not null,

  series varchar(64) primary key,

  token varchar(64) not null,

  last_used timestamp not null);

create table reader
(
  id serial not null
    constraint reader_pkey
    primary key,
  password varchar(255),
  user_created_date date,
  user_type varchar(255),
  username varchar(255)
)
;

create table post
(
  id serial not null
    constraint post_pkey
    primary key,
  body text,
  created_date date,
  title varchar(255),
  reader_id integer
    constraint fkruqvg6nyn45qstievlxcs3jx3
    references reader
)
;

