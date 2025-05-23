package com.coder.appointment.kafkaConfiguration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String,String> producerFactory(Environment environment){
        Map<String,Object> properties = new HashMap<>();
         properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
         properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
         return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(Environment c){
        return new KafkaTemplate<>(producerFactory(c));
    }
}
