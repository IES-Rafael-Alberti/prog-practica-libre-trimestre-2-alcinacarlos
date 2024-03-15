# Actividad: Desarrollo de Proyecto Software en Kotlin

# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, **asegúrate de hacer referencia y enlazar al código relevante** en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.f, 4.a)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos específicos de tu código.
- Se utilizan constructores para inicializar los atributos de un objeto al crearlo.
  La clase *Mina* tiene un constructor que define el nombre, dinero y los trabajadores iniciales.
- https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/1541c1b5a6d49eb1350b380b69578c148ba5768d/src/main/kotlin/Main.kt#L13
  Ejemplo 1
- https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/2901d020500de4756de82863fd226f480e367340/src/main/kotlin/GestionarJuego.kt#L43-L48
- Ejemplo 2
- https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/2901d020500de4756de82863fd226f480e367340/src/main/kotlin/GestionarJuego.kt#L57-L71

#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.i)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?
- El obejetivo era poder generar una tabla a partir de una lista de tipo [Mineral](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/master/src/main/kotlin/Mineral.kt)
- https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/2901d020500de4756de82863fd226f480e367340/src/main/kotlin/Menu.kt#L47-L74
- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?
- En el bucle principal del menú para poder ver todos los minerales que hay en el inventario
- https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-alcinacarlos/blob/2901d020500de4756de82863fd226f480e367340/src/main/kotlin/Menu.kt#L117-L123

#### **Criterio global 3: Uso de entornos**
**(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.
- Entorno de desarrollo:
- Se utilizó el IDE IntelliJ IDEA para el desarrollo del proyecto.
- Creación del proyecto:
* Se creó un nuevo proyecto Kotlin.
* Se agregaron las dependencias necesarias al proyecto.
* Se crearon las clases y archivos necesarios para el programa. 
* Se utilizó el botón "Run" del IDE para compilar y ejecutar el programa. 
* Se utilizó el debugger del IDE para detectar y corregir errores en el código.

#### **Criterio global 4: Definir clases y su contenido**
- **(4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.h, 4.j, 7.a, 7.b, 7.c)**: Describe sobre tu código cómo has implementado la herencia o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? ¿Mostrando tu código, contesta a qué principios has utilizado y qué beneficio has obtenido?

#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.k)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?

