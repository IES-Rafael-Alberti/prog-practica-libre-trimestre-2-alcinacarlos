package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal

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