ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 16);
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;
ALTER TABLE student
    ADD CONSTRAINT name UNIQUE (name);
ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (colour, name);
ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20
