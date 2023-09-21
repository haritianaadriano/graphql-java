insert into "bank_account" ( id, ref, client_id, status, open_session ) values
('bank1', 'ref1', 1, 'ENABLE', CURRENT_TIMESTAMP),
('bank2', 'ref2', 2, 'ENABLE', LOCALTIMESTAMP),
('bank3', 'ref3', 3, 'DISABLE', NOW());