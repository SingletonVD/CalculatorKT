package com.singletonv.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 40.dp,
                        bottomStart = 40.dp
                    )
                )
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(bottom = 16.dp, end = 40.dp, start = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when (val currentState = state.value) {
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = currentState.result,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "",
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SQRT))
                    },
                text = "√",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PI))
                    },
                text = "π",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.POWER))
                    },
                text = "^",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.FACTORIAL))
                    },
                text = "!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Clear)
                    }
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AC",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "( )",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PERCENT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "÷",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "7",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "8",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "9",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "X",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "6",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SUBTRACT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape)
                    .aspectRatio(2f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ",",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Evaluate)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "=",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp
                )
            }
        }
    }
}