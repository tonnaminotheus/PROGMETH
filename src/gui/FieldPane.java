package gui;

import application.Main;
import entity.base.Entity;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
import logic.GameController;
import interact.Player;
import interact.WhiteTile;

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private static ObservableList<FieldCell> fieldCells = FXCollections.observableArrayList();
	
	public FieldPane() {
		
		super();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(17*40);
		this.setPrefHeight(17*40);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
	
	
	
	public static void setFieldCell(FieldCell e,FieldPane E) {
		//E.set(e, E);
		FadeTransition fade = new FadeTransition();
		fade.setNode(e);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setDuration(Duration.millis(3000));
		fade.play();
		E.fieldCells.set(e.getEntity().getX()*17+e.getEntity().getY(),e);
		E.getChildren().clear();
		//if(e.getEntity().isPlayer()) {
		//	E.add(fieldCells.get(e.getEntity().getX()*17+e.getEntity().getY()), e.getEntity().getY(), e.getEntity().getX());
		//}
		if(e.getEntity().isPlayer()) {
			E.add(fieldCells.get(e.getEntity().getX()*17+e.getEntity().getY()), e.getEntity().getY(), e.getEntity().getX());
		}
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
					if(e.getEntity().isPlayer()) {
						if(e.getEntity().getX()==i&&e.getEntity().getY()==j) {
							continue;
						}
					}
					E.add(fieldCells.get(i*17+j), j, i);
			}
		}
		//E.getChildren().set(e.getEntity().getY()*17+e.getEntity().getX(), E);
		//fieldCells.notify();
	}
	
	
	
	public static FieldCell findPlayer(int side) {
		for(int i=0;i<fieldCells.size();i++) {
			Entity e =fieldCells.get(i).getEntity();
			if(e.isPlayer()) {
				if(((Player)e).getSide()==side) {
					return fieldCells.get(i);
				}
			}
		}
		return null;
	}
	
	public static FieldCell getFieldCell(Entity E) {
		for(int i=0;i<fieldCells.size();i++) {
			Entity e =fieldCells.get(i).getEntity();
			if(e.getX()==E.getX()&&e.getY()==E.getY()) {
				return fieldCells.get(i);
			}
		}
		return null;
	}
}



