CREATE TABLE  author
(
	id SERIAL PRIMARY KEY,
	fullname VARCHAR(255) NOT NULL

);

CREATE TABLE book
(
	id SERIAL PRIMARY KEY,
	author_id INT NOT NULL,
	title VARCHAR(255) NOT NULL,
	cover BYTEA,
	synopsis TEXT,
	saga BOOLEAN,
	all_tome_published BOOLEAN,
	number_of_tome INT
);

CREATE TABLE genre
(
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	book_id INT NOT NULL	
);

CREATE TABLE trope
(
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	book_id INT

);

CREATE TABLE quotation
(
	id SERIAL PRIMARY KEY,
	quote_text TEXT NOT NULL,
	book_id INT NOT NULL
);

CREATE TABLE reading
(
	id SERIAL PRIMARY KEY,
	book_id INT NOT NULL,
	year_of_reading INT,
	month_of_reading INT,
	starting TIMESTAMP NOT NULL,
	finished TIMESTAMP,
	status_of_reading VARCHAR(255) NOT NULL,
	type_of_reading VARCHAR(255) NOT NULL,
	page_number INT,
	current_page INT,
	rating DECIMAL	
);

CREATE TABLE genre_list
(
	id SERIAL PRIMARY KEY,
	genre VARCHAR(255)	
);

CREATE TABLE trope_list
(
	id SERIAL PRIMARY KEY,
	trope VARCHAR(255)	
);