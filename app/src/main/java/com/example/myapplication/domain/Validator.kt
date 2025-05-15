package com.example.myapplication.domain

interface Validator {
    fun <T> validate(value: T): Boolean
}