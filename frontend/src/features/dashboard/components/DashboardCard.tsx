interface DashboardCardProps {
  icon: string;
  title: string;
  value: string | number;
  description?: string;
}

export const DashboardCard = ({
  icon,
  title,
  value,
  description,
}: DashboardCardProps) => {
  return (
    <div className="dashboard-card">
      <div className="card-icon">{icon}</div>
      <div className="card-content">
        <h3>{title}</h3>
        <p className="card-value">{value}</p>
        {description && <p className="card-description">{description}</p>}
      </div>
    </div>
  );
};
