CREATE TABLE IF NOT EXISTS hotel (
    id          SERIAL       PRIMARY KEY,
    city_name   VARCHAR(255) NOT NULL,
    city_ufi    INT NULL,
    country     VARCHAR(255) NOT NULL,
    dest_id     VARCHAR(50)  NOT NULL,
    dest_type   VARCHAR(50)  NOT NULL,
    hotels      INT          NOT NULL,
    label       VARCHAR(255) NOT NULL,
    latitude    DECIMAL(9,6) NOT NULL,
    longitude   DECIMAL(9,6) NOT NULL,
    lc          VARCHAR(10)  NOT NULL,
    name        VARCHAR(255) NOT NULL,
    nr_hotels   INT          NOT NULL,
    region      VARCHAR(255) NOT NULL,
    roundtrip   TEXT         NOT NULL,
    search_type VARCHAR(50)  NOT NULL,
    type        VARCHAR(50)  NOT NULL
);
