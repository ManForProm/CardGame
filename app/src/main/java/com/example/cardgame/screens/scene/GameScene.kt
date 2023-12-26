package com.example.cardgame.screens.scene

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cardgame.R
import com.example.cardgame.utils.CoinScore
import com.example.cardgame.utils.openDialog
import com.example.cardgame.ui.theme.PrimaryLight
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScene(navHostController: NavHostController,
            userScore:Int){
    val openAlertDialog = remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {},
        bottomBar = {},
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            Row(modifier = Modifier.fillMaxWidth()){
                time = TimerView(time = 60, stopTimer = isPaused)
                Box(contentAlignment = Alignment.TopEnd){ CoinScore(score = 100, modifier = Modifier.padding(15.dp))}
            }
            GameSceneGrid(generateGameCardList()){ winState ->
                if(winState){
                    isPaused = true
                    openAlertDialog.value = true
                }
            }
            Text(modifier = Modifier.padding(15.dp), text = "Keep matching up two of the same object" +
                    " until there are no more to be pared and you clear the board.")

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "fast", fontSize = 20.sp)}
        }
    }
    when{openAlertDialog.value ->
        openDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onClose = {openAlertDialog.value = false  },
            dialogTitle = "You WIN",
            dialogText = "Congrads your time is $time"
        )

    }

}
fun generateGameCardList():MutableList<GameCard> {
   val listIcons =  listOf(
        Icons.Filled.CheckCircle,
        Icons.Filled.Check,
//        Icons.Filled.List,
//        Icons.Filled.AccountCircle,
//        Icons.Filled.Add,
//        Icons.Filled.ShoppingCart,
//        Icons.Filled.ArrowBack,
//        Icons.Filled.ExitToApp,
//        Icons.Filled.Favorite,
//        Icons.Filled.Star
    )
    val pairedList = (listIcons + listIcons).shuffled()
    val gameCardList = mutableListOf<GameCard>()
    pairedList.forEach{item -> gameCardList += GameCard( item, mutableStateOf(CardFace.Front) )}
    return gameCardList
}
@Composable
fun TimerView(time:Int,stopTimer:Boolean):Int{
    var timeLeft by remember { mutableStateOf(time) }
    var isPaused by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        while (timeLeft > 0 && !isPaused) {
            delay(1000L)
            isPaused = stopTimer
            timeLeft--
        }
    }
    Box(modifier = Modifier.padding(15.dp)) {
        Box(
            modifier = Modifier
                .background(color = Color.Gray, shape = RoundedCornerShape(25.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(PaddingValues(start = 50.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(timeLeft.toString(),)
        }
        Image(
            painter = painterResource(R.drawable.baseline_timer_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
        )
    }
    return timeLeft
}

data class GameCard(val Icon:ImageVector, val State:MutableState<CardFace>)
@Composable
fun GameSceneGrid(listCards: List<GameCard>,win:(Boolean) -> Unit){
    val list by remember {
        mutableStateOf(listCards)
    }
    val counterTime = 2
    var countOpen by remember { mutableStateOf(0) }
    var firstIcon by remember {
        mutableStateOf(Icons.Default.Delete)
    }
    var secondIcon by remember {
        mutableStateOf(Icons.Default.Delete)
    }
        Box(Modifier.padding(20.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 50.dp),
                contentPadding = PaddingValues(7.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(listCards.size) { cardIndex ->
                    var timeLeft by remember { mutableStateOf(counterTime) }
                    var isPaused by remember { mutableStateOf(true) }

                    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
                        while (timeLeft > 0 && !isPaused) {
                            delay(1000L)
                            timeLeft--
                        }
                    }
                    // закрываем после времени
                    if (timeLeft == 0 && list[cardIndex].State.value == CardFace.Back) {
                        isPaused = true
                        countOpen--
                        timeLeft = counterTime
                        if (firstIcon != secondIcon) {
                            list[cardIndex].State.value = CardFace.Front
                        }
                    }

                    Box(modifier = Modifier) {
                        FlipCard(cardFace = list[cardIndex].State.value,
                            onClick = {
                                // открываем
                                if (countOpen < 2) {
                                    if (list[cardIndex].State.value == CardFace.Front) {
                                        isPaused = false
                                        countOpen++
                                        list[cardIndex].State.value = CardFace.Back
                                        if (countOpen == 1) {
                                            firstIcon = list[cardIndex].Icon
                                        }
                                        if (countOpen == 2) {
                                            secondIcon = list[cardIndex].Icon
                                        }
                                    } else list[cardIndex].State.value = CardFace.Front;
                                }
                                if (allCardsOpened(list)) win(true) else win(false)
                            },
                            back = { BackGameCard(pairIconImage = list[cardIndex].Icon) },
                            front = { FrontGameCard() }
                        )
                    }
                }
            }
        }
}
fun allCardsOpened(gameCards: List<GameCard>): Boolean {
    return gameCards.all { card ->
        card.State.value == CardFace.Back
    }
}

@Composable
fun BackGameCard(pairIconImage:ImageVector){
    Box(
        modifier = Modifier
            .background(color = Color.Blue)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp),
            imageVector = pairIconImage,
            contentDescription = "Game Card Icon",
            tint = PrimaryLight
        )
    }
}
@Composable
fun FrontGameCard(){
    Box(
        modifier = Modifier
            .background(color = Color.Gray)
            .fillMaxSize()
    ){
        Text(text = "Front")
    }
}


enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier.size(60.dp),
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    Card(
        onClick = { onClick(cardFace) },
        modifier = modifier
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density
            },
    ) {
        if (rotation.value <= 90f) {
            Box(
                Modifier.fillMaxSize()
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}