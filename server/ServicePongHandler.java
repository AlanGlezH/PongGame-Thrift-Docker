
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

public class ServicePongHandler implements PongService.Iface {
  public static boolean isGameRunning;
  public static List<Player> playerList;
  private final int BALL_INITIAL_POSITION_X = 400;
  private final int BALL_INITIAL_POSITION_Y = 300;
  private final int BALL_INITIAL_SPEED_X = 3;
  private final int BALL_INITIAL_SPEED_Y = 3;
  private final String RIGHT_ORIENTATION = "RIGHT";
  private final String LEFT_ORIENTATION = "LEFT";
  public static Ball ballGame;
  public static String orientationBall;
  public static int idPlayerWinner = -1;
  Thread threadBallMovement = new Thread(new Runnable() {
    @Override
    public void run() {
      while (isGameRunning) {
        try {
          if(isBallonLimitsOfY()){
            ballGame.setSpeedOnY(ballGame.getSpeedOnY() * -1);
          }

          if(isBallOnRightLimitX()){
            InvertBallPosition();
            AddScore(0);
          }

          if(isBallOnLeftLimitX()){
            InvertBallPosition();
            AddScore(1);

          }
          ballGame.getPosition().setX(ballGame.getPosition().getX() + ballGame.getSpeedOnX());
          ballGame.getPosition().setY(ballGame.getPosition().getY() + ballGame.getSpeedOnY());
          Thread.sleep(10);
        } catch (Exception e) {
          e.printStackTrace();

        }
      }
      System.out.println("Juego terminado, ganador Player:" + idPlayerWinner);
    }
    

  });

  public void AddScore(int idPlayer){
    playerList.get(idPlayer).setScore(playerList.get(idPlayer).getScore() + 1);
    if(isPlayerWinner(idPlayer)){
      isGameRunning = false;
      idPlayerWinner = idPlayer;
    }
  }

  public boolean isPlayerWinner(int idPlayer){
    if(playerList.get(idPlayer).getScore() == 5){
      return true;
    }else{
      return false;
    }
  }

  public boolean isBallOnLeftLimitX(){
    boolean result = false;
    if(ballGame.getPosition().getX() < 0){
      result = true;

    }

    return result;
  }

  public boolean isBallOnRightLimitX(){
    boolean result = false;
    if(ballGame.getPosition().getX() > 800){
      result = true;

    }
    return result;
  }
  

  public boolean isBallonLimitsOfY(){
    boolean result = false;
    if(ballGame.getPosition().getY() > 590 || ballGame.getPosition().getY() < 10){
      result = true;

    }
    return result;	
  }

  public ServicePongHandler() {
    ballGame = new Ball(new Position(), 0, 0);
    playerList = new ArrayList<Player>();
    orientationBall = RIGHT_ORIENTATION;
    isGameRunning = false;
  }

  public void InvertBallPosition(){
    ballGame.getPosition().setX(BALL_INITIAL_POSITION_X);
    ballGame.getPosition().setY(BALL_INITIAL_POSITION_Y);
    ballGame.setSpeedOnX(ballGame.getSpeedOnX() * -1);
    ballGame.setSpeedOnY(ballGame.getSpeedOnY() * -1);
  }

  public void ResetBallPosition() {
    ballGame.getPosition().setX(BALL_INITIAL_POSITION_X);
    ballGame.getPosition().setY(BALL_INITIAL_POSITION_Y);
    ballGame.setSpeedOnX(BALL_INITIAL_SPEED_X);
    ballGame.setSpeedOnY(BALL_INITIAL_SPEED_Y);

  }

  @Override
  public boolean StartGame() throws TException {
    boolean result;
    if (!isGameRunning) {
      if (playerList.size() == 2) {
        isGameRunning = true;
        ResetBallPosition();
        threadBallMovement.start();
        System.out.println("Juego iniciado");
        result = true;
      } else {
        System.out.println("Esperando jugador para inicar el juego");
        result = false;
      }
    }else{
      result = true;
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
    Position position;
    if(newPlayer.IdPlayer == 1){
      position = new Position(50,255);
    }else{
      position = new Position(700,255);
    }
    newPlayer.setPosition(position);
    playerList.add(newPlayer);

    return newPlayer.IdPlayer;
  }

  @Override
  public Position GetBallPosition() throws TException {

    return ballGame.getPosition();
  }

  @Override
  public void UpdatePosition(Player player) throws TException {

    playerList.get(player.getIdPlayer() - 1).setPosition(player.getPosition());

    
  }

  @Override
  public Position GetEnemyPosition(int idPlayer) throws TException {
    return playerList.get(idPlayer -1).getPosition();
  }

  @Override
  public List<Integer> GetScore() throws TException {
    return new ArrayList<Integer>(List.of(playerList.get(0).getScore(), playerList.get(1).getScore()));
  }

  @Override
  public void HittingBall(int idPlayer) throws TException {
    ballGame.setSpeedOnX(ballGame.getSpeedOnX() * -1);

  }

  @Override
  public int GetPlayerWinner() throws TException{
    return idPlayerWinner;
  }

  @Override
  public void ExitGame(int idPlayer) throws TException{
    if(idPlayer == 1){
      idPlayerWinner = 2;
    }else {
      idPlayerWinner = 1;
    }
    isGameRunning = false;
  }

 



}

  


