CREATE TABLE crops (

    id UUID PRIMARY KEY,

    crop_code VARCHAR(50)
        NOT NULL
        UNIQUE,

    name VARCHAR(100)
        NOT NULL,

    scientific_name VARCHAR(150),

    crop_type VARCHAR(50)
        NOT NULL,

    season VARCHAR(30)
        NOT NULL,

    duration_days INTEGER
        NOT NULL,

    status VARCHAR(30)
        NOT NULL,

    description VARCHAR(1000),

    created_at TIMESTAMP WITH TIME ZONE
        NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE
        NOT NULL,

    CONSTRAINT chk_crop_duration
        CHECK (duration_days > 0)
);

CREATE INDEX idx_crop_name
    ON crops(name);

CREATE INDEX idx_crop_type
    ON crops(crop_type);

CREATE INDEX idx_crop_season
    ON crops(season);

CREATE INDEX idx_crop_status
    ON crops(status);