import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToppingsList {
	//Creates an ArrayList of Toppings objects.
	private ArrayList<Toppings> toppings;
	
	public ToppingsList(){
		this.toppings = new ArrayList<Toppings>();
	}
	
	//Loads in Strings from a file.
	public void loadFile() throws FileNotFoundException{

		File myFile = new File("toppings.txt");
		Scanner sc = new Scanner(myFile);
		
		//if for some reason there are already toppings in the ArrayList, this removes them all.
		for (int i = 0; i < toppings.size(); i++)
		{
			toppings.remove(i);
		}
		
		//This adds new toppings until the file is completely scanned.
		while(sc.hasNext())
		{
			 String line = sc.nextLine();
			 Toppings topping = new Toppings(line);
			 toppings.add(topping);
		}
		sc.close();
	}
	
	//A method which gets the names based on their number in the ArrayList.
	public String getToppingName(int n) {
		
		return toppings.get(n).getName();
	}
	
	//A method returns the length of the ArrayList.
	public int getListLength() {
		
		return toppings.size();
	}
}
