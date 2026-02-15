package org.example.asyncthreestep.repository;

import org.example.asyncthreestep.model.GenAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface Genai extends JpaRepository<GenAI, Long> {

    public GenAI findByRequestID(String requestID);

    public GenAI findByEmail(String email);
}
