import axios from "axios";

import environment from "../config/environment";

export const httpClient = axios.create({

    baseURL:
        environment.apiBaseUrl,

    timeout:
        environment.apiTimeout,

    headers: {
        "Content-Type":
            "application/json",

        Accept:
            "application/json",
    },
});


httpClient.interceptors.request.use(
    (config) => {

        const token =
            sessionStorage.getItem(
                "access_token"
            );

        if (token) {

            config.headers.Authorization =
                `Bearer ${token}`;
        }

        config.headers[
            "X-Correlation-Id"
        ] = crypto.randomUUID();

        return config;
    },

    (error) => {

        return Promise.reject(error);
    }
);


httpClient.interceptors.response.use(

    (response) => response,

    async (error) => {

        if (
            error.response?.status === 401
        ) {

            sessionStorage.removeItem(
                "access_token"
            );

            window.location.href =
                "/login";
        }

        return Promise.reject(error);
    }
);