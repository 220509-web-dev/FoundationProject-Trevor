create schema design_style;

set search_path to design_style;

create table app_users (
  id            int generated always as identity primary key,
  first_name    varchar not null,
  last_name     varchar not null,
  email         varchar unique not null,
  username      varchar unique not null check (length(username) >= 4),
  password      varchar not null check (length(password) >= 8)

);

create table style_category (
  id            int generated always as identity primary key,
  name          varchar unique  not null,
  description   varchar not null,
  creator_id    int,

  constraint category_creator_fk
  foreign key (creator_id)
  references app_users(id)
);

create table design (
  id                int generated always as identity primary key,
  style             varchar not null,
  design_category   int,
  creator_id        int,

  constraint design_style_fk
  foreign key (design_category)
  references style_category (id),

  constraint style_creator_fk
  foreign key (creator_id)
  references app_users (id) 
);

