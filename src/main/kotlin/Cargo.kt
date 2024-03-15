package org.practicatrim2

/**
 * Enum class que define los diferentes cargos que puede tener un empleado.
 *
 * @param nombre El nombre del cargo.
 * @param salario El salario base asociado al cargo.
 */
enum class Cargo(val nombre:String, val salario:Int) {
    JEFE("Jefe", 550),
    AYUDANTE("Ayudante", 120),
    PRACTICAS("Practicas", 0),
    TECNICO("Tecnico", 190),
    OPERARIO("Operario", 250),
    PERFORISTA("Perforista", 300),
    CONDUCTOR("Conductor", 350)
}