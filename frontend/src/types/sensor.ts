export type SensorStatus = "ACTIVE" | "INACTIVE" | "OFFLINE" | "MAINTENANCE" | "FAULTY";
export type SensorType = "TEMPERATURE" | "HUMIDITY" | "SOIL_MOISTURE" | "pH" | "PRESSURE";

export interface Sensor {
  id: string;
  sensorCode: string;
  farmId: string;
  zoneId?: string;
  sensorType: SensorType;
  unit: string;
  deviceId?: string;
  status: SensorStatus;
  createdAt?: string;
  updatedAt?: string;
}

export interface SensorReading {
  id?: string;
  sensorId: string;
  value: number;
  unit: string;
  measuredAt: string;
  status?: "VALID" | "INVALID" | "ANOMALY";
}