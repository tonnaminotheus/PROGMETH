package application;

import gui.ControlPane;
import gui.FieldPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameController;

public class Main extends Application {

	public static Scene scene1, scene2, scene3, scene4, scene5;
	public static Button playB, nextB, backB;
	public static Button exitButton, exitButton2, exitButton3;
	public static ControlPane controlPane;
	public static FieldPane fieldPane;
	public static Stage primary;
	public static int gameActionNow = 0;
	public static int player1Index = 4;
	public static int player2Index = 4;
	public static Button left1, right1, left2, right2;
	public static int xposlr = 120;
	public static int yposlr = 500;
	public static Button P1, P2;

	@Override
	public void start(Stage primaryStage) {

		primary = primaryStage;

		// setScene1

		Group root = new Group();

		// background
		Image image = new Image("file:res/testMenu.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		root.getChildren().add(iv);

		// StartButton
		Image playI = new Image("file:res/Play.png");
		ImageView iv1 = new ImageView(playI);
		iv1.setFitHeight(100);
		iv1.setFitWidth(300);

		playB = new Button("", iv1);
		playB.setLayoutX(440);
		playB.setLayoutY(360);
		playB.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		playB.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		playB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, scene5);
			}

		});
		playB.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/PlayTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				playB.setGraphic(iv1);
			}
		});
		playB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Play.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				playB.setGraphic(iv1);
			}

		});
		root.getChildren().add(playB);

		// ExitButton
		Image exitI = new Image("file:res/Exit.png");
		ImageView iv2 = new ImageView(exitI);
		iv2.setFitHeight(100);
		iv2.setFitWidth(300);
		exitButton = new Button("", iv2);
		exitButton.setLayoutX(440);
		exitButton.setLayoutY(490);
		exitButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		exitButton.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/ExitTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton.setGraphic(iv1);
			}

		});
		exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Exit.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton.setGraphic(iv1);
			}

		});
		root.getChildren().add(exitButton);

		scene1 = new Scene(root, 1280, 720);
		// end setScene1

		// setScene2
		// GameController
		GameController.IntializeMap(player1Index,player2Index);
		//System.out.println("player1"+player1Index);
		//System.out.println("player2"+player1Index);
		HBox root2 = new HBox();
		root2.setPadding(new Insets(10));
		root2.setSpacing(10);
		root2.setPrefHeight(400);

		controlPane = new ControlPane();
		fieldPane = new FieldPane();
		root2.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		root2.getChildren().add(controlPane);
		root2.getChildren().add(fieldPane);

		scene2 = new Scene(root2);
		// end setScene2

		// setScene3
		Image player1Win = new Image("file:res/player1win.png");
		ImageView ivP1W = new ImageView(player1Win);
		Group root3 = new Group();

		// ExitButton
		Image exitII = new Image("file:res/Exit.png");
		ImageView iv22 = new ImageView(exitII);
		iv22.setFitHeight(100);
		iv22.setFitWidth(300);
		exitButton2 = new Button("", iv22);
		exitButton2.setLayoutX(440);
		exitButton2.setLayoutY(490);
		exitButton2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		exitButton2.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		exitButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/ExitTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton2.setGraphic(iv1);
			}

		});
		exitButton2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Exit.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton2.setGraphic(iv1);
			}

		});
		root3.getChildren().add(exitButton2);
		root3.getChildren().add(ivP1W);

		scene3 = new Scene(root3);
		// end setScene3

		// setScene3
		Image player2Win = new Image("file:res/player2win.png");
		ImageView ivP2W = new ImageView(player2Win);
		Group root4 = new Group();

		// ExitButton
		Image exitIII = new Image("file:res/Exit.png");
		ImageView iv222 = new ImageView(exitIII);
		iv222.setFitHeight(100);
		iv222.setFitWidth(300);
		exitButton3 = new Button("", iv222);
		exitButton3.setLayoutX(440);
		exitButton3.setLayoutY(490);
		exitButton3.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		exitButton3.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		exitButton3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/ExitTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton3.setGraphic(iv1);
			}

		});
		exitButton3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Exit.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton3.setGraphic(iv1);
			}

		});
		root4.getChildren().add(exitButton3);
		root4.getChildren().add(ivP2W);

		scene4 = new Scene(root4);
		// end setScene3

		// setScene5

		Group root5 = new Group();

		// background
		Image image5 = new Image("file:res/SelectPlayer.png");
		ImageView iv5 = new ImageView();
		iv5.setImage(image5);
		root5.getChildren().add(iv5);

		// NextButton
		Image NextI = new Image("file:res/Next.png");
		ImageView ivN = new ImageView(NextI);
		ivN.setFitHeight(100);
		ivN.setFitWidth(300);

		nextB = new Button("", ivN);
		nextB.setLayoutX(900);
		nextB.setLayoutY(600);
		nextB.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		nextB.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		nextB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index != player2Index)
					setScene(primaryStage, scene2);
			}

		});
		nextB.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/NextTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextB.setGraphic(iv1);
			}
		});
		nextB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Next.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextB.setGraphic(iv1);
			}

		});
		root5.getChildren().add(nextB);

		// BackButton
		Image BackI = new Image("file:res/Back.png");
		ImageView ivB = new ImageView(BackI);
		ivB.setFitHeight(100);
		ivB.setFitWidth(300);

		backB = new Button("", ivB);
		backB.setLayoutX(100);
		backB.setLayoutY(600);
		backB.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		backB.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		backB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, scene1);
			}

		});
		backB.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/BackTouch.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backB.setGraphic(iv1);
			}
		});
		backB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image("file:res/Back.png");
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backB.setGraphic(iv1);
			}

		});
		root5.getChildren().add(backB);

		// left1
		Image leftI = new Image("file:res/left.png");
		ImageView ivL = new ImageView(leftI);

		left1 = new Button("", ivL);
		left1.setLayoutX(xposlr + 100);
		left1.setLayoutY(yposlr);
		left1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		left1.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		left1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index > 1) {
					player1Index = player1Index - 1;
					System.out.println(player1Index + " " + player2Index);
					setScene5();
				}
			}

		});
		left1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				left1.setBackground(
						new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		left1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				left1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}

		});
		root5.getChildren().add(left1);

		// left2
		Image leftII = new Image("file:res/left.png");
		ImageView ivLL = new ImageView(leftII);

		left2 = new Button("", ivLL);
		left2.setLayoutX(xposlr + 730);
		left2.setLayoutY(yposlr);
		left2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		left2.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		left2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player2Index > 1) {
					player2Index = player2Index - 1;
					System.out.println(player1Index + " " + player2Index);
					setScene5();
				}
			}

		});
		left2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				left2.setBackground(
						new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		left2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				left2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}

		});
		root5.getChildren().add(left2);

		// right1
		Image rightI = new Image("file:res/right.png");
		ImageView ivR = new ImageView(rightI);

		right1 = new Button("", ivR);
		right1.setLayoutX(xposlr + 200);
		right1.setLayoutY(yposlr);
		right1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		right1.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		right1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index < 7) {
					player1Index = player1Index + 1;
					System.out.println(player1Index + " " + player2Index);
					setScene5();
				}
			}

		});
		right1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				right1.setBackground(
						new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		right1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				right1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}

		});
		root5.getChildren().add(right1);

		// right2
		Image rightII = new Image("file:res/right.png");
		ImageView ivRR = new ImageView(rightII);

		right2 = new Button("", ivRR);
		right2.setLayoutX(xposlr + 830);
		right2.setLayoutY(yposlr);
		right2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		right2.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		right2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player2Index < 7) {
					player2Index = player2Index + 1;
					System.out.println(player1Index + " " + player2Index);
					setScene5();
				}
			}

		});
		right2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				right2.setBackground(
						new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		right2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				right2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}

		});
		root5.getChildren().add(right2);

		P1 = new Button();
		P2 = new Button();
		P1.setLayoutX(xposlr + 150); /////////////////////////////////////////////////////////////
		P1.setLayoutY(yposlr - 100);
		P2.setLayoutX(xposlr + 780);
		P2.setLayoutY(yposlr - 100);
		root5.getChildren().add(P1);
		root5.getChildren().add(P2);
		setScene5();

		scene5 = new Scene(root5, 1280, 720);
		// end setScene1

		// start
		setScene(primaryStage, scene1);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setScene(Stage primaryStage, Scene scene) {
		// CreateScene
		primaryStage.setTitle("Quoridor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void setScene5() {
		System.out.println(player1Index + " " + player2Index);
		if (player1Index == 1) {
			left1.setVisible(false);
			right1.setVisible(true);
		} else if (player1Index == 7) {
			left1.setVisible(true);
			right1.setVisible(false);
		} else {
			left1.setVisible(true);
			right1.setVisible(true);
		}
		if (player2Index == 1) {
			left2.setVisible(false);
			right2.setVisible(true);
		} else if (player2Index == 7) {
			left2.setVisible(true);
			right2.setVisible(false);
		} else {
			left2.setVisible(true);
			right2.setVisible(true);
		}

		if (player1Index == 1) {
			Image PI = new Image("file:res/Token1.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else if (player1Index == 2) {
			Image PI = new Image("file:res/Token2.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else if (player1Index == 3) {
			Image PI = new Image("file:res/Token3.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else if (player1Index == 4) {
			Image PI = new Image("file:res/Token4.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else if (player1Index == 5) {
			Image PI = new Image("file:res/Token5.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else if (player1Index == 6) {
			Image PI = new Image("file:res/Token6.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		} else {
			Image PI = new Image("file:res/Token7.png");
			ImageView iv = new ImageView(PI);
			P1.setGraphic(iv);
		}
		if (player2Index == 1) {
			Image PI = new Image("file:res/Token1.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else if (player2Index == 2) {
			Image PI = new Image("file:res/Token2.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else if (player2Index == 3) {
			Image PI = new Image("file:res/Token3.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else if (player2Index == 4) {
			Image PI = new Image("file:res/Token4.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else if (player2Index == 5) {
			Image PI = new Image("file:res/Token5.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else if (player2Index == 6) {
			Image PI = new Image("file:res/Token6.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		} else {
			Image PI = new Image("file:res/Token7.png");
			ImageView iv = new ImageView(PI);
			P2.setGraphic(iv);
		}
		GameController.getPlayer1().setIndex(player1Index);
		GameController.getPlayer2().setIndex(player2Index);
		FieldPane.setFieldPane(fieldPane);
	}

	public void handle(ActionEvent event) {
		if (event.getSource() == playB) {
			// System.out.println("done");
			setScene(primary, scene5);
		}
	}

}
