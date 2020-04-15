package application;

import java.io.File;

import gui.ControlPane;
import gui.ControlPane2;
import gui.FieldPane;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameController;

public class Main extends Application {

	public static Scene scene1, scene2, scene3, scene4, scene5;
	public static Pane playB, nextB, backB;
	public static Pane exitButton, exitButton2, exitButton3;
	public static ControlPane controlPane;
	public static ControlPane2 controlPane2;
	public static FieldPane fieldPane;
	public static Stage primary;
	public static int gameActionNow = 0;
	public static int player1Index = 4;
	public static int player2Index = 4;
	public static Pane left1, right1, left2, right2;
	public static int xposlr = 120;
	public static int yposlr = 500;
	public static Pane P1, P2;
	public static MediaPlayer mediaplayer;
	public static MediaPlayer operate;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		
		

		primary = primaryStage;
		//setMusic
		Media musicFile=new Media(ClassLoader.getSystemResource("gameSong.mp3").toString());
		mediaplayer = new MediaPlayer(musicFile);
		mediaplayer.setAutoPlay(true);
		mediaplayer.setVolume(0.10);
		mediaplayer.setCycleCount(Animation.INDEFINITE);
		mediaplayer.setVolume(0.05);
		// setScene1

		Group root = new Group();

		// background
		Image image = new Image(ClassLoader.getSystemResource("testMenu.png").toString());
		ImageView iv = new ImageView();
		iv.setImage(image);
		root.getChildren().add(iv);

		// StartButton
		Image playI = new Image(ClassLoader.getSystemResource("Play.png").toString());
		ImageView iv1 = new ImageView(playI);
		iv1.setFitHeight(100);
		iv1.setFitWidth(300);
		
