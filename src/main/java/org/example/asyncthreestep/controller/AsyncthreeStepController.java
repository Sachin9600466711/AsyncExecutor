package org.example.asyncthreestep.controller;

import org.example.asyncthreestep.dto.AiRequest;
import org.example.asyncthreestep.dto.AiResponse;
import org.example.asyncthreestep.service.GenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api")
public class AsyncthreeStepController {

    @Autowired
    public GenAIService service;

    @PostMapping("/chat")
    private AiResponse getResponse(@RequestBody AiRequest request){
        return service.initate(request);
    }
}
