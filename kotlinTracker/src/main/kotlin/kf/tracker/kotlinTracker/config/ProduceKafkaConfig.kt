package kf.tracker.kotlinTracker.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class ProduceKafkaConfig {

//    @Bean
//    fun myTopic(): NewTopic? {
//        return TopicBuilder.name("trackerTopic")
//            .partitions(1)
//            .replicas(1)
//            .build()
//    }
}