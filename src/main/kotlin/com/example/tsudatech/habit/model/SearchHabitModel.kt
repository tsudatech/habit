package com.example.tsudatech.habit.model

import java.time.LocalDate
import java.util.*

data class SearchHabitModel(
        val habitId : Long? = null
        , val habitName : String? = null
        , val createDate : String? = null
        , val updateDate : String? = null
) {

    public fun isAllAttributesNull() : Boolean {
        for (field in this.javaClass.declaredFields) {
            if (field[this] != null) {
                return false
            }
        }
        return true;
    }

}