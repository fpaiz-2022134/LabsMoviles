package plat.labs.paiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import plat.labs.paiz.ui.theme.LabsFPTheme

import plat.labs.paiz.lab4.PortadaLaboratorio

/*
GITHUB REPOSITORY:
https://github.com/fpaiz-2022134/LabsMoviles.git

author Franco Paiz
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabsFPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

// 1. Configuración de las rutas de navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {

        composable("menu") {
            MenuScreen(
                onNavigateToLab = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable("lab1") {
            PantallaInfoLab(
                titulo = "Laboratorio 1",
                descripcion = "Este laboratorio fue desarrollado para su ejecución en consola.\n\nDirígete a la carpeta 'lab1' en el código fuente para observar el trabajo.",
                onBack = { navController.popBackStack() }
            )
        }

        composable("lab2") {
            PantallaInfoLab(
                titulo = "Laboratorio 2",
                descripcion = "Este laboratorio fue desarrollado para su ejecución en consola.\n\nDirígete a la carpeta 'lab2' en el código fuente para observar el trabajo.",
                onBack = { navController.popBackStack() }
            )
        }

        composable("lab3") {
            PantallaInfoLab(
                titulo = "Laboratorio 3",
                descripcion = "Las fotografías y capturas del trabajo realizado se encuentran dentro de la carpeta 'lab3'.",
                onBack = { navController.popBackStack() }
            )
        }

        composable("lab4") {
            Box(modifier = Modifier.fillMaxSize()) {
                PortadaLaboratorio()

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar al menú"
                    )
                }
            }
        }
    }
}

// 2. Menú Principal
@Composable
fun MenuScreen(onNavigateToLab: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Menú Principal UVG",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { onNavigateToLab("lab1") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Laboratorio 1 (en consola)")
        }

        Button(
            onClick = { onNavigateToLab("lab2") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Laboratorio 2 (en consola)")
        }

        Button(
            onClick = { onNavigateToLab("lab3") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Laboratorio 3")
        }

        Button(
            onClick = { onNavigateToLab("lab4") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Laboratorio 4 (Portada)")
        }
    }
}

// 3. Plantilla Reutilizable para las pantallas de información (Labs 1, 2 y 3)
@Composable
fun PantallaInfoLab(titulo: String, descripcion: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = descripcion,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Regresar al menú")
        }
    }
}