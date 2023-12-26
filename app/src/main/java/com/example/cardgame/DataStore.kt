package com.example.cardgame

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User_settings")

