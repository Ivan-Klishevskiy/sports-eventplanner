create table comment
(
    id            bigint generated by default as identity,
    creator_name  varchar(255),
    date_creating timestamp,
    description   varchar(255),
    event_id      bigint not null,
    title         varchar(255),
    primary key (id)
);

create table event
(
    id                   bigint generated by default as identity,
    creator_organization varchar(255),
    date                 date,
    description          varchar(255),
    sport_type           varchar(255),
    venue                varchar(255),
    weather_id           bigint,
    primary key (id)
);

create table event_participants
(
    event_id     bigint not null,
    participants varchar(255)
);

create table organization
(
    id           bigint generated by default as identity,
    description  varchar(255),
    name         varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    primary key (id)
);

create table weather
(
    id          bigint generated by default as identity,
    city        varchar(255),
    date        date,
    description varchar(255),
    humidity    integer not null,
    pressure    integer not null,
    temp        float   not null,
    visibility  integer not null,
    wind_speed  float   not null,
    primary key (id)
);

alter table event
    add constraint FK4250wea7wsn71ybt4chendboq foreign key (weather_id) references weather;

alter table event_participants
    add constraint FK5232w1ta0icpkemgsxyw8a976 foreign key (event_id) references event;