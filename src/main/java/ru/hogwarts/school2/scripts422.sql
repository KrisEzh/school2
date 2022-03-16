CREATE TABLE people (
                         id SERIAL,
                         name TEXT PRIMARY KEY,
                         age INTEGER,
                         license TEXT,
                         car_id INTEGER REFERENCES cars(id)
);
CREATE TABLE cars (
                        id SERIAL PRIMARY KEY,
                        brand TEXT,
                        model TEXT,
                        price INTEGER
);
INSERT INTO people (Name, Age, License, car_id) VALUES ('Bob', 20, 'yes', 1);
INSERT INTO people (Name, Age, License, car_id) VALUES ('Ann', 23, 'yes', 1);
INSERT INTO people (Name, Age, License, car_id) VALUES ('Joe', 29, 'yes', 2);

INSERT INTO cars (Brand, Model, Price) VALUES ('BMW', 3, 100);
INSERT INTO cars (Brand, Model, Price) VALUES ('Audi', 'A6', 150);
