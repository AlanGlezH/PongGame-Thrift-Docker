
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import org.apache.thrift.TException;

public class ServicePongHandler implements PongService.Iface{
    public static boolean isGameRunning = false;
    public static  List<Player> playerList = new ArrayList<Player>();


    
    @Override
    public boolean StartGame() throws TException {
        
      return true;
    }

    @Override
    public int JoinGame() throws TException{
        if(playerList.size() == 2){
            NotPlaceAvaibleException ex = new NotPlaceAvaibleException();
            ex.message = "No hay lugar";
            throw ex;
        }
        Player newPlayer = new Player();
        newPlayer.IdPlayer = (playerList.size() + 1);
        playerList.add(newPlayer);
        System.out.println("New player: " +  newPlayer.IdPlayer);
        
      return newPlayer.IdPlayer;
    }

    // @Override
    // public Player GetEnemyPosition() throws TException {

        
    //   return new Player();
    // }

    // @Override
    // public void UpdatePosition(Player player) throws TException {

        
    //   return;
    // }

    // @Override
    // public Position GetBallPosition() throws TException {
    
    //   return new Position();
    // }


    // @Override
    // public void HittingBall() throws TException {

        
    //   return;
    // }

    // @Override
    // public ArrayList<int> GetScore() throws TException {

        
    //   return new ArrayList<int>();
    // }





}