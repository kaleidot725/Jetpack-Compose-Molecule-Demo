package com.example.demo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.presenter.CounterModel
import kotlinx.coroutines.flow.Flow

@Composable
fun CounterApp(
    modelFlow: Flow<CounterModel>,
    onIncreaseOne: () -> Unit,
    onIncreaseTen: () -> Unit,
    onDecreaseOne: () -> Unit,
    onDecreaseTen: () -> Unit,
    onRandomize: () -> Unit
) {
    val model by modelFlow.collectAsState(CounterModel())

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            if (model.loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            } else {
                Text(
                    text = model.value.toString(),
                    fontSize = 100.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }

        Button(onClick = onIncreaseOne, modifier = Modifier.fillMaxWidth()) {
            Text(text = "INCREASE ONE")
        }

        Button(onClick = onIncreaseTen, modifier = Modifier.fillMaxWidth()) {
            Text(text = "INCREASE TEN")
        }

        Button(onClick = onDecreaseOne, modifier = Modifier.fillMaxWidth()) {
            Text(text = "DECREASE ONE")
        }

        Button(onClick = onDecreaseTen, modifier = Modifier.fillMaxWidth()) {
            Text(text = "DECREASE TEN")
        }

        Button(onClick = onRandomize, modifier = Modifier.fillMaxWidth()) {
            Text(text = "RANDOMIZE")
        }
    }
}
