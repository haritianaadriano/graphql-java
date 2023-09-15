do
$$
    begin
        if not exists(select from pg_type where typname = 'interest_timerate') then
            create type "status" as enum ('ENABLE', 'DISABLE');
        end if;
    end
$$;

create table if not exists "bank_account" (
    id serial primary key,
    open_session timestamp,
    ref varchar not null,
    client_id int references client(id),
    status status
);