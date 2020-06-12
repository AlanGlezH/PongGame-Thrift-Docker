
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class ServerPong {
    public static void main(String[] args) throws TTransportException {

        TServerSocket transporte = new  TServerSocket(9090);

        ServicePongHandler servicePongHandler = new ServicePongHandler();
        TProcessor procesor = new PongService.Processor<>(servicePongHandler);
        TServer servidor = new TThreadPoolServer(new TThreadPoolServer.Args(transporte).processor(procesor));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        
                    }
                }
            }
        });

        
        
        
        servidor.serve();

 


    }
}