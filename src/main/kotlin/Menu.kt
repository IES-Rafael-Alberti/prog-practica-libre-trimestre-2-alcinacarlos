package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal

object Menu {
    fun presentarJuego(){
        println("Bienvenido a Mining Simulator Ultimate Deluxe Premium Edition (nombre muy original)")
        println("En este juego, comienzas siendo el dueño de una MINA, tu objetivo es gestionar la mina de la manera más eficiente sin llegar a bancarota")
        println("Cada ${GestionarJuego.DIAS_ENTRE_PEDIDOS} dias recibes un pedido que tienes que cumplir en un plazo")
        println("Puedes intentar hablar con empresas a ver si te ofrecen nuevos pedidos")
        println("TODOS los dias los trabajadores cobran su sueldo")
        println("Cuidado! Hay veces que los trabajadores se lesionan y se ponen de baja (siguen cobrando igual)")
        println("Si te quedas en bancarota pierdes")
        terminal.println(brightWhite("Recuerda ir GESTIONANDO LOS PEDIDOS de vez en cuando, no se harán solos"))
    }
    private fun mostrar(){
        terminal.println(table {
            borderStyle = brightMagenta
            style = brightWhite
            header {
                row("Qué quieres hacer?")
                style = brightCyan
            }
            body {
                row("0 ->", "Avanzar día")
                row("1 ->", "Hablar con empresas a ver si nos dan algún pedido")
                row("2 ->", "Gestionar los pedidos")
                row("3 ->", "Ver todos los empleados")
                row("4 ->", "Ver todos los minerales obtenidos")
                row("5 ->", "Ver los pedidos en cola")
                row("6 ->", "Ver los pedidos pendientes de entrega")
                row("7 ->", "Ver los pedidos realizados")
                row("8 ->", "Ver el historial de acciones")
                row("9 ->", "Contratar a más trabajadores")
                row("10 ->", "Abandonar la mina")
            }
        })
    }
    private fun tablaMinerales(minerales: List<Mineral>){
        val tabla = mutableListOf<List<Any>>()
        for (mineral in minerales) {
            tabla.add(listOf(mineral.nombre, mineral.valor, mineral.calidad.nombre))
        }

        terminal.println(table {
            borderStyle = brightCyan
            header {
                style = brightBlue
                row("Nombre", "Valor", "Calidad")
            }
            body {
                style = brightWhite
                for (fila in tabla) {
                    row {
                        cells(fila[0], fila[1], fila[2])
                    }
                }
            }

        })
    }
    private fun obtenerEstadoActualMina(mina: Mina){
        terminal.println(brightBlue("Los mineros han picado muy fuertemente hoy, tienes en total ${mina.obtenerInventario().size} minerales"))
        terminal.println(table {
            borderStyle = green
            style = brightWhite
            header {
                row("Informacion general", mina.nombre)
                style = brightYellow
            }
            body {
                row("Dia", mina.dia)
                row("Dinero", "${mina.dinero}$")
                row("Trabajadores", mina.obtenerTrabajadores().size)
                row("Minerales en inventario", mina.obtenerInventario().size)
                row("Pedidos pendientes", mina.obtenerPedidos().size + mina.obtenerpedidosPendientesEntrega().size)
            }
        })
    }
    fun buclePrincipal(mina: Mina){
        var opcion:Int? = null
        while (opcion != 0){
            obtenerEstadoActualMina(mina)
            mostrar()
            opcion = readln().toIntOrNull()
            when(opcion){
                0 -> terminal.println(brightMagenta("Avanzando día..."))
                1 -> GestionarJuego.añadirPedidoRandom(mina)
                2 -> GestionarJuego.logicaGestionarPedidoCadaDia(mina)
                3 -> {
                    terminal.println(brightMagenta("Total de trabajadores: ${mina.obtenerTrabajadores().size}"))
                    Thread.sleep(1000)
                    mina.obtenerTrabajadores().forEach { it.mostrarTrabajador() }
                }
                4 -> {
                    val inventario = mina.obtenerInventario()
                    terminal.println(brightMagenta("Total de minerales en el inventario: ${inventario.size}"))
                    //inventario.forEach { it.mostrar() }
                    tablaMinerales(inventario)
                    Thread.sleep(1000)
                }
                5 -> {
                    val pedidos = mina.obtenerPedidos()
                    if (pedidos.size == 0) terminal.println(brightRed("Actualemente no hay pedidos en cola"))
                    pedidos.forEach { it.mostrarPedido() }
                }
                6 -> {
                    val pedidos = mina.obtenerpedidosPendientesEntrega()
                    if (pedidos.size == 0) terminal.println(brightRed("Actualemente no hay pedidos pendientes de entrega"))
                    pedidos.forEach { it.mostrarPedido() }
                }
                7 -> {
                    val pedidos = mina.obtenerpedidosRealizados()
                    if (pedidos.size == 0) terminal.println(brightRed("Actualemente no hay pedidos realizados en el historial"))
                    pedidos.forEach { it.mostrarPedido() }
                }
                8 -> {
                    val historialAcciones = mina.obtenerHistorialAcciones()
                    println(historialAcciones)
                }
                9 -> GestionarJuego.contratarTrabajador(mina)
                10 -> {
                    mina.finalizar()
                    opcion = 0
                }
                else -> {
                    terminal.println(brightRed("Opcion no válida"))}
            }
            Thread.sleep(3000)
        }
    }
}