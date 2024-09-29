package com.example.demo.presenter

sealed interface CounterEvent
data class Change(val delta: Int) : CounterEvent
data object Randomize : CounterEvent
