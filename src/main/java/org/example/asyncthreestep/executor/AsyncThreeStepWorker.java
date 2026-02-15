package org.example.asyncthreestep.executor;

import org.example.asyncthreestep.dto.AiRequest;
import org.example.asyncthreestep.dto.AiResponse;

public interface AsyncThreeStepWorker<
        REQ,        // Request type
        ID,         // Request ID type
        INT_RES,    // Integration response type
        FINAL_RES   // Final response type
        > {
    ID prepareRequestAndSave(REQ request);

    INT_RES doIntegration(REQ req, ID id);

    FINAL_RES doAfterIntegration(INT_RES res);

    void execute(REQ req);

}
