package plat.labs.paiz.lab6

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import plat.labs.paiz.ui.theme.LabsFPTheme


enum class MovementType{
    INCREMENT,
    DECREMENT
}

/**
 * Representación de movimiento individual del historial
 *
 * @property value valor resultante después del movimiento
 * @property type indica si el movimiento fue incremento o decremento
 */
data class CounterMovement(
    val value: Int,
    val type: MovementType
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Lab6Screen(
    modifier: Modifier = Modifier
){
    /*
     * Estado principal del contador
     *
     * Utilizando rememberSaveable
      */
    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }

    var totalIncrements by rememberSaveable {
        mutableIntStateOf(0)
    }

    var totalDecrements by rememberSaveable {
        mutableIntStateOf(0)
    }

    var maximumValue by rememberSaveable {
        mutableIntStateOf(0)
    }

    var minimumValue by rememberSaveable {
        mutableIntStateOf(0)
    }

    /*
    * Lista con todos los movimientos
    *
    * Usamos mutableStateOf para que Compose actualice el historial
     */
    var history by remember{
        mutableStateOf(emptyList<CounterMovement>())
    }

    /*
    * Calculamos total de cambios con los contadores existentes
     */
    val totalChanges = totalIncrements + totalDecrements

    Column(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 72.dp,
                bottom = 16.dp,

            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                TitleSection(
                    name = "Franco Alejandro Paiz González"
                )
            }

            item {
                CounterSection(
                    counter = counter,
                    onDecrease = {
                        counter--

                        totalDecrements++


                        if (counter < minimumValue) {
                            minimumValue = counter
                        }

                        history = history + CounterMovement(
                            value = counter,
                            type = MovementType.DECREMENT
                        )
                    },
                    onIncrease = {
                        counter++

                        totalIncrements++

                        if (counter > maximumValue) {
                            maximumValue = counter
                        }

                        history = history + CounterMovement(
                            value = counter,
                            type = MovementType.INCREMENT
                        )
                    }
                )
            }

            item {
                HorizontalDivider()
            }

            item {
                StatisticsSection(
                    totalIncrements = totalIncrements,
                    totalDecrements = totalDecrements,
                    maximumValue = maximumValue,
                    minimumValue = minimumValue,
                    totalChanges = totalChanges
                )
            }

            item {
                HistorySection(
                    history = history
                )
            }
        }

        Button(
            onClick = {
                counter = 0
                totalIncrements = 0
                totalDecrements = 0
                maximumValue = 0
                minimumValue = 0
                history = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Reiniciar")
        }
    }
}

@Composable
private fun TitleSection(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CounterSection(
    counter: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledTonalIconButton(
            onClick = onDecrease,
            modifier = Modifier.size(52.dp)
        ) {
            Text(
                text = "−",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .size(
                    width = 100.dp,
                    height = 96.dp
                )
        ) {
            Text(
                text = counter.toString(),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
        }

        FilledTonalIconButton(
            onClick = onIncrease,
            modifier = Modifier.size(52.dp)
        ) {
            Text(
                text = "+",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
private fun StatisticsSection(
    totalIncrements: Int,
    totalDecrements: Int,
    maximumValue: Int,
    minimumValue: Int,
    totalChanges: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Estadísticas",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        StatisticRow(
            label = "Total incrementos:",
            value = totalIncrements
        )

        StatisticRow(
            label = "Total decrementos:",
            value = totalDecrements
        )

        StatisticRow(
            label = "Valor máximo:",
            value = maximumValue
        )

        StatisticRow(
            label = "Valor mínimo:",
            value = minimumValue
        )

        StatisticRow(
            label = "Total cambios:",
            value = totalChanges
        )
    }
}

@Composable
private fun StatisticRow(
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HistorySection(
    history: List<CounterMovement>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Historial:",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        if (history.isEmpty()) {
            Text(
                text = "Aún no hay movimientos.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                )
            )
        } else {
            /*
             * FlowRow envía automáticamente los elementos
             * siguientes a una nueva fila.
             *
             * maxItemsInEachRow garantiza que cada fila tenga
             * como máximo cinco elementos.
             */
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 5,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                history.forEach { movement ->
                    HistoryItem(
                        movement = movement
                    )
                }
            }
        }
    }
}

@Composable
private fun HistoryItem(
    movement: CounterMovement,
    modifier: Modifier = Modifier
) {
    /*
     * El color depende del movimiento, no del valor.
     * Si incrementa es color verde, si decrementa es rojo.
     *
     */
    val backgroundColor = if (
        movement.type == MovementType.INCREMENT
    ) {
        Color(0xFF2E7D32)
    } else {
        Color(0xFFC62828)
    }

    Surface(
        modifier = modifier.size(
            width = 56.dp,
            height = 48.dp
        ),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Box {
            Text(
                text = movement.value.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLab6Screen() {
    LabsFPTheme(
        darkTheme = false
    ) {
        Surface {
            Lab6Screen()
        }
    }
}