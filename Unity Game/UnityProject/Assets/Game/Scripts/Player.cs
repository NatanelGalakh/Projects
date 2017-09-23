//Script used for the player
using UnityEngine;
using System.Collections;

public class Player : MonoBehaviour {

	Animator anim;
	private Rigidbody2D rgd;
	public int verticalSpeed;
	public Score counter;
	public bool isRed;

	void Start () {
		rgd = GetComponent<Rigidbody2D> ();
		anim = GetComponent<Animator> ();
	}

	void Update () {
		//This allows for vertical movement. verticalSpeed allows to change the player's movement speed.
		rgd.velocity = new Vector2 (0, Input.GetAxis ("Vertical") * verticalSpeed);

		//Space changes the boolean value "isRed". This links to the animation which changes the player's colour.
		//Pressing space will always make isRed the opposite to what it was before you pressed it.
		if (Input.GetKeyDown("space")) {
			if (isRed == false) {
				isRed = true;
			} 
			else {
				isRed = false;
			}

			//Pressing space also changes the player's tag. Since the player's tag is altered, it allows for different lasers to interact with the player in different ways.
			if (gameObject.tag == "PlayerRed") {
				transform.gameObject.tag = "PlayerBlue";
			} 
			else if (gameObject.tag == "PlayerBlue") {
				transform.gameObject.tag = "PlayerRed";
			}
		}

		//This sets the IsRed, the bool value in the animator equal to isRed, the value changed up above. This is how the colour changing actually happens. 
		anim.SetBool("IsRed", isRed);
	}

	//This allows the player to pick up energy. It calls the increasScore method in the Score class to add 1 point to the score every time the player touches an energy ball.
	void OnTriggerEnter2D(Collider2D other)
	{
		if (other.gameObject.tag == "Energy") {
			counter.increaseScore();
			Destroy(other.gameObject);
		}
	}
}
