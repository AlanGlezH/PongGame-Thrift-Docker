import pygame
from connection import Connection, Player, Position

# class Player:
# 	def __init__(self, pos_x, pos_y, speed, forme=None):
# 		self.position_X = pos_x
# 		self.position_Y = pos_y
# 		self.speed = speed
# 		self.form = forme

class Ball:
	def __init__(self):
		self.position_X = 400
		self.position_Y = 300

def mainGame(conn: Connection, idclient: int):
	pygame.init()
	#Colores
	WHITE = (255, 255, 255)
	BLACK = (0, 0, 0)
	SCREEN_SIZE = (800, 600)
	PLAYER_PADDLE_WIDTH = 15
	PLAYER_PADDLE_HEIGHT = 90
	screen = pygame.display.set_mode(SCREEN_SIZE)
	clock = pygame.time.Clock()
	#Instancia del jugador 1
	if idclient == 1:
		playerOne = Player(idclient,Position(50,255),0,0)
		playerTwo = Player(2,Position(700,255),0,0)

	else:
		playerOne = Player(1,Position(50,255),0,0)
		playerTwo = Player(idclient,Position(700,255),0,0)
	# Coordenadas de la pelota
	ball = Ball()
	game_over = False


	while not game_over:
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				game_over = True
			
			if event.type == pygame.KEYDOWN:
				# Jugador 1
				if event.key == pygame.K_UP:
					if idclient == 1:
						playerOne.speed = -3
					else:
						playerTwo.speed = -3

				if event.key == pygame.K_DOWN:
					if idclient == 1:
						playerOne.speed = 3
					else:
						playerTwo.speed = 3

			if event.type == pygame.KEYUP:
				# Jugador 1
				if event.key == pygame.K_UP:
					if idclient == 1:
						playerOne.speed = 0
					else:
						playerTwo.speed = 0

				if event.key == pygame.K_DOWN:
					if idclient == 1:
						playerOne.speed = 0
					else:
						playerTwo.speed = 0


		# Modifica las coordenadas para dar mov. a los jugadores
		if idclient == 1:
			playerOne.position.Y += playerOne.speed
			conn.client.UpdatePosition(playerOne)
			posEnemy = conn.client.GetEnemyPosition(2)
			playerTwo.position.Y = posEnemy.Y
		else:
			playerTwo.position.Y += playerTwo.speed
			conn.client.UpdatePosition(playerTwo)
			posEnemy = conn.client.GetEnemyPosition(1)
			playerOne.position.Y = posEnemy.Y

		# Movimiento pelota
		position = conn.client.GetBallPosition()
		ball.position_X = position.X
		ball.position_Y = position.Y

		screen.fill(BLACK)
		#Zona de dibujo
		jugador1 = pygame.draw.rect(screen, WHITE, (playerOne.position.X, playerOne.position.Y, PLAYER_PADDLE_WIDTH, PLAYER_PADDLE_HEIGHT))
		jugador2 = pygame.draw.rect(screen, WHITE, (playerTwo.position.X, playerTwo.position.Y, PLAYER_PADDLE_WIDTH, PLAYER_PADDLE_HEIGHT))
		pelota = pygame.draw.circle(screen, WHITE, (ball.position_X, ball.position_Y), 10)
		# # Colisiones
		# if pelota.colliderect(jugador1) or pelota.colliderect(jugador2):
		# 	ball.speed_X *= -1
		pygame.display.flip()
		clock.tick(60)
	pygame.quit()


# if __name__ == "__main__":
# 	main()
