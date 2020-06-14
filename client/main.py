import sys, os
sys.path.append("../thrift/gen-py")
from connection import Connection
from pongGame import mainGame
from pongservice.ttypes import NotPlaceAvaibleException
import time



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
    return conn


def _joinGame(conn: Connection):
    try:
        client = conn.client.JoinGame()
        return client
    except NotPlaceAvaibleException:
        return None


if __name__ == "__main__":
    _print_welcome()
    command = input("Selección: ")
    command = command.upper()
    

    if command == 'I':
        conn = _startGame()
        client = _joinGame(conn)
        if client != None:
            print("Inicializando juego...")
            while conn.client.StartGame() == False:
                print ("Esperando a un contrincante ...")
                time.sleep(2)

            mainGame(conn, client)
        else:
            print("No hay un juego disponible :c")
    elif command == 'S':
        print("Hasta la proxima...")
    else:
        print("Opción no válida")


