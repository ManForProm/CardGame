package com.example.cardgame

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.cardgame.screens.navigation.AppNavHost
import com.example.cardgame.ui.theme.CardGameTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val USER_SCORE = intPreferencesKey("score")
class MainActivity : ComponentActivity() {
    val userScoreFlow: Flow<Int> = applicationContext.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_SCORE] ?: 0
        }
    var userScore:Int = 0
    runBlocking(Dispatchers.IO) {
        userScore = userScoreFlow.first()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CardGameTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardGameTheme {
        Greeting("Android")
    }
}