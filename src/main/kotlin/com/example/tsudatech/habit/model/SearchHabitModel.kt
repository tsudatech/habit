package com.example.tsudatech.habit.model

import javax.validation.constraints.Pattern

data class SearchHabitModel(
        val habitId : Long?,
        val habitName : String,
        @field:Pattern(regexp="[0-9]{8}", message = "createDateFromは8文字で指定してください")
        val createDateFrom : String?,
        val createDateTo : String?,
        val updateDateFrom : String?,
        val updateDateTo : String?
) {

    public fun isAllAttributesNull() : Boolean {
        for (field in this.javaClass.declaredFields) {
            if (field[this] != null) {
                return false
            }
        }
        return true;
    }

//    @AssertTrue(message = "updateがcreateより過去")
//    fun isLater(): Boolean {
////        if(create == null || update == null) return true
////        return create.before(update) || create == update
//        return true
//    }

}