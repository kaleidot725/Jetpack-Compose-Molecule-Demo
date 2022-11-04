package com.example.demo.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.demo.data.RandomService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun CounterPresenter(
    events: Flow<CounterEvent>,
    randomService: RandomService,
): CounterModel {
    var count by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is Change -> {
                    count += event.delta
                }

                Randomize -> {
                    loading = true
                    launch {
                        count = randomService.get(-20, 20)
                        loading = false
                    }
                }
            }
        }
    }

    return CounterModel(count, loading)
}
