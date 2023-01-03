SET search_path TO bornlist;

insert into units(first_name, middle_name, last_name, phone_number, telegram, date, description)
values ('Gerald', 'From','Rivia', '+375-29-563-08-39', 'Geralt', current_timestamp, 'TestSubject');

insert into users(user_name, telegram_id)
values ('Geralt', 'Geralt_tid');

insert into bornlist(user_id, unit_id)
values (1, 1);
