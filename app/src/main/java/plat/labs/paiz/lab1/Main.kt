package plat.labs.paiz.lab1

/**
 * @author Franco Paiz
 * Carnet: 25780
 * Universidad del Valle de Guatemala
 * Programación de plataformas móviles
 */
/*
Entregable 1: El enfoque es utilizar los conocimientos aprendidos en clase y una lógica que permita a
tráves de variables simples individuales realizar un CRUD de productos.
 */



fun main() {
    // Declaramos las variables a utilizar (Sin arrays o similar, ya tu sabe)
    var producto1Nombre : String = "Paralelas Push ups"
    var producto1Cantidad : Int = 10
    var producto1Disponible: Boolean = true

    var producto2Nombre : String = "Paquete 5 Ligas de Entrenamiento"
    var producto2Cantidad : Int = 10
    var producto2Disponible: Boolean = true

    var producto3Nombre : String = "Muñequeras"
    var producto3Cantidad : Int = 10
    var producto3Disponible: Boolean = true

    var producto4Nombre : String = "Cubo de magnesio"
    var producto4Cantidad : Int = 10
    var producto4Disponible: Boolean = true

    var producto5Nombre : String = "Gel de calentamiento"
    var producto5Cantidad : Int = 10
    var producto5Disponible: Boolean = true

    var op: Int = 0

    //CRUD de funciones
    fun mostrarInventario(){
        println("\n--- Inventario Actual ---")
        println("1. $producto1Nombre | Cantidad: $producto1Cantidad | Disponible: $producto1Disponible")
        println("2. $producto2Nombre | Cantidad: $producto2Cantidad | Disponible: $producto2Disponible")
        println("3. $producto3Nombre | Cantidad: $producto3Cantidad | Disponible: $producto3Disponible")
        println("4. $producto4Nombre | Cantidad: $producto4Cantidad | Disponible: $producto4Disponible")
        println("5. $producto5Nombre | Cantidad: $producto5Cantidad | Disponible: $producto5Disponible")

    }

    fun buscarProductos() {
        print("\nIngresa el nombre o patrón a buscar: ")
        val busqueda = readLine() ?: ""

        //Importante: Esto no lo hemos visto, pero creí sería conveniente utilizar un Regex y try catch
        // 1. Manejamos posibles errores por si el usuario ingresa un Regex inválido
        val regex = try {
            Regex(busqueda, RegexOption.IGNORE_CASE)
        } catch (e: Exception) {
            println("Tu búsqueda ha sido inválida")
            return
        }

        var encontroAlMenosUno = false

        println("\n--- Resultados de Búsqueda ---")

        // 2. Evaluamos cada producto de forma independiente usando containsMatchIn()
        if (regex.containsMatchIn(producto1Nombre)) {
            println("-> $producto1Nombre | Cantidad: $producto1Cantidad | Disponible: $producto1Disponible")
            encontroAlMenosUno = true
        }

        if (regex.containsMatchIn(producto2Nombre)) {
            println("-> $producto2Nombre | Cantidad: $producto2Cantidad | Disponible: $producto2Disponible")
            encontroAlMenosUno = true
        }

        if (regex.containsMatchIn(producto3Nombre)) {
            println("-> $producto3Nombre | Cantidad: $producto3Cantidad | Disponible: $producto3Disponible")
            encontroAlMenosUno = true
        }

        if (regex.containsMatchIn(producto4Nombre)) {
            println("-> $producto4Nombre | Cantidad: $producto4Cantidad | Disponible: $producto4Disponible")
            encontroAlMenosUno = true
        }

        if (regex.containsMatchIn(producto5Nombre)) {
            println("-> $producto5Nombre | Cantidad: $producto5Cantidad | Disponible: $producto5Disponible")
            encontroAlMenosUno = true
        }

        // 3. Si la bandera sigue en false, es que ningún if se cumplió
        if (!encontroAlMenosUno) {
            println("Ningún producto coincide con '$busqueda' :/")
        }
    }

    fun actualizarProductos(){
        // Opción 3: Actualizar cantidad y disponibilidad
        println("\n--- Actualizar Producto ---")
        println("1. $producto1Nombre")
        println("2. $producto2Nombre")
        println("3. $producto3Nombre")
        println("4. $producto4Nombre")
        println("5. $producto5Nombre")
        print("Ingresa el número del producto a actualizar (1-5): ")

        val seleccion = readLine()?.toIntOrNull() ?: 0

        if (seleccion in 1..5) {
            print("Ingresa la nueva cantidad (0-100): ")
            val nuevaCantidad = readLine()?.toIntOrNull() ?: -1

            // Validamos que la cantidad esté en el rango permitido
            if (nuevaCantidad in 0..100) {
                // Actualizamos la variable que corresponde
                when (seleccion) {
                    1 -> {
                        producto1Cantidad = nuevaCantidad
                        producto1Disponible = nuevaCantidad > 0
                    }
                    2 -> {
                        producto2Cantidad = nuevaCantidad
                        producto2Disponible = nuevaCantidad > 0
                    }
                    3 -> {
                        producto3Cantidad = nuevaCantidad
                        producto3Disponible = nuevaCantidad > 0
                    }
                    4 -> {
                        producto4Cantidad = nuevaCantidad
                        producto4Disponible = nuevaCantidad > 0
                    }
                    5 -> {
                        producto5Cantidad = nuevaCantidad
                        producto5Disponible = nuevaCantidad > 0
                    }
                }
                println("Cantidad actualizada exitosamente.")
            } else {
                println("Error: La cantidad debe estar entre 0 y 100.")
            }
        } else {
            println("Intenta de nuevo, número de producto inválido.")
        }

    }

    fun mostrarEstadisticas() {
        println("\n--- Estadísticas del Inventario ---")

        var tiposDisponibles = 0
        if (producto1Disponible) tiposDisponibles++
        if (producto2Disponible) tiposDisponibles++
        if (producto3Disponible) tiposDisponibles++
        if (producto4Disponible) tiposDisponibles++
        if (producto5Disponible) tiposDisponibles++

        // 1. Cálculos generales
        val cantidadTotal = producto1Cantidad + producto2Cantidad + producto3Cantidad + producto4Cantidad + producto5Cantidad
        val productosAgotados = 5 - tiposDisponibles
        val promedio = cantidadTotal / 5.0

        // 2. Lógica para encontrar el producto con mayor stock
        var maxCantidad = producto1Cantidad
        var productoMaxNombre = producto1Nombre

        if (producto2Cantidad > maxCantidad) {
            maxCantidad = producto2Cantidad
            productoMaxNombre = producto2Nombre
        }
        if (producto3Cantidad > maxCantidad) {
            maxCantidad = producto3Cantidad
            productoMaxNombre = producto3Nombre
        }
        if (producto4Cantidad > maxCantidad) {
            maxCantidad = producto4Cantidad
            productoMaxNombre = producto4Nombre
        }
        if (producto5Cantidad > maxCantidad) {
            maxCantidad = producto5Cantidad
            productoMaxNombre = producto5Nombre
        }

        // 3. Resultados pro
        println("Productos diferentes disponibles: $tiposDisponibles/5")
        println("Productos agotados (0 unidades): $productosAgotados")
        println("Total de artículos en bodega: $cantidadTotal unidades")
        println("Promedio de unidades por producto: $promedio")
        println("Producto con mayor inventario: $productoMaxNombre ($maxCantidad unidades)")
    }


    //Menú
    do {
        println("\n--------Menú---------")
        println("1. Ver inventario")
        println("2. Buscar un producto")
        println("3. Actualizar producto")
        println("4. Ver estadísticas")
        println("5. Salir")
        print("Ingresa la operación que deseas realizar: ")

        op = readLine()?.toIntOrNull() ?: 0

        when (op) {
            1 -> mostrarInventario()
            2 -> buscarProductos()
            3 -> actualizarProductos()
            4 -> mostrarEstadisticas()
            5 -> println("Gracias por usar el programa mi buen dev :)")
            else -> println("Opción no valida, intenta de nuevo.")
        }
    } while(op != 5)

    //La documentación soy yo
}