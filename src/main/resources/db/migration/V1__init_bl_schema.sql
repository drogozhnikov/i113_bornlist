create schema if not exists bornlist;

SET search_path TO bornlist;

DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS bornlist;

CREATE TABLE IF NOT EXISTS units
(
    id          SERIAL PRIMARY KEY,
    first_name   CHAR(100) not null,
    middle_name  CHAR(100),
    last_name    CHAR(100) unique not null,
    phone_number CHAR(100) unique,
    telegram    CHAR(100),
    date        timestamp not null,
    description text      not null
);

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    user_name   CHAR(100) not null unique,
    telegram_id CHAR(100) not null unique
);

CREATE TABLE IF NOT EXISTS bornlist
(
    id     SERIAL PRIMARY KEY,
    user_id int not null,
    unit_id int not null
);

ALTER TABLE bornlist
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE bornlist
    ADD FOREIGN KEY (unit_id) REFERENCES units (id);



