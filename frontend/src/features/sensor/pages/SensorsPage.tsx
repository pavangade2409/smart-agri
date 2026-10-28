import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import StatusBadge from "../../../components/common/StatusBadge";
import { useQuery } from "@tanstack/react-query";
import { sensorApi } from "../api/sensorApi";
import "./sensors.css";

export function SensorsPage() {
  const { data: sensors = [], isLoading, isError, error } = useQuery({
    queryKey: ["sensors"],
    queryFn: sensorApi.getAll,
    staleTime: 60_000,
  });

  if (isLoading) return <LoadingSpinner />;
  if (isError) return <ErrorMessage message={getApiErrorMessage(error)} />;

  const activeSensors = sensors.filter((sensor) => sensor.status === "ACTIVE").length;

  return (
    <section className="sensors-page">
      <header className="sensors-page__header">
        <div>
          <p className="sensors-page__eyebrow">Field monitoring</p>
          <h2>Sensors</h2>
          <p>Track connected devices and their assigned farm zones.</p>
        </div>
        <div className="sensors-page__summary"><strong>{activeSensors}</strong> active of {sensors.length}</div>
      </header>

      {sensors.length === 0 ? (
        <div className="sensors-empty-state"><span>📡</span><h3>No sensors yet</h3><p>Create sensor records in the backend to monitor them here.</p></div>
      ) : (
        <div className="sensors-grid">
          {sensors.map((sensor) => (
            <article className="sensor-card" key={sensor.id}>
              <div className="sensor-card__topline"><span className="sensor-card__icon">📡</span><StatusBadge status={sensor.status} /></div>
              <h3>{sensor.sensorCode}</h3>
              <p>{sensor.sensorType.replaceAll("_", " ")}</p>
              <dl>
                <div><dt>Unit</dt><dd>{sensor.unit}</dd></div>
                <div><dt>Device ID</dt><dd>{sensor.deviceId ?? "Not linked"}</dd></div>
                <div><dt>Farm ID</dt><dd>{sensor.farmId}</dd></div>
                <div><dt>Zone ID</dt><dd>{sensor.zoneId ?? "Not assigned"}</dd></div>
              </dl>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}
