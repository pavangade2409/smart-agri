package com.agri.agent.controller;

import com.agri.agent.agent.AgentOrchestrator;
import com.agri.agent.dto.AgentRequest;
import com.agri.agent.dto.AgentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmer-agent")
public class FarmerAgentController {

    private final AgentOrchestrator orchestrator;

    public FarmerAgentController(AgentOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping(
            value = "/ask",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<AgentResponse> ask(
            @RequestBody AgentRequest request) {

        System.out.println("========== AGENT REQUEST ==========");
        System.out.println("farmerId = " + request.farmerId());
        System.out.println("farmId   = " + request.farmId());
        System.out.println("plotId   = " + request.plotId());
        System.out.println("message  = " + request.message());
        System.out.println("===================================");

        return ResponseEntity.ok(orchestrator.process(request));
    }

    @GetMapping("/health")
    public String health() {
        return "Farmer Agent is running";
    }
}