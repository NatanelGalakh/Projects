//Accessed in the HowToPlay scene
using UnityEngine;
using System.Collections;

public class ControlScript : MonoBehaviour {

	//Returns to the Main Menu
	public void switchScene()
	{
		Application.LoadLevel ("Main Menu");
	}
}
