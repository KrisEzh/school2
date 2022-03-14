

SELECT student.name, student.age, faculty.id, faculty.colour, faculty.name
FROM student
         LEFT JOIN faculty ON student.faculty_id = faculty.id;



SELECT student.name, student.age
FROM student
         INNER JOIN avatar a on student.id = a.student_id

