package kf.tracker.kotlinTracker.config

import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class ConsumerKafkaConfig {

    @Bean
    fun consumerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "kt.tracker"
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ErrorHandlingDeserializer::class.java
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = ErrorHandlingDeserializer::class.java
        props[ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS] = JsonDeserializer::class.java
        return props
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String?, TrackerMapperDTO?> {
        return DefaultKafkaConsumerFactory<String?, TrackerMapperDTO?>(
            consumerConfigs(),
            StringDeserializer(),
            ErrorHandlingDeserializer(JsonDeserializer(TrackerMapperDTO::class.java, false))
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String?, TrackerMapperDTO?> {
        val factory = ConcurrentKafkaListenerContainerFactory<String?, TrackerMapperDTO?>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}