CREATE TABLE apartamento (
     id BIGINT PRIMARY KEY,
     numero VARCHAR(10) NOT NULL UNIQUE,
     andar INTEGER NOT NULL,
);
