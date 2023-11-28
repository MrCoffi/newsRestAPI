package kf.tracker.kotlinTracker.kafka

import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class KafkaConsumerComponent {

    @KafkaListener(topics = ["trackerTopic"], groupId = "trackerConsumerGroup")
    fun consumeMessage(trackerMapperDTO: TrackerMapperDTO) {
        println("Received message: $trackerMapperDTO")
    }
}
