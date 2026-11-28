# Buscaminas

Ejecuta `ups.poo.buscaminas.aplicacion.AplicacionBuscaminas` en IntelliJ IDEA con el JDK 26 del proyecto.

## Cómo jugar

1. El juego empieza directamente con un tablero fijo de 10×10 y 10 minas.
2. Escribe una jugada directamente:

| Entrada | Acción |
| --- | --- |
| `A5` | Abrir la casilla A5. |
| `F A5` | Colocar o quitar una bandera en A5. |
| `0` | Salir. |

Las minúsculas también funcionan. Para abrir una casilla de la fila F, escribe
por ejemplo `F7`. Para marcarla, escribe `F F7`.

Símbolos del tablero: `#` oculta, `.` vacía, `F` bandera, `*` mina,
`1`–`8` cantidad de minas vecinas.

Ganas al abrir todas las casillas seguras y pierdes al abrir una mina.
Después puedes iniciar otra partida. Salir voluntariamente no cuenta como derrota.
Los resultados se acumulan durante la ejecución y se reinician al cerrar el programa.

## Simplificación visual

- Tablero compacto con letras y números alineados, y bordes solo por fuera.
- Una entrada por jugada; se eliminó el menú numérico en cada turno.
- Se eliminó la pregunta por el nombre y se acortaron los mensajes.
- Una sola línea muestra minas, jugadas y derrotas.

Todas las partidas tienen 10 filas (A–J) y 10 columnas (1–10), también al volver a
jugar. El tamaño no se puede cambiar. Se mantienen las banderas, las validaciones
y los estados de victoria/derrota. La primera jugada puede encontrar una mina.

## Comentarios, recursividad y excepciones

Los comentarios explican decisiones y reglas que no resultan evidentes; están en
MAYÚSCULAS. La expansión se realiza en `Tablero.descubrirRegionRecursivamente(casilla)`:

1. Se detiene si la casilla ya está abierta, tiene bandera o contiene una mina.
   El método compartido `obtenerVecinas` solo devuelve posiciones válidas.
2. Abre la casilla antes de continuar para no visitarla dos veces.
3. Si muestra un número, termina esa llamada.
4. Si no tiene minas alrededor, se llama a sí mismo para sus ocho vecinas.

`OpcionMenuInvalidaException` informa cuando el menú no reconoce un comando
como `3` o `hola`. El controlador muestra las opciones válidas y permite volver
a escribir. Una coordenada con formato válido pero fuera del tablero, como
`K1`, se maneja con `CoordenadaInvalidaException`.

## Organización del código

- `AplicacionBuscaminas` inicia la aplicación y separa el ciclo de partidas de la preparación.
- `EntradaConsola` lee comandos y `VistaConsola` dibuja el tablero.
- `BuscaminasControlador` separa lectura, ejecución y actualización de las jugadas.
- `CasillaSegura` representa una casilla sin mina, aunque contenga un número.
- `Tablero.obtenerVecinas` se reutiliza para contar minas y descubrir regiones.
- `Jugador.registrarResultado` unifica el registro de victorias y derrotas.

## Compilar y probar

Con el JDK en el PATH, desde PowerShell en la raíz del proyecto:

```powershell
$fuentes = (Get-ChildItem -Recurse src -Filter *.java).FullName
javac -encoding UTF-8 -Xlint:all -d out $fuentes
java -cp out ups.poo.buscaminas.aplicacion.AplicacionBuscaminas
```

Las pruebas no requieren bibliotecas externas:

```powershell
$pruebas = (Get-ChildItem -Recurse tests -Filter *.java).FullName
javac -encoding UTF-8 -Xlint:all -cp out -d out $pruebas
java -cp out ups.poo.buscaminas.pruebas.PruebasBuscaminas
```
