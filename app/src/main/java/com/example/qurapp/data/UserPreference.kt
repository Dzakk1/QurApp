package com.example.qurapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference (private val dataStore: DataStore<Preferences>) {

    companion object {
        val NAME = stringPreferencesKey("name")
        val GENDER = stringPreferencesKey("gender")
    }

    suspend fun saveUser(name : String, gender : Gender) {
        dataStore.edit {
            it[NAME] = name
            it[GENDER] = gender.name
        }
    }

    fun getUser(): Flow<User> {
        return dataStore.data.map { pref ->
            User(
                name = pref[NAME] ?: "Guest",
                gender = Gender.valueOf(
                    pref[GENDER] ?: Gender.MALE.name
                )
            )
        }
    }

}