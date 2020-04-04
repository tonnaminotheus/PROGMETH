package gui;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

//You might need to do something to the following line
public class FieldCell extends Pane{
	
	private Item crop;
	private Tooltip tooltip;

	public FieldCell(int type) {
		// TODO	Complete the FieldCell's constructor
		if(type==1){
			this.setPrefWidth(48);
			this.setPrefHeight(48);
			this.setMinWidth(48);
			this.setMaxHeight(48);
			//this.setPadding(new Insets(8));
			this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setBackgroundSoilColor(1);
			this.setUpTooltip();	
		}
		else if(type==2) {
			this.setPrefWidth(24);
			this.setPrefHeight(48);
			this.setMinWidth(24);
			this.setMaxHeight(48);
			//this.setPadding(new Insets(8));
			this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setBackgroundSoilColor(2);
			this.setUpTooltip();
		}
		else if(type==3) {
			this.setPrefWidth(48);
			this.setPrefHeight(24);
			this.setMinWidth(48);
			this.setMaxHeight(24);
			//this.setPadding(new Insets(8));
			this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setBackgroundSoilColor(2);
			this.setUpTooltip();
		}else {
			this.setPrefWidth(24);
			this.setPrefHeight(24);
			this.setMinWidth(24);
			this.setMaxHeight(24);
			//this.setPadding(new Insets(8));
			this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setBackgroundSoilColor(2);
			this.setUpTooltip();
		}
		
		
		/*
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent  e) {
					// TODO Auto-generated method stub
					//onClickHandler();					
				}
		});*/
	}
	

	private void setBackgroundSoilColor(int type) {
		if(type==2)
			this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		else
			this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void setBackgroundSoilColor(Image image) {
		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = {bgFill};
		BackgroundSize bgSize = new BackgroundSize(48,48,false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(bgFillA,bgImgA));
	}
	
	private void setUpTooltip() {
		tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		this.setOnMouseMoved((MouseEvent e) -> {
			if (crop != null)
			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
		
	}
	
	public Item getCrop() {
		return crop;
	}
	
	public Tooltip getTooltip() {
		return tooltip;
	}
	
	
}
