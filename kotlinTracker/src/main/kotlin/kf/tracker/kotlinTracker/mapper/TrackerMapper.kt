package kf.tracker.kotlinTracker.mapper

import kf.tracker.kotlinTracker.entity.Tracker
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface TrackerMapper {
    companion object {
        val INSTANCE: TrackerMapper = Mappers.getMapper(TrackerMapper::class.java)
    }

    @Mapping(target = "trackerId", ignore = false)
    fun toResponse(tracker: Tracker): TrackerMapperDTO

    fun toEntity(trackerMapperDTO: TrackerMapperDTO): Tracker

}