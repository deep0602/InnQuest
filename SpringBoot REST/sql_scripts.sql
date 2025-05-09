create table users (
        id number(10,0) generated as identity,
        email varchar2(255 char),
        first_name varchar2(255 char),
        last_name varchar2(255 char),
        phone_no varchar2(255 char),
        primary key (id)
    );

