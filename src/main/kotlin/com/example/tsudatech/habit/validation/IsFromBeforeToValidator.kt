package com.example.tsudatech.habit.validation

import com.example.tsudatech.habit.common.DateTimeUtility
import org.springframework.beans.BeanWrapperImpl
import java.time.LocalDateTime
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class IsFromBeforeToValidator: ConstraintValidator<IsFromBeforeTo, Any> {
    lateinit var fromName: String
    lateinit var toName: String

    override fun initialize(constraintAnnotation: IsFromBeforeTo) {
        fromName = constraintAnnotation.from
        toName = constraintAnnotation.to
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if(value == null) return true
        val beanWrapper = BeanWrapperImpl(value)

        //入力から値を取り出す
        val from : LocalDateTime = DateTimeUtility.fromYYYYMMDD(beanWrapper.getPropertyValue(fromName) as String)
        val to : LocalDateTime = DateTimeUtility.fromYYYYMMDD(beanWrapper.getPropertyValue(toName) as String)

        if(from == null || to == null) return true
        return from.isBefore(to) || from.isEqual(to)
    }
}