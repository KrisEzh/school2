-- liquibase formatted sql

-- changeset krisEzh:1
CREATE INDEX students_name_index ON student (name);
SELECT * FROM student WHERE name='oleg';

CREATE INDEX faculty_nameAndColor_index ON faculty (colour, name);
SELECT * FROM faculty WHERE colour='red' AND name='grif';

