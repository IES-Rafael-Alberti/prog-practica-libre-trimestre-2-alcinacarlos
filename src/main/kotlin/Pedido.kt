package org.practicatrim2
import org.practicatrim2.Terminal.terminal
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors.*

/**
 * Representa un pedido
 *
 * @param nombreEmpresa El nombre de la empresa que realiza el pedido.
 * @param listaMinerales La lista de minerales solicitados en el pedido.
 * @param calidadMinima La calidad mínima requerida para los minerales del pedido.
 * @param entregado Indica si el pedido ya se ha entregado (por defecto `false`).
 */
class Pedido(
    val nombreEmpresa:String,
    var listaMinerales:MutableList<Mineral>,
    var calidadMinima:Calidad,
    var entregado: Boolean = false
) {
    private val id:Int = generarId() //Identificador único del pedido

    /**
     * Genera un identificador único para el pedido.
     */
    companion object{
        private var contador_id:Int = 0
        fun generarId(): Int {
            return ++contador_id
        }
    }

    /**
     * Añade un mineral a la lista de minerales del pedido.
     *
     * @param mineral El mineral a añadir.
     */
    private fun agregarMineral(mineral: Mineral) {
        listaMinerales.add(mineral)
    }

    /**
     * Elimina un mineral de la lista de minerales del pedido.
     *
     * @param mineral El mineral a eliminar.
     */
    private fun eliminarMineral(mineral: Mineral) {
        listaMinerales.remove(mineral)
    }

    /**
     * Calcula el valor total del pedido.
     *
     * @return El valor total del pedido (suma de los valores base de los minerales).
     */
    fun obtenerTotalValor(): Double {
        var total = 0.0 //Valor total
        val nombresMinerales = listaMinerales.map { mineral ->
            MineralesPosibles.entries.find { it.nombre == mineral.nombre }!!
        }
        for (mineral in nombresMinerales){
            total += mineral.valor
        }
        return total
    }

    /**
     * Convierte la lista de minerales a una cadena de texto separada por comas.
     *
     * @return La lista de minerales como cadena de texto.
     */
    fun listaMineralesAString():String{
        return listaMinerales.joinToString(", ") { it.nombre }
    }
    fun mostrarPedido() {
        terminal.println(table {
            borderStyle = green
            style = brightWhite
            header {
                row("Pedido", "Valores")
                style = brightYellow
            }
            body {
                row("ID", id)
                row("Nombre de la Empresa:", nombreEmpresa)
                row("Minerales", listaMineralesAString())
                row("Calidad Minima", calidadMinima.nombre)
                row("Entregado", entregado)
            }
        })
    }

}