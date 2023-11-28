package kf.tracker.kotlinTracker.controller

import kf.tracker.kotlinTracker.entity.Tracker
import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import kf.tracker.kotlinTracker.service.TrackerServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/")
class TrackerController() {

    @Autowired
    private lateinit var trackerServiceImpl: TrackerServiceImpl;

    @GetMapping
    fun getAllTrack(): ResponseEntity<List<TrackerMapperDTO>> {
        return ResponseEntity.ok(trackerServiceImpl.getAllData())
    }

    @GetMapping()
    @RequestMapping("{id}")
    fun getTrackById(@PathVariable id: Long): ResponseEntity<TrackerMapperDTO> {
        return ResponseEntity.ok(trackerServiceImpl.findById(id))
    }

    @PostMapping("{name}")
    fun saveTrack(@PathVariable name: String) {
        trackerServiceImpl.saveTracker(TrackerMapperDTO(name, "date", "address"))
    }
}