CREATE TABLE irrigation_schedules (

    id UUID PRIMARY KEY,

    zone_id UUID NOT NULL,

    scheduled_start TIMESTAMP WITH TIME ZONE NOT NULL,

    duration_minutes INTEGER NOT NULL,

    water_volume_liters NUMERIC(12,2),

    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_irrigation_schedule_zone
        FOREIGN KEY (zone_id)
        REFERENCES irrigation_zones(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_schedule_duration
        CHECK (duration_minutes > 0)
);

CREATE INDEX idx_irrigation_schedule_zone
    ON irrigation_schedules(zone_id);

CREATE INDEX idx_irrigation_schedule_start
    ON irrigation_schedules(scheduled_start);

CREATE INDEX idx_irrigation_schedule_status
    ON irrigation_schedules(status);