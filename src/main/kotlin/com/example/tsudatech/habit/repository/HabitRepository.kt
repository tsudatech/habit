package com.example.tsudatech.habit.repository

import com.example.tsudatech.habit.entity.Habit
import org.springframework.data.repository.CrudRepository

interface HabitRepository : CrudRepository<Habit, Long> {
    fun findAllByOrderByHabitId(): Iterable<Habit>
    fun findByHabitNameContains(habitName: String?): Iterable<Habit>
}