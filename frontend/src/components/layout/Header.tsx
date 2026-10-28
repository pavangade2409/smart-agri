import { useNavigate } from "react-router-dom";
import "./header.css";

export const Header = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    sessionStorage.removeItem("access_token");
    sessionStorage.removeItem("token_type");
    sessionStorage.removeItem("expires_in");
    sessionStorage.removeItem("user_id");
    sessionStorage.removeItem("user_email");
    sessionStorage.removeItem("user_role");
    navigate("/login");
  };

  return (
    <header className="header">
      <div className="header-content">
        <h1>🌾 Smart Agri</h1>
        <div className="header-actions">
          <span>Farm Management System</span>
          <button
            className="header-logout"
            type="button"
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>
      </div>
    </header>
  );
};