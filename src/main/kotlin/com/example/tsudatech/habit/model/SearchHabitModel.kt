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

    private val regex = Regex("[0-9]{8}")

    public fun isAllAttributesNull() : Boolean {
        for (field in this.javaClass.declaredFields) {
            if (field[this] != null) {
                return false
            }
        }
        return true;
    }

    @AssertTrue(message = "{invalidCreateDate}")
    private fun isCreateDateValid(): Boolean {
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
            return this.isFromBeforeTo(createDateFrom, createDateTo)
        }
        return true
    }

    @AssertTrue(message = "{invalidUpdateDate}")
    private fun isUpdateDateValid(): Boolean {
        // どちらかだけがnullの場合はFalse
        if ((updateDateFrom == null && updateDateTo != null)
                || (updateDateFrom != null && updateDateTo == null)) {
            return false
        }
        if (updateDateFrom != null && !regex.matches(updateDateFrom)) {
            return false
        }
        if (updateDateTo != null && !regex.matches(updateDateTo)) {
            return false
        }
        if(updateDateFrom != null && updateDateTo != null) {
            return this.isFromBeforeTo(updateDateFrom, updateDateTo)
        }
        return true
    }

    private fun isFromBeforeTo(from:String, to:String) : Boolean {
        val from: LocalDateTime = DateTimeUtility.fromYYYYMMDD(from)
        val to: LocalDateTime = DateTimeUtility.fromYYYYMMDD(to)
        return from.isBefore(to) || from.isEqual(to)
    }

}