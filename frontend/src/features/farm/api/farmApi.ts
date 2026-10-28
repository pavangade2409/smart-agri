import { httpClient } from "../../../api/httpClient";
import type { Farm } from "../../../types/farm";

interface FarmApiResponse {
  farmId: number;
  farmName: string;
  latitude: number | null;
  longitude: number | null;
  totalAcres: number;
  createdAt: string;
  updatedAt: string;
}

function toFarm(farm: FarmApiResponse): Farm {
  const hasCoordinates = farm.latitude !== null && farm.longitude !== null;

  return {
    id: String(farm.farmId),
    farmCode: `FARM-${farm.farmId}`,
    name: farm.farmName,
    location: hasCoordinates ? `${farm.latitude}, ${farm.longitude}` : "",
    areaAcres: farm.totalAcres,
    status: "ACTIVE",
    createdAt: farm.createdAt,
  };
}

export const farmApi = {
  async getAll(): Promise<Farm[]> {
    const response = await httpClient.get<FarmApiResponse[]>("/api/v1/farms");
    return response.data.map(toFarm);
  },

  async getById(id: string): Promise<Farm> {
    const response = await httpClient.get<FarmApiResponse>(`/api/v1/farms/${id}`);
    return toFarm(response.data);
  },
};
