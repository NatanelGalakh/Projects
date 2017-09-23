//Accessed in the Main Menu scene.
using UnityEngine;
using System.Collections;

public class Controller : MonoBehaviour {

	//Switches to HowToPlay, the instructions page.
	public void howToPlay()
	{
		Application.LoadLevel ("HowToPlay");
	}

	//Switches to GameScene, starting the game.
	public void startGame()
	{
		Application.LoadLevel ("GameScene");
	}

	//quits the game
	public void quitGame()
	{
		Application.Quit();
	}
}
