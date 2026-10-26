const environment = {
    apiBaseUrl:
        import.meta.env.VITE_API_BASE_URL ||
        (import.meta.env.VITE_API_HOST
            ? `https://${import.meta.env.VITE_API_HOST}`
            : "http://localhost:8080"),

    appName: "Smart Agri",

    apiTimeout: 10000,
};

export default environment;