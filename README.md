# Desafío Técnico SAM Sistemas

## Desafío de prueba de automatización propuesto por la organización SAM Sistemas

### Descripción General

Prueba de automatización utilizando Selenium webdriver y TestNG en lenguaje Java. Dicha automatización fue realizada en la página web de la [empresa SAM Sistemas](https://www.samsistemas.com.ar/).
Puede observar la consigna pedida en el archivo adjunto [SAMDesafío técnico - Test automation-1.pdf](")
El tipo de pruebas realizadas fueron del tipo funcional, involucrando:

+ Botones habilitados correctamente
+ Links enviando a la dirección esperada
+ Lupa de búsqueda funcional
+ Información de footer correcta
+ Page number funcional

### Como desplegar

Asegúrese de tener Java 8+ instalado en su sistema, junto con los plugins y dependencias de TestNG y Selenium webdriver en su IDE de preferencia.

Para ejecutar el programa clone el repositorio en su dispositivo, mediante agregar carpeta al IDE de su elección o consola de comandos, 
diríjase a la siguiente dirección y ejecute el programa C:\Users\Gael\Desktop\Proyecto SAM\src\test\java\DesafioTecnicoSAM.java

### Información sobre implementación

En interpretación de la consigna tome una decisión de como abordarla, decidí utilizar TestNG para separar el test en 4 separados, lo cual garantizaría mejor control de errores y ordenamiento del código:
1. Test lupa de búsqueda
2. Test page numbers
3. Test apartado de contacto
4. Test footer

Cada Test posee descripciónes a lo largo del código para demostrar mi aprendizaje durante la investigación realizada en cumplimiento del desafío.

#### Tecnologías utilizadas
+java version "17.0.10" 2024-01-16 LTS
+Apache Maven 3.9.6
+Selenium webdriver 4.17.0
+TestNG 7.9.0

