drop table if exists TEST_TABLE;

create table TEST_TABLE (id bigint not null auto_increment, f1 VARCHAR(20), primary key (id));

INSERT INTO TEST_TABLE(f1) VALUES('블라블라');