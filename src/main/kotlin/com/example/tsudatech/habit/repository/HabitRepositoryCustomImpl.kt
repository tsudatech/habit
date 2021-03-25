package com.example.tsudatech.habit.repository

import com.example.tsudatech.habit.entity.Habit
import com.example.tsudatech.habit.model.SearchHabitModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.lang.StringBuilder
import javax.persistence.EntityManager

@Repository
class HabitRepositoryCustomImpl @Autowired constructor(
        private val entityManager: EntityManager) : HabitRepositoryCustom {

    override fun findHabitsWithSearchCondition(searchCondition: SearchHabitModel) : Iterable<Habit>{
        val sql = "SELECT h FROM Habit h " + this.createSearchCondition(searchCondition)
        return entityManager.createQuery(sql, Habit::class.java).resultList
    }

    private fun createSearchCondition(searchCondition: SearchHabitModel) : String {
        if (searchCondition.isAllAttributesNull()) {
            return ""
        }
        var conditionList : ArrayList<String> = ArrayList<String>()
        if (searchCondition.habitId != null) {
            conditionList.add("habitId = " + searchCondition.habitId.toString())
        }
        if (searchCondition.habitName != null) {
            conditionList.add("habitName like '%" + searchCondition.habitName + "%'")
        }
        if (searchCondition.createDate != null) {
            conditionList.add("DATE_FORMAT(createDateTime, '%Y%m%d') = '" + searchCondition.createDate + "'")
        }
        if (searchCondition.updateDate != null) {
            conditionList.add("DATE_FORMAT(updateDateTime, '%Y%m%d') = '" + searchCondition.updateDate + "'")
        }
        return convertConditionListToWhereClause(conditionList)
    }

    private fun convertConditionListToWhereClause(conditionList : ArrayList<String>) : String {
        var builder : StringBuilder = StringBuilder()
        if (conditionList.isEmpty()) return ""
        builder.append("WHERE ")
        builder.append(conditionList[0])
        for (i in 1 until conditionList.size) {
            builder.append(" AND ")
            builder.append(conditionList[i])
        }
        return builder.toString()
    }
}