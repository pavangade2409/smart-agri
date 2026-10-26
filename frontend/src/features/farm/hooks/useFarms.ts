import {
    useQuery,
} from "@tanstack/react-query";

import {
    farmApi,
} from "../api/farmApi";


export function useFarms() {

    return useQuery({

        queryKey: ["farms"],

        queryFn:
            farmApi.getAll,

        staleTime:
            60_000,

    });
}