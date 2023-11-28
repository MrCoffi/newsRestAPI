package kf.tracker.kotlinTracker.entity

import jakarta.persistence.*



@Entity
class Tracker (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id", nullable = false)
    var trackerId: Long,
    var name: String,
    var date: String,
    var address: String,
        )