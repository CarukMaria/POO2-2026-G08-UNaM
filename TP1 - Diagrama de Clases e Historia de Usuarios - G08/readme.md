# TP1 - Diagrama de Clases e Historias de Usuario (G08)

**Cátedra:** Programación Orientada a Objetos II (POO2) — UNaM (2026)  
**Grupo:** G08

---

## Arquitectura y Decisiones de Diseño

El modelo de dominio del sistema académico se estructuró sobre **tres pilares principales** (**Persona**, **Inscripción** y **Edilicio**), aplicando principios de diseño orientado a objetos para garantizar cohesión, bajo acoplamiento y extensibilidad:

> *"Se separó el dominio en 3 pilares, aplicando herencia donde existe comportamiento compartido (`Persona`, `Inscripcion`, `EspacioFisico`), asociación donde la herencia introducía rigidez o doble herencia (`TipoAula`, `TipoRol`), interfaces para segregar las responsabilidades de cada actor, y clases intermedias para alojar los atributos propios de cada relación (`estado`, `nota`)."*

---

## 1. Pilar Persona (Actores e Interfaces)

| Elemento en el Diagrama | Decisión y Concepto POO | Justificación en el Dominio |
| :--- | :--- | :--- |
| `Persona` *(abstracta)* → `Alumno`, `Docente`, `NoDocente` | **Herencia y Generalización** | Centraliza atributos comunes (`dni`, `nombre`, `apellido`, `email`) y comportamiento (`getNombreCompleto()`). Es abstracta porque en el sistema nunca se instancia una "persona genérica", siempre cumple un rol específico. |
| `NoDocente` → `rol: TipoRol` *(enum)* | **Atributo tipado vs. Subclases** | `BEDELIA`, `ADMINISTRACION`, `BIBLIOTECA` e `INVESTIGADOR` comparten exactamente el mismo comportamiento dentro del sistema. Cambia el dato del rol, no su lógica, evitando crear subclases innecesarias. |
| `IAlumno`, `IDocente`, `INoDocente` | **Interfaces / Segregación de tareas** | Define contratos explícitos de lo que cada actor puede ejecutar. Permite que el resto del sistema dependa de la abstracción (contrato) y no de la clase concreta. |
| `Docente` — *dicta* (`1..*` a `0..*`) → `Materia` | **Asociación** | Vincula a los docentes con las asignaturas que tienen asignadas, permitiendo consultar inscriptos y cargar calificaciones/estados. |

---

## 2. Pilar Edilicio (Espacios Físicos)

| Elemento en el Diagrama | Decisión y Concepto POO | Justificación en el Dominio |
| :--- | :--- | :--- |
| `EspacioFisico` | **Clase vs. Instancia (Objeto)** | Espacios como *Sala*, *Gerencia* o *Biblioteca* se registran como instancias de `EspacioFisico` (con `codigo`, `nombre`, `ubicacion`), ya que no requieren comportamiento propio en el alcance actual. |
| `Aula` *(hereda de `EspacioFisico`)* | **Abstracción enfocada en funcionalidad** | Es el único espacio que modela lógica operativa del sistema: posee `capacidad`, estado `habilitada` (`habilitar()`, `inhabilitar()`) y se asocia con `Materia` (*se dicta en*). |
| `Aula` — *es de tipo* (`0..*` a `1`) → `TipoAula` | **Composición/Asociación sobre Herencia** | Evita la explosión de subclases y problemas de herencia múltiple (ej. un aula que sea *Laboratorio* y *Auditorio* a la vez). Permite clasificar instancias como *Aula Magna*, *Laboratorio* o aulas comunes (*ARSO, Aula 2, Aula 3*) mediante datos (`nombre`, `equipamiento`). |

---

## 3. Pilar Inscripción (Cursadas y Exámenes)

| Elemento en el Diagrama | Decisión y Concepto POO | Justificación en el Dominio |
| :--- | :--- | :--- |
| `Inscripcion` *(abstracta)* → `InscripcionMateria`, `InscripcionExamen` | **Generalización** | Ambas inscripciones comparten estructura base (`fecha`), vinculan a un `Alumno` y registran el resultado o situación académica correspondiente. |
| `InscripcionMateria` (`estado: EstadoAcademico`) | **Clase intermedia (Atributos de relación)** | La condición (`CURSANDO`, `REGULAR`, `PROMOCIONADO`, `LIBRE`) no pertenece al `Alumno` ni a la `Materia` por separado, sino al vínculo entre ambos. Por ello `Alumno` se relaciona con `Materia` a través de la inscripción. |
| `InscripcionExamen` (`nota: int`) | **Clase intermedia** | La calificación (`registrarNota()`) es propia de la participación de un alumno puntual en una mesa específica. |
| `MesaExamen` ◇— `InscripcionExamen` (`1` a `0..*`) | **Agregación / Composición de detalle** | Cada mesa (`fecha`, `cupo`, `hayCupo()`, `descontarCupo()`) agrupa sus inscripciones como detalle, está vinculada a una `Materia` y es dada de alta por un `NoDocente`. |

---

## Alcance del Modelo

* **Incluido:** Gestión de usuarios por rol, alta de espacios físicos, administración de aulas, asignación docente, inscripciones a cursadas y mesas de examen con control de cupo y registro de notas/estados.
* **Fuera de alcance (iteraciones futuras):** Estados avanzados de mesas (*cancelada*, *postergada*) y estructura de *Planes de Estudio / Carreras*, omitidos intencionalmente según los requerimientos definidos para esta etapa.

---

## Archivos del Directorio

* `*.drawio` — Fuente editable del diagrama de clases.
