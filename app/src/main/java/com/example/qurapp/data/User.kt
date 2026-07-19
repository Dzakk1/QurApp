package com.example.qurapp.data

data class User(
    val name : String,
    val gender : Gender
)

enum class Gender {
    MALE,
    FEMALE
}
