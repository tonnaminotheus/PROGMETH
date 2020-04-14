package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.GameController;

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private static ObservableList<FieldCell> fieldCells = FXCollections.observableArrayList();
	
	public FieldPane() {
		
		super();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(17*40);
		this.setPrefHeight(17*40);
		this.setBackground(new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
		//this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, 
		//		CornerRadii.EMPTY, BorderWidths.FULL)));
		//this.setVgap(5);
		setFieldPane(this);
	}
	
	public static void setFieldPane(FieldPane e) {
		fieldCells.clear();
		e.getChildren().clear();
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
					fieldCells.add(new FieldCell(GameController.gameMap.getEntity(i, j)));
					e.add(fieldCells.get(i*17+j), j, i);
			}
		}	
	}
}



