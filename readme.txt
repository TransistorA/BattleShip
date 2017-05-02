Team Name: Team
Team Members: Joseph DiPalma, Annan Miao, and Ben Xu


Introduction:
	Our project is a GUI based version of the classic board game Battleship.  This game contains two different modes.  The modes are two player multiplayer and playing against an AI.  Currently the two player multiplayer
	doesn't work because we didn't have enough knowledge of networking to properly connect the two computers.  We can only connect computers when one is on the Wi-Fi network and the other is on a wired network.  The problem
	with the current implementation is that it doesn't pass the Controller object over the network.  As a result, the Client and Server objects are not properly starting the game.  We tried to get this working before the
	deadline but unfortunately we were unable to figure out the correct way to share the Controller object over the network to synchronize the players.

Compiling and Executing the Program:
	Running the two player multiplayer:
		In order to play two player multiplayer, you have to run the GameStartView class in the root source folder.  Note that each player has to run this file on a computer and one computer has to be on a Wi-Fi network and
		the other has to be on a wired network.  One of the players needs to press the "Host Game" button in the window that appears on their computer and the other player needs to press the 
		"Connect To Opponent" button that appears on their computer.  For the user that presses the "Host Game" button, a window will appear that displays their IP address.  For the user that presses the
		"Connect To Opponent" button, a window will appear that contains a text entry box.  This player should enter the IP addess of the host player and then press the "Connect" button.  After this the two players will be 
		connected and they will be able to play the game.

	Running to play against the AI:
		In order to play against the AI, you need to run the Main class in the GUI_AI package.  After running this file a window will appear that allows you to place your ships and play against the AI.  

Critical Classes in the Project:
	The most critical classes to the functionality of this project are the following:
		- Player 
		- Board
		- Ship
	The classes related to the networking aspect of the project are the following:
		- Server
		- Client
	The following classes encapsulate the interactions between Player, Board, and Ship and create the GUI:
		- Model
		- View
		- Controller

Limitation of networking:
	We didn't have enough knowledge to know how to properly connect the two computers using the server/client design.  Currently, we managed to get it working by having one computer on a Wi-Fi network and 
	the other one on a wired network.  Additionally, the implementation currently doesn't share a single Controller object over the network so the game does not start even when the network connection
	is established.
