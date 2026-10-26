import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { DashboardCard } from "../components/DashboardCard";
import { useDashboard } from "../hooks/useDashboard";
import "./dashboard.css";

export const DashboardPage = () => {
  const {
    farms,
    crops,
    sensors,
    zones,
    latestReading,
    isLoading,
    isError,
    error,
  } = useDashboard();

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return (
      <ErrorMessage
        message={
          (error as Error)?.message ||
          "Failed to load dashboard"
        }
      />
    );
  }

  const activeSensors = sensors.filter(
    (sensor) => sensor.status === "ACTIVE"
  ).length;

  const activeZones = zones.filter(
    (zone) =>
      zone.status === "ACTIVE" ||
      zone.status === "RUNNING"
  ).length;

  const moisture = latestReading?.value ?? null;

  return (
    <div className="dashboard-container dashboard-page">

      {/* Header */}
      <header className="dashboard-page__header">
        <div>
          <p>Smart Agri overview</p>
          <h2>Dashboard</h2>
        </div>

        <span>🟢 Live service data</span>
      </header>

      {/* Summary Cards */}
      <div className="dashboard-grid">

        <DashboardCard
          icon="🏡"
          title="Farms"
          value={farms.length}
          description="Total farms"
        />

        <DashboardCard
          icon="🌱"
          title="Crops"
          value={crops.length}
          description="Active crops"
        />

        <DashboardCard
          icon="📡"
          title="Sensors"
          value={sensors.length}
          description={`${activeSensors} active sensors`}
        />

        <DashboardCard
          icon="💧"
          title="Soil Moisture"
          value={
            moisture !== null
              ? `${moisture}%`
              : "--"
          }
          description={
            latestReading
              ? `Status: ${latestReading.status}`
              : "No reading available"
          }
        />

        <DashboardCard
          icon="🚿"
          title="Irrigation Zones"
          value={zones.length}
          description={`${activeZones} active zones`}
        />

      </div>

      {/* Lower Dashboard */}
      <section className="dashboard-page__lower-grid">

        {/* Recent Farms */}
        <article className="dashboard-page__panel">

          <h3>Recent farms</h3>

          {farms.length === 0 ? (
            <p>No farms available yet.</p>
          ) : (
            <ul>
              {farms.slice(0, 4).map((farm) => (
                <li key={farm.id}>
                  <span>
                    🏡 {farm.name}
                  </span>

                  <strong>
                    {farm.areaAcres} acres
                  </strong>
                </li>
              ))}
            </ul>
          )}

        </article>

        {/* System Status */}
        <article className="dashboard-page__panel">

          <h3>System status</h3>

          <ul>

            <li>
              <span>Crop catalog</span>
              <strong>
                {crops.length} available
              </strong>
            </li>

            <li>
              <span>Monitoring devices</span>
              <strong>
                {activeSensors} online
              </strong>
            </li>

            <li>
              <span>Soil moisture</span>
              <strong>
                {moisture !== null
                  ? `${moisture}%`
                  : "No data"}
              </strong>
            </li>

            <li>
              <span>Irrigation</span>
              <strong>
                {activeZones} active
              </strong>
            </li>

          </ul>

        </article>

      </section>

    </div>
  );
};