package org.example.asyncthreestep.consumer;

import org.example.asyncthreestep.dto.AiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Value("${rabbitmq.jsonQueue.name}")
    private String queue;

    public RabbitMQConsumer(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER =LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.jsonQueue.name}"})
    public AiResponse consume() {
        AiResponse response =  (AiResponse) rabbitTemplate.receiveAndConvert(queue);
        LOGGER.info("Message Recieved.. {}",response);
        return  response;

    }
}
