create table urls (
	id INTEGER IDENTITY,
	url varchar(500) NOT NULL UNIQUE,
	status varchar(10) DEFAULT 'NEW' NOT NULL check (status in ('NEW', 'WIP', 'ERROR', 'DONE'))
);

-- TODO
-create table files (
-    name varchar(35) PRIMARY KEY,
-    path VARCHAR(500) NOT NULL
-);