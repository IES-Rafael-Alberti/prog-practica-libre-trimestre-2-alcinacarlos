package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal

/**
 * Representa un mineral en el juego
 *
 * @param nombre El nombre del mineral.
 * @param valor El valor base del mineral.
 * @param calidad La calidad del mineral.
 */
data class Mineral(
    val nombre:String,
    val valor:Double,
    val calidad:Calidad
) {
    fun mostrar() {
        terminal.println(table {
            borderStyle = TextColors.green
            style = TextColors.brightWhite
            body {
                row("Nombre", nombre)
                row("Valor", valor)
                row("Calidad", calidad.nombre)
            }
        })
    }
}