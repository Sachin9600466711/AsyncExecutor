package org.example.asyncthreestep.executor;

import org.example.asyncthreestep.dto.AiRequest;
import org.example.asyncthreestep.dto.AiResponse;
import org.springframework.transaction.annotation.Transactional;


public interface AsyncThreeStepWorker<
        REQ,
        ID,
        INT_RES,
        FINAL_RES
        > {
    ID prepareRequestAndSave(REQ request);

    INT_RES doIntegration(REQ req, ID id);

    FINAL_RES doAfterIntegration(INT_RES res);

    void execute(REQ req);

}
