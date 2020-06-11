struct Player{
    1: i32 IdPlayer;
    2: Position position;
    3: i32 score;
}

struct Position{
    1: i32 X;
    2: i32 Y;
}

exception ConnectionException{
    1: string message;
}
exception NotPlaceAvaibleException{
    1: string message;
}

service PongService{
    bool StartGame()
    i32 JoinGame() throws (1:NotPlaceAvaibleException ex)
    // Player GetEnemyPosition()
    // void UpdatePosition(1:Player player) 
    // Position GetBallPosition()
    // void HittingBall()
    // list<i32> GetScore()

}








