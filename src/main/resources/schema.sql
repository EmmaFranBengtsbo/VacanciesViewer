DROP TABLE vacancies IF EXISTS;

CREATE TABLE vacancies
(
    id               INTEGER IDENTITY PRIMARY KEY,
    name             VARCHAR(50)            NOT NULL,
    salary           INTEGER,
    experience       VARCHAR(50),
    city             VARCHAR(30)
);