import type {
    Sensor,
} from "../../../types/sensor";


interface Props {

    sensors: Sensor[];
}


function SoilMoistureCard({
    sensors,
}: Props) {

    const moistureSensors =
        sensors.filter(
            sensor =>
                sensor.sensorType ===
                "SOIL_MOISTURE"
        );


    return (

        <div className="dashboard-panel">

            <div className="panel-header">

                <h2>
                    Soil Moisture
                </h2>

                <span>
                    Live
                </span>

            </div>


            {moistureSensors.length === 0 ? (

                <p>
                    No soil moisture
                    sensors configured.
                </p>

            ) : (

                moistureSensors.map(
                    sensor => (

                        <div
                            className="soil-row"
                            key={sensor.id}
                        >

                            <span>
                                {sensor.sensorCode}
                            </span>

                            <div className="progress">

                                <div
                                    className=
                                        "progress-value"
                                    style={{
                                        width:
                                            "50%",
                                    }}
                                />

                            </div>

                            <strong>
                                --
                            </strong>

                        </div>
                    )
                )

            )}

        </div>
    );
}

export default SoilMoistureCard;
