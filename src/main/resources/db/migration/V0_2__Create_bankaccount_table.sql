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
    ref varchar not null,
    client_id varchar references client(id),
    status status
);