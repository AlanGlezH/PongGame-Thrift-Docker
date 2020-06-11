
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import org.apache.thrift.TException;

public class ServicePongHandler implements PongService.Iface {
  public static boolean isGameRunning = false;
  public static List<Player> playerList = new ArrayList<Player>();
  private final int BALL_INITIAL_POSITION_X = 400;
  private final int BALL_INITIAL_POSITION_Y = 400;
  private final int BALL_INITIAL_SPEED_X = 3;
  private final int BALL_INITIAL_SPEED_Y = 3;
  private final String RIGHT_ORIENTATION = "RIGHT";
  private final String LEFT_ORIENTATION = "LEFT";

  public static Ball ballGame = new Ball();
  public static String orientationBall = RIGHT_ORIENTATION;

  Thread threadBallMovement = new Thread(new Runnable() {
    @Override
    public void run() {
      while (isGameRunning) {
        try {
          if(orientationBall == RIGHT_ORIENTATION){
            ballGame.position.X += ballGame.speedOnX;
            ballGame.position.Y += ballGame.speedOnY;
            System.out.println("Ball: X[" + ballGame.position.X +"]");
            System.out.println("Ball: Y[" + ballGame.position.Y +"]");
          }
        } catch (Exception e) {
          e.printStackTrace();

        }
      }
    }
  });

public void ResetBallPosition(){
  ballGame.position.X = BALL_INITIAL_POSITION_X;
  ballGame.position.Y = BALL_INITIAL_POSITION_Y;
  ballGame.speedOnX = BALL_INITIAL_SPEED_X;
  ballGame.speedOnY = BALL_INITIAL_SPEED_Y;
}

  @Override
  public boolean StartGame() throws TException {
    boolean result;
    if (playerList.size() == 2) {
      System.out.println("Entro");
      isGameRunning = true;
      ResetBallPosition();
      System.out.println("Entro position");
      threadBallMovement.start();
      System.out.println("Entro hilo");
      result = true;
    } else {
      System.out.println("No Entro");
      result = false;
    }
    return result;
  }

  @Override
  public int JoinGame() throws TException {
    if (playerList.size() == 2) {
      NotPlaceAvaibleException ex = new NotPlaceAvaibleException();
      ex.message = "No hay lugar";
      throw ex;
    }
    Player newPlayer = new Player();
    newPlayer.IdPlayer = (playerList.size() + 1);
    playerList.add(newPlayer);
    System.out.println("New player: " + newPlayer.IdPlayer);

    return newPlayer.IdPlayer;
  }

  // @Override
  // public Player GetEnemyPosition() throws TException {

  // return new Player();
  // }

  // @Override
  // public void UpdatePosition(Player player) throws TException {

  // return;
  // }

  // @Override
  // public Position GetBallPosition() throws TException {

  // return new Position();
  // }

  // @Override
  // public void HittingBall() throws TException {

  // return;
  // }

  // @Override
  // public ArrayList<int> GetScore() throws TException {

  // return new ArrayList<int>();
  // }

}