# Wordle GUI Game

Our Java-based Wordle game, developed in NetBeans 14 and compatible with Java 11, recreates the popular word-guessing experience within an interactive GUI. Players have six attempts to guess a random secret word, enhancing the classic gameplay with an engaging visual interface. This project incorporates an embedded database using Apache Derby, allowing for the saving and reviewing of game and player histories, making it easy to track past games and scores. We also utilized the JUnit framework for testing.

## Authors
- [nooswa](https://github.com/nooswa)
- [larissagoh](https://github.com/larissagoh)

## Features
- **Interactive GUI**: Experience smooth and engaging gameplay through an intuitive graphical user interface.
- **Hint System**: Get feedback on your guesses with color-coded hints:
  - **Green** indicates the correct letter in the correct position.
  - **Yellow** shows a correct letter in the wrong position.
  - **Grey** denotes an incorrect letter.
- **Score and History Tracking**: Game scores are saved, allowing players to review their past games through an embedded history file.
- **Play Again Option**: Easily restart the game after each round.
- **Sign-out Option**: Allow for the option to sign out after, before and during each round.
- **Support for Game Rules**: Input validation, hint display, and limits on incorrect guesses ensure a fair gaming experience.

## Demo
[![Wordle Game Demo](./assets/DemoForReadMe.gif)](https://vimeo.com/1025327007?share=copy)

## Build

### Clone the Repository
To clone this project to your local machine, use:
```bash
git clone https://github.com/nooswa/pdc_project_2.git

