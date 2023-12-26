package com.example.cardgame.screens.endGameScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import com.example.cardgame.USER_SCORE
import com.example.cardgame.dataStore

@Composable
fun EndGamePopup(navHostController: NavHostController,) {
    val context = LocalContext.current
    suspend fun incrementCounter() {
        context.dataStore.edit { settings ->
            val currentCounterValue = settings[USER_SCORE] ?: 0
            settings[USER_SCORE] = 115
        }
    }
}