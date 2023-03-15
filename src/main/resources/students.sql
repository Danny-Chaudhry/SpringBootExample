CREATE TABLE students (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), 
studentId VARCHAR(255), exercises DOUBLE, assignment1 DOUBLE, assignment2 DOUBLE, assignment3 DOUBLE, 
midterm DOUBLE, finalExam DOUBLE, finalGrade DOUBLE, letterGrade VARCHAR(2));

INSERT INTO students (name, studentId, exercises, assignment1, assignment2, 
assignment3, midterm, finalExam, finalGrade, letterGrade) VALUES
('One', '111222001', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Two', '111222002', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Three', '111222003', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Four', '111222004', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Five', '111222005', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Six', '111222006', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Seven', '111222007', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Eight', '111222008', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Nine', '111222009', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+'),
('Ten', '111222010', 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 'A+');