package kf.tracker.kotlinTracker.controller

import kf.tracker.kotlinTracker.kafka.KafkaProduceComponent
import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class KafkaRestController(
    var kafkaProduceComponent: KafkaProduceComponent
) {

    @PostMapping("/send")
    fun sendData() {
        kafkaProduceComponent.sendMessage(TrackerMapperDTO("test", "testData", "testAddress"))
        println("data send!")
    }

}