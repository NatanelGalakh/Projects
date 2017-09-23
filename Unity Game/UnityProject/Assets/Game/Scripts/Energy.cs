using UnityEngine;
using System.Collections;

public class Energy : MonoBehaviour {

	private Rigidbody2D rgb;
	//Energy moves faster than lasers.
	public int speed = -10;

	void Start () {
		rgb = GetComponent<Rigidbody2D> ();
	}

	void FixedUpdate () {
		//Enables constant movement
		rgb.velocity = new Vector2 (speed, 0);
	}


	//when the object goes off screen, it is destroyed.
	void OnBecameInvisible()
	{
		Destroy(gameObject);
	}
}
