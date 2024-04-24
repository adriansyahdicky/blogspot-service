CREATE TABLE IF NOT EXISTS blog (
    id UUID PRIMARY KEY,
    title varchar(255),
    body TEXT,
    user_author varchar(255)
);