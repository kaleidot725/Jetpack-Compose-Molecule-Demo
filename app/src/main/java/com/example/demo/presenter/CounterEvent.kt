package com.example.demo.presenter

sealed interface CounterEvent
data class Change(val delta: Int) : CounterEvent
object Randomize : CounterEvent
