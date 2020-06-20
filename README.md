# PongGame-Thrift-Docker

Para iniciar el servidor del juego solo es necesario seguir los siguientes pasos:

1. Desde la raíz: docker build -t [nombrarImagen] -f server/dockerfile

2. docker run -i -d -p 9090:9090 [nombreImagen] para inicar el servidor en segundo plano en el puerto 9090.


Para poder utilizar el cliente es necesario hacerlo de manera manual debido a que está construido con python usando la biblioteca pygame y es necesario la conexión a la GUI para desplegarse en docker, no se puedo desplegar correctamente en el contenedor.

## Mac OS 

Pygame necesita ser instalado en la computadora si aún no se tiene, en este caso para sistemas operativos Mac OS se necesita realizar lo siguiente, usando el gestor de paquetes Homebrew: 

1. brew install sdl sdl_image sdl_mixer sdl_ttf portmidi
2. pip install pygame==2.0.0.dev6


## Ubuntu 
1. sudo apt-get update
2. sudo apt-get install python-pygame


Una vez que se instaló correctamente pygame, solo es necesario ir a la carpeta servidor y ejecutar el archivo main.py para iniciar.
