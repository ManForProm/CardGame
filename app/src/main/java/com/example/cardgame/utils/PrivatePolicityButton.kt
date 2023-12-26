package com.example.cardgame.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrivatePolicityButton(onClickButton:() -> Unit ){
    Button(onClick = {
            onClickButton()
        } ) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Filled.Lock,
                contentDescription = "Logo Icon",
                tint = Color.Black
            )

    }
}


@Composable
fun openDialog(
    onDismissRequest: () -> Unit,
    onClose: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    ){
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onClose()
                }
            ) {
                Text("Close")
            }
        },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    onDismissRequest()
//                }
//            ) {
//                Text("Dismiss")
//            }
//        }
    )

}