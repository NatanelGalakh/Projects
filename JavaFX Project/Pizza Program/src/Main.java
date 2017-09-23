import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{
	
	//This is a live counter of the price.
	private static double priceCounter = 8;
	
	private Stage window;
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Setting up the window.
		window = primaryStage;
		window.setTitle("Pizza");
		
		//By using BorderPane, I can place several layouts in a single scene. I used GridPane and VBox.
		BorderPane layout = new BorderPane();
		VBox vbox = new VBox();
		
		//Instantiates toppings, an object of the class ToppingsList which contains a list of toppings. 
		ToppingsList toppings = new ToppingsList();
		//Calls the loadFile method, which reads the toppings.txt file.
		toppings.loadFile();
		//easier to create a single int now than to constantly call toppings.getListLength() every time.
		int listLength = toppings.getListLength();
		
		boolean[] isSelected = new boolean[listLength];
		CheckBox[] add = new CheckBox[listLength];
		
		
		//This loads in an image.
		ImageView image = new ImageView();	
		Image img = new Image("Pizza.jpg");
		image.setImage(img);
		image.setFitWidth(300);
        image.setPreserveRatio(true);
       
		//Group to link the radio buttons.
		ToggleGroup sauce = new ToggleGroup();
		
		//Label for the Sauces category
		Label sauceLabel = new Label("Sauces");
		
		RadioButton ketchup = new RadioButton("Ketchup");
		ketchup.setToggleGroup(sauce);
		//ketchup is selected when the program starts.
		ketchup.setSelected(true);
		
		RadioButton bbq = new RadioButton("BBQ");
		bbq.setToggleGroup(sauce);
		
		RadioButton chilli = new RadioButton("Sweet Chilli");
		chilli.setToggleGroup(sauce);
		
		//The order button. Once pressed, it reads which radio button is selected and then starts up OrderConfirm.java.
		Button order = new Button("Order");
		order.setOnAction(e -> {
			String sauceName = "";
			if(ketchup.isSelected())
			{
				sauceName = "Ketchup";
			}
			
			else if(bbq.isSelected())
			{
				sauceName = "BBQ";
			}
			
			else if (chilli.isSelected())
			{
				sauceName = "Sweet Chilli";
			}
			
			try {
				OrderConfirm.start(toppings, isSelected, listLength, sauceName ,price());
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		//A live counter of the price. This text field is disabled and cannot be interacted with.
		TextField price = new TextField(price());
		price.setDisable(true);
		price.setPrefWidth(40);
		
		//This creates a list of checkboxes for the toppings. It goes through each topping in ToppingsList and adds it to a checkbox.
		for(int i = 0; i < listLength; i++)
		{
			add[i] = new CheckBox(toppings.getToppingName(i));
			int x = i;
			add[i].setOnAction(e -> {
			
				//If checked, price goes up.
				if(add[x].isSelected())
				{
					priceCounter++;
					price.setText(price());
					
					//sets the boolean which checks if each box is selected to true for this box.
					isSelected[x] = true;
				}
				
				//if unchecked, price goes down. 
				else
				{
					priceCounter--;
					price.setText(price());
			
					//sets the boolean which checks if each box is selected to false for this box.
					isSelected[x] = false;
				}
			});
			
			//unlike everything else, we're adding toppings to a VBox.
			vbox.getChildren().add(add[i]);
		}
		
		//Creates a new Grid
		GridPane grid = new GridPane();
		
		//adds grid to layout.
		layout.setCenter(grid);
		
		//adds vbox to layout.
		layout.setLeft(vbox);
		
		//sets grid parameters.
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		//sets the place of each item on the grid.
		GridPane.setConstraints(sauceLabel, 1,0);
		GridPane.setConstraints(ketchup, 1,1);
		GridPane.setConstraints(bbq, 2,1);
		GridPane.setConstraints(chilli, 3,1);
		GridPane.setConstraints(order, 1,5);
		GridPane.setConstraints(price, 2, 5);;
		GridPane.setConstraints(image, 5,5);
		
		//adds each item to the grid.
		grid.getChildren().addAll(sauceLabel, ketchup, bbq, chilli, order, price, image);
		
		//creates the scene.
		Scene scene = new Scene(layout, 750, 400);
		
		//Assigns scene to the window
		window.setScene(scene);
		window.show();
	}
	
	//used to make priceCounter have a euro symbol in front and 2 decimal places at the end to indicate cents.
	public String price()
	{
		return "€" + String.format("%.2f", priceCounter);
	}

}
