import {
    useMutation,
    useQuery,
    useQueryClient,
} from "@tanstack/react-query";

import {
    irrigationApi,
} from "../api/irrigationApi";


export function useIrrigation() {

    const queryClient =
        useQueryClient();


    const zones =
        useQuery({

            queryKey:
                ["irrigation-zones"],

            queryFn:
                irrigationApi.getZones,

            refetchInterval:
                15_000,

        });


    const start =
        useMutation({

            mutationFn:
                ({
                    zoneId,
                    durationMinutes,
                }: {
                    zoneId: string;
                    durationMinutes: number;
                }) =>
                    irrigationApi.start(
                        zoneId,
                        durationMinutes
                    ),

            onSuccess: () => {

                queryClient.invalidateQueries({
                    queryKey:
                        ["irrigation-zones"],
                });

            },

        });


    const stop =
        useMutation({

            mutationFn:
                (zoneId: string) =>
                    irrigationApi.stop(
                        zoneId,
                        crypto.randomUUID()
                    ),

            onSuccess: () => {

                queryClient.invalidateQueries({
                    queryKey:
                        ["irrigation-zones"],
                });

            },

        });


    return {
        zones:
            zones.data ?? [],

        isLoading:
            zones.isLoading,

        isError:
            zones.isError,

        start,

        stop,
    };
}
