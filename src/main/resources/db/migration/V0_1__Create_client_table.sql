create table if not exists "client" (
    id serial primary key,
    first_name varchar,
    last_name varchar,
    phone_number varchar not null,
    email varchar not null
);