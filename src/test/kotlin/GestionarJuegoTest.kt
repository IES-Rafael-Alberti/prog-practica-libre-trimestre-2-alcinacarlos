import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.practicatrim2.GestionarJuego
import org.practicatrim2.Mineral
import org.practicatrim2.Minero
import org.practicatrim2.Pedido

class GestionarJuegoTest {
    @Test
    fun generarMineralAleatorios() {
        val mineralAleatorio = GestionarJuego.generarMineralAleatorios()
        assertEquals(Mineral::class.java, mineralAleatorio.javaClass)
    }

    @Test
    fun generarTrabajadorAleatorio() {
        val trabajadorAleatorio = GestionarJuego.generarTrabajadorAleatorio("minero")
        assertEquals(Minero::class.java, trabajadorAleatorio.javaClass)
    }

}