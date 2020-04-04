package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class ItemPane extends GridPane{
	private ObservableList<ItemButton> itemButtonList = FXCollections.observableArrayList();

	public ItemPane() {
		// TODO implements the ItemPane's constructor
		super();
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		System.out.println("5");
		this.itemButtonList.add(new ItemButton("HorizontalBarricade"));
		System.out.println("11");
		this.itemButtonList.add(new ItemButton("VerticalBarricade"));
		System.out.println("12");
		this.itemButtonList.add(new ItemButton("Cancel"));
		System.out.println("13");
		this.itemButtonList.add(new ItemButton("Home"));
		System.out.println("14");
		this.itemButtonList.add(new ItemButton("Bomb"));
		System.out.println("15");
		/*for (ItemButton itemButton : itemButtonList) {
			itemButton.setOnAction(e -> {
				if (SimulationManager.getMoney() >= itemButton.getItem().getPrice())
				setSelectedButton(itemButton);
			});
		}*/
		this.addRow(0, itemButtonList.get(0), itemButtonList.get(1), itemButtonList.get(2));
		this.addRow(1, itemButtonList.get(3), itemButtonList.get(4));
	}

	public void setSelectedButton(ItemButton selecteditemButton) {
		// TODO set selectedItemButton of SimulationManager to given ItemButton
		// resetButtonsBackgroundColor and the highlight the selecteditemButton
		resetButtonsBackGroundColor();
		selecteditemButton.highlight();
	}

	public void resetButtonsBackGroundColor() {
		// TODO unhighlight each button in itemButtonList
		for(ItemButton itemButton : itemButtonList) {
			itemButton.unhighlight();
		}
	}
}
