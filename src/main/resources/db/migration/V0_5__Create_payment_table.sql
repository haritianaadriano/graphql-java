create table if not exists "payment" (
  id serial primary key,
  created_at timestamp,
  is_late boolean,
  client_id int references "client"(id),
  bank_account_id int references "bank_account"(id)
);