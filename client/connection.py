import sys
sys.path.append("../thrift/gen-py")
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from pongservice import PongService
from pongservice.ttypes import NotPlaceAvaibleException
from pongservice.ttypes import Player
from pongservice.ttypes import Position


class Connection:
    def __init__(self):
        self.transport_endpoint = TSocket.TSocket("localhost", 9090)
        self.transport = TTransport.TBufferedTransport(self.transport_endpoint)
        self.protocol = TBinaryProtocol.TBinaryProtocol(self.transport)
        self.client = PongService.Client(self.protocol)

    def open(self):
        self.transport.open()

    def close(self):
        self.transport.close()