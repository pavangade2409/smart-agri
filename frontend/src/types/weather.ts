export interface WeatherData {
  location: string;
  temperatureCelsius: number;
  humidityPercentage: number;
  rainfallMm: number;
  windSpeedKmh: number;
  weatherCondition: string;
  recordedAt: string;
}

export interface WeatherForecast {
  date: string;
  temperatureCelsius: number;
  rainfallProbability: number;
  expectedRainfallMm: number;
  weatherCondition: string;
}
