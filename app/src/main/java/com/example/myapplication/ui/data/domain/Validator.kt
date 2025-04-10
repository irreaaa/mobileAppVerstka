package com.example.myapplication.ui.data.domain

interface Validator {
    fun <T> validate(value: T): Boolean
}