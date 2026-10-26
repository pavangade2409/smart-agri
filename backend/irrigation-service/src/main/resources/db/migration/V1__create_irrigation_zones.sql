CREATE TABLE irrigation_zones (

    id UUID PRIMARY KEY,

    farm_id UUID NOT NULL,

    zone_code VARCHAR(50) NOT NULL UNIQUE,

    name VARCHAR(100) NOT NULL,

    crop_id UUID,

    area_acres DOUBLE NOT NULL,

    flow_rate_lpm DOUBLE,

    pump_id VARCHAR(100),

    valve_id VARCHAR(100),

    status VARCHAR(30) NOT NULL,

    irrigation_mode VARCHAR(30) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT chk_zone_area
        CHECK (area_acres > 0),

    CONSTRAINT chk_zone_flow
        CHECK (
            flow_rate_lpm IS NULL
            OR flow_rate_lpm > 0
        )
);