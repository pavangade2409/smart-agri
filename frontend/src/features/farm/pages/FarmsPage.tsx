import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import StatusBadge from "../../../components/common/StatusBadge";
import { getApiErrorMessage } from "../../../api/apiError";
import { useFarms } from "../hooks/useFarms";
import "./farms.css";

function formatUpdatedAt(value?: string): string {
  if (!value) return "Not available";

  const date = new Date(value);
  return Number.isNaN(date.getTime()) ? "Not available" : date.toLocaleDateString();
}

export function FarmsPage() {
  const { data: farms = [], isLoading, isError, error } = useFarms();
  const totalArea = farms.reduce((total, farm) => total + farm.areaAcres, 0);

  if (isLoading) return <LoadingSpinner />;

  if (isError) {
    return <ErrorMessage message={getApiErrorMessage(error)} />;
  }

  return (
    <section className="farms-page">
      <header className="farms-page__header">
        <div>
          <p className="farms-page__eyebrow">Farm management</p>
          <h2>Farms</h2>
          <p className="farms-page__description">
            Monitor your farms and their cultivated area in one place.
          </p>
        </div>
        <div className="farms-page__summary">
          <span>{farms.length} farms</span>
          <strong>{totalArea.toLocaleString()} acres</strong>
        </div>
      </header>

      {farms.length === 0 ? (
        <div className="farms-empty-state">
          <span aria-hidden="true">🏡</span>
          <h3>No farms yet</h3>
          <p>Create a farm in the backend to see it listed here.</p>
        </div>
      ) : (
        <div className="farms-grid">
          {farms.map((farm) => (
            <article className="farm-card" key={farm.id}>
              <div className="farm-card__topline">
                <div className="farm-card__icon" aria-hidden="true">🏡</div>
                <StatusBadge status={farm.status} />
              </div>
              <h3>{farm.name}</h3>
              <p className="farm-card__location">📍 {farm.location ?? "Location not set"}</p>
              <div className="farm-card__metrics">
                <div>
                  <span>Area</span>
                  <strong>{farm.areaAcres.toLocaleString()} acres</strong>
                </div>
                <div>
                  <span>Last updated</span>
                  <strong>{formatUpdatedAt(farm.createdAt)}</strong>
                </div>
              </div>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}
