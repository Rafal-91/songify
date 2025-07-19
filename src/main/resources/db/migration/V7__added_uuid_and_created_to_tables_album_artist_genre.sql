CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE album
    ADD creation_date TIMESTAMP(6) WITH TIME ZONE;

ALTER TABLE album
    ADD uuid UUID DEFAULT uuid_generate_v4() NOT NULL UNIQUE;

ALTER TABLE artist
    ADD creation_date TIMESTAMP(6) WITH TIME ZONE;

ALTER TABLE artist
    ADD uuid UUID DEFAULT uuid_generate_v4() NOT NULL UNIQUE;

ALTER TABLE genre
    ADD creation_date TIMESTAMP(6) WITH TIME ZONE;

ALTER TABLE genre
    ADD uuid UUID DEFAULT uuid_generate_v4() NOT NULL UNIQUE;