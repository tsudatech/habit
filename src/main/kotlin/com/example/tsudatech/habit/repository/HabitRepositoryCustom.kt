package com.example.tsudatech.habit.repository

import com.example.tsudatech.habit.entity.Habit
import com.example.tsudatech.habit.model.SearchHabitModel

interface HabitRepositoryCustom {
    fun findHabitsWithSearchCondition(searchCondition : SearchHabitModel) : Iterable<Any?>
}