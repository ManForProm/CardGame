package com.example.cardgame.screens.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cardgame.screens.endGameScreen.EndGamePopup
import com.example.cardgame.screens.main.MainScreen
import com.example.cardgame.screens.scene.GameScene
import com.example.cardgame.screens.splash.SplashScreen
import com.example.cardgame.ui.theme.changeColorBars
import com.example.cardgame.ui.theme.myColors
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = Screen.Splash.route
    ){
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(
            Screen.Main.route,
            enterTransition = { fadeIn(animationSpec = tween(1000)) }
        ) {
            MainScreen(
                navController,
            )
            changeColorBars(color = MaterialTheme.myColors.background)
        }
        composable(
            Screen.Splash.route,
            exitTransition = { fadeOut(animationSpec = tween(1000)) }) {
            SplashScreen(navController)
            changeColorBars(color = MaterialTheme.myColors.background)
        }
        composable(
            Screen.GameScene.route,
            enterTransition = { fadeIn(animationSpec = tween(500)) }) {
            GameScene(navController)
            changeColorBars(color = MaterialTheme.myColors.background)
        }
        composable(
            Screen.EndGamePopup.route,
            enterTransition = { fadeIn(animationSpec = tween(1000)) }) {
            EndGamePopup(navController)
            changeColorBars(color = MaterialTheme.myColors.background)
        }
    }
}

sealed class Screen(val route: String,val params:String = "") {
    val routeParams = "/{$params}"
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
    object GameScene : Screen("gameScene_screen")
    object EndGamePopup : Screen("endGamePopup_screen","furnitureId")
}
