package com.coder.notification.config;

import com.coder.notification.model.Appointment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String KafkaAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;



    @Bean
    public ConsumerFactory<String, Appointment> consumerFactory(){

        Map<String,Object> propes = new HashMap<>();

        propes.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaAddress);
        propes.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        propes.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propes.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        propes.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // Disable auto-commit for better control
        propes.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(propes);

    }

    @Bean
  public ConcurrentKafkaListenerContainerFactory<String,Appointment> appointmentConcurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Appointment> factory = new ConcurrentKafkaListenerContainerFactory<>();

         factory.setConsumerFactory(consumerFactory());

         factory.setConcurrency(3);

         factory.getContainerProperties().setPollTimeout(3000);
         return factory;
    }
}
