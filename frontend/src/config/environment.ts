const environment = {
    apiBaseUrl:
        import.meta.env.VITE_API_BASE_URL
            ? import.meta.env.VITE_API_BASE_URL.includes("://")
                ? import.meta.env.VITE_API_BASE_URL
                : `https://${import.meta.env.VITE_API_BASE_URL}`
            : "http://localhost:8080",

    appName: "Smart Agri",

    apiTimeout: Number(import.meta.env.VITE_API_TIMEOUT) || 120000,
};

export default environment;