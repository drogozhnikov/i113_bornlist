SET search_path TO bornlist;

insert into users(user_name)
values ('master');

insert into units(user_id, first_name, last_name, date, description)
values (1,'Geralt', 'Rivia', current_timestamp, 'description');




