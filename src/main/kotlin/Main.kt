package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors.*
import org.practicatrim2.Terminal.terminal

fun main() {
    val trabajadores = mutableListOf<Trabajador>()
    repeat(5){
        trabajadores.add(GestionarJuego.generarTrabajadorAleatorio("minero"))
    }
    trabajadores.add(GestionarJuego.generarTrabajadorAleatorio("transportista"))

    val mina = Mina("Mina Guapisima", 10000.00, trabajadores)

    Menu.presentarJuego()
    Thread.sleep(15000)
    GestionarJuego.primerDia(mina)
    GestionarJuego.iniciarMina(mina)
    if (mina.obtenerDinero() <= 0){
        terminal.println(brightRed("Te arruinaste!!!"))
        terminal.println(brightWhite("Mucha suerte en tu próxima aventura!!"))
    }else{
        terminal.println(brightYellow("Abandonaste la mina, mucha suerte en tu proxima vez!!"))
    }
}