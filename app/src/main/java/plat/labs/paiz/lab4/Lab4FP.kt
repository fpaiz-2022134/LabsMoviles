package plat.labs.paiz.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import plat.labs.paiz.R

class Lab4FP : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            //surface de fondo general
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background

            ){
               PortadaLaboratorio()
            }
        }
    }
}
@Composable
fun PortadaLaboratorio() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(width = 8.dp, color = Color(0xFF006400))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // 1. logo UVG
        Image(
            painter = painterResource(id = R.drawable.udelvallelogo),
            contentDescription = "Escudo UVG Fondo",
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .alpha(0.10f),
            contentScale = ContentScale.FillWidth
        )

        // 2. Contenido de Texto
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Título Principal
            Text(
                text = "Universidad del Valle\nde Guatemala",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 34.sp
            )

            // Subtítulo
            Text(
                text = "Programación de plataformas\nmóviles, Sección 30",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Integrantes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "INTEGRANTES",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Franco Paiz")
                    Text(text = "Joao Castillo")
                    Text(text = "Weslly Cabrera")
                }
            }

            // Sección de Catedrático
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "CATEDRÁTICO",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Juan Carlos Durini",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Datos del Alumno
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Franco Paiz",
                    fontSize = 18.sp
                )
                Text(
                    text = "25780",
                    fontSize = 18.sp
                )
            }
        }
    }
}