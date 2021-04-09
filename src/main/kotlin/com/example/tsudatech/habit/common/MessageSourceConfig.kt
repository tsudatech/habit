package com.example.tsudatech.habit.common

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.nio.charset.Charset


@Configuration
class MessageSourceConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("messages")
        messageSource.setDefaultEncoding(Charset.forName("MS932").name())
        messageSource.setFallbackToSystemLocale(false)
        return messageSource
    }

    @Bean
    fun getValidator(): LocalValidatorFactoryBean? {
        val bean = LocalValidatorFactoryBean()
        bean.setValidationMessageSource(messageSource())
        return bean
    }
}