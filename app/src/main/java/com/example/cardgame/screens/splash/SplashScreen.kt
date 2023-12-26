package com.example.cardgame.screens.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cardgame.R
import com.example.cardgame.screens.navigation.Screen
import com.example.cardgame.ui.theme.PrimaryLight
import com.example.cardgame.ui.theme.myColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController){

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        navHostController.popBackStack()
        navHostController.navigate(Screen.Main.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha:Float){
    Box(
        modifier = Modifier
            .background(MaterialTheme.myColors.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_filter_none_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp).padding(30.dp)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash(alpha = 1f)
}