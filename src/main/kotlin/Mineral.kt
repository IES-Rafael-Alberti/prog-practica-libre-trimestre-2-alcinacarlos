package org.practicatrim2

data class Mineral(
    val nombre:String,
    val valor:Double,
    val calidad:Calidad
) {
    override fun toString(): String {
        return """
            Mineral:
            Nombre: $nombre
            Valor: $valor
            Calidad: ${calidad.nombre}
        """
    }
}