package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.example.demo.data.RandomService
import com.example.demo.presenter.Change
import com.example.demo.presenter.CounterEvent
import com.example.demo.presenter.CounterModel
import com.example.demo.presenter.CounterPresenter
import com.example.demo.presenter.Randomize
import com.example.demo.ui.CounterApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val scope = CoroutineScope(Main)
    private val randomService = RandomService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val event = MutableSharedFlow<CounterEvent>()
        val modelFlow: Flow<CounterModel> = scope.launchMolecule(mode = RecompositionMode.Immediate) {
            CounterPresenter(event, randomService)
        }

        setContent {
            CounterApp(
                modelFlow = modelFlow,
                onIncreaseOne = { scope.launch { event.emit(Change(1)) } },
                onIncreaseTen = { scope.launch { event.emit(Change(10)) } },
                onDecreaseOne = { scope.launch { event.emit(Change(-1)) } },
                onDecreaseTen = { scope.launch { event.emit(Change(-10)) } },
                onRandomize = { scope.launch { event.emit(Randomize) } }
            )
        }
    }
}
