--liquibase formatted sql

--changeset kkorzhuk:1
CREATE TABLE notification_task (
id SERIAL,
chat_id LONG,
notification TEXT,
alarm_date TIMESTAMP,
added_at TIMESTAMP,
CONSTRAINT notification_task_pk PRIMARY KEY (id)
);