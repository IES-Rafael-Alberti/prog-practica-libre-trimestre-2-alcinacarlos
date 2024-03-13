package org.practicatrim2
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.*
import com.github.ajalt.mordant.terminal.YesNoPrompt

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

    fun generarMineralAleatorios():Mineral{
        val nombre = MineralesPosibles.entries.random().nombre
        val valorMineral = MineralesPosibles.entries.random().valor
        val calidadMineral = Calidad.entries.toTypedArray().random()
        return Mineral(nombre, valorMineral, calidadMineral)
    }
    fun contratarTransportista(){
        t.println(TextColors.brightRed("No hay transportistas disponibles"))
        val respuesta = YesNoPrompt("Quieres contratar a otro transportista?", t)
        respuesta.ask()
        TODO("HACER ESTO, HACER BUSCARTRANSPORTITADISPONIBLE Y GESTIONARPEDIDO")

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