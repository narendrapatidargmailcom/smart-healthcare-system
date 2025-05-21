package com.coder.notification.kafkaListener;

import com.coder.notification.model.Appointment;
import com.coder.notification.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaComsumerListener {
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    private EmailService emailService;


    @KafkaListener(topics = "hospital",groupId = "mygroupid")
    public  void  listen(String msg){
        try {
            System.out.println("Listening on topic");
            Appointment appointment = objectMapper.readValue(msg,Appointment.class);

            System.out.println("Received Appointment  "+msg);

            emailService.triggerEmailNotification(appointment);

            System.out.println("Email notification are send for "+appointment.getId());
        }catch (Exception e){

        }
    }

}
