SET search_path TO bornlist;

insert into users(chat_id, user_name, first_name, last_name)
values ('425222583', 'drogozhnikov', 'Master', 'root');

insert into units(user_id, first_name, last_name, date)
values (1,'Geralt', 'Rivia', current_timestamp);

insert into units(user_id, first_name, last_name, date)
values (1,'Raivo', 'Pandaren', current_timestamp);



