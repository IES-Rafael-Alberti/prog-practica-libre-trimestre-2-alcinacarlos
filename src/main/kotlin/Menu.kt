package org.practicatrim2
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal

object Menu {
    fun mostrar(){
        terminal.println(table {
            borderStyle = brightMagenta
            style = brightWhite
            header {
                row("Qué quieres hacer?")
                style = brightCyan
            }
            body {
                row("0 ->", "Avanzar día")
                row("1 ->", "Ver todos los empleados")
                row("2 ->", "Ver todos los minerales obtenidos")
                row("3 ->", "Ver los pedidos en cola")
                row("4 ->", "Ver los pedidos pendientes de entrega")
                row("5 ->", "Ver los pedidos realizados")
                row("6 ->", "Ver el historial de acciones")
                row("7 ->", "Contratar a más trabajadores")
                row("8 ->", "Hablar con empresas a ver si nos dan algún pedido")
                row("9 ->", "Abandonar la mina")
            }
        })
    }
}