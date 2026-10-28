package com.agri.agent.context;

import com.agri.agent.client.CropClient;
import org.springframework.stereotype.Component;

@Component
public class CropContextBuilder {
    private final CropClient client;
    public CropContextBuilder(CropClient client) { this.client=client; }
    public String build(Long farmId) { return client.get(farmId); }
}
