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
        val sql = "SELECT h FROM Habit h " + this.createSearchCondition(searchCondition)
        return entityManager.createQuery(sql, Habit::class.java).resultList
    }

    private fun createSearchCondition(searchCondition: SearchHabitModel) : String {
        var condition : String = ""
        if (searchCondition.habitId != null) condition += "WHERE habitId = " + searchCondition.habitId
        if (searchCondition.habitName != null) condition += " AND habitName = '" + searchCondition.habitName + "'"
        if (searchCondition.createDate != null) condition += " AND createDate = " + searchCondition.createDate + "'"
        if (searchCondition.updateDate != null) condition += " AND updateDate = " + searchCondition.updateDate + "'"
        return condition
    }
}