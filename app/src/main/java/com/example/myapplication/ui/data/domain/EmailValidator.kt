package com.example.myapplication.ui.data.domain

import java.util.regex.Pattern

object EmailValidator: Validator {

    override fun <T> validate(value: T): Boolean{
        val email = value as String
        val regex = Regex("^([a-z0-9]+)@([a-z0-9]+)\\.([a-z]{2,})$")
        return email.matches(regex)
    }
}