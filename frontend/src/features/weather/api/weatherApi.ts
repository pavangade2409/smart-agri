import { httpClient } from "../../../api/httpClient";
import type { WeatherData, WeatherForecast } from "../../../types/weather";

interface BackendForecastDay {
    date: string;
    temperatureMax?: number;
    temperatureMin?: number;
    precipitation?: number;
    precipitationProbability?: number;
}

interface BackendForecastResponse {
    dailyForecast?: BackendForecastDay[];
}

export const weatherApi = {
    async getCurrent(latitude: number, longitude: number): Promise<WeatherData> {
        const response = await httpClient.get<WeatherData>(
            "/api/v1/weather/current",
            { params: { latitude, longitude } }
        );
        return response.data;
    },

    async getForecast(latitude: number, longitude: number): Promise<WeatherForecast[]> {
        const response = await httpClient.get<BackendForecastResponse>(
            "/api/v1/weather/forecast",
            { params: { latitude, longitude } }
        );
        return (response.data.dailyForecast ?? []).map((day) => ({
            date: day.date,
            temperatureCelsius: day.temperatureMax ?? day.temperatureMin ?? 0,
            rainfallProbability: day.precipitationProbability ?? 0,
            expectedRainfallMm: day.precipitation ?? 0,
            weatherCondition: "UNKNOWN",
        }));
    },
};