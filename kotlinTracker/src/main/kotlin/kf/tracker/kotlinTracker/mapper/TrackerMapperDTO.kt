package kf.tracker.kotlinTracker.mapper

data class TrackerMapperDTO (
    var name: String,
    var date: String,
    var address: String,
) {
    var trackerId: Long = 0
}