DROP TABLE IF EXISTS student, teacher, class, class_student, roster, course;
DROP TYPE IF EXISTS SEX;

CREATE TYPE SEX as ENUM ('man', 'vrouw', 'onbepaald', 'onbekend');

CREATE TABLE student (
    studentnumber CHAR(7) UNIQUE NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    insertion VARCHAR(20) NOT NULL,
    age INT NOT NULL,
	  sex SEX NOT NULL,
    street VARCHAR(255) NOT NULL,
    postalcode VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(20) CHECK (phonenumber ~* '^([0-9\.\-\(\)\s]){10,20}$') NOT NULL
);

CREATE TABLE teacher (
    teacherscode char(7) UNIQUE CHECK (teacherscode = REPLACE(teacherscode, ' ', '')) NOT NULL,
    firstname varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    insertion varchar(20) NOT NULL,
    age int NOT NULL,
	  sex SEX NOT NULL,
    street varchar(255) NOT NULL,
    postalcode VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(20) NOT NULL,
    CONSTRAINT phonenumber CHECK (phonenumber ~* '^([0-9\.\-\(\)\s]){10,20}$')
);

CREATE TABLE class (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL CHECK (enddate > startdate)
);

CREATE TABLE class_student (
    class_id INT REFERENCES class (id) ON DELETE CASCADE,
	student_studentnumber CHAR(7) REFERENCES student (studentnumber) ON DELETE CASCADE
);

CREATE TABLE course (
    coursecode VARCHAR(10) UNIQUE NOT NULL CHECK( coursecode ~ '^[A-Z]+$' ),
    name TEXT NOT NULL,
    teacher CHAR(7) REFERENCES teacher(teacherscode) ON DELETE CASCADE,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL CHECK (enddate > startdate)
);

CREATE TABLE roster (
    class INT REFERENCES class(id) NOT NULL,
    classroom VARCHAR(15) NOT NULL,
    starttime TIMESTAMP NOT NULL,
    endtime TIMESTAMP NOT NULL,
    teacher CHAR(7) REFERENCES teacher(teacherscode) ON DELETE CASCADE,
    course VARCHAR(10) REFERENCES course(coursecode) ON DELETE CASCADE
);