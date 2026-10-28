export type CropStatus = "ACTIVE" | "INACTIVE" | "DISCONTINUED";
export type CropType = "CEREAL" | "PULSE" | "OILSEED" | "CASH_CROP" | "VEGETABLE" | "FRUIT";
export type Season = "KHARIF" | "RABI" | "SUMMER";

export interface Crop {
  id: string;
  cropCode: string;
  name: string;
  scientificName?: string;
  cropType: CropType;
  season: Season;
  durationDays: number;
  status: CropStatus;
  description?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CropClimateRequirement {
  id?: string;
  cropId: string;
  minTemperature: number;
  maxTemperature: number;
  minHumidity: number;
  maxHumidity: number;
  minRainfall: number;
  maxRainfall: number;
  waterRequirement: number;
}