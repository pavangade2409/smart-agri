import { httpClient } from "../../../api/httpClient";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName?: string;
  phone: string;
}

export interface SendOtpRequest {
  phone: string;
}

export interface VerifyOtpRequest {
  phone: string;
  otp: string;
}

export interface LoginResponse {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
  userId: number;
  email: string;
  role: string;
}

interface ApiEnvelope<T> {
  success: boolean;
  message: string;
  data?: T;
}

export const authApi = {
  async login(request: LoginRequest): Promise<LoginResponse> {
    const response = await httpClient.post<ApiEnvelope<LoginResponse>>(
      "/api/auth/login",
      request
    );
    return response.data.data!;
  },

  async register(request: RegisterRequest): Promise<void> {
    await httpClient.post<ApiEnvelope<void>>("/api/auth/register", request);
  },

  async sendOtp(request: SendOtpRequest): Promise<void> {
    await httpClient.post<ApiEnvelope<void>>("/api/auth/otp/send", request);
  },

  async verifyOtp(request: VerifyOtpRequest): Promise<LoginResponse> {
    const response = await httpClient.post<ApiEnvelope<LoginResponse>>(
      "/api/auth/otp/verify",
      request
    );
    return response.data.data!;
  },
};