package com.example.tsudatech.habit.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Habit (
    var habitName: String? = null,
    var createDateTime: LocalDateTime? = LocalDateTime.now(),
    var updateDateTime: LocalDateTime? = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var habitId: Long? = null)
