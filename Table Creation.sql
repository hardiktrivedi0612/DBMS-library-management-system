create database lms;

use lms;

-- BOOK TABLE CREATION
create table book (book_id varchar(10) not null, title varchar(1000), 
constraint pk_book primary key(book_id));

-- BOOK_AUTHORS TABLE CREATION
create table book_authors (book_id varchar(10) not null, author_name varchar(200),
constraint fk_book_authors_book foreign key(book_id) references book(book_id));

-- LIBRARY_BRANCH CREATION
create table library_branch (branch_id int not null, branch_name varchar(500), address varchar(1000), 
constraint pk_library_branch primary key(branch_id));

-- BOOK_COPIES TABLE CREATION
create table book_copies (book_id varchar(10) not null, branch_id int not null, no_of_copies int,
constraint fk_book_copies_book foreign key(book_id) references book(book_id),
constraint fk_book_copies_library_branch foreign key(branch_id) references library_branch(branch_id));

-- BORROWER TABLE CREATION
create table borrower (card_no int not null AUTO_INCREMENT, fname varchar(100) NOT NULL, lname varchar(100) NOT NULL, email varchar(500) NOT NULL, address varchar(500) NOT NULL, city varchar(100) NOT NULL, state varchar(100) NOT NULL, phone varchar(50) NOT NULL,
constraint pk_borrower primary key (card_no));

-- BOOK_LOANS TABLE CREATION
create table book_loans (loan_id int not null AUTO_INCREMENT, book_id varchar(10) not null, branch_id int not null, card_no int not null, date_out date, due_date date, date_in date, 
constraint pk_book_loans primary key (loan_id),
constraint fk_book_loans_book foreign key (book_id) references book(book_id),
constraint fk_book_loans_branch foreign key (branch_id) references library_branch(branch_id),
constraint fk_book_loans_card_no foreign key (card_no) references borrower(card_no));

-- FINES TABLE CREATION
create table fines (loan_id int not null, fine_amt DECIMAL(20,2) not null, paid boolean DEFAULT 0,
constraint fk_fines_book_loans foreign key(loan_id) references book_loans(loan_id));