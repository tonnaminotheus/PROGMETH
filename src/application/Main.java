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

	public static Scene mainMenu, BoardScene, player1WinScene, player2WinScene, characterSelectScene;
	public static Pane playButton, nextButton, backButton;
	public static Pane MenuExitButton, Player1ExitButton, Player2ExitButton;
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
	private static Timeline timeline;
	private static int secondCount;
	private boolean checkFirstStart=false;
	
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
		// setmainMenu

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
		
		playButton = new Pane(iv1);
		playButton.setLayoutX(440);
		playButton.setLayoutY(360);
		playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, characterSelectScene);
			}

		});
		playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("PlayTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				//playButton.setGraphic(iv1);
				playButton.getChildren().clear();
				playButton.getChildren().add(iv1);
			}
		});
		playButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Play.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				playButton.getChildren().clear();
				playButton.getChildren().add(iv1);
			}

		});
		FadeTransition playButtonAction = new FadeTransition();
		playButtonAction.setNode(playButton);
		playButtonAction.setFromValue(1);
		playButtonAction.setToValue(0.2);
		playButtonAction.setCycleCount(Animation.INDEFINITE);
		playButtonAction.setDuration(Duration.millis(500));
		playButtonAction.setAutoReverse(true);
		playButtonAction.play();
		root.getChildren().add(playButton);

		// MenuExitButton
		Image exitI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv2 = new ImageView(exitI);
		iv2.setFitHeight(100);
		iv2.setFitWidth(300);
		MenuExitButton = new Pane(iv2);
		MenuExitButton.setLayoutX(440);
		MenuExitButton.setLayoutY(490);
		MenuExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		MenuExitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				MenuExitButton.getChildren().clear();
				MenuExitButton.getChildren().add(iv1);
			}

		});
		MenuExitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				MenuExitButton.getChildren().clear();
				MenuExitButton.getChildren().add(iv1);
			}

		});
		FadeTransition exitBAction = new FadeTransition();
		exitBAction.setNode(MenuExitButton);
		exitBAction.setFromValue(1);
		exitBAction.setToValue(0.2);
		exitBAction.setCycleCount(Animation.INDEFINITE);
		exitBAction.setDuration(Duration.millis(500));
		exitBAction.setAutoReverse(true);
		exitBAction.play();
		root.getChildren().add(MenuExitButton);

		mainMenu = new Scene(root, 1280, 720);
		// end setmainMenu

		// setBoardScene
		restart();
		// end setBoardScene

		// setplayer1WinScene
		Image player1Win = new Image(ClassLoader.getSystemResource("player1win.png").toString());
		ImageView ivP1W = new ImageView(player1Win);
		Group root3 = new Group();

		// MenuExitButton
		Image exitII = new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv22 = new ImageView(exitII);
		iv22.setFitHeight(100);
		iv22.setFitWidth(300);
		Player1ExitButton = new Pane(iv22);
		Player1ExitButton.setLayoutX(440);
		Player1ExitButton.setLayoutY(490);
		Player1ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		Player1ExitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				Player1ExitButton.getChildren().clear();
				Player1ExitButton.getChildren().add(iv1);
			}

		});
		Player1ExitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				Player1ExitButton.getChildren().clear();
				Player1ExitButton.getChildren().add(iv1);
			}

		});
		FadeTransition exitB2Action = new FadeTransition();
		exitB2Action.setNode(Player1ExitButton);
		exitB2Action.setFromValue(1);
		exitB2Action.setToValue(0.2);
		exitB2Action.setCycleCount(Animation.INDEFINITE);
		exitB2Action.setDuration(Duration.millis(500));
		exitB2Action.setAutoReverse(true);
		exitB2Action.play();
		root3.getChildren().add(Player1ExitButton);
		root3.getChildren().add(ivP1W);

		player1WinScene = new Scene(root3,1280,720);
		// end setplayer1WinScene

		// setplayer1WinScene
		Image player2Win = new Image(ClassLoader.getSystemResource("player2win.png").toString());
		ImageView ivP2W = new ImageView(player2Win);
		Group root4 = new Group();

		// MenuExitButton
		Image exitIII =  new Image(ClassLoader.getSystemResource("Exit.png").toString());
		ImageView iv222 = new ImageView(exitIII);
		iv222.setFitHeight(100);
		iv222.setFitWidth(300);
		Player2ExitButton = new Pane(iv222);
		Player2ExitButton.setLayoutX(440);
		Player2ExitButton.setLayoutY(490);
		Player2ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				primaryStage.close();
			}

		});
		Player2ExitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("ExitTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				Player2ExitButton.getChildren().clear();
				Player2ExitButton.getChildren().add(iv1);
			}

		});
		Player2ExitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Exit.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				Player2ExitButton.getChildren().clear();
				Player2ExitButton.getChildren().add(iv1);
			}

		});
		FadeTransition exitB3Action = new FadeTransition();
		exitB3Action.setNode(Player2ExitButton);
		exitB3Action.setFromValue(1);
		exitB3Action.setToValue(0.2);
		exitB3Action.setCycleCount(Animation.INDEFINITE);
		exitB3Action.setDuration(Duration.millis(500));
		exitB3Action.setAutoReverse(true);
		exitB3Action.play();
		root4.getChildren().add(Player2ExitButton);
		root4.getChildren().add(ivP2W);

		player2WinScene = new Scene(root4,1280,720);
		// end setplayer1WinScene

		// setcharacterSelectScene

		Group root5 = new Group();

		// background
		Image image5 = new Image(ClassLoader.getSystemResource("SelectPlayer.png").toString());
		ImageView iv5 = new ImageView();
		iv5.setImage(image5);
		root5.getChildren().add(iv5);

		// nextButtonutton
		Image NextI =  new Image(ClassLoader.getSystemResource("Next.png").toString());
		ImageView ivN = new ImageView(NextI);
		ivN.setFitHeight(100);
		ivN.setFitWidth(300);

		nextButton = new Pane(ivN);
		nextButton.setLayoutX(900);
		nextButton.setLayoutY(600);
		nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (player1Index != player2Index)
					setScene(primaryStage, BoardScene);
					secondCount = 0;
					gameActionNow=0;
					if(!checkFirstStart) {
						secTime();
						checkFirstStart=true;
					}
			}

		});
		nextButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("NextTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextButton.getChildren().clear();
				nextButton.getChildren().add(iv1);
			}
		});
		nextButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Next.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				nextButton.getChildren().clear();
				nextButton.getChildren().add(iv1);
			}

		});
		FadeTransition nextButtonAction = new FadeTransition();
		nextButtonAction.setNode(nextButton);
		nextButtonAction.setFromValue(1);
		nextButtonAction.setToValue(0.2);
		nextButtonAction.setCycleCount(Animation.INDEFINITE);
		nextButtonAction.setDuration(Duration.millis(500));
		nextButtonAction.setAutoReverse(true);
		nextButtonAction.play();
		root5.getChildren().add(nextButton);

		// backButtonutton
		Image BackI = new Image(ClassLoader.getSystemResource("Back.png").toString());
		ImageView ivB = new ImageView(BackI);
		ivB.setFitHeight(100);
		ivB.setFitWidth(300);

		backButton = new Pane(ivB);
		backButton.setLayoutX(100);
		backButton.setLayoutY(600);
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setScene(primaryStage, mainMenu);
			}

		});
		backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("BackTouch.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backButton.getChildren().clear();
				backButton.getChildren().add(iv1);
			}
		});
		backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Image playI = new Image(ClassLoader.getSystemResource("Back.png").toString());
				ImageView iv1 = new ImageView(playI);
				iv1.setFitHeight(100);
				iv1.setFitWidth(300);
				backButton.getChildren().clear();
				backButton.getChildren().add(iv1);
			}

		});
		FadeTransition backButtonAction = new FadeTransition();
		backButtonAction.setNode(backButton);
		backButtonAction.setFromValue(1);
		backButtonAction.setToValue(0.2);
		backButtonAction.setCycleCount(Animation.INDEFINITE);
		backButtonAction.setDuration(Duration.millis(500));
		backButtonAction.setAutoReverse(true);
		backButtonAction.play();
		root5.getChildren().add(backButton);

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
					setcharacterSelectScene();
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
					setcharacterSelectScene();
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
					setcharacterSelectScene();
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
					setcharacterSelectScene();
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
		setcharacterSelectScene();

		characterSelectScene = new Scene(root5, 1280, 720);
		// end setmainMenu
		
		//button sound effect
		
		playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		MenuExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		Player1ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Media musicFile=new Media(ClassLoader.getSystemResource("Click.mp3").toString());
				operate = new MediaPlayer(musicFile);
				operate.setVolume(0.3);
				operate.setAutoPlay(true);
			}

		});
		Player2ExitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
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
		setScene(primaryStage, mainMenu);

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

	public static void setcharacterSelectScene() {
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
			nextButton.setVisible(false);
		}else {
			nextButton.setVisible(true);
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
		if (event.getSource() == playButton) {
			setScene(primary, characterSelectScene);
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
		BoardScene = new Scene(root2,1280,720);
	}
	
	public static void secTime() {
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		if (timeline == null) {
			timeline.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ControlPane2.Time.setText("Time: "+secondCount);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				secondCount++;
			}
		});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();
	}

}
