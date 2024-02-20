CREATE TABLE users
(
    registration INTEGER NOT NULL,
    name         VARCHAR(255),
    password     VARCHAR(255),
    gender       VARCHAR(255),
    birth_date   date,
    roles        SMALLINT,
    email        VARCHAR(255),
    phone        VARCHAR(255),
    street       VARCHAR(255),
    number       VARCHAR(255),
    city         VARCHAR(255),
    zip_code     VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (registration)
);