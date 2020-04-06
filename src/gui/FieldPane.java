package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import logic.GameController;

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private static ObservableList<FieldCell> fieldCells = FXCollections.observableArrayList();
	
	public FieldPane() {
		
		super();
		this.setPrefWidth(17*35);
		this.setPrefHeight(17*35);
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



