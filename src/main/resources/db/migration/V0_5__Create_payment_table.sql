create table if not exists "payment" (
  id varchar primary key,
  created_at timestamp,
  is_late boolean,
  client_id int references "client"(id),
  bank_account_id varchar references "bank_account"(id)
);