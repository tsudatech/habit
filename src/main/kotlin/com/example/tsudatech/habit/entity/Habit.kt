package com.example.tsudatech.habit.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Habit (
    var habitName: String? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var habitId: Long? = null)
