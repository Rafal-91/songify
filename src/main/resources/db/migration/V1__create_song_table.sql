CREATE TABLE song
(
    id            BIGSERIAL PRIMARY KEY ,
    name          VARCHAR(255) NOT NULL,
    artist        VARCHAR(255) NOT NULL,
    released_date TIMESTAMP WITHOUT TIME ZONE,
    duration      BIGINT,
    language      VARCHAR(255)
);

