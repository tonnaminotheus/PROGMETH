package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

public class ControlPane extends VBox {

	private ItemPane itemPane;
	private static Label turn = new Label();
	private static Label playerTurn = new Label();
	private static Label haveBarricade = new Label();
	private static Label lp = new Label();
	private static Label bomb = new Label();
	private static Label removeBarricade = new Label();
	
	public ControlPane() {
		super();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);
		;
		this.setSpacing(15);
		this.setFillWidth(true);

		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		itemPane = new ItemPane();
		Label label = new Label("Please select action");
		label.setFont(new Font(14));
		labelUpdate();

		this.getChildren().addAll(turn, playerTurn, haveBarricade, lp, bomb,removeBarricade, label, itemPane);
	}

	public static void labelUpdate() {
		turn.textProperty().setValue("Turn: " + GameController.getTurn());
		turn.setFont(Font.font("Turn: " + GameController.getTurn(), FontPosture.ITALIC, 20));
		playerTurn.textProperty().setValue(GameController.getTurn() % 2 == 1 ? "Player 1" : "Player 2");
		playerTurn.setFont(new Font(14));
		haveBarricade.textProperty().setValue(
				"Barricade: " + (GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveBaricade()
						: GameController.getPlayer2().getHaveBaricade()));
		haveBarricade.setFont(new Font(14));
		lp.textProperty()
				.setValue("Life Point: " + (GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getLp()
						: GameController.getPlayer2().getLp()));
		lp.setFont(new Font(14));
		bomb.textProperty().setValue(
				"Current Bomb: " + (GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveExploding()
						: GameController.getPlayer2().getHaveExploding()));
		bomb.setFont(new Font(14));
		removeBarricade.textProperty().setValue(
				"Current RemoveBarricade: " + (GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveRemoveBarricade()
						: GameController.getPlayer2().getHaveRemoveBarricade()));
		removeBarricade.setFont(new Font(14));
	}

	public ItemPane getItemPane() {
		return itemPane;
	}

}
