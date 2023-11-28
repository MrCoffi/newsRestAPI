package kf.tracker.kotlinTracker.service

import kf.tracker.kotlinTracker.mapper.TrackerMapper
import kf.tracker.kotlinTracker.mapper.TrackerMapperDTO
import kf.tracker.kotlinTracker.repository.TrackerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional()
class TrackerServiceImpl(
    private val trackerRepository: TrackerRepository
) {
    private val trackerMapper: TrackerMapper = TrackerMapper.INSTANCE

    fun getAllData(): List<TrackerMapperDTO> {
        val trackers = trackerRepository.findAll()
        return trackers.map { t -> trackerMapper.toResponse(t) }
    }

    fun saveTracker(trackerMapperDTO: TrackerMapperDTO) {
        val tracker = trackerMapper.toEntity(trackerMapperDTO)
        trackerRepository.save(tracker)
    }

    fun findById(id: Long): TrackerMapperDTO? {
        val tracker = trackerRepository.findById(id).orElse(null)
        return tracker?.let { trackerMapper.toResponse(it) }
    }
}