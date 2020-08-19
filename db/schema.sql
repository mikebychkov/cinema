
CREATE TABLE seats (
   id SERIAL PRIMARY KEY,
   row int,
   number int,
   price float
);

CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  name TEXT,
  phone TEXT,
  seatId int
);

INSERT INTO seats(row, number, price) VALUES(1, 1, 1000);
INSERT INTO seats(row, number, price) VALUES(1, 2, 1000);
INSERT INTO seats(row, number, price) VALUES(1, 3, 1000);

INSERT INTO seats(row, number, price) VALUES(2, 1, 800);
INSERT INTO seats(row, number, price) VALUES(2, 2, 800);
INSERT INTO seats(row, number, price) VALUES(2, 3, 800);

INSERT INTO seats(row, number, price) VALUES(3, 1, 600);
INSERT INTO seats(row, number, price) VALUES(3, 2, 600);
INSERT INTO seats(row, number, price) VALUES(3, 3, 600);
