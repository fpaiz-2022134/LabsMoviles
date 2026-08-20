package plat.labs.paiz.lab2

/*
 * @author Franco Paiz
 *
 *
 * Universidad del Valle de Guatemala
 * Programación de Plataformas Móviles
 * Lab 2
 */


enum class ElementType {
    CADENA,
    ENTERO,
    BOOLEANO,
    DESCONOCIDO
}

// Declarar propiedades
data class ItemData(
    val originalPos: Int,
    val originalValue: Any,
    val type: ElementType,
    val info: String
){
    //Función para sobreescribir el toString
    override fun toString(): String{
        val tipoFormateado = type.name.lowercase()
        val infoFormateada = info.lowercase()
        return "'$originalValue' estaba en la posición $originalPos, es de tipo $tipoFormateado e info es $infoFormateada"
    }
}



fun main() {

    // Lista de prueba
    val inputList = listOf(10, "Enero", null, true, 5.5)

    val result = processList(inputList)


    if (result != null) {
        if (result.isEmpty()) {
            println("La lista resultante está vacía.")
        } else {
            result.forEach { item ->
                println(item.toString())
            }
        }
    } else {
        println("La lista resultante es nula.")
    }
}

fun processList(inputList: List<Any?>?): MutableList<ItemData>? {
    //Si la lista que pasaron es nula, retornamos nulo
    if (inputList == null) return null

    val resultList = mutableListOf<ItemData>()

    //Iteración para obtener índice y valor
    //Ponemos en práctica una estructura lambda

    inputList.forEachIndexed { index, element ->

        //Ignoramos los elementos que sean null
        if (element != null) {
            val elementType: ElementType
            val elementInfo: String

            //Chequeamos los tipos
            when (element) {
                is Int -> {
                    elementType = ElementType.ENTERO
                    elementInfo = when {
                        element % 10 == 0 -> "M10"
                        element % 5 == 0 -> "M5"
                        element % 2 == 0 -> "M2"
                        else -> "-"
                    }
                }
                is String -> {
                    elementType = ElementType.CADENA
                    elementInfo = "L${element.length}"
                }
                is Boolean -> {
                    elementType = ElementType.BOOLEANO
                    elementInfo = if (element) "verdadero" else "falso"
                }
                else -> {
                    elementType = ElementType.DESCONOCIDO
                    elementInfo = "desconocido"
                }
            }

            // Creamos el objeto y lo agregamos a la lista
            resultList.add(
                ItemData(
                    originalPos = index,
                    originalValue = element,
                    type = elementType,
                    info = elementInfo
                )
            )
        }

    }

    return resultList
}

