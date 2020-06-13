import sys, os
from connection import Connection

def _print_welcome():
    print("JUEGO PONG DISTRIBUIDO")
    print("*" * 50)
    print("* ¿Quieres jugar?")
    print("* [I]niciar a juego")
    print("* [S]alir del sistema")
    print("*" * 50)


def _startGame():
    conn = Connection()
    conn.open()
    idPlayer = conn.client.JoinGame()
    print("Jugador ID: " + str(idPlayer))



if __name__ == "__main__":
    _print_welcome()
    command = input("Selección: ")
    command = command.upper()

    if command == 'I':
        print("Inicializando juego...")
        #_startGame()
    elif command == 'S':
        print("Hasta la proxima...")
    else:
        print("Opción no válida")
