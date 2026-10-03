from pathlib import Path

readme = """# Laboratorio L2 — SOLID
## Taller integrador: el backend de Banco Andino

**Asignatura:** Ingeniería de Software II  
**Universidad Nacional de Colombia — Sede Bogotá**  
**Año:** 2026  
**Lenguaje:** Java

---

# Bloque 0 — Arranque

## 0.1 Preparación del proyecto

## 0.2 Salida original

## 0.3 Comprensión del flujo

---

# Bloque 1 — Diagnóstico

## 1.1 Tabla de hallazgos

| Clase / método | Letra | Evidencia en el código | Consecuencia para el banco o cliente |
|---|---|---|---|
| | | | |
| | | | |
| | | | |
| | | | |
| | | | |

## 1.2 Experimentos

### Experimento 1 — El CDT

### Experimento 2 — La prueba imposible

## 1.3 Medición "antes"

| Métrica | Antes |
|---|---|
| Líneas del método `transferir` | |
| Número de razones distintas por las que `TransaccionService` podría cambiar | |
| Clases concretas que `TransaccionService` crea con `new` | |
| Métodos vacíos o que lanzan excepción por "no aplica" | |
| ¿Se puede probar `transferir` sin Oracle ni SMS? | |

## 1.4 Diagrama de clases del código original

---

# Bloque 2 — Refactorización

## Punto de control S

### Cambios realizados

### Pregunta de control

### Commit

`control-S`

---

## Punto de control O

### Cambios realizados

### Pregunta de control

### Commit

`control-O`

---

## Punto de control L

### Cambios realizados

### Pregunta de control

### Commit

`control-L`

---

## Punto de control I

### Cambios realizados

### Pregunta de control

### Commit

`control-I`

---

## Punto de control D

### Cambios realizados

### Pregunta de control

### Commit

`control-D`

---

# Bloque 3 — Pruebas unitarias

## Prueba 1

## Prueba 2

## Prueba 3

## Prueba 4

## Prueba 5

## Pregunta de control

### Commit

`bloque-3-pruebas`

---

# Bloque 4 — Negocio pidió cambios

| Req. | Archivos a modificar en el código original | Archivos existentes modificados | Archivos nuevos | ¿Se rompió alguna prueba? |
|---|---|---|---|---|
| R1 | | | | |
| R2 | | | | |
| R3 | | | | |
| R4 | | | | |
| R5 | | | | |

## R1

### Commit

`req-1`

## R2

### Commit

`req-2`

## R3

### Commit

`req-3`

## R4

### Commit

`req-4`

## R5

### Commit

`req-5`

---

# Bloque 5 — Revisión cruzada

## Lista de revisión

| Criterio | Sí | No |
|---|:---:|:---:|
| Entendimos qué hace cada clase leyendo solo su nombre y sus métodos públicos. | | |
| Pudimos reutilizar piezas existentes sin copiar y pegar código. | | |
| Implementamos el requerimiento sin modificar la lógica de clases existentes. | | |
| No encontramos métodos vacíos ni que lancen "no aplica". | | |
| No encontramos `if/switch` por tipo que tuvimos que extender. | | |
| Las pruebas existentes siguieron pasando después de nuestro cambio. | | |
| No encontramos abstracciones innecesarias. | | |

## Lo mejor del diseño

## Lo que nos costó entender o extender

### Commit

`revision-cruzada`

---

# Bloque 6 — Cierre

## 6.1 Diagrama de clases final

## 6.2 Tabla comparativa

| Métrica | Antes | Después |
|---|---|---|
| Líneas del método `transferir` | | |
| Razones distintas por las que `TransaccionService` podría cambiar | | |
| Clases concretas que `TransaccionService` crea con `new` | | |
| Métodos vacíos o que lanzan "no aplica" | | |
| ¿Se puede probar `transferir` sin Oracle ni SMS? | | |
| Número total de archivos | | |
| Archivos existentes modificados en total en el bloque 4 | | |

## 6.3 Reflexión final

### a)

### b)

### c)

### d)

### e)

### Commit

`bloque-6-cierre`

---

# Entregables

- [ ] Código refactorizado.
- [ ] Pruebas unitarias.
- [ ] Cinco requerimientos del bloque 4.
- [ ] Historial de commits.
- [ ] Tabla de hallazgos.
- [ ] Resultados de los experimentos.
- [ ] Respuestas a las preguntas de control.
- [ ] Tabla del bloque 4.
- [ ] Tabla comparativa.
- [ ] Diagrama UML original.
- [ ] Diagrama UML final.
- [ ] ListaDO de revisión cruzada.
- [ ] Reflexión final.
"""
o`
path = Path("/mnt/data/README_cascara.md")
path.write_text(readme, encoding="utf-8")
print(path)

