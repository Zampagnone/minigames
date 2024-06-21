from operator import contains
from random import randrange
from pynput import keyboard
import os
from threading import Timer

dir = "right"

def printField(field):
    line = ""
    print("____________")
    for arr in field:
        for i in arr:
            line += i;
        print("|" + line + "|")
        line = ""
    print("------------")

def addApple(field, points):
    selected = False
    length = len(field)
    while not selected:
        x = randrange(0, length)
        y = randrange(0, length)

        if points >= length*length-6:
            return field
        if field[y][x] != "s" and field[y][x] != "a":
            selected = True
    field[y][x] = "a"
    return field

def shiftSnake(snake, newPos):
    for j in snake:
        lastPos = j

    snake = snake[-1:] + snake[:-1]
    
    snake[0] = newPos
    
    return snake, lastPos

def updateField(field, snake, currentPos, newPos, points):
    snake, lastPos = shiftSnake(snake, newPos)

    if newPos[0] <= -1 or newPos[0] >= len(field) or newPos[1] <= -1 or newPos[1] >= len(field):
        return field, points, snake, True

    if field[newPos[1]][newPos[0]] == "s":
        return field, points, snake, True

    if field[newPos[1]][newPos[0]] == "a":
        snake.append(lastPos)
        points += 1
        field = addApple(field, points)
    else:
        field[lastPos[1]][lastPos[0]] = " "
    
    for i in snake:
        field[i[1]][i[0]] = "s"
    return field, points, snake, False

def move(pos, dir):
    x = pos[0]
    y = pos[1]
    if dir == "up":
        pos = [x, y-1]
    elif dir == "down":
        pos = [x, y+1]
    elif dir == "left":
        pos = [x-1, y]
    elif dir == "right":
        pos = [x+1, y]
    return pos

def checkWin(points, completed):
    if points == (len(field)*len(field))-1:
        completed = True
        print("You win!")
    return completed


def on_press(key):
    global dir
    if key == keyboard.Key.esc:
        return False  # stop listener
    try:
        k = key.char  # single-char keys
    except:
        k = key.name  # other keys
    if k == 'w':  # keys of interest
        dir = "up"
        return 0
    elif k == 'a':
        dir = "left"
        return 0
    elif k == 's':
        dir = "down"
        return 0
    elif k == 'd':
        dir = "right"
        return 0

field = [
    ["s"," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "],
    [" "," "," "," "," "," "," "," "," "," "]
]
points = 0;

for i in range(5):
    field = addApple(field, points)

currentPos = [0, 0]
snake = [
    [0, 0]
]
completed = False

while(not completed):
    os.system('clear')
    printField(field)
    print("points: " + str(points))

    listener = keyboard.Listener(on_press=on_press)
    listener.start()
    Timer(1, listener.stop).start()
    listener.join()
    
    newPos = move(currentPos, dir)

    field, points, snake, completed = updateField(field, snake, currentPos, newPos, points)

    snake[0] = newPos
    currentPos = newPos
    if not completed:
        completed = checkWin(field, completed)

print("Game over, points: " + str(points))
