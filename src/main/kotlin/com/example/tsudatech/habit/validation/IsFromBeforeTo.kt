package com.example.tsudatech.habit.validation

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ReportAsSingleViolation
@Constraint(validatedBy = [IsFromBeforeToValidator::class])
annotation class IsFromBeforeTo(
        val message: String = "message",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Payload>> = [],
        val from: String,
        val to: String
)
