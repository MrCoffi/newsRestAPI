package kf.tracker.kotlinTracker.kafka

import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProduceComponent(
    var kafkaTemplate: KafkaTemplate<String, TrackerMapperDTO>
) {
    fun sendMessage(trackerMapperDTO: TrackerMapperDTO) {
        kafkaTemplate.send("trackerTopic", trackerMapperDTO)
    }
}