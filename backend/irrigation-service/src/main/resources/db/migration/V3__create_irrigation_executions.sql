CREATE TABLE irrigation_executions (

    id UUID PRIMARY KEY,

    zone_id UUID NOT NULL,

    command_type VARCHAR(30) NOT NULL,

    started_at TIMESTAMP WITH TIME ZONE,

    completed_at TIMESTAMP WITH TIME ZONE,

    requested_duration_minutes INTEGER,

    actual_duration_minutes INTEGER,

    requested_water_liters DOUBLE,

    actual_water_liters DOUBLE,

    status VARCHAR(30) NOT NULL,

    failure_reason VARCHAR(1000),

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    idempotency_key VARCHAR(100) NOT NULL UNIQUE,

    CONSTRAINT fk_irrigation_execution_zone
        FOREIGN KEY (zone_id)
        REFERENCES irrigation_zones(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_irrigation_execution_zone
    ON irrigation_executions(zone_id);

CREATE INDEX idx_irrigation_execution_created
    ON irrigation_executions(created_at);

CREATE INDEX idx_irrigation_execution_status
    ON irrigation_executions(status);