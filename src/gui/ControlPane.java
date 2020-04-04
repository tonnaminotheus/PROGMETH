package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ControlPane extends VBox{

	private ItemPane itemPane;
	
	public ControlPane () {
		// TODO complete the ControlPane's constructor
		super();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);;
		this.setSpacing(15);
		this.setFillWidth(true);

		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		System.out.println("3");
		itemPane = new ItemPane();
		System.out.println("4");
		Label label = new Label("Please select action");
		label.setFont(new Font(14));
		
		this.getChildren().addAll(label, itemPane);		
	}

	
	public ItemPane getItemPane() {
		return itemPane;
	}
	
}
