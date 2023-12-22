package com.example.cardgame.global

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cardgame.ui.theme.PrimaryLight

@Composable
fun CoinScore(score:Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .size(40.dp),
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Logo Icon",
            tint = PrimaryLight
        )
            Text(score.toString(), fontWeight = FontWeight.Bold)
    }
}