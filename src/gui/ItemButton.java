package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import application.Main;

public class ItemButton extends Button{
	private Item item;
	ItemButton(String itemName){
		// TODO complete the constructor
		super();
		this.setPadding(new Insets(5));
		this.item=new Item(itemName);
		Image image=new Image(this.item.getUrl());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(48);
		imageView.setFitWidth(48);
		this.setGraphic(imageView);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setTooltip();
		switch(itemName) {
		case "HorizontalBarricade" : this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=2;
				ItemButton now = (ItemButton)e.getSource();
				now.highlight();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action place barricade"
						: "Player 2 action place barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}

		});break;
		case "VerticalBarricade" : this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=1;
				ItemButton now = (ItemButton)e.getSource();
				now.highlight();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action place barricade"
						: "Player 2 action place barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}
			
		});break;
		case "Cancel" : this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=0;
				ItemPane.resetButtonsBackGroundColor();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action cancel"
						: "Player 2 action cancel";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}
			
		}); break;
		case "Home" :	this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.setScene(Main.primary,Main.scene1);
				ItemButton now = (ItemButton)e.getSource();
				Main.restart();
				String playermessage ="";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				//now.highlight();
			}
			
		}); break;
		case "Bomb" : 	this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=4;
				ItemButton now = (ItemButton)e.getSource();
				now.highlight();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action place bomb"
						: "Player 2 action place bomb";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}
			
		});break;
		case "Move" : 	this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=3;
				ItemButton now = (ItemButton)e.getSource();
				now.highlight();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action move"
						: "Player 2 action move";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}
			
		});break;
		case "RemoveBarricade" : 	this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				ItemPane.resetButtonsBackGroundColor();
				Main.gameActionNow=5;
				ItemButton now = (ItemButton)e.getSource();
				now.highlight();
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 action remove barricade"
						: "Player 2 action remove barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			}
			
		});break;
		
	}
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public Item getItem() {
		return item;
	}
	
	private void setTooltip() {
		Tooltip	tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(item.getItemName());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (item != null)
			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});		
	}
}
