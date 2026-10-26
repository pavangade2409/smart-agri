CREATE TABLE sensor_readings (

    id UUID PRIMARY KEY,

    sensor_id UUID NOT NULL,

    reading_value DOUBLE NOT NULL,

    unit VARCHAR(20) NOT NULL,

    measured_at TIMESTAMP WITH TIME ZONE NOT NULL,

    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_sensor_reading_sensor
        FOREIGN KEY (sensor_id)
        REFERENCES sensors(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_sensor_reading_sensor_time
    ON sensor_readings (
        sensor_id,
        measured_at
    );