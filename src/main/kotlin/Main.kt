package org.practicatrim2

fun main() {
    GestionarJuego.presentarJuego()
    Thread.sleep(5000)
    val trabajadores = mutableListOf<Trabajador>()
    repeat(5){
        trabajadores.add(GestionarJuego.generarTrabajadorAleatorio("minero"))
    }
    trabajadores.add(GestionarJuego.generarTrabajadorAleatorio("transportista"))

    val mina = Mina("Mina Guapisima", 10000.00, trabajadores)
    GestionarJuego.iniciarMina(mina)
}