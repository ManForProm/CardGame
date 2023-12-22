package com.example.cardgame.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cardgame.R
import com.example.cardgame.global.CoinScore
import com.example.cardgame.global.PrivatePolicityButton
import com.example.cardgame.global.openDialog
import com.example.cardgame.screens.navigation.Screen
import com.example.cardgame.ui.theme.PrimaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            Box(Modifier.fillMaxWidth()
                .height(100.dp)
                .padding(25.dp), contentAlignment = Alignment.TopEnd) {
                CoinScore(100)
            }
        },
        bottomBar = {
            Box(Modifier.fillMaxWidth()
                .height(100.dp)
                .padding(25.dp), contentAlignment = Alignment.BottomEnd) {
                PrivatePolicityButton {
                    openAlertDialog.value = true
                }
            }
        },
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(R.drawable.baseline_filter_none_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).padding(30.dp)
            )
            Button(onClick = {navHostController.navigate(Screen.GameScene.route)},
                modifier = Modifier.size(150.dp,50.dp)) {
                Text("PLAY")
            }

        }
    }
    when{openAlertDialog.value ->
        openDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onClose = {openAlertDialog.value = false  },
            dialogTitle = "Private Policity",
            dialogText = "Private policity text"
        )

    }
}
