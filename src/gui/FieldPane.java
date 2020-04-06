package gui;

import java.util.ArrayList;

import entity.base.Entity;
import interact.BlackTile;
import interact.WhiteTile;
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
import logic.Cell;
import logic.GameController;
import logic.GameMap;

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private static ObservableList<FieldCell> fieldCells = FXCollections.observableArrayList();
	
	public FieldPane() {
		
		super();
		
		this.setPadding(new Insets(9));
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(500);
		this.setPrefHeight(500);
		//GameMap

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



