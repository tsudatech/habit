package com.example.tsudatech.habit.model

import com.example.tsudatech.habit.common.DateTimeUtility
import java.time.LocalDateTime
import javax.validation.constraints.AssertTrue

data class SearchHabitModel(
        val habitId : Long?,
        val habitName : String,
        val createDateFrom : String?,
        val createDateTo : String?,
        val updateDateFrom : String?,
        val updateDateTo : String?
) {

    val regex = Regex("[0-9]{8}")

    public fun isAllAttributesNull() : Boolean {
        for (field in this.javaClass.declaredFields) {
            if (field[this] != null) {
                return false
            }
        }
        return true;
    }

    @AssertTrue(message = "createDateFromおよびcreateDateToの形式が不正です。")
    fun isCreateDateFromBeforeTo(): Boolean {
        // どちらかだけがnullの場合はFalse
        if ((createDateFrom == null && createDateTo != null)
                || (createDateFrom != null && createDateTo == null)) {
            return false
        }

        if (createDateFrom != null && !regex.matches(createDateFrom)) {
            return false
        }

        if (createDateTo != null && !regex.matches(createDateTo)) {
            return false
        }

        if(createDateFrom != null && createDateTo != null) {
            val from: LocalDateTime = DateTimeUtility.fromYYYYMMDD(createDateFrom)
            val to: LocalDateTime = DateTimeUtility.fromYYYYMMDD(createDateTo)
            return from.isBefore(to) || from.isEqual(to)
        }

        return true
    }

}