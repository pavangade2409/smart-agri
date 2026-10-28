import { httpClient } from "../../../api/httpClient";

export interface AgentRequest {
  farmerId: number;
  farmId: number;
  plotId?: number;
  conversationId?: string;
  message: string;
}

export interface AgentResponse {
  conversationId: string;
  answer: string;
  recommendation: string;
  source: string;
}

export const agentApi = {
  async ask(request: AgentRequest): Promise<AgentResponse> {
    const response = await httpClient.post<AgentResponse>(
      "/api/farmer-agent/ask",
      request
    );
    return response.data;
  },
};
