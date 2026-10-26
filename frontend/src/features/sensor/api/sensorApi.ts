import { httpClient } from "../../../api/httpClient";
import type { Sensor, SensorReading } from "../../../types/sensor";

export const sensorApi = {
  async getAll(): Promise<Sensor[]> {
    const response = await httpClient.get<Sensor[]>("/api/v1/sensors");
    return response.data;
  },

  async getById(id: string): Promise<Sensor> {
    const response = await httpClient.get<Sensor>(`/api/v1/sensors/${id}`);
    return response.data;
  },

  async getLatestReading(sensorId: string): Promise<SensorReading> {
    const response = await httpClient.get<SensorReading>(
      `/api/v1/sensor-readings/sensor/${sensorId}/latest`
    );
    return response.data;
  },
};
