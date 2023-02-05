SET search_path TO bornlist;

insert into units(first_name, middle_name, last_name, phone_number, telegram, date, description)
values ('Gerald', 'From', 'Rivia', '+375-29-563-08-39', 'Geralt', current_timestamp, 'TestSubject');

insert into units(first_name, middle_name, last_name, phone_number, telegram, date, description)
values ('Totoro', 'From', 'Livia', '+375-29-563-08-38', 'Totoro', current_timestamp, 'TestSubject');

insert into users(user_name, telegram_id)
values ('root', '425222583');

insert into binds(user_id, unit_id)
values (1, 1);

insert into binds(user_id, unit_id)
values (1, 2);
