package com.example.cardgame.screens.scene

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cardgame.R
import com.example.cardgame.screens.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScene(navHostController: NavHostController){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {},
        bottomBar = {},
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            Image(
                painter = painterResource(R.drawable.baseline_filter_none_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(10.dp)
            )
            Button(onClick = {navHostController.navigate(Screen.Main.route)}) {
                Text("Game Scene")
            }

        }
    }

}