import sys


sys.path.append("../thrift/gen-py")

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from pongservice import PongService


transport_endpoint = TSocket.TSocket("localhost", 9090)
transport = TTransport.TBufferedTransport(transport_endpoint)
protocol = TBinaryProtocol.TBinaryProtocol(transport)
client = PongService.Client(protocol)

transport.open()

print("[Ciente]: " + client.Welcome())

transport.close()