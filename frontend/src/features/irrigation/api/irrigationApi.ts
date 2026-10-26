import { httpClient } from "../../../api/httpClient";
import type {
  IrrigationCommand,
  IrrigationZone,
} from "../../../types/irrigation";

interface IrrigationCommandResponse {
  executionId: string;
  zoneId: string;
  status: string;
  message: string;
}

export const irrigationApi = {
  async getZones(): Promise<IrrigationZone[]> {
    const response = await httpClient.get<IrrigationZone[]>(
      "/api/v1/irrigation/zones"
    );
    return response.data;
  },

  async start(
    zoneId: string,
    durationMinutes: number
  ): Promise<IrrigationCommandResponse> {
    const request: IrrigationCommand = {
      zoneId,
      commandType: "START",
      durationMinutes,
      idempotencyKey: crypto.randomUUID(),
    };
    const response = await httpClient.post<IrrigationCommandResponse>(
      "/api/v1/irrigation/commands",
      request
    );
    return response.data;
  },

  async stop(
    zoneId: string,
    idempotencyKey: string
  ): Promise<IrrigationCommandResponse> {
    const response = await httpClient.post<IrrigationCommandResponse>(
      "/api/v1/irrigation/commands",
      {
        zoneId,
        commandType: "STOP",
        idempotencyKey,
      }
    );
    return response.data;
  },
};

