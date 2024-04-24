CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY,
    username character varying(100) NOT NULL,
    password character varying(255) NOT NULL
);