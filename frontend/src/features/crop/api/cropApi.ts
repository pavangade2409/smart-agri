import { httpClient } from "../../../api/httpClient";
import type { Crop } from "../../../types/crop";

export const cropApi = {
  async getAll(): Promise<Crop[]> {
    const response = await httpClient.get<{ content: Crop[] }>("/api/v1/crops");
    return response.data.content;
  },
};
