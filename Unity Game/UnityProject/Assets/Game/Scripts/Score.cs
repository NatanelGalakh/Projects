using UnityEngine;
using System.Collections;

//This allows to interact with UI elements. Here, it is used for "Text".
using UnityEngine.UI;

public class Score : MonoBehaviour {

	public int score;
	public Text scoreText;

	//This updates the scoreboard constantly.
	void Update () {
		scoreText.text = "Energy: " + score;
	}

	//This increases the score by one. It is called by the player class when an energy ball is picked up.
	public void increaseScore(){
		score++;
	}
}
