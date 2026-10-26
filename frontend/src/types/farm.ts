export type FarmStatus = "ACTIVE" | "INACTIVE";

export interface Farm {
  id: string;
  farmCode: string;
  name: string;
  location: string;
  areaAcres: number;
  status: FarmStatus;
  createdAt: string;
}

export interface Plot {
  id: string;
  farmId: string;
  plotName: string;
  acres: number;
}