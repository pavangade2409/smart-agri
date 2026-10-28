import { useState } from "react";
import type { FormEvent } from "react";
import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { authApi } from "../api/authApi";
import type { LoginResponse } from "../api/authApi";
import "./login.css";

type AuthMode = "login" | "otp" | "register";

function storeSession(data: LoginResponse) {
  sessionStorage.setItem("access_token", data.accessToken);
  sessionStorage.setItem("token_type", data.tokenType);
  sessionStorage.setItem("expires_in", String(data.expiresIn));
  sessionStorage.setItem("user_id", String(data.userId));
  sessionStorage.setItem("user_email", data.email);
  sessionStorage.setItem("user_role", data.role);
}

export const LoginPage = () => {
  const navigate = useNavigate();
  const [mode, setMode] = useState<AuthMode>("login");

  // Login form
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // OTP form
  const [phone, setPhone] = useState("");
  const [otp, setOtp] = useState("");
  const [otpSent, setOtpSent] = useState(false);

  // Register form
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [registerEmail, setRegisterEmail] = useState("");
  const [registerPhone, setRegisterPhone] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerMessage, setRegisterMessage] = useState("");

  const loginMutation = useMutation({
    mutationFn: authApi.login,
    onSuccess: (data) => {
      storeSession(data);
      navigate("/");
    },
  });

  const sendOtpMutation = useMutation({
    mutationFn: authApi.sendOtp,
    onSuccess: () => {
      setOtpSent(true);
      setOtp("");
    },
  });

  const verifyOtpMutation = useMutation({
    mutationFn: authApi.verifyOtp,
    onSuccess: (data) => {
      storeSession(data);
      navigate("/");
    },
  });

  const registerMutation = useMutation({
    mutationFn: authApi.register,
    onSuccess: () => {
      setRegisterMessage("Registration successful! You can now log in.");
      setMode("login");
      setEmail(registerEmail);
      setPassword("");
    },
  });

  function handleLoginSubmit(event: FormEvent) {
    event.preventDefault();
    loginMutation.mutate({ email, password });
  }

  function handleOtpSend(event: FormEvent) {
    event.preventDefault();
    sendOtpMutation.mutate({ phone });
  }

  function handleOtpVerify(event: FormEvent) {
    event.preventDefault();
    verifyOtpMutation.mutate({ phone, otp });
  }

  function handleRegisterSubmit(event: FormEvent) {
    event.preventDefault();
    registerMutation.mutate({
      email: registerEmail,
      password: registerPassword,
      firstName,
      lastName: lastName || undefined,
      phone: registerPhone,
    });
  }

  return (
    <div className="login-page">
      <div className="login-card">
        <header className="login-header">
          <h1>🌾 Smart Agri</h1>
          <p>Farm Management System</p>
        </header>

        <nav className="login-tabs" aria-label="Authentication method">
          <button type="button" className={mode === "login" ? "active" : ""} onClick={() => setMode("login")}>Login</button>
          <button type="button" className={mode === "otp" ? "active" : ""} onClick={() => setMode("otp")}>OTP</button>
          <button type="button" className={mode === "register" ? "active" : ""} onClick={() => setMode("register")}>Register</button>
        </nav>

        {mode === "login" && (
          <form className="login-form" onSubmit={handleLoginSubmit}>
            <h2>Sign in</h2>

            {loginMutation.isError && (
              <ErrorMessage message={getApiErrorMessage(loginMutation.error)} />
            )}

            <label>
              Email
              <input
                type="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                placeholder="you@example.com"
                required
                autoComplete="email"
              />
            </label>

            <label>
              Password
              <input
                type="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                placeholder="••••••••"
                required
                autoComplete="current-password"
              />
            </label>

            <button type="submit" disabled={loginMutation.isPending || !email || !password}>
              {loginMutation.isPending ? "Signing in..." : "Sign in"}
            </button>
          </form>
        )}

        {mode === "otp" && (
          <div className="login-form">
            <h2>Phone OTP login</h2>

            {sendOtpMutation.isError && (
              <ErrorMessage message={getApiErrorMessage(sendOtpMutation.error)} />
            )}
            {verifyOtpMutation.isError && (
              <ErrorMessage message={getApiErrorMessage(verifyOtpMutation.error)} />
            )}

            <form onSubmit={handleOtpSend}>
              <label>
                Phone number
                <input
                  type="tel"
                  value={phone}
                  onChange={(event) => setPhone(event.target.value)}
                  placeholder="10-digit mobile number"
                  pattern="[0-9]{10}"
                  required
                  autoComplete="tel"
                />
              </label>

              <button type="submit" disabled={sendOtpMutation.isPending || phone.length !== 10}>
                {sendOtpMutation.isPending ? "Sending..." : otpSent ? "Resend OTP" : "Send OTP"}
              </button>
            </form>

            {otpSent && (
              <form className="otp-verify-form" onSubmit={handleOtpVerify}>
                <label>
                  One-time password
                  <input
                    type="text"
                    value={otp}
                    onChange={(event) => setOtp(event.target.value)}
                    placeholder="6-digit OTP"
                    pattern="[0-9]{6}"
                    required
                    autoComplete="one-time-code"
                  />
                </label>

                <button type="submit" disabled={verifyOtpMutation.isPending || otp.length !== 6}>
                  {verifyOtpMutation.isPending ? "Verifying..." : "Verify & Sign in"}
                </button>
              </form>
            )}
          </div>
        )}

        {mode === "register" && (
          <form className="login-form" onSubmit={handleRegisterSubmit}>
            <h2>Create account</h2>

            {registerMutation.isError && (
              <ErrorMessage message={getApiErrorMessage(registerMutation.error)} />
            )}
            {registerMessage && <p className="login-success">{registerMessage}</p>}

            <label>
              First name
              <input
                type="text"
                value={firstName}
                onChange={(event) => setFirstName(event.target.value)}
                placeholder="John"
                required
                autoComplete="given-name"
              />
            </label>

            <label>
              Last name
              <input
                type="text"
                value={lastName}
                onChange={(event) => setLastName(event.target.value)}
                placeholder="Doe"
                autoComplete="family-name"
              />
            </label>

            <label>
              Email
              <input
                type="email"
                value={registerEmail}
                onChange={(event) => setRegisterEmail(event.target.value)}
                placeholder="you@example.com"
                required
                autoComplete="email"
              />
            </label>

            <label>
              Phone
              <input
                type="tel"
                value={registerPhone}
                onChange={(event) => setRegisterPhone(event.target.value)}
                placeholder="10-digit mobile number"
                pattern="[0-9]{10}"
                required
                autoComplete="tel"
              />
            </label>

            <label>
              Password
              <input
                type="password"
                value={registerPassword}
                onChange={(event) => setRegisterPassword(event.target.value)}
                placeholder="At least 8 characters"
                minLength={8}
                required
                autoComplete="new-password"
              />
            </label>

            <button type="submit" disabled={registerMutation.isPending || !firstName || !registerEmail || !registerPhone || registerPassword.length < 8}>
              {registerMutation.isPending ? "Creating account..." : "Register"}
            </button>
          </form>
        )}
      </div>
    </div>
  );
};