export interface ApiErrorResponse {
  message: string;
  status?: number;
  timestamp?: string;
}

export function getApiErrorMessage(error: unknown): string {
  if (typeof error === "object" && error !== null && "response" in error) {
    const response = (
      error as { response?: { data?: ApiErrorResponse } }
    ).response;

    if (response?.data?.message) return response.data.message;
  }

  if (error instanceof Error) return error.message;

  return "Something went wrong";
}
