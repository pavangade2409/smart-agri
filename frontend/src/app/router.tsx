import { createBrowserRouter, Navigate } from "react-router-dom";
import type { ReactNode } from "react";
import { MainLayout } from "../components/layout/MainLayout";
import { LoginPage } from "../features/auth/pages/LoginPage";
import { DashboardPage } from "../features/dashboard/pages/DashboardPage";
import { FarmsPage } from "../features/farm/pages/FarmsPage";
import { CropsPage } from "../features/crop/pages/CropsPage";
import { SensorsPage } from "../features/sensor/pages/SensorsPage";
import { WeatherPage } from "../features/weather/pages/WeatherPage";
import { FarmerAgentPage } from "../features/agent/pages/FarmerAgentPage";

function isAuthenticated() {
  return Boolean(sessionStorage.getItem("access_token"));
}

function ProtectedLayout({ children }: { children: ReactNode }) {
  if (!isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
}

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/",
    element: (
      <ProtectedLayout>
        <MainLayout />
      </ProtectedLayout>
    ),
    children: [
      {
        index: true,
        element: <DashboardPage />,
      },
      {
        path: "farms",
        element: <FarmsPage />,
      },
      {
        path: "crops",
        element: <CropsPage />,
      },
      {
        path: "sensors",
        element: <SensorsPage />,
      },
      {
        path: "weather",
        element: <WeatherPage />,
      },
      {
        path: "farmer-agent",
        element: <FarmerAgentPage />,
      },
      {
        path: "irrigation",
        element: <div>Irrigation Page - Coming Soon</div>,
      },
    ],
  },
  {
    path: "*",
    element: <Navigate to={isAuthenticated() ? "/" : "/login"} replace />,
  },
]);