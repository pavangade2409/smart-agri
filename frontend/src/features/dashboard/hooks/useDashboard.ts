import { useQuery } from "@tanstack/react-query";
import { farmApi } from "../../farm/api/farmApi";
import { cropApi } from "../../crop/api/cropApi";
import { sensorApi } from "../../sensor/api/sensorApi";
import { irrigationApi } from "../../irrigation/api/irrigationApi";

export const useDashboard = () => {
  const farmsQuery = useQuery({
    queryKey: ["farms"],
    queryFn: farmApi.getAll,
  });

  const cropsQuery = useQuery({
    queryKey: ["crops"],
    queryFn: cropApi.getAll,
  });

  const sensorsQuery = useQuery({
    queryKey: ["sensors"],
    queryFn: sensorApi.getAll,
  });

  const irrigationQuery = useQuery({
    queryKey: ["irrigation-zones"],
    queryFn: irrigationApi.getZones,
  });

  const latestSensor = sensorsQuery.data?.[0];

  const latestReadingQuery = useQuery({
    queryKey: ["sensor-latest-reading", latestSensor?.id],
    queryFn: () => sensorApi.getLatestReading(latestSensor!.id),
    enabled: !!latestSensor?.id,
  });

  const isLoading =
    farmsQuery.isLoading ||
    cropsQuery.isLoading ||
    sensorsQuery.isLoading ||
    irrigationQuery.isLoading ||
    latestReadingQuery.isLoading;

  const isError =
    farmsQuery.isError ||
    cropsQuery.isError ||
    sensorsQuery.isError ||
    irrigationQuery.isError ||
    latestReadingQuery.isError;

  const error =
    farmsQuery.error ||
    cropsQuery.error ||
    sensorsQuery.error ||
    irrigationQuery.error ||
    latestReadingQuery.error;

  return {
    farms: farmsQuery.data || [],
    crops: cropsQuery.data || [],
    sensors: sensorsQuery.data || [],
    zones: irrigationQuery.data || [],
    latestReading: latestReadingQuery.data || null,
    isLoading,
    isError,
    error,
  };
};