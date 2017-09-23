using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class Laser : MonoBehaviour {

	private Rigidbody2D rgb;
	//killTest is semi-linked to isRed from the player class. killTest determines whether or not the player will be killed.
	public bool killTest;
	public int speed = -5;

	void Start () {
		rgb = GetComponent<Rigidbody2D> ();
	}

	void FixedUpdate () {
		rgb.velocity = new Vector2 (speed, 0);
	}

	//Object is destroyed if it goes off screen
	void OnBecameInvisible()
	{
		Destroy(gameObject);
	}

	//This kills the player when the player enters the hitbox, but only on certain conditions.
	void OnTriggerEnter2D(Collider2D other)
	{
		//The killTest is used to determine whether or not a laser should affect the player. Different states of the killTest correspond to different colours of the laser.
		//The laser looks at both the player's tag and the killTest to see if the player must be killed.
		if (other.gameObject.tag == "PlayerBlue" && killTest == true) {
			Destroy (other.gameObject);

			//Upon the player's death, they are booted back to the main menu.
			Application.LoadLevel("Main Menu");
		}

		else if (other.gameObject.tag == "PlayerRed" && killTest == false){
			Destroy (other.gameObject);
			Application.LoadLevel("Main Menu");
		}
	}

	//If a player changes colours mid laser, without this, they would survive. With this, even if they are already in the laser, they will suffer the same punishment as entering a laser.
	void OnTriggerStay2D(Collider2D other)
	{
		if (other.gameObject.tag == "PlayerBlue" && killTest == true) {
			Destroy (other.gameObject);
			Application.LoadLevel("Main Menu");
		}

		else if (other.gameObject.tag == "PlayerRed" && killTest == false){
			Destroy (other.gameObject);
			Application.LoadLevel("Main Menu");
		}
	}
}
