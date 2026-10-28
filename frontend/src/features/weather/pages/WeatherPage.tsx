import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import { weatherApi } from "../api/weatherApi";
import "./weather.css";

const DEFAULT_LATITUDE = 17.5;
const DEFAULT_LONGITUDE = 80.2;

export function WeatherPage() {
  const [latitude, setLatitude] = useState(DEFAULT_LATITUDE);
  const [longitude, setLongitude] = useState(DEFAULT_LONGITUDE);
  const current = useQuery({ queryKey: ["weather", "current", latitude, longitude], queryFn: () => weatherApi.getCurrent(latitude, longitude) });
  const forecast = useQuery({ queryKey: ["weather", "forecast", latitude, longitude], queryFn: () => weatherApi.getForecast(latitude, longitude) });
  const isLoading = current.isLoading || forecast.isLoading;
  const error = current.error ?? forecast.error;

  return (
    <section className="weather-page">
      <header className="weather-page__header"><div><p className="weather-page__eyebrow">Field forecast</p><h2>Weather</h2><p>Live weather conditions and a seven-day forecast for your coordinates.</p></div></header>
      <form className="weather-location" onSubmit={(event) => event.preventDefault()}>
        <label>Latitude<input type="number" step="0.01" value={latitude} onChange={(event) => setLatitude(Number(event.target.value))} /></label>
        <label>Longitude<input type="number" step="0.01" value={longitude} onChange={(event) => setLongitude(Number(event.target.value))} /></label>
      </form>
      {isLoading && <LoadingSpinner />}
      {error && <ErrorMessage message={getApiErrorMessage(error)} />}
      {current.data && forecast.data && !isLoading && !error && <>
        <article className="weather-current"><div><span>Current conditions</span><strong>{Math.round(current.data.temperatureCelsius)}°C</strong><p>{String(current.data.weatherCondition ?? "Conditions unavailable").replaceAll("_", " ")}</p></div><dl><div><dt>Humidity</dt><dd>{current.data.humidityPercentage}%</dd></div><div><dt>Rain</dt><dd>{current.data.rainfallMm} mm</dd></div><div><dt>Wind</dt><dd>{current.data.windSpeedKmh} km/h</dd></div></dl></article>
        <h3 className="weather-forecast-title">Forecast</h3><div className="weather-forecast">{forecast.data.map((day) => <article key={day.date}><strong>{new Date(day.date).toLocaleDateString(undefined, { weekday: "short" })}</strong><span>{Math.round(day.temperatureCelsius)}°C</span><small>Rain {day.rainfallProbability}%</small><small>{day.expectedRainfallMm} mm expected</small></article>)}</div>
      </>}
    </section>
  );
}
