package com.example.myapplication

import com.example.myapplication.ui.data.domain.EmailValidator
import com.example.myapplication.ui.data.domain.PasswordValidator
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(Parameterized::class)
class EmailValidatorTest(val email: String, val result: Boolean) {
    @Test
    fun validation_isCorrect() {
        val resultValidation = EmailValidator.validate(email)
        assertEquals(result, resultValidation)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun data() = listOf(
            arrayOf("@", false),
            arrayOf("test", false),
            arrayOf("@email", false),
            arrayOf("123@email", false),
            arrayOf("qwe@email.r", false),
            arrayOf("DER@email.ru", false),
            arrayOf("qweQ1@email.ru", false),
            arrayOf("test@mail.ru", true),
            arrayOf("test124@email.com", true),
            arrayOf("123346568@email.com", true),
        )
    }
}

@RunWith(Parameterized::class)
class PasswordValidatorTest(val password: String, val result: Boolean){
    @Test
    fun validation_isCorrest() {
        val resultValidation = PasswordValidator.validate(password)
        assertEquals(result, resultValidation)
    }

    companion object{
        @JvmStatic
        @Parameters
        fun data() = listOf(
            arrayOf("1", false),
            arrayOf("qwe", false),
            arrayOf("qweR", false),
            arrayOf("qwe*", false),
            arrayOf("123!", false),
            arrayOf("1Qq!", false),
            arrayOf("qweQ*", false),
            arrayOf("wer123", false),
            arrayOf("FTY12*", false),
            arrayOf("qwe12*", false),
            arrayOf("qweR13!", false),
            arrayOf("qweR123", false),
            arrayOf("123qweQWE!", true),
            arrayOf("po90!wqDFW", true),
            arrayOf("*!#1qweFYU", true),
        )
    }
}