		playB = new Pane(iv1);
		playB.setLayoutX(440);
		playB.setLayoutY(360);
		playB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, scene5);
			}

		});
		playB.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("PlayTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				//playB.setGraphic(iv1);
				playB.getChildren().clear();
				playB.getChildren().add(iv1);
			}
		});
		playB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Play.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				playB.getChildren().clear();
				playB.getChildren().add(iv1);
			}

		});
		FadeTransition playBAction = new FadeTransition();
		playBAction.setNode(playB);
		playBAction.setFromValue(1);
		playBAction.setToValue(0.2);
		playBAction.setCycleCount(Animation.INDEFINITE);
		playBAction.setDuration(Duration.millis(500));
		playBAction.setAutoReverse(true);
		playBAction.play();
		root.getChildren().add(playB);

		// ExitButton
		Image exitI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv2 = new ImageView(exitI);
		iv2.setFitHeight(100);
		iv2.setFitWidth(300);
		exitButton = new Pane(iv2);
		exitButton.setLayoutX(440);
		exitButton.setLayoutY(490);
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton.getChildren().clear();
				exitButton.getChildren().add(iv1);
			}

		});
		exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton.getChildren().clear();
				exitButton.getChildren().add(iv1);
			}

		});
		FadeTransition exitBAction = new FadeTransition();
		exitBAction.setNode(exitButton);
		exitBAction.setFromValue(1);
		exitBAction.setToValue(0.2);
		exitBAction.setCycleCount(Animation.INDEFINITE);
		exitBAction.setDuration(Duration.millis(500));
		exitBAction.setAutoReverse(true);
		exitBAction.play();
		root.getChildren().add(exitButton);

		scene1 = new Scene(root, 1280, 720);
		// end setScene1

		// setScene2
		restart();
		// end setScene2

		// setScene3
		Image player1Win = new Image(ClassLoader.getSystemResource("player1win.png").toString());
		ImageView ivP1W = new ImageView(player1Win);
		Group root3 = new Group();

		// ExitButton
		Image exitII = new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv22 = new ImageView(exitII);
		iv22.setFitHeight(100);
		iv22.setFitWidth(300);
		exitButton2 = new Pane(iv22);
		exitButton2.setLayoutX(440);
		exitButton2.setLayoutY(490);
		exitButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton2.getChildren().clear();
				exitButton2.getChildren().add(iv1);
			}

		});
		exitButton2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton2.getChildren().clear();
				exitButton2.getChildren().add(iv1);
			}

		});
		FadeTransition exitB2Action = new FadeTransition();
		exitB2Action.setNode(exitButton2);
		exitB2Action.setFromValue(1);
		exitB2Action.setToValue(0.2);
		exitB2Action.setCycleCount(Animation.INDEFINITE);
		exitB2Action.setDuration(Duration.millis(500));
		exitB2Action.setAutoReverse(true);
		exitB2Action.play();
		root3.getChildren().add(exitButton2);
		root3.getChildren().add(ivP1W);

		scene3 = new Scene(root3,1280,720);
		// end setScene3

		// setScene3
		Image player2Win = new Image(ClassLoader.getSystemResource("player2win.png").toString());
		ImageView ivP2W = new ImageView(player2Win);
		Group root4 = new Group();

		// ExitButton
		Image exitIII =  new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv222 = new ImageView(exitIII);
		iv222.setFitHeight(100);
		iv222.setFitWidth(300);
		exitButton3 = new Pane(iv222);
		exitButton3.setLayoutX(440);
		exitButton3.setLayoutY(490);
		exitButton3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		exitButton3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton3.getChildren().clear();
				exitButton3.getChildren().add(iv1);
			}

		});
		exitButton3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				exitButton3.getChildren().clear();
				exitButton3.getChildren().add(iv1);
			}

		});
		FadeTransition exitB3Action = new FadeTransition();
		exitB3Action.setNode(exitButton3);
		exitB3Action.setFromValue(1);
		exitB3Action.setToValue(0.2);
		exitB3Action.setCycleCount(Animation.INDEFINITE);
		exitB3Action.setDuration(Duration.millis(500));
		exitB3Action.setAutoReverse(true);
		exitB3Action.play();
		root4.getChildren().add(exitButton3);
		root4.getChildren().add(ivP2W);

		scene4 = new Scene(root4,1280,720);
		// end setScene3

		// setScene5

		Group root5 = new Group();

		// background
		Image image5 = new Image(ClassLoader.getSystemResource("SelectPlayer.png").toString());
		ImageView iv5 = new ImageView();
		iv5.setImage(image5);
		root5.getChildren().add(iv5);

		// NextButton
		Image NextI =  new Image(ClassLoader.getSystemResource("Next.png").toString());
		ImageView ivN = new ImageView(NextI);
		ivN.setFitHeight(100);
		ivN.setFitWidth(300);

		nextB = new Pane(ivN);
		nextB.setLayoutX(900);
		nextB.setLayoutY(600);
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
				Image playI = new Image(ClassLoader.getSystemResource("NextTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextB.getChildren().clear();
				nextB.getChildren().add(iv1);
			}
		});
		nextB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Next.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextB.getChildren().clear();
				nextB.getChildren().add(iv1);
			}

		});
		FadeTransition nextBAction = new FadeTransition();
		nextBAction.setNode(nextB);
		nextBAction.setFromValue(1);
		nextBAction.setToValue(0.2);
		nextBAction.setCycleCount(Animation.INDEFINITE);
		nextBAction.setDuration(Duration.millis(500));
		nextBAction.setAutoReverse(true);
		nextBAction.play();
		root5.getChildren().add(nextB);

		// BackButton
		Image BackI = new Image(ClassLoader.getSystemResource("Back.png").toString());
		ImageView ivB = new ImageView(BackI);
		ivB.setFitHeight(100);
		ivB.setFitWidth(300);

		backB = new Pane(ivB);
		backB.setLayoutX(100);
		backB.setLayoutY(600);
		backB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, scene1);
			}

		});
		backB.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("BackTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backB.getChildren().clear();
				backB.getChildren().add(iv1);
			}
		});
		backB.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Back.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backB.getChildren().clear();
				backB.getChildren().add(iv1);
			}

		});
		FadeTransition backBAction = new FadeTransition();
		backBAction.setNode(backB);
		backBAction.setFromValue(1);
		backBAction.setToValue(0.2);
		backBAction.setCycleCount(Animation.INDEFINITE);
		backBAction.setDuration(Duration.millis(500));
		backBAction.setAutoReverse(true);
		backBAction.play();
		root5.getChildren().add(backB);

		// left1
		Image leftI = new Image(ClassLoader.getSystemResource("left.png").toString());
		ImageView ivL = new ImageView(leftI);

		left1 = new Pane(ivL);
		left1.setLayoutX(xposlr + 100);
		left1.setLayoutY(yposlr);
		left1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index > 1) {
					player1Index = player1Index - 1;
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
		Image leftII = new Image(ClassLoader.getSystemResource("left.png").toString());
		ImageView ivLL = new ImageView(leftII);

		left2 = new Pane(ivLL);
		left2.setLayoutX(xposlr + 730);
		left2.setLayoutY(yposlr);
		left2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player2Index > 1) {
					player2Index = player2Index - 1;
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
		Image rightI = new Image(ClassLoader.getSystemResource("right.png").toString());
		ImageView ivR = new ImageView(rightI);

		right1 = new Pane(ivR);
		right1.setLayoutX(xposlr + 200);
		right1.setLayoutY(yposlr);
		right1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		right1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index < 7) {
					player1Index = player1Index + 1;
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
		Image rightII = new Image(ClassLoader.getSystemResource("right.png").toString());
		ImageView ivRR = new ImageView(rightII);

		right2 = new Pane(ivRR);
		right2.setLayoutX(xposlr + 830);
		right2.setLayoutY(yposlr);
		right2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player2Index < 7) {
					player2Index = player2Index + 1;
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

		P1 = new Pane();
		P2 = new Pane();
		P1.setLayoutX(xposlr + 150); /////////////////////////////////////////////////////////////
		P1.setLayoutY(yposlr - 100);
		P2.setLayoutX(xposlr + 780);
		P2.setLayoutY(yposlr - 100);
		root5.getChildren().add(P1);
		root5.getChildren().add(P2);
		setScene5();

		scene5 = new Scene(root5, 1280, 720);
		// end setScene1
		
		//button sound effect
		
		playB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		nextB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		backB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		exitButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		exitButton3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		left1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		left2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		right1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		right2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

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
		if(player1Index==player2Index) {
			nextB.setVisible(false);
		}else {
			nextB.setVisible(true);
		}

		if (player1Index == 1) {
			Image PI = new Image(ClassLoader.getSystemResource("Token1.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else if (player1Index == 2) {
			Image PI = new Image(ClassLoader.getSystemResource("Token2.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else if (player1Index == 3) {
			Image PI = new Image(ClassLoader.getSystemResource("Token3.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else if (player1Index == 4) {
			Image PI = new Image(ClassLoader.getSystemResource("Token4.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else if (player1Index == 5) {
			Image PI = new Image(ClassLoader.getSystemResource("Token5.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else if (player1Index == 6) {
			Image PI = new Image(ClassLoader.getSystemResource("Token6.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		} else {
			Image PI = new Image(ClassLoader.getSystemResource("Token7.png").toString());
			ImageView iv = new ImageView(PI);
			P1.getChildren().clear();
			P1.getChildren().add(iv);
		}
		if (player2Index == 1) {
			Image PI = new Image(ClassLoader.getSystemResource("Token1.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else if (player2Index == 2) {
			Image PI = new Image(ClassLoader.getSystemResource("Token2.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else if (player2Index == 3) {
			Image PI = new Image(ClassLoader.getSystemResource("Token3.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else if (player2Index == 4) {
			Image PI = new Image(ClassLoader.getSystemResource("Token4.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else if (player2Index == 5) {
			Image PI = new Image(ClassLoader.getSystemResource("Token5.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else if (player2Index == 6) {
			Image PI = new Image(ClassLoader.getSystemResource("Token6.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		} else {
			Image PI = new Image(ClassLoader.getSystemResource("Token7.png").toString());
			ImageView iv = new ImageView(PI);
			P2.getChildren().clear();
			P2.getChildren().add(iv);
		}
		GameController.getPlayer1().setIndex(player1Index);
		GameController.getPlayer2().setIndex(player2Index);
		FieldPane.setFieldPane(fieldPane);
	}

	public void handle(ActionEvent event) {
		if (event.getSource() == playB) {
			setScene(primary, scene5);
		}
	}
	public static void restart() {
		GameController.IntializeMap(player1Index,player2Index);
		HBox root2 = new HBox();
		root2.setPadding(new Insets(10));
		root2.setSpacing(10);
		root2.setPrefHeight(400);

		controlPane = new ControlPane();
		fieldPane = new FieldPane();
		controlPane2 = new ControlPane2();
		root2.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
		root2.getChildren().add(controlPane);
		root2.getChildren().add(fieldPane);
		root2.getChildren().add(controlPane2);
		
		scene2 = new Scene(root2,1280,720);
	}

}
