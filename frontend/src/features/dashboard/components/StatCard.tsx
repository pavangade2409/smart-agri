interface StatCardProps {

    title: string;

    value: number;

    subtitle: string;

    icon: string;
}


function StatCard({
    title,
    value,
    subtitle,
    icon,
}: StatCardProps) {

    return (

        <div className="stat-card">

            <div className="stat-icon">
                {icon}
            </div>

            <div>

                <span className="stat-title">
                    {title}
                </span>

                <strong className="stat-value">
                    {value}
                </strong>

                <small className="stat-subtitle">
                    {subtitle}
                </small>

            </div>

        </div>
    );
}

export default StatCard;