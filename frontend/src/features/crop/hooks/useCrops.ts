import {
    useQuery,
} from "@tanstack/react-query";

import {
    cropApi,
} from "../api/cropApi";


export function useCrops() {

    return useQuery({

        queryKey: ["crops"],

        queryFn:
            cropApi.getAll,

        staleTime:
            60_000,

    });
}