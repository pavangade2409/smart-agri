import type {
    IrrigationZone,
} from "../../../types/irrigation";

import StatusBadge
    from "../../../components/common/StatusBadge";


interface Props {

    zones: IrrigationZone[];
}


function IrrigationStatusCard({
    zones,
}: Props) {

    return (

        <div className="dashboard-panel">

            <div className="panel-header">

                <h2>
                    Irrigation Status
                </h2>

                <span>
                    Live
                </span>

            </div>


            {zones.length === 0 ? (

                <p>
                    No irrigation zones
                    configured.
                </p>

            ) : (

                zones.map(zone => (

                    <div
                        className="irrigation-row"
                        key={zone.id}
                    >

                        <div>

                            <strong>
                                {zone.zoneName}
                            </strong>

                            <span>
                                Pump:{" "}
                                {zone.pumpId ||
                                    "N/A"}
                            </span>

                        </div>

                        <StatusBadge
                            status={
                                zone.status
                            }
                        />

                    </div>

                ))

            )}

        </div>
    );
}

export default IrrigationStatusCard;
