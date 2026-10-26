package com.agri.agent.recommendation;

import org.springframework.stereotype.Service;

@Service
public class SafetyPolicyService {

    public String message() {
        return "Use only products registered for the crop/problem. "
                + "Follow the current label, PPE requirements and pre-harvest interval. "
                + "Do not mix products unless compatibility is confirmed.";
    }

    public boolean allowed(String disease, double confidence) {

        return disease != null
                && !disease.isBlank()
                && !disease.equalsIgnoreCase("UNKNOWN")
                && confidence >= 0.70;
    }
}