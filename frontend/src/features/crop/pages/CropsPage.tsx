import { useMemo, useState } from "react";
import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import StatusBadge from "../../../components/common/StatusBadge";
import { useCrops } from "../hooks/useCrops";
import "./crops.css";

export function CropsPage() {
  const { data: crops = [], isLoading, isError, error } = useCrops();
  const [search, setSearch] = useState("");

  const visibleCrops = useMemo(() => {
    const term = search.trim().toLowerCase();
    if (!term) return crops;

    return crops.filter((crop) =>
      [crop.name, crop.cropCode, crop.cropType, crop.season]
        .filter(Boolean)
        .some((value) => value.toLowerCase().includes(term))
    );
  }, [crops, search]);

  if (isLoading) return <LoadingSpinner />;
  if (isError) return <ErrorMessage message={getApiErrorMessage(error)} />;

  return (
    <section className="crops-page">
      <header className="crops-page__header">
        <div>
          <p className="crops-page__eyebrow">Crop catalog</p>
          <h2>Crops</h2>
          <p>Browse the crop varieties available for your farm planning.</p>
        </div>
        <div className="crops-page__count">{crops.length} crops</div>
      </header>

      <label className="crops-search">
        <span className="sr-only">Search crops</span>
        <span aria-hidden="true">⌕</span>
        <input
          type="search"
          value={search}
          onChange={(event) => setSearch(event.target.value)}
          placeholder="Search by name, code, type, or season"
        />
      </label>

      {visibleCrops.length === 0 ? (
        <div className="crops-empty-state">
          <span aria-hidden="true">🌱</span>
          <h3>{crops.length === 0 ? "No crops yet" : "No matching crops"}</h3>
          <p>
            {crops.length === 0
              ? "Create crop records in the backend to populate this catalog."
              : "Try a different search term."}
          </p>
        </div>
      ) : (
        <div className="crops-grid">
          {visibleCrops.map((crop) => (
            <article className="crop-card" key={crop.id}>
              <div className="crop-card__topline">
                <span className="crop-card__code">{crop.cropCode}</span>
                <StatusBadge status={crop.status} />
              </div>
              <h3>{crop.name}</h3>
              <p className="crop-card__scientific">{crop.scientificName ?? "Scientific name not set"}</p>
              <dl className="crop-card__details">
                <div><dt>Type</dt><dd>{crop.cropType}</dd></div>
                <div><dt>Season</dt><dd>{crop.season}</dd></div>
                <div><dt>Duration</dt><dd>{crop.durationDays ? `${crop.durationDays} days` : "-"}</dd></div>
                <div><dt>Water need</dt><dd>{"waterRequirement" in crop ? String(crop.waterRequirement ?? "-") : "-"}</dd></div>
              </dl>
            </article>
          ))}
        </div>
      )}
    </section>
  );
}
