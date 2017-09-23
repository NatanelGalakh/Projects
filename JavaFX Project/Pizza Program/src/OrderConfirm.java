import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OrderConfirm {
	
	public static void start(ToppingsList toppings, boolean[] isSelected, int length, String sauce, String price) throws Exception {
		
		//Creates a new stage
		Stage window = new Stage();
		window.setTitle("Confirmation");
		BorderPane layout = new BorderPane();
		
		//From what I understand, ListView is the JavaFX variant of Swing's Listbox.
		ListView<String> list = new ListView<String>();
		
		//set width and height of list.
		list.setPrefWidth(400);
		list.setPrefHeight(100);
		
		//creates a string that is to be addet to a label.
		String orderedItems = "Sauce:\n" +sauce + "\n\nToppings:";
		
		//cycles through toppings and uses the isSelected boolean array to determine which toppings are to be added to the list
		for(int i = 0; i < length; i++)
		{
			if (isSelected[i] == true)
			{
				list.getItems().add(toppings.getToppingName(i)); 
			}
		}
		//Labels print out info
		Label printOrder = new Label(orderedItems);
		Label printPrice = new Label(price);
		
		//textfield says "Enter Your Name" as a prompt. As soon as it is clicked, the prompt disappears.
		TextField name = new TextField();
		name.setPromptText("Enter Your Name");
		
		Label label = new Label();
		
		Button order = new Button("Order");
		//when the button is clicked, it takes the String you entered into the textfield and puts it into this label.
		order.setOnAction(e -> {
			label.setText("Thank you for your order " + name.getText());	
		});
		//Creates the grid.
		GridPane grid = new GridPane();
		layout.setCenter(grid);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);		
		
		//assigns items to the grid.
		GridPane.setConstraints(printOrder, 0,0);
		GridPane.setConstraints(list, 0,1);
		GridPane.setConstraints(printPrice, 0,2);
		GridPane.setConstraints(name, 0,3);
		GridPane.setConstraints(order, 0,4);
		GridPane.setConstraints(label, 0,5);
		grid.getChildren().addAll(list, printOrder, printPrice, name, order, label);
		
		Scene scene = new Scene(layout, 400, 500);
		window.setScene(scene);
		window.show();
	}

}
