package org.practicatrim2
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.terminal.Terminal

import kotlin.math.min

object GestionarJuego {
    val t = Terminal()

    fun presentarJuego(){
        println("Bienvenido a Mining Simulator Ultimate Deluxe Premium Edition (nombre muy original)")
        println("En este juego, comienzas siendo el dueño de una MINA, tu objetivo es gestionar la mina de la manera más eficiente sin llegar a bancarota")
        println("Cada 3 dias recibes un pedido que tienes que cumplir en un plazo")
        println("Cada 5 dias los trabajadores cobran su sueldo")
        println("Cada 10 dias tienes que pagar el alquiler de maquinaria")
        println("Si te quedas en bancarota pierdes")
        println("Con esta explicacion es suficiente, lo demás lo verás sobre la marcha, mucha suerte y cuidado con los gases!")
    }
    fun iniciarDia(mina:Mina){
        mina.avanzarDia()
        println("""
            Dia: ${mina.dia}
            Has llegado 
        """)

    }
    fun iniciarMina(mina:Mina){
        do {
            iniciarDia(mina)

        }while (mina.activa)
    }
}