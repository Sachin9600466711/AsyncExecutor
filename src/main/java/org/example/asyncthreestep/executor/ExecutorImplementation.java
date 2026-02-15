package org.example.asyncthreestep.executor;


import org.example.asyncthreestep.dto.AiRequest;
import org.example.asyncthreestep.dto.AiResponse;
import org.example.asyncthreestep.dto.ChatReply;
import org.example.asyncthreestep.model.GenAI;
import org.example.asyncthreestep.repository.Genai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Service
public class ExecutorImplementation implements AsyncThreeStepWorker<AiRequest, String, AiResponse,AiResponse>{

    @Autowired
    private Genai genai;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public String prepareRequestAndSave(AiRequest request) {
        GenAI model = new GenAI();
        model.setRequest(request.getRequest());
        model.setEmail(request.getEmail());
        model.setLanguage(request.getLanguage());
        String requestId= "GenAi"+System.currentTimeMillis();
        model.setRequestID(requestId);
        genai.save(model);
        return requestId;
    }

    @Override
    public AiResponse doIntegration(AiRequest request, String requestId) {
        AiResponse aiResponse = new AiResponse();
        aiResponse.setRequest(request.getRequest());
        aiResponse.setLanguage(request.getLanguage());
        String url = "http://localhost:3000/chat";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AiRequest> entity = new HttpEntity<>(request, headers);
        ObjectMapper objectMapper =new ObjectMapper();
        ResponseEntity<ChatReply> response = restTemplate.postForEntity(url, entity, ChatReply.class);
        aiResponse.setResponse(response.getBody().getReply());
        aiResponse.setRequestID(requestId);
        return aiResponse;
    }

    @Override
    @Transactional
    public AiResponse doAfterIntegration(AiResponse response) {
        GenAI model = genai.findByRequestID(response.getRequestID());
        model.setResponse(response.getResponse());
        genai.save(model);
        return response;
    }


    @Override
    @Transactional
    public void execute(AiRequest req) {
        String context = prepareRequestAndSave(req);
        AiResponse res = doIntegration(req, context);
        doAfterIntegration(res);
        System.out.println("Execution completed for: " + res.getRequestID());

    }

}


