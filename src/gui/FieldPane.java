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

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private ObservableList<FieldCell> fieldCells = FXCollections.observableArrayList();

	public FieldPane() {
		// TODO complete the FieldPane's constructor
		super();
		//this.setHgap(9);
		//this.setVgap(9);
		this.setPadding(new Insets(9));
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(500);
		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		int row = 17;
		int column = 17;
		for (int i = row-1; i >=0 ; i--) {
			for(int j=0;j<column;j++) {
				if(i%2==0&&j%2==0)
					fieldCells.add(new FieldCell(1));
				else if(i%2==0&&j%2==1) {
					fieldCells.add(new FieldCell(3));
				}else if(i%2==1&&j%2==0) {
					fieldCells.add(new FieldCell(2));
				}else {
					fieldCells.add(new FieldCell(4));
				}
			}
		}

		for (int i = row-1; i >=0; i--) {
			for (int j = 0; j < column; j++) {
				this.add(fieldCells.get(i * column + j), j, i);
			}
		}
	}

}
