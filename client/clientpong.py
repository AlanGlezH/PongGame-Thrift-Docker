import sys


sys.path.append("../thrift/gen-py")

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from pongservice import PongService
from pongservice.ttypes import NotPlaceAvaibleException



transport_endpoint = TSocket.TSocket("localhost", 9090)
transport = TTransport.TBufferedTransport(transport_endpoint)
protocol = TBinaryProtocol.TBinaryProtocol(transport)
client = PongService.Client(protocol)

transport.open()

try:
    print("[Ciente my ID]: " + str(client.JoinGame()))
except NotPlaceAvaibleException as e:
    print(e.message)

if client.StartGame() == True:
    print("Game inited")
else:
    print("Not game inited")



transport.close()