
import org.apache.thrift.TException;
public class ServicePongHandler implements PongService.Iface{
    
    
    @Override
    public String Welcome() throws TException {
        return "Bienvenido";
    }



}