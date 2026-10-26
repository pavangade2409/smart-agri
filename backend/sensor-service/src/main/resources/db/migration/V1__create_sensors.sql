CREATE TABLE sensors (

    id UUID PRIMARY KEY,

    sensor_code VARCHAR(50)
        NOT NULL UNIQUE,

    farm_id UUID
        NOT NULL,

    zone_id UUID,

    sensor_type VARCHAR(50)
        NOT NULL,

    unit VARCHAR(20)
        NOT NULL,

    device_id VARCHAR(100),

    status VARCHAR(30)
        NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE
        NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE
        NOT NULL
);

CREATE INDEX idx_sensor_farm
    ON sensors(farm_id);

CREATE INDEX idx_sensor_zone
    ON sensors(zone_id);

CREATE INDEX idx_sensor_type
    ON sensors(sensor_type);

CREATE INDEX idx_sensor_status
    ON sensors(status);