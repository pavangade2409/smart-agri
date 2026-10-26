import { NavLink } from "react-router-dom";
import "./sidebar.css";

export const Sidebar = () => {
  return (
    <aside className="sidebar">
      <nav className="sidebar-nav">
        <NavLink
          to="/"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          📊 Dashboard
        </NavLink>
        <NavLink
          to="/farms"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          🏡 Farms
        </NavLink>
        <NavLink
          to="/crops"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          🌱 Crops
        </NavLink>
        <NavLink
          to="/sensors"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          📡 Sensors
        </NavLink>
        <NavLink
          to="/weather"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          🌤️ Weather
        </NavLink>
        <NavLink
          to="/irrigation"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          💧 Irrigation
        </NavLink>
        <NavLink
          to="/farmer-agent"
          className={({ isActive }) => (isActive ? "nav-item active" : "nav-item")}
        >
          🤖 Farmer Agent
        </NavLink>
      </nav>
    </aside>
  );
};
