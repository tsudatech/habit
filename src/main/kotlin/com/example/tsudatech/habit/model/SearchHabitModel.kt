package com.example.tsudatech.habit.model

import java.time.LocalDate
import java.util.*

data class SearchHabitModel(
        val habitId : Long? = null
        , val habitName : String? = null
        , val createDate : String? = null
        , val updateDate : String? = null
) {
}