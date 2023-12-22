package com.example.cardgame.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class MyColors(
    val material: ColorScheme,
    val interviewHeaderColor: Color,
    val answeredColor: Color,
    val correctColor: Color,
    val incorrectColor: Color,
    val invisible: Color,
    val incorrectColorALittle: Color,
    val correctColorALittle: Color,
    val reviewColor: Color,
    val whiteColor: Color,

    ) {
    val primary: Color get() = material.primary
    val secondary: Color get() = material.secondary
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
}


private val DarkColorScheme = MyColors(
    material = darkColorScheme(
        primary = PrimaryDark,
        secondary = SecondaryDark,
        background = BackgroundColorDark,
    ),
    interviewHeaderColor = InterviewHeaderColorDark,
    answeredColor = AnsweredColorDark,
    correctColor = CorrectColorDark,
    incorrectColor = IncorrectColorDark,
    invisible = InvisibaleColor,
    incorrectColorALittle = IncorrectColorALittleDark,
    correctColorALittle = CorrectColorDarkALittle,
    reviewColor = ReviewColorDark,
    whiteColor = WhiteColorDark,
)

private val LightColorScheme = MyColors(
    material = lightColorScheme(
        primary = PrimaryLight,
        secondary = SecondaryLight,
        background = BackgroundColorLight,
    ),
    interviewHeaderColor = InterviewHeaderColor,
    answeredColor = AnsweredColorLight,
    correctColor = CorrectColorLight,
    incorrectColor = IncorrectColorLight,
    invisible = InvisibaleColor,
    incorrectColorALittle = IncorrectColorALittleLight,
    correctColorALittle = CorrectColorLightALittle,
    reviewColor = ReviewColorLight,
    whiteColor = WhiteColorLight,
)

@Composable
fun CardGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    } ?: LightColorScheme
    CompositionLocalProvider(LocalColors provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme.material,
            typography = Typography,
            content = content
        )
    }
}

@Composable
fun changeColorBars(color: Color){
    val systemUiController = rememberSystemUiController()
    var barColor by remember { mutableStateOf(color) }
    systemUiController.setStatusBarColor(
        color = barColor
    )
    systemUiController.setSystemBarsColor(
        color = barColor
    )
}

val MaterialTheme.myColors: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

private val LocalColors = staticCompositionLocalOf { LightColorScheme }