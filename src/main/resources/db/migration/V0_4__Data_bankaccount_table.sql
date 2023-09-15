insert into "bank_account" ( ref, client_id, status, open_session ) values
('ref1', 1, 'ENABLE', CURRENT_TIMESTAMP),
('ref2', 2, 'ENABLE', LOCALTIMESTAMP),
('ref3', 3, 'DISABLE', NOW());