insert into "payment"
    (id, created_at, is_late, client_id, bank_account_id)
values
    ('pai1', '2021-11-08T07:30:00.00Z', true, 1, 'bank1'),
    ('pai2', '2021-11-08T08:30:00.00Z', true, 2, 'bank2'),
    ('pai3', '2021-10-08T07:30:00.00Z', false, 2, 'bank2'),
    ('pai4', null, false, 3, 'bank3');