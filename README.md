[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/NBVXLiSy)
# Actividad: Desarrollo de Proyecto Software en Kotlin

**ID actividad:** 2324_PRO_u4u5u6_libre

**Agrupamiento de la actividad**: Individual 

---

### Descripción:

La actividad consiste en el desarrollo de un proyecto software en Kotlin, permitiendo al estudiante elegir la temática. Este proyecto debe demostrar la comprensión y aplicación de los conceptos de programación orientada a objetos (POO), incluyendo la definición y uso de clases, herencia, interfaces, genericos, principios SOLID y el uso de librerías externas.

**Objetivo:**

- Demostrar comprensión de los fundamentos de POO mediante la instanciación y uso de objetos.
- Aplicar conceptos avanzados de POO como herencia, clases abstractas, e interfaces.
- Crear y usar clases que hagan uso de genéricos. 
- Aplicar principios SOLID.
- Integrar y utilizar librerías de clases externas para extender la funcionalidad del proyecto.
- Documentar y presentar el código de manera clara y comprensible.

**Trabajo a realizar:**

1. **Selección de la Temática:** Elige un tema de tu interés que pueda ser abordado mediante una aplicación software. Esto podría ser desde una aplicación de gestión para una pequeña empresa, una herramienta para ayudar en la educación, hasta un juego simple. Define claramente el problema que tu aplicación pretende resolver.

2. **Planificación:** Documenta brevemente cómo tu aplicación solucionará el problema seleccionado, incluyendo las funcionalidades principales que desarrollarás.

3. **Desarrollo:**
   - **Instancia de Objetos:** Tu aplicación debe crear y utilizar objetos, demostrando tu comprensión de la instanciación y el uso de constructores, métodos, y propiedades.
   - **Métodos Estáticos:** Define y utiliza al menos un método estático, explicando por qué es necesario en tu aplicación.
   - **Uso de IDE:** Desarrolla tu proyecto utilizando un IDE, aprovechando sus herramientas para escribir, compilar, y probar tu código.
   - **Definición de Clases:** Crea clases personalizadas con sus respectivas propiedades, métodos, y constructores.
   - **Clases con genéricos:** Define y utiliza al menos una clase que haga uso de genéricos en tu aplicación.
   - **Herencia y Polimorfismo:** Implementa herencia y/o interfaces en tu proyecto para demostrar la reutilización de código y la flexibilidad de tu diseño.  **Usa los principios SOLID:** Ten presente durante el desarrollo los principios SOLID y úsalos durante el diseño para mejorar tu aplicación.
   - **Librerías de Clases:** Integra y utiliza una o más librerías externas que enriquezcan la funcionalidad de tu aplicación.
   - **Documentación:** Comenta tu código de manera efectiva, facilitando su comprensión y mantenimiento.

4. **Prueba y Depuración:** Realiza pruebas para asegurarte de que tu aplicación funciona como se espera y depura cualquier error encontrado.
5. **Contesta a las preguntas** ver el punto **Preguntas para la Evaluación**

### Recursos

- Apuntes dados en clase sobre programación orientada a objetos, Kotlin, uso de IDEs, y manejo de librerías.
- Recursos vistos en clase, incluyendo ejemplos de código, documentación de Kotlin, y guías de uso de librerías.

### Evaluación y calificación

**RA y CE evaluados**: Resultados de Aprendizaje 2, 4, 6, 7 y Criterios de Evaluación asociados.

**Conlleva presentación**: SI

**Rubrica**: Mas adelante se mostrará la rubrica.

### Entrega

> **La entrega tiene que cumplir las condiciones de entrega para poder ser calificada. En caso de no cumplirlas podría calificarse como no entregada.**
>
- **Conlleva la entrega de URL a repositorio:** El contenido se entregará en un repositorio GitHub. 
- **Respuestas a las preguntas:** Deben contestarse en este fichero, README.md


# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, **asegúrate de hacer referencia y enlazar al código relevante** en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.f, 4.a)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos      específicos de tu código.

  En este proyecto se han instanciado varios objetos, Armas y Armaduras, por ejemplo. Un ejemplo de como se han instanciado podría bien ser el siguiente:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Item.kt#L91-L98
  
  En dicho ejemplo se instancian mediante variables que se declaran en una función.
  En cuanto al uso, se pueden ver varios:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorConsola.kt#L332-L340
  
  Debido a la naturaleza de las clases Arma y Armadura en este proyecto, no se les puede dar otro uso que no sea de argumento para una función, ya que ambas son data clases. En otros casos, o en otros códigos se   les puede emplear a la hora de llamar a métodos específicos como el siguiente, donde se pasan como parámetro:
  https://github.com/rmungar/SistemaVehiculos_II/blob/bd7271edbcd156d3365eee8de0da924d94dc2226/src/Main.kt#L19-L28

#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.i)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?
- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?

Sí, en cuanto a métodos estáticos, he empleado varios en la clase item. Se han hecho estáticos, no solo por la capacidad de ser llamados sin la necesidad de instanciar una clase, sino porque son propios de la clase, no de los objetos:
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Item.kt#L72-L140
Si nos vamos al código, podemos ver que se llaman en situaciones como esta:
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Juego.kt#L366-L380

En la función anterior podemos ver como se llama al metodo **estático** y al metodo **del objeto**.

#### **Criterio global 3: Uso de entornos**
- **(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.

Para crear este proyecto empleé el IDE IntelliJ Idea, el cual nos ofrece varias facilidades a la hora de redactar el código, una forma de emplear el controlador de versiones de Git e incluye la facilidad de compilar mi código conforme lo escribo. A la hora de probar mi código de forma "superficial" hice uso de la función **debug** para controlar de forma detenida las secciónes del programa que me interesaban.
Para llevar a cabo las pruebas utilicé el framework de pruebas **JUnit**, para llevar a cabo pruebas de funciones específicas del programa tras configurar diferentes tests.

#### **Criterio global 4: Definir clases y su contenido**
- **(4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?

Como se explicó antes, en este proyecto se han empleado varios tipos de clases, desde **data classes** hasta **abstract classes**. Varios ejemplos bien serían los siguentes:
   - **DATA CLASS**
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Arma.kt#L16-L20
   - **ABSTRACT CLASS**
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/personajes/Personaje.kt#L16-L18
   - **ENUM CLASS**
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/modosDeJuego/GameModes.kt#L11-L13
   - **OPEN CLASS**
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Item.kt#L58-L67

Las propiedades y los métodos de las clases se decidieron en función de lo mínimo que han de tener o ser capaces de hacer para que cumplan con la idea o la responsabilidad del objeto que representan.
Puesto que el proyecto en sí es un mero gestor de objetos, estos son primordiales para su funcionamiento, por lo que diría que sin los objetos ni siquiera habría un problema a resolver. Por ende, diría que contribuyen de manera significativa todas y cada una de ellas a dar forma y propósito a este proyecto.

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.h, 4.j, 7.a, 7.b, 7.c)**: Describe sobre tu código cómo has implementado la herencia o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? ¿Mostrando tu código, contesta a qué principios has utilizado y qué beneficio has obtenido?

Al igual que se han implementado **clases** y **objetos**, tambien se han empleado varias **interfaces**:

   - *EQUIPABLE*
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/E_S_G.kt#L5-L16

   - *SUSTITUIBLE*
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/E_S_G.kt#L18-L26

   - *GUARDABLE*
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/E_S_G.kt#L28-L36

Aparte de las 3 mencionadas arriba, hay 4 interfaces más en este proyecto. 

**¿Por qué interfaces?**

   Pues porque las interfaces nos permiten establecer una base para lo que la clase u objeto que los implemente deba hacer. Por no hablar de que pueden ser genéricas y nos permiten trabajar con cualquier tipo de    dato.

   Las implementaciones y las herencias de estas interfaces y clases se pueden ver en los siguientes ejemplos:

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Item.kt#L58-L68

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Juego.kt#L15-L22

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestionJuego.kt#L13-L20

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorEntrada.kt#L5-L10

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorConsola.kt#L15-L28

**PRINCIPIOS DE SOLID**

   - **SRP** **S**ingle Responsibility Principle
     
        El SRP lo podemos ver en los objetos **GestorConsola** y **GestorEntrada**. Aunque muy optimizables, ambos objetos implementan funciones que se dedican expresamente a una función, es decir, a mostrar por         consola o a recibir entradas por consola.
     
https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorConsola.kt#L29-L55

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorEntrada.kt#L10-L30

   - **OCP** **O**pen Closed Principle
     
   El OCP nos deice que debemos tener un código abierto a la extensión, pero no a la modificación. Es por eso que he elegido el uso de las interfaces como forma de implementar dicho principio. Las                   interfaces genéricas nos permiten pasar cualquier tipo de dato, que la funcionalidad no se ve alterada.

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/E_S_G.kt#L17-L27

   - **LSP** **L**iskov Substitution Principle
   
        El LSP nos dice que debemos poder emplear elementos de una subclase en vez de elementos de una superclase sin que el código se vea alterado o deje de funcionar. Un claro ejemplo de esto lo tenemos aquí:

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Juego.kt#L306-L334

   Aunque esta función parezca complicada, administra un String. Primero lo convierte a lista e instancia un objeto con dichos datos. Si nos fijamos bien, vemos que cuando sustituimos o equipamos, llamamos          a **itemObtenidoProcesado**. Esta variable, puede ser Arma o Armadura, pero puede ser cambiado por Item y el código seguiría funcionando.
      

   - **ISP** **I**nterface Segregation Principle

        El ISP nos indica que no debemos hacer a los usuarios del programa depender de interfaces que ellos no van a utilizar. Es decir, un Item es Equipable, Sustituible y Guardable, sin embargo, no es            Jugable o Comprobable. Con esto quiero llegar a que no podemos hacer depende un Item de interfaces que no aportan nada a su funcionamiento y solo complican el código y su comprensión.

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/items/Item.kt#L45-L58

https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Juego.kt#L10-L17

   - **DIP** **D**ependency Inversion Principle

     El DIP dice que debemos hacer a nuestro código depender de abstracciones, no de instancias, de esta manera los detalles son los que tienen que depender de las abstracciones, no al revés.
     En mi código desconozco si hay algún ejemplo claro del **DIP** pero, considero que **GestorEntrada** y **GestorConsola** son ejemplos muy cercanos a lo que propone este principio.
     
     *MOSTRABLE*
     https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Mostrable.kt#L7-L34

     *GESTOR CONSOLA*
     https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorConsola.kt#L15-L74

     *INTRODUCIBLE*
     https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/Introducible.kt#L3-L21

     *GESTOR ENTRADA*
     https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/033590c71a1d34cf1fcb1df7f108e9db580ea6d5/src/main/kotlin/juego/GestorEntrada.kt#L5-L31

     
#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?

     *ESPECIFICACIÓN*
  
  En cuanto a herencias de especificación en mi proyecto tenemos varias, **GestorConsola** con **Mostrable**, **GestorEntrada** con **Introducible** o incluso el **Companion Object de Item** con **GestorItem**.
  Un ejemplo de lo anterior es el siguiente:
  
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/items/Item.kt#L16-L44
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/items/Item.kt#L69-L140
  
     *ESPECIALIZACIÓN*
  
  En el apartado de especialización tenemos las 6 más intuitivas, **Arma** y **Armadura** con **Item** y las **3 clases de personaje** con **personaje**. Esto quiere decir que cada una hereda los métodos y las
  propiedades de la superclase.
  **PERSONAJE**
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/personajes/Personaje.kt#L4-L19
  
  **CLASES DE PERSONAJES**
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/personajes/Hunter.kt#L4-L17
  
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/personajes/Warlock.kt#L4-L17

  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/personajes/Titan.kt#L4-L17
  
     *EXTENSIÓN*
  
  El único ejemplo de herencia de extensión es **GestionJuego** con **Juego**. En esta herencia, **GestionJuego** añade funcionalidades a **Juego** sin alterar el funcionamiento de este en la mayoria de            ocasiones.
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/juego/Juego.kt#L10-L22
  
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/juego/GestionJuego.kt#L13-L20
  
     *CONSTRUCCIÓN*
  
  En cuanto a herencias de construcción, no he empleado ninguna, pero se trata de usar una clase como base para otra aunque no tengan nada que ver. El ejemplo mas claro lo tenemos con las **listas** y las 
  **pilas**. Una **lista** tiene la misma funcionalidad que una **pila** ya que ambas guardan datos. Sin embargo, **pila** los almacena de forma distinta a **lista** y aun así **puede emplear los métodos que 
  hereda de lista añadiendoles restricciones o funcionalidades acorde a su propósito**.

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.k)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.
  
  Para el proyecto he decidio usar la libreria ***MORDANT***, que me permite añadir un toque estético a la consola, haciendo que la información que se muestra aparezca más atractiva. Además, ayuda a la estética 
  del proyecto haciendo que sea mas agradable para la vista.
  Para incorporarlas, hubo que hacer un par de modificaciones a las dependencias:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/build.gradle.kts#L11-L17
  Y una vez esté incorporada solo hay que importar lo necesario. Podemos ver su uso y su potencial en el objeto **GestorConsola** donde instanciamos una terminal con propiedades de Mordant:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/juego/GestorConsola.kt#L2-L28
  Tambien cuenta con potencial para crear tablas:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/juego/GestorConsola.kt#L302-L331
  O crear animaciones:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/69d4e5a648a1ea1ffe6556ff5952aa94dae25624/src/main/kotlin/animaciones/AnimationManager.kt#L2-L35

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?
  
  La única forma de comprobar que funciona es ponerlo a prueba. En mi caso he realizado una especie de prueba del ciego, y he solicitado a gente sin conocimientos de programación que leyendo la descripcion de una función me dijeran como creían que iba a ser el 
  contenido de esta y viceversa. Puesto que las propias funciones tienen comentarios internos en caso de que sean necesarios para su comprensión y depuración.
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/6e380a6d21f025fa2d10191192febc52714dc28d/src/main/kotlin/juego/GestionJuego.kt#L96-L119

  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/6e380a6d21f025fa2d10191192febc52714dc28d/src/main/kotlin/juego/GestionJuego.kt#L313-L323

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?
  En el proyecto he realizado varios usos de los genéricos, algunos de ellos son los siguientes:
  https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-rmungar/blob/6e380a6d21f025fa2d10191192febc52714dc28d/src/main/kotlin/items/E_S_G.kt#L4-L36

  Las tres interfaces anteriores hacen uso de los genericos para permitirme llevar a cabo sus funciones sin importar el tipo de dato que estos van a tomar mediante los parámetros genéricos **T**, **E** y **A**. En cuanto a las clases con genéricos, no tengo 
  ninguna en mi código. Las clases genéricas no difieren mucho de las interfaces genéricas. Se las pasa el parámetro genérico y cuando se quiera instanciar la clase, se le pasa el tipo del parámetro que se va a emplear en los métodos de la función.
  
