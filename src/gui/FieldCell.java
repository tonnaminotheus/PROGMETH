package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.GameController;
import entity.base.Entity;
import interact.AddBomb;
import interact.GetBarricade;
import interact.GetHeal;
import interact.MoveOtherPlayer;
import interact.MovePlayer;
import interact.Player;
import interact.RandomTile;
import interact.RemoveAllBarricade;
import interact.RemoveAllSpecialTile;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import application.Main;
import gui.ControlPane;



//You might need to do something to the following line
public class FieldCell extends Pane {
	private Entity myEntity;
	private boolean isEmpty;
	ImageView iv;
	public FadeTransition fade=new FadeTransition();
	
	public FieldCell(Entity entity) {
		this.fade.setNode(this);
		this.setPrefWidth(60);
		this.setPrefHeight(60);
		Image image = null;
		System.out.println(entity.getClass());
		if (entity.isBlackTile()) {
			this.setBackground(new Background(new BackgroundFill(Color.rgb(230, 230, 230), CornerRadii.EMPTY, Insets.EMPTY)));
		} else if (entity.isWhiteTile()) {
			this.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
			int x = entity.getX();
			int y = entity.getY();
			if (x % 2 == 0 && y % 2 == 1) {
				this.setPrefWidth(10);
			} else if (y % 2 == 0 && x % 2 == 1) {
				this.setPrefHeight(10);
			} else {
				this.setPrefWidth(10);
				this.setPrefHeight(10);
			}
		} else if (entity.isExplodingTile())
			image = new Image("file:res/Bomb.png");
		else if (entity.isPlayer()) {
			int index = ((Player) entity).getIndex();
			if (index == 1)
				image = new Image("file:res/Token1.png");
			else if (index == 2)
				image = new Image("file:res/Token2.png");
			else if (index == 3)
				image = new Image("file:res/Token3.png");
			else if (index == 4)
				image = new Image("file:res/Token4.png");
			else if (index == 5)
				image = new Image("file:res/Token5.png");
			else if (index == 6)
				image = new Image("file:res/Token6.png");
			else
				image = new Image("file:res/Token7.png");
		} else if (entity.isSpecialTile()) {
			if(entity.getClass()==RandomTile.class)
				image = new Image("file:res/SpecialTile.png");
			else if(entity.getClass()==RemoveAllBarricade.class)
				this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==RemoveAllSpecialTile.class)
				this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==AddBomb.class)
				this.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==GetBarricade.class)
				this.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==GetHeal.class)
				this.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==MovePlayer.class)
				this.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
			else if(entity.getClass()==MoveOtherPlayer.class)
				this.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
			
		} else {
			this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			int x = entity.getX();
			int y = entity.getY();
			if (x % 2 == 0 && y % 2 == 1) {
				this.setPrefWidth(10);
			} else if (y % 2 == 0 && x % 2 == 1) {
				this.setPrefHeight(10);
			} else {
				this.setPrefWidth(10);
				this.setPrefHeight(10);
			}
		}
		iv = new ImageView();
		iv.setImage(image);
		this.getChildren().add(iv);
		isEmpty = false;
		myEntity = entity;

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				boolean pass = false;
				if (Main.gameActionNow == 1) {
					int haveBarricade = GameController.getTurn() % 2 == 1
							? GameController.getPlayer1().getHaveBaricade()
							: GameController.getPlayer2().getHaveBaricade();
					if (haveBarricade > 0) {
						FieldCell now = (FieldCell) e.getSource();
						if (now.getEntity().isWhiteTile()) {
							try {
								GameController.addHorizontalBarricade(now.getEntity().getX(), now.getEntity().getY());
								boolean ch1 = GameController.checkbfs(GameController.getPlayer1(), 16);
								System.out.println("----end-----");
								boolean ch2 = GameController.checkbfs(GameController.getPlayer2(), 0);
								System.out.println("----end-----");
								System.out.println(ch1 + " " + ch2);

								if (ch1 && ch2) {
									pass = true;
									/*String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.setNoti(playermessage);*/

									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
									System.out.println("can not place barricade there");
									String playermessage = "You cannot place a barricade there";
									ControlPane.setNoti(playermessage);
									ControlPane.labelUpdate();
									ControlPane2.labelUpdate();
								}
							} catch (Exception ee) {
								/*System.out.println("can not place barricade there");
								String playermessage = "You cannot place a barricade there";
								ControlPane.setNoti(playermessage);*/
							}
						} else {
							/*System.out.println("can not place barricade there");
							String playermessage = "That is not a white tile";
							ControlPane.setNoti(playermessage);*/
						}
					}

				} else if (Main.gameActionNow == 2) {
					int haveBarricade = GameController.getTurn() % 2 == 1
							? GameController.getPlayer1().getHaveBaricade()
							: GameController.getPlayer2().getHaveBaricade();
					FieldCell now = (FieldCell) e.getSource();
					if (haveBarricade > 0) {
						System.out.println(haveBarricade);
						System.out.println(now.getEntity().getX() + " " + now.getEntity().getY());
						if (now.getEntity().isWhiteTile()) {
							try {
								GameController.addVerticalBarricade(now.getEntity().getX(), now.getEntity().getY());
								boolean ch1 = GameController.checkbfs(GameController.getPlayer1(), 16);
								System.out.println("--end--");
								boolean ch2 = GameController.checkbfs(GameController.getPlayer2(), 0);
								if (ch1 && ch2) {
									pass = true;
									/*String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.setNoti(playermessage);*/
									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
									System.out.println("can not place barricade there");
									String playermessage = "You cannot place a barricade there";
									ControlPane.setNoti(playermessage);
									ControlPane.labelUpdate();
									ControlPane2.labelUpdate();
								}
							} catch (Exception ee) {
								/*System.out.println("can not place barricade there");
								String playermessage = "You cannot place barricade there";
								ControlPane.setNoti(playermessage);*/
							}
						} else {
							/*System.out.println("can not place barricade there");
							String playermessage = "That is not a white tile";
							ControlPane.setNoti(playermessage);*/
						}
					}
				} else if (Main.gameActionNow == 3) {
					FieldCell now = (FieldCell) e.getSource();
					if (now.getEntity().isBlackTile() || now.getEntity().isSpecialTile()
							|| now.getEntity().isExplodingTile()) {
						if (GameController.getTurn() % 2 == 1) {
							try {
								GameController.move(GameController.getPlayer1(), now.getEntity().getX(),
										now.getEntity().getY(), GameController.getPlayer1().getX(),
										GameController.getPlayer1().getY());
								pass = true;
								/*String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 moved"
										: "Player 2 moved";
								ControlPane.setNoti(playermessage);*/
							} catch (Exception ee) {
								System.out.println("can not move there");
								/*String playermessage = "You cannot move there";
								ControlPane.setNoti(playermessage);*/
							}
						} else {
							try {
								GameController.move(GameController.getPlayer2(), now.getEntity().getX(),
										now.getEntity().getY(), GameController.getPlayer2().getX(),
										GameController.getPlayer2().getY());
								pass = true;
								/*String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 moved"
										: "Player 2 moved";
								ControlPane.setNoti(playermessage);*/
							} catch (Exception ee) {
								System.out.println("can not move there");
								/*String playermessage = "You cannot move there";
								ControlPane.setNoti(playermessage);*/
							}
						}
					} else {
						System.out.println("That is a white tile");
						/*String playermessage = "You cannot move there";
						ControlPane.setNoti(playermessage);*/
					}
				} else if (Main.gameActionNow == 4) {
					int haveBomb = GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveExploding()
							: GameController.getPlayer2().getHaveExploding();
					FieldCell now = (FieldCell) e.getSource();
					if (haveBomb > 0) {
						try {
							GameController.placeBomb(now.getEntity().getX(), now.getEntity().getY());
							pass = true;
							/*String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 placed a bomb"
									: "Player 2 placed a bomb";
							ControlPane.setNoti(playermessage);*/
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveExploding(haveBomb - 1);
							else
								GameController.getPlayer2().setHaveExploding(haveBomb - 1);
						} catch (Exception e1) {
							e1.printStackTrace();
							/*String playermessage = "You cannot place a bomb there";
							ControlPane.setNoti(playermessage);*/
						}
					}
				} else if (Main.gameActionNow == 5) {
					int haveRemoveBarricade = GameController.getTurn() % 2 == 1
							? GameController.getPlayer1().getHaveRemoveBarricade()
							: GameController.getPlayer2().getHaveRemoveBarricade();
					FieldCell now = (FieldCell) e.getSource();
					if (haveRemoveBarricade > 0) {
						try {
							GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
							pass = true;
							/*String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 removed a barricade"
									: "Player 2 removed a barricade";
							ControlPane.setNoti(playermessage);*/
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveRemoveBarricade(haveRemoveBarricade - 1);
							else
								GameController.getPlayer2().setHaveRemoveBarricade(haveRemoveBarricade - 1);
						} catch (Exception e1) {
							e1.printStackTrace();
							/*String playermessage = "That tile is not barricade";
							ControlPane.setNoti(playermessage);*/
						}
					}
					
				}
				//FieldPane.setFieldPane(Main.fieldPane);
				if (pass) {
					if (GameController.getTurn() % 2 == 0) {
						GameController.spawnSpecialTile();
					}
					GameController.increaseTurn();
					FieldPane.setFieldPane(Main.fieldPane);
					ItemPane.resetButtonsBackGroundColor();
					if (GameController.getPlayer1().getX() == 16 || GameController.getPlayer2().getLp() == 0) {
						System.out.println("player1 win");
						Main.setScene(Main.primary, Main.scene3);
						// Main.primary.close();
					} else if (GameController.getPlayer2().getX() == 0 || GameController.getPlayer1().getLp() == 0) {
						System.out.println("player2 win");
						Main.setScene(Main.primary, Main.scene4);
						// Main.primary.close();
					}
					Main.gameActionNow = 0;
					System.out.println("label start");
					ControlPane.labelUpdate();
					ControlPane2.labelUpdate();
				}

			}
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile()||now.myEntity.isPlayer())) {
						Image select = new Image("file:res/Selected.png");
						// ImageView iv1 = new ImageView(select);
						now.iv.setImage(select);
						
					}
				} else if (Main.gameActionNow == 1 || Main.gameActionNow == 2) {
					if (now.myEntity.isWhiteTile() && now.myEntity.getX() % 2 == 1 && now.myEntity.getY() % 2 == 1) {
						int x = now.myEntity.getX();
						int y = now.myEntity.getY();
						if (Main.gameActionNow == 1) {
							if (GameController.getCurrentMap().getEntity(x - 1, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x + 1, y).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x * 17 + y - 17))
										.setBackground(new Background(
												new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 17))
										.setBackground(new Background(
												new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x * 17 + y - 1))
										.setBackground(new Background(
												new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 1))
										.setBackground(new Background(
												new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						}
						// now.setBackground(
						// new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY,
						// Insets.EMPTY)));
					}
				}
			}
		});
		
		
		//Fade Transition
		this.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile()||now.myEntity.isPlayer())) {
						now.fade.stop();
						now.fade.setFromValue(1);
						now.fade.setToValue(0.2);
						now.fade.setCycleCount(Animation.INDEFINITE);
						now.fade.setDuration(Duration.millis(500));
						now.fade.setAutoReverse(true);
						now.fade.play();
						
					}
				}else if (Main.gameActionNow == 1 || Main.gameActionNow == 2) {
					if (now.myEntity.isWhiteTile() && now.myEntity.getX() % 2 == 1 && now.myEntity.getY() % 2 == 1) {
						int x = now.myEntity.getX();
						int y = now.myEntity.getY();
						FieldCell now1,now2,now3;
						if (Main.gameActionNow == 1) {
							if (GameController.getCurrentMap().getEntity(x - 1, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x + 1, y).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 17));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+17));
								now1.fade.stop();
								now1.fade.setFromValue(1);
								now1.fade.setToValue(0.2);
								now1.fade.setCycleCount(Animation.INDEFINITE);
								now1.fade.setDuration(Duration.millis(500));
								now1.fade.setAutoReverse(true);
								now1.fade.play();
								now2.fade.stop();
								now2.fade.setFromValue(1);
								now2.fade.setToValue(0.2);
								now2.fade.setCycleCount(Animation.INDEFINITE);
								now2.fade.setDuration(Duration.millis(500));
								now2.fade.setAutoReverse(true);
								now2.fade.play();
								now3.fade.stop();
								now3.fade.setFromValue(1);
								now3.fade.setToValue(0.2);
								now3.fade.setCycleCount(Animation.INDEFINITE);
								now3.fade.setDuration(Duration.millis(500));
								now3.fade.setAutoReverse(true);
								now3.fade.play();
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 1));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+1));
								now1.fade.stop();
								now1.fade.setFromValue(1);
								now1.fade.setToValue(0.2);
								now1.fade.setCycleCount(Animation.INDEFINITE);
								now1.fade.setDuration(Duration.millis(500));
								now1.fade.setAutoReverse(true);
								now1.fade.play();
								now2.fade.stop();
								now2.fade.setFromValue(1);
								now2.fade.setToValue(0.2);
								now2.fade.setCycleCount(Animation.INDEFINITE);
								now2.fade.setDuration(Duration.millis(500));
								now2.fade.setAutoReverse(true);
								now2.fade.play();
								now3.fade.stop();
								now3.fade.setFromValue(1);
								now3.fade.setToValue(0.2);
								now3.fade.setCycleCount(Animation.INDEFINITE);
								now3.fade.setDuration(Duration.millis(500));
								now3.fade.setAutoReverse(true);
								now3.fade.play();
							}
						}
						// now.setBackground(
						// new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY,
						// Insets.EMPTY)));
					}
				}
			}
		});
		this.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile()||now.myEntity.isPlayer())) {
						now.fade.stop();
						now.fade.setFromValue(0.2);
						now.fade.setToValue(1);
						now.fade.setCycleCount(1);
						now.fade.setDuration(Duration.millis(500));
						now.fade.setAutoReverse(false);
						now.fade.play();
					}
				}else if (Main.gameActionNow == 1 || Main.gameActionNow == 2) {
					if (now.myEntity.isWhiteTile() && now.myEntity.getX() % 2 == 1 && now.myEntity.getY() % 2 == 1) {
						int x = now.myEntity.getX();
						int y = now.myEntity.getY();
						FieldCell now1,now2,now3;
						if (Main.gameActionNow == 1) {
							if (GameController.getCurrentMap().getEntity(x - 1, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x + 1, y).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 17));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+17));
								now1.fade.stop();
								now1.fade.setFromValue(0.2);
								now1.fade.setToValue(1);
								now1.fade.setCycleCount(1);
								now1.fade.setDuration(Duration.millis(500));
								now1.fade.setAutoReverse(false);
								now1.fade.play();
								now2.fade.stop();
								now2.fade.setFromValue(0.2);
								now2.fade.setToValue(1);
								now2.fade.setCycleCount(1);
								now2.fade.setDuration(Duration.millis(500));
								now2.fade.setAutoReverse(false);
								now2.fade.play();
								now3.fade.stop();
								now3.fade.setFromValue(0.2);
								now3.fade.setToValue(1);
								now3.fade.setCycleCount(1);
								now3.fade.setDuration(Duration.millis(500));
								now3.fade.setAutoReverse(false);
								now3.fade.play();
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 1));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+1));
								now1.fade.stop();
								now1.fade.setFromValue(0.2);
								now1.fade.setToValue(1);
								now1.fade.setCycleCount(1);
								now1.fade.setDuration(Duration.millis(500));
								now1.fade.setAutoReverse(false);
								now1.fade.play();
								now2.fade.stop();
								now2.fade.setFromValue(0.2);
								now2.fade.setToValue(1);
								now2.fade.setCycleCount(1);
								now2.fade.setDuration(Duration.millis(500));
								now2.fade.setAutoReverse(false);
								now2.fade.play();
								now3.fade.stop();
								now3.fade.setFromValue(0.2);
								now3.fade.setToValue(1);
								now3.fade.setCycleCount(1);
								now3.fade.setDuration(Duration.millis(500));
								now3.fade.setAutoReverse(false);
								now3.fade.play();
							}
						}
					}
				}
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile()||now.myEntity.isPlayer())) {
						Image image = null;
						ControlPane.labelUpdate();
						 System.out.println(entity.getClass());
						if (now.myEntity.isBlackTile())
							now.setBackground(new Background(
									new BackgroundFill(Color.rgb(230, 230, 230), CornerRadii.EMPTY, Insets.EMPTY)));
						else if (now.myEntity.isWhiteTile()) {
							now.setBackground(
									new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
							int x = entity.getX();
							int y = entity.getY();
							if (x % 2 == 0 && y % 2 == 1) {
								now.setPrefWidth(10);
							} else if (y % 2 == 0 && x % 2 == 1) {
								now.setPrefHeight(10);
							} else {
								now.setPrefWidth(10);
								now.setPrefHeight(10);
							}
						} else if (now.myEntity.isExplodingTile())
							image = new Image("file:res/Bomb.png");
						else if (now.myEntity.isPlayer()) {
							int index = ((Player) entity).getIndex();
							if (index == 1)
								image = new Image("file:res/Token1.png");
							else if (index == 2)
								image = new Image("file:res/Token2.png");
							else if (index == 3)
								image = new Image("file:res/Token3.png");
							else if (index == 4)
								image = new Image("file:res/Token4.png");
							else if (index == 5)
								image = new Image("file:res/Token5.png");
							else if (index == 6)
								image = new Image("file:res/Token6.png");
							else
								image = new Image("file:res/Token7.png");
						} else if (now.myEntity.isSpecialTile()) {
							if(entity.getClass()==RandomTile.class)
								image = new Image("file:res/SpecialTile.png");
							else if(entity.getClass()==RemoveAllBarricade.class)
								now.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==RemoveAllSpecialTile.class)
								now.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==AddBomb.class)
								now.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==GetBarricade.class)
								now.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==GetHeal.class)
								now.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==MovePlayer.class)
								now.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
							else if(entity.getClass()==MoveOtherPlayer.class)
								now.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
						} else {
							now.setBackground(
									new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
							int x = entity.getX();
							int y = entity.getY();
							if (x % 2 == 0 && y % 2 == 1) {
								now.setPrefWidth(10);
							} else if (y % 2 == 0 && x % 2 == 1) {
								now.setPrefHeight(10);
							} else {
								now.setPrefWidth(10);
								now.setPrefHeight(10);
							}
						}
						// ImageView iv = new ImageView();
						// iv.setImage(image);
						now.iv.setImage(image);
						ControlPane.labelUpdate();
					}
				} else if (Main.gameActionNow == 1 || Main.gameActionNow == 2) {
					if (now.myEntity.isWhiteTile() && now.myEntity.getX() % 2 == 1 && now.myEntity.getY() % 2 == 1) {
						int x = now.myEntity.getX();
						int y = now.myEntity.getY();
						if (Main.gameActionNow == 1) {
							if (GameController.getCurrentMap().getEntity(x - 1, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x + 1, y).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x * 17 + y - 17))
										.setBackground(new Background(
												new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 17))
										.setBackground(new Background(
												new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x * 17 + y - 1))
										.setBackground(new Background(
												new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 1))
										.setBackground(new Background(
												new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						}
					}

				}
			}
		});
	}

	public boolean IsEmpty() {
		return isEmpty;
	}

	public boolean setEntity(Entity e) {
		if (isEmpty) {
			myEntity = e;
			isEmpty = false;
			return true;
		} else {

			return true;
		}
	}

	public Entity getEntity() {
		return myEntity;
	}

	public void removeEntity() {
		myEntity = null;
		isEmpty = true;
	}

}
