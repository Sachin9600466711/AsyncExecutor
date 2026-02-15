package org.example.asyncthreestep.dto;

import lombok.Data;
import tools.jackson.databind.node.StringNode;

@Data
public class AiResponse {

    private String request;

    private String language;

    private String response;

    private String requestID;

}
