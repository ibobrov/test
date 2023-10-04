CREATE TABLE IF NOT EXISTS evaluations (
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(1000) UNIQUE NOT NULL,
    birthday DATE NOT NULL,
    evaluation_id INT REFERENCES evaluations(id),
    UNIQUE (full_name, birthday)
);