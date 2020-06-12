
import java.util.ArrayList;
import java.util.List;



import org.apache.thrift.TException;

public class ServicePongHandler implements PongService.Iface {
  public static boolean isGameRunning;
  public static List<Player> playerList;
  private final int BALL_INITIAL_POSITION_X = 400;
  private final int BALL_INITIAL_POSITION_Y = 400;
  private final int BALL_INITIAL_SPEED_X = 3;
  private final int BALL_INITIAL_SPEED_Y = 3;
  private final String RIGHT_ORIENTATION = "RIGHT";
  private final String LEFT_ORIENTATION = "LEFT";
  public static Ball ballGame;
  public static String orientationBall;
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
          Thread.sleep(4000);
        } catch (Exception e) {
          e.printStackTrace();

        }
      }
    }
    
  });

  public ServicePongHandler(){
    ballGame = new Ball(new Position(),0,0);
    playerList = new ArrayList<Player>();
    orientationBall = RIGHT_ORIENTATION;
    isGameRunning = false;
  }

public void ResetBallPosition(){  
  ballGame.getPosition().setX(BALL_INITIAL_POSITION_Y);
  ballGame.getPosition().setX(BALL_INITIAL_POSITION_X);
  ballGame.setSpeedOnX(BALL_INITIAL_SPEED_X);
  ballGame.setSpeedOnY(BALL_INITIAL_SPEED_Y);  
}

  @Override
  public boolean StartGame() throws TException {
    boolean result;
    if (playerList.size() == 2) {
      System.out.println("Jugador 2 en linea");
      isGameRunning = true;
      ResetBallPosition();
      System.out.println("Iniciando movimiento de pelota");

      if(!threadBallMovement.isAlive()){
        threadBallMovement.start();
      }
      
      System.out.println("Entro hilo");
      result = true;
    } else {
      System.out.println("Esperando jugador para inicar el juego");
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

    @Override
    public Position GetBallPosition() throws TException {

     return ballGame.getPosition();
    }

    @Override
    public void UpdatePosition(Player player) throws TException {
      System.out.println("Posicion jugador ID[" + player.getIdPlayer()+ "] "+ 
      "X[" + player.getPosition().getX() + "]-" +"[Y"+ player.getPosition().getY()+ "]" );  
      
      for (Player playerAux : playerList) {
        if(playerAux.getIdPlayer() == player.getIdPlayer()){
          playerAux.setPosition(player.getPosition());
          //Agrego el score para probar, pero lo tendria que calcular el servidor
          playerAux.setScore(player.getScore());
          System.out.println("Posicion actualizada" );
        }
        
      }
    }


    @Override
     public Position GetEnemyPosition(int idPlayer) throws TException {
      for (Player playerAux : playerList) {
        if(playerAux.getIdPlayer() == idPlayer ){
          return playerAux.getPosition();
        }

        
      }
      return null;
    }

    @Override
    public List<Integer> GetScore() throws TException {
      System.out.println("Score entrando");
      int scorePlayerOne = playerList.get(0).getScore();
      System.out.println("Score entrando" + playerList.get(1).getScore());
      int scorePlayerTwo = playerList.get(1).getScore();
      List<Integer> arrayScore = new ArrayList<Integer>();
      arrayScore.add(scorePlayerOne);
      arrayScore.add(scorePlayerTwo);
      System.out.println("Score saliendo");



    return arrayScore;
    }


  }


  

 

  // @Override
  // public void HittingBall() throws TException {

  // return;
  // }

  // @Override
  // public ArrayList<int> GetScore() throws TException {

  // return new ArrayList<int>();
  // }



