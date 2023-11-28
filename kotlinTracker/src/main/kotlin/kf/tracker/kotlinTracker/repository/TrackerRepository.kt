package kf.tracker.kotlinTracker.repository

import kf.tracker.kotlinTracker.entity.Tracker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackerRepository : JpaRepository<Tracker, Long> {

}