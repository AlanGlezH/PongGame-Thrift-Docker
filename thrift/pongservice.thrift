struct Player{
    1: i32 IdPlayer;
    2: Position position;
    3: i32 speed;
    4: i32 score;
}

struct Ball{
    1: Position position;
    2: i32 speedOnY;
    3: i32 speedOnX;
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
    Position GetEnemyPosition(1: i32 idPlayer)
    void UpdatePosition(1:Player player) 
    Position GetBallPosition()
    void HittingBall(1: i32 idPlayer)
    list<i32> GetScore()
    i32 GetPlayerWinner()
    void ExitGame(1: i32 idPlayer)

}








