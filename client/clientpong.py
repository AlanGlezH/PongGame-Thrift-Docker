import sys




sys.path.append("../thrift/gen-py")

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from pongservice import PongService
from pongservice.ttypes import NotPlaceAvaibleException
from pongservice.ttypes import Player
from pongservice.ttypes import Position



transport_endpoint = TSocket.TSocket("localhost", 9090)
transport = TTransport.TBufferedTransport(transport_endpoint)
protocol = TBinaryProtocol.TBinaryProtocol(transport)
client = PongService.Client(protocol)

transport.open()

# Constantes para la inicialización de la superficie de dibujo
VENTANA_HORI = 800  # Ancho de la ventana
VENTANA_VERT = 600  # Alto de la ventana
FPS = 60  # Fotogramas por segundo
BLANCO = (255, 255, 255)  # Color del fondo de la ventana (RGB)

try:
    idPlayer = client.JoinGame()
    print("[Cliente my ID]: " + str(idPlayer))
    while True:
        if client.StartGame() == True:
            print("Game started")
            while True:
                print("BallPosition: " + "X[" +str(client.GetBallPosition().X) + "] - Y[" + str(client.GetBallPosition().Y) + "]")
                player = Player(idPlayer,Position(10,20),5)
                client.UpdatePosition(player) 
                if idPlayer == 1:
                    print("Posicion player 2: " + str(client.GetEnemyPosition(2)))
                    
                else:
                    print("Posicion player 1: " + str(client.GetEnemyPosition(1)))

                scoreList = client.GetScore()
                print("Puntacion player 1: " + str(scoreList[0]))
                print("Puntacion player 2: " + str(scoreList[1]))

                
        else:
            print("Esperando jugador...")
except NotPlaceAvaibleException as e:
    print(e.message)



        





transport.close()