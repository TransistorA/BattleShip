Team Name: Team
Members: Joseph DiPalma, Annan Miao, and Ben Xu

This project is a graphical implementation of the board game Battleship. There two different modes. One mode is establish a connection between users. Another one is playing against an AI.

Main class to run in order to start the program to play over network:
GameStartView

Main class to run in order to start the program to play against an AI:
Main.java inside the GUI_AI package.

Main code for the program are the following classes:
Player
Board
Ship

The networking part of the program are the following classes:
Server
Client

The following classes encapsulates the interactions for Player, Board, and Ship plus all the GUI related code:
Controller
Model
View 

Limitation of networking:
We don't have enough knowledge to know the require conditions to ensure two computers can successful using the server/client design. Currently, we manage to get it working by allowing a wifi network to connect to a wired network on campus. Also, the implementation currently does not share the single model class over the network, so the game currently does not work properly over the network even the connection is established.
