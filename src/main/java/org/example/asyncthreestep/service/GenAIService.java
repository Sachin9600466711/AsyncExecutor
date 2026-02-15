package org.example.asyncthreestep.service;

import org.example.asyncthreestep.dto.AiRequest;
import org.example.asyncthreestep.dto.AiResponse;
import org.example.asyncthreestep.executor.ExecutorImplementation;
import org.example.asyncthreestep.model.GenAI;
import org.example.asyncthreestep.repository.Genai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenAIService {

    @Autowired
    ExecutorImplementation implementation;

    @Autowired
    Genai genai;

    public AiResponse initate(AiRequest request){

        AiResponse response = new AiResponse();
        implementation.execute(request);
        GenAI res= genai.findByEmail(request.getEmail());
        response.setResponse(res.getResponse());
        response.setRequest(res.getRequest());
        response.setRequestID(res.getRequestID());
        response.setLanguage(res.getLanguage());
        return response;
    }
}
