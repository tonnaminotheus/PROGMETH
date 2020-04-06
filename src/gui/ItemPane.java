package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class ItemPane extends GridPane{
	private static ObservableList<ItemButton> itemButtonList = FXCollections.observableArrayList();

	public ItemPane() {
		// TODO implements the ItemPane's constructor
		super();
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		this.itemButtonList.add(new ItemButton("HorizontalBarricade"));
		this.itemButtonList.add(new ItemButton("VerticalBarricade"));
		this.itemButtonList.add(new ItemButton("Cancel"));
		this.itemButtonList.add(new ItemButton("Home"));
		this.itemButtonList.add(new ItemButton("Bomb"));
		this.itemButtonList.add(new ItemButton("Move"));
		this.itemButtonList.add(new ItemButton("RemoveBarricade"));
		
		this.addRow(0, itemButtonList.get(0), itemButtonList.get(1), itemButtonList.get(2));
		this.addRow(1, itemButtonList.get(6), itemButtonList.get(4), itemButtonList.get(5));
		this.addRow(2, itemButtonList.get(3));
	}

	public void setSelectedButton(ItemButton selecteditemButton) {
		resetButtonsBackGroundColor();
		selecteditemButton.highlight();
	}

	public static void resetButtonsBackGroundColor() {
		for(ItemButton itemButton : itemButtonList) {
			itemButton.unhighlight();
		}
	}
}
