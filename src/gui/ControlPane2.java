package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import logic.GameController;

public class ControlPane2 extends VBox {

	
	private static Label haveBarricade1 = new Label();
	private static Label lp1 = new Label();
	private static Label bomb1 = new Label();
	private static Label removeBarricade1 = new Label();
	private static Label haveBarricade2 = new Label();
	private static Label lp2 = new Label();
	private static Label bomb2 = new Label();
	private static Label removeBarricade2 = new Label();
	protected static Label player1=new Label("Player1");
	protected static Label player2=new Label("Player2");
	
	public ControlPane2() {
		super();
		player1.setFont(new Font(30));
		player2.setFont(new Font(30));
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);
		this.setSpacing(15);
		this.setFillWidth(true);

		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		labelUpdate();

		this.getChildren().addAll(player1, haveBarricade1, lp1, bomb1,removeBarricade1,player2, haveBarricade2, lp2, bomb2,removeBarricade2);
	}

	public static void labelUpdate() {
		haveBarricade1.textProperty().setValue(
				"Barricade: " +  GameController.getPlayer1().getHaveBaricade());
		haveBarricade1.setFont(new Font(14));
		lp1.textProperty()
				.setValue("Life Point: " + GameController.getPlayer1().getLp());
		lp1.setFont(new Font(14));
		bomb1.textProperty().setValue(
				"Current Bomb: " +GameController.getPlayer1().getHaveExploding()+"\n\n");
		bomb1.setFont(new Font(14));
		removeBarricade1.textProperty().setValue(
				"Current Barricade removal: " + GameController.getPlayer1().getHaveRemoveBarricade());
		removeBarricade1.setFont(new Font(14));
		haveBarricade2.textProperty().setValue(
				"Barricade: " +  GameController.getPlayer2().getHaveBaricade());
		haveBarricade2.setFont(new Font(14));
		lp2.textProperty()
				.setValue("Life Point: " + GameController.getPlayer2().getLp());
		lp2.setFont(new Font(14));
		bomb2.textProperty().setValue(
				"Current Bomb: " +GameController.getPlayer2().getHaveExploding());
		bomb2.setFont(new Font(14));
		removeBarricade2.textProperty().setValue(
				"Current Barricade removal: " + GameController.getPlayer1().getHaveRemoveBarricade());
		removeBarricade2.setFont(new Font(14));
		
	}

}