package org.example.asyncthreestep.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="async_req_res")
@Data
public class GenAI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String request;

    @Column(columnDefinition = "TEXT")
    private String response;

    private String language;

    private String requestID;

    private String email;

}
