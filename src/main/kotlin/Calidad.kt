package org.practicatrim2

/**
 * Enum class que define los diferentes niveles de calidad.
 *
 * @param multiplicador El multiplicador que se aplicará a un valor base para obtener el valor final.
 * @param nombre El nombre del valor de la enumeración.
 */
enum class Calidad(val multiplicador: Double, val nombre:String) {
    BAJA(0.3, "Baja"),
    MEDIA(0.5, "Media"),
    ALTA(0.7, "Alta"),
    EXCELENTE(1.0, "Excelente")
}