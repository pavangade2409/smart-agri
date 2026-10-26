CREATE TABLE crop_climate_requirements (

    id UUID PRIMARY KEY,

    crop_id UUID
        NOT NULL
        UNIQUE,

    min_temperature NUMERIC(5,2),

    max_temperature NUMERIC(5,2),

    min_humidity NUMERIC(5,2),

    max_humidity NUMERIC(5,2),

    min_rainfall NUMERIC(8,2),

    max_rainfall NUMERIC(8,2),

    min_wind_speed NUMERIC(6,2),

    max_wind_speed NUMERIC(6,2),

    min_ph NUMERIC(4,2),

    max_ph NUMERIC(4,2),

    water_requirement NUMERIC(10,2),

    CONSTRAINT fk_crop_climate_crop
        FOREIGN KEY (crop_id)
        REFERENCES crops(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_humidity_min
        CHECK (
            min_humidity IS NULL
            OR min_humidity >= 0
        ),

    CONSTRAINT chk_humidity_max
        CHECK (
            max_humidity IS NULL
            OR max_humidity <= 100
        ),

    CONSTRAINT chk_ph_min
        CHECK (
            min_ph IS NULL
            OR min_ph >= 0
        ),

    CONSTRAINT chk_ph_max
        CHECK (
            max_ph IS NULL
            OR max_ph <= 14
        )
);