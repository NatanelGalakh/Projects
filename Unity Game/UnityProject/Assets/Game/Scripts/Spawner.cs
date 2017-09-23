
//The spawner spawns both lasers and energy. Because of this, there are different exceptions in the code which are used only by Energy.

using UnityEngine;
using System.Collections;

public class Spawner : MonoBehaviour {

	public Score counter;
	public GameObject toSpawn;
	public float test;
	public float spawnTime;

	void Start () {
		test = counter.score;
		InvokeRepeating("addItem", 0, spawnTime);
	}

	void addItem()
	{
		Renderer rd = GetComponent<Renderer>();

		//Used by the lasers to give them random starting positions.
		float x1 = transform.position.x - rd.bounds.size.x / 2;
		float x2 = transform.position.x + rd.bounds.size.x / 2;

		//Used by the energy to give it random starting positions.
		float y1 = transform.position.y - rd.bounds.size.y / 2;
		float y2 = transform.position.y + rd.bounds.size.y / 2;
		Vector2 spawnPoint;
		if (gameObject.tag == "Energy") {
			spawnPoint = new Vector2 (transform.position.x, Random.Range (y1, y2));
		} 
		else {
			spawnPoint = new Vector2 (Random.Range (x1, x2), transform.position.y);
		}

		//Instanciates the object at the selected random spawnpoint.
		Instantiate(toSpawn, spawnPoint, transform.rotation);
	}

	//This checks for updates in the score. Every time energy is picked up, a small portion of the spawnTime is removed. This increases the difficulty of the game as more energy is collected.
	//This is only used by the Laser spawners.
	void Update () {
		if (counter.score > test && spawnTime > 0.75f && gameObject.tag != "Energy") {
			spawnTime -= (0.05f );
			print ("spawnTime set to " + spawnTime);
			test = counter.score;
		}
	}
}
