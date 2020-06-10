
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ServerPong {
    public static void main(String[] args) throws TTransportException {
        TServerSocket transporte = new TServerSocket(9090);
        TProcessor procesor = new PongService.Processor<>(new ServicePongHandler());
        TServer servidor = new TThreadPoolServer(new TThreadPoolServer.Args(transporte).processor(procesor));

        
        servidor.serve();


    }
}