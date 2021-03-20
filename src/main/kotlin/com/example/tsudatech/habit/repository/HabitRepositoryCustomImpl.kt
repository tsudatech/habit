package com.example.tsudatech.habit.repository

import com.example.tsudatech.habit.entity.Habit
import com.example.tsudatech.habit.model.SearchHabitModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class HabitRepositoryCustomImpl @Autowired constructor(
        private val entityManager: EntityManager) : HabitRepositoryCustom {

    override fun findHabitsWithSearchCondition(searchCondition: SearchHabitModel) : Iterable<Habit>{
        return entityManager.createQuery("SELECT h FROM Habit h WHERE h.habitId = 1", Habit::class.java).resultList
    }
}