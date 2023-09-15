create table if not exists "payment" (
  id serial primary key,
  created_at timestamp,
  is_late boolean,
  clien_id int references "client"(id),
  bank_account int references "bank_account"(id)
);