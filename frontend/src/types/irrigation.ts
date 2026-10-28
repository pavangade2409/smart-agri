export type IrrigationStatus = "RUNNING" | "READY" | "STOPPED" | "FAILED" | "CANCELLED" | "COMPLETED" | "ACTIVE" | "INACTIVE";
export type CommandType = "START" | "STOP";

export interface IrrigationZone {
  id: string;
  farmId?: string;
  zoneCode?: string;
  zoneName: string;
  status: IrrigationStatus;
  pumpId?: string;
  valveId?: string;
  areaAcres?: number;
  flowRateLpm?: number;
}

export interface IrrigationCommand {
  zoneId: string;
  commandType: CommandType;
  durationMinutes?: number;
  idempotencyKey?: string;
}

export interface IrrigationExecution {
  id: string;
  zoneId: string;
  commandType: CommandType;
  status: "PENDING" | "RUNNING" | "COMPLETED" | "FAILED";
  startedAt?: string;
  completedAt?: string;
  requestedDurationMinutes?: number;
  actualDurationMinutes?: number;
}
