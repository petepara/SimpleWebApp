CREATE TYPE GENDER AS ENUM ('MALE', 'FEMALE');
CREATE TABLE employee
(
    employee_id    SERIAL PRIMARY KEY,
    first_name     VARCHAR(25) NOT NULL ,
    last_name      VARCHAR(50)  NOT NULL,
    department_id  int  NOT NULL,
    job_title      VARCHAR(100)  NOT NULL,
    gender         GENDER  NOT NULL,
    date_of_birth  DATE  NOT NULL
);

INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES ('Igor', 'Kortikov', 10, 'manager', 'MALE', '10.04.2000'),
       ('Sara', 'Terminatorova', 10, 'janitor', 'FEMALE', '22.12.1912'),
       ('Rob', 'Kopov', 10, 'security', 'MALE', '01.07.1961'),
       ('John', 'Nozhov', 10, 'HR', 'MALE', '01.01.1975'),
       ('Alla', 'Borisova', 10, 'director', 'FEMALE', '11.11.1989');
