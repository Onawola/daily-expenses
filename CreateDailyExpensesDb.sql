DROP TABLE IF EXISTS categorytbl;
DROP TABLE IF EXISTS userlogintbl;
DROP TABLE IF EXISTS expendituretbl;

CREATE TABLE categorytbl
(
	id SERIAL PRIMARY KEY,
	username TEXT NOT NULL,
	category TEXT NOT NULL
);

CREATE TABLE expendituretbl
(
	id SERIAL PRIMARY KEY,
	username TEXT NOT NULL,
	category TEXT NOT NULL,
	amount REAL NOT NULL,
	date_of_expense DATE NOT NULL
);

CREATE TABLE userlogintbl
(	
	username TEXT PRIMARY KEY,
	budget REAL NOT NULL,
	pass TEXT NOT NULL
);