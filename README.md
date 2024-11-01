# LUCIANO MARQUESINI
# 🧬 Mutant Detector aplicación

## 🔗 Link Render
https://oneerparcialdesarollo.onrender.com


## Ejecución de la aplicación
Para ejecutar la aplicación tienes 2 opciones:
-Utilizar el link de render en postman( más lento)
-Descargar el proyecto ,ejecutarlo y colocar del local host en postman


### POST `/mutant`

Envía una secuencia de ADN en formato JSON para verificar si corresponde a un mutante. <br>
Esta secuencia es de un mutante <br>
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
} <br>
Esta secuencia es de un humano <br>
{
  "dna": ["ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"]
}<br>
Esta secuencia es debería devolver un error <br>
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTGG"]
} <br>

### GET `/stats`

Obtiene todas cuantos adn humanos y mutantes hay en la base de datos y saca el promedio de mutantes.<br>
Recomiendo ejecutarlo en el localhost ,luego de haber cargado varios ADN.






