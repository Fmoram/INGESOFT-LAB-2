
## Taller 

**Asignatura:** Ingeniería de Software II  
**Universidad Nacional de Colombia — Sede Bogotá**  
**Fabian David Mora Martinez**  
**Año:** 2026  
**Lenguaje:** Java

---

# Bloque 1 — Diagnóstico

## 1.1 Tabla de hallazgos

| Clase / método | Letra | Evidencia en el código | Consecuencia para el banco o cliente |
|---|---|---|---|
| CDT - RETIRAR| L | El código hereda un método que lanza una excepción | Si se mezclan cuentas para retirar dinero y aparece un CDT, el sistema explota.
|Transaction Service - TRANSFERIR | O | Se encuentra el uso de un SWITCH para identificar a donde va el dinero | Si la empresa quiere adicionar tipos de transferencia, se tiene que tocar directamente la clase para ello. 
|Transaction Service - TRANSFERIR | D | Se hace llamado estricto a repositorio oracle y sms Gateway | Solo se pueden usar estos proveedores por el momento, pero en caso de que se desee cambiar, el cambio en código seria mucho mas significativo que de lo necesario.  
|Transaction Service - TRANSFERIR | S | Se tienen demasiadas responsabilidades por las cuales se podrían realizar cambios | Si el ticket que lanza la transferencia cambia de formato, se tiene que cambiar una clase super importante. 
| Cobro Cuota Manejo - CobrarMensual | L | Se tiene como argumento la clase CUENTA | En el código actual, la clase CUENTA tiene hijos que no permiten el retiro de dinero. 
| ProductoBancario | I | se delegan muchas tareas a esta interfaz que no son usadas por aquellas clases que la implementan | Un futuro cambio en las funcionalidades de esta interfaz haría que tengamos que cambiar todas las clases que la implementen también. 

## 1.2 Experimentos

### Experimento 1 — El CDT

### Experimento 2 — La prueba imposible

## 1.3 Medición "antes"

| Métrica | Antes |
|---|---|
| Líneas del método `transferir` | 37 |
| Número de razones distintas por las que `TransaccionService` podría cambiar | 3 |
| Clases concretas que `TransaccionService` crea con `new` | 2 |
| Métodos vacíos o que lanzan excepción por "no aplica" | 4 |
| ¿Se puede probar `transferir` sin Oracle ni SMS? | No |

## SALIDA ORIGINAL DEL CODIGO 

![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/inicio%20.png
)
https://github.com/Fmoram/INGESOFT-LAB-2/blob/5d6bc569f4ce2b769689452c7d0d13ad5444be64/1.png\

## PRUEBA CDT
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/prueba%20CDT%20.png
)
---

# Bloque 2 — Refactorización

## Punto de control S

### Cambios realizados

Se delegaron las responsabilidades de la clase transaccion a otras clases con el fin de evitar cambios directos
### Pregunta de control
La clase como tal solo tiene 1 responsabilidad, las demas son de otras clases


## Punto de control O

### Cambios realizados
el switch se mantiene al ser la única forma de diferenciar tipos de envió, pero las asignaciones a comisiones se hacen por medio de clases exteras. 
### Pregunta de control
solo se modificaria el switch en terminos de creación de la variable, pero la nueva instancia e crea en una clase aparte. 


## Punto de control L

### Cambios realizados
CDT DEJA DE SER UNA CUENTA
### Pregunta de control
Ahora los problemas respecto a cdt pasan en compilación y no en ejecución, pues se diferencia una cuenta corriente de un CDT. El problema no se resuelve con try y catch porque CDT seguía siendo cuenta, y al tratarla como tal el programa se Moria.  




## Punto de control I

### Cambios realizados
Se separaron responsabilidades de la interfaz en otras interfaces. 
### Pregunta de control
Si se pudo lograr el generador de extractos para todas las clases que lo implementan, solo se necesitó 3 interfaces, y por clase se acoplaban las necesarias. 


## Punto de control D

### Cambios realizados
Ahora los repositorios  servicios de mensajería implementan si o si una interfaz, lo que nos permite abstraerlas y usar mas de un servicio en caso de ser necesario, pues el que escoge es el usuario. Si, y es posible la prueba.
### Pregunta de control



# Bloque 3 — Pruebas unitarias

## Prueba 1
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/1.png)
## Prueba 2
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/2.png)
## Prueba 3
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/3.png)
## Prueba 4
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/4.png)
## Prueba 5
![image alt](https://github.com/Fmoram/INGESOFT-LAB-2/blob/22842f4a3bb4e1ce66b2449dc53c0cc9164283ae/5.png)
## Pregunta de control
las pruebas tardaron entre 60 y 70 mls , mas que cambiar líneas de la clase, se procedió, con ayuda de la ia, a colocarlas todas en un solo archivo y correr las necesarias por consola. Estas mismas pruebas en el bloque 1 serian imposibles de realizar pues se tendría que haber cambiado el código fuente mas de 1 vez, a diferencia de este en el que ya tenemos todo segmentado.  

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

