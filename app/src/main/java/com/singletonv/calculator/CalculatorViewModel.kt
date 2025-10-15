package com.singletonv.calculator

import androidx.compose.runtime.mutableStateOf

class CalculatorViewModel {

    val state = mutableStateOf(
        Display(
            expression = "45x8",
            result="360")
    )

    fun processCommand(command: CalculatorCommand) {
        when (command) {
            CalculatorCommand.Clear -> {}
            CalculatorCommand.Evaluate -> {}
            is CalculatorCommand.Input -> {}
        }
    }

    fun processUserInput(input: String) {
        when (input) {
            "AC" -> state.value = Display("", "")
            "1" -> state.value = Display("1", "")
            "2" -> state.value = Display("", "2")
        }
    }
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand
}

enum class Symbol {
    DIGIT_0,
    DIGIT_1,
    DIGIT_2,
    DIGIT_3,
    DIGIT_4,
    DIGIT_5,
    DIGIT_6,
    DIGIT_7,
    DIGIT_8,
    DIGIT_9,
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    PERCENT,
    POWER,
    FACTORIAL,
    SQRT,
    PI,
    DOT,
    PARENTHESIS
}

data class Display(
    val expression: String,
    val result: String
)