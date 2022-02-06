
DROP TABLE users;
CREATE TABLE users (
    name       VARCHAR2(20)
        CONSTRAINT nn_users_name NOT NULL,
    surname    VARCHAR2(20),
    password   VARCHAR2(20)
        CONSTRAINT nn_users_password NOT NULL,
    email      VARCHAR2(30)
        CONSTRAINT pk_users_email PRIMARY KEY
);

insert into USERS (NAME, SURNAME, PASSWORD, EMAIL)
VALUES ('Ana', 'Kalemi', '1', 'anakalemi@unyt.edu.al');

DROP TABLE countries;
CREATE TABLE countries (
    iso_code  VARCHAR2(15)
        CONSTRAINT pk_countries_iso_code PRIMARY KEY,
    continent VARCHAR2(40),
    location  VARCHAR2(40)
);

DROP TABLE records;
CREATE TABLE records (
                            iso_code            VARCHAR2(15)
                                CONSTRAINT fk_records_iso_code
                                    REFERENCES countries
                                        ON DELETE CASCADE,
                            reg_date            DATE,
                            total_cases         NUMBER(10),
                            new_cases           NUMBER(10),
                            new_cases_smoothed  NUMBER(10),
                            total_deaths        NUMBER(10),
                            new_deaths          NUMBER(10),
                            new_deaths_smoothed NUMBER(10),
                            reproduction_rate   NUMBER(5),
                            new_tests           NUMBER(10),
                            total_tests         NUMBER(10),
                            stringency_index    NUMBER(3),
                            population          NUMBER(10),
                            median_age          NUMBER(4),
                            new_deaths_per_case NUMBER(10),
                            CONSTRAINT pk_records_id PRIMARY KEY ( iso_code, reg_date )
);