CREATE TABLE users
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    registration INTEGER,
    name         VARCHAR(255),
    password     VARCHAR(255),
    gender       VARCHAR(255),
    birth_date   date,
    roles        SMALLINT,
    created_at   TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    email        VARCHAR(255),
    phone        VARCHAR(255),
    street       VARCHAR(255),
    number       VARCHAR(255),
    city         VARCHAR(255),
    zip_code     VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_registration UNIQUE (registration);