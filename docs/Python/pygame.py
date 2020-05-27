import pygame
pygame.init()

win = pygame.display.set_mode((500,500)) # Creating a window.

pygame.display.set_caption("First Game") # Setting the caption on the window.

x = 50
y = 50
width = 40
height = 60
vel = 5

run = True

# All pygame run in a loop and we keep refreshing the display with pygame.display.update()
while run:
    pygame.time.delay(10)

    for event in pygame.event.get(): # You can run a loop on pygame events
        if event.type == pygame.QUIT:
            run = False
   
    # Getting the keys pressed and then changing the position of the x and y co-ordinates
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT]:
        x -= vel
    if keys[pygame.K_RIGHT]:
        x += vel
    if keys[pygame.K_DOWN]:
        y += vel
    if keys[pygame.K_UP]:
        y -= vel

    
    # Mod x and y so that the rectangle does not jump off the screen
    x = x%500
    y = y%500

    pygame.draw.rect(win, (255, 0, 0), (x, y, width, height))
    pygame.display.update() # To display something in pygame you need to refresh the display or update the display
    win.fill((0,0,0)) # In the main loop after each refresh 

pygame.quit()
