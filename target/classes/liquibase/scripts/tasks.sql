----liquibase formatted sql
----
----changeset kkorzhuk:1
CREATE TABLE notification_task (
id SERIAL,
chat_id LONG,
notification TEXT,
alarm_date TIMESTAMP,
added_at TIMESTAMP,
CONSTRAINT notification_task_pk PRIMARY KEY (id)
);
--UPDATE `databasechangelog` SET `MD5SUM`=`8:44ea5837fc800ffbbb86604dc3788ab3` WHERE `ID`=`00000000000001`;