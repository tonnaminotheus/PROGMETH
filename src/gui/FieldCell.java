package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import logic.GameController;
import logic.addBarricadeFail;
import logic.moveFail;
import logic.removeBarricadeFail;
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
import javafx.animation.PathTransition;
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
		
		/*if(t) {
			FadeTransition T = new FadeTransition();
			T.setNode(this);
			T.setFromValue(0);
			T.setToValue(1);
			T.setDuration(Duration.millis(3000));
			T.play();
		}*/
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
			image = new Image(ClassLoader.getSystemResource("Bomb.png").toString());
		else if (entity.isPlayer()) {
			int index = ((Player) entity).getIndex();
			if (index == 1)
				image = new Image(ClassLoader.getSystemResource("Token1.png").toString());
			else if (index == 2)
				image = new Image(ClassLoader.getSystemResource("Token2.png").toString());
			else if (index == 3)
				image = new Image(ClassLoader.getSystemResource("Token3.png").toString());
			else if (index == 4)
				image = new Image(ClassLoader.getSystemResource("Token4.png").toString());
			else if (index == 5)
				image = new Image(ClassLoader.getSystemResource("Token5.png").toString());
			else if (index == 6)
				image = new Image(ClassLoader.getSystemResource("Token6.png").toString());
			else
				image = new Image(ClassLoader.getSystemResource("Token7.png").toString());
		} else if (entity.isSpecialTile()) {
			if(entity.getClass()==RandomTile.class)
				image = new Image(ClassLoader.getSystemResource("SpecialTile.png").toString());
			else if(entity.getClass()==RemoveAllBarricade.class)
				image = new Image(ClassLoader.getSystemResource("RemoveAllBarricade.png").toString());
			else if(entity.getClass()==RemoveAllSpecialTile.class)
				image = new Image(ClassLoader.getSystemResource("RemoveAllTile2.png").toString());
			else if(entity.getClass()==AddBomb.class)
				image = new Image(ClassLoader.getSystemResource("Get1Bomb.png").toString());
			else if(entity.getClass()==GetBarricade.class)
				image = new Image(ClassLoader.getSystemResource("Get1Barricade.png").toString());
			else if(entity.getClass()==GetHeal.class)
				image = new Image(ClassLoader.getSystemResource("Heal.png").toString());
			else if(entity.getClass()==MovePlayer.class)
				image = new Image(ClassLoader.getSystemResource("MovePlayer.png").toString());
			else if(entity.getClass()==MoveOtherPlayer.class)
				image = new Image(ClassLoader.getSystemResource("MoveOtherPlayer.png").toString());
			
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
		iv.setVisible(true);
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
								boolean ch2 = GameController.checkbfs(GameController.getPlayer2(), 0);

								if (ch1 && ch2) {
									pass = true;
									MediaPlayer operate;
									Media musicFile=new Media(ClassLoader.getSystemResource("DoorClose.mp3").toString());
									operate = new MediaPlayer(musicFile);
									operate.setVolume(0.05);
									operate.setAutoPlay(true);
									/*String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.setNoti(playermessage);*/

									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									try {
										GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
									} catch (removeBarricadeFail e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									String playermessage = "You cannot place a barricade there";
									ControlPane.setNoti(playermessage);
									ControlPane.labelUpdate();
									ControlPane2.labelUpdate();
								}
							} catch (addBarricadeFail ee) {
								
							}
						} else {
							
						}
					}

				} else if (Main.gameActionNow == 2) {
					int haveBarricade = GameController.getTurn() % 2 == 1
							? GameController.getPlayer1().getHaveBaricade()
							: GameController.getPlayer2().getHaveBaricade();
					FieldCell now = (FieldCell) e.getSource();
					if (haveBarricade > 0) {
						if (now.getEntity().isWhiteTile()) {
							try {
								GameController.addVerticalBarricade(now.getEntity().getX(), now.getEntity().getY());
								boolean ch1 = GameController.checkbfs(GameController.getPlayer1(), 16);
								boolean ch2 = GameController.checkbfs(GameController.getPlayer2(), 0);
								if (ch1 && ch2) {
									pass = true;
									MediaPlayer operate;
									Media musicFile=new Media(ClassLoader.getSystemResource("DoorClose.mp3").toString());
									operate = new MediaPlayer(musicFile);
									operate.setVolume(0.05);
									operate.setAutoPlay(true);
									/*String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.setNoti(playermessage);*/
									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									try {
										GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
									} catch (removeBarricadeFail e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									String playermessage = "You cannot place a barricade there";
									ControlPane.setNoti(playermessage);
									ControlPane.labelUpdate();
									ControlPane2.labelUpdate();
								}
							} catch (addBarricadeFail ee) {
								
							}
						} else {
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
							} catch (moveFail ee) {
								//System.out.println("okokokokok");
							}
						} else {
							try {
								GameController.move(GameController.getPlayer2(), now.getEntity().getX(),
										now.getEntity().getY(), GameController.getPlayer2().getX(),
										GameController.getPlayer2().getY());
								pass = true;
							} catch (moveFail ee) {
							}
						}
					} else {
					}
				} else if (Main.gameActionNow == 4) {
					int haveBomb = GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveExploding()
							: GameController.getPlayer2().getHaveExploding();
					FieldCell now = (FieldCell) e.getSource();
					if (haveBomb > 0) {
						try {
							GameController.placeBomb(now.getEntity().getX(), now.getEntity().getY());
							pass = true;
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveExploding(haveBomb - 1);
							else
								GameController.getPlayer2().setHaveExploding(haveBomb - 1);
						} catch (Exception e1) {
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
							Player p=null;
							if(GameController.getTurn() % 2 == 1) {
								p=GameController.getPlayer1();
							}else {
								p=GameController.getPlayer2();
							}
							p.setHaveRemoveBarricade(p.getHaveRemoveBarricade()-1);
							pass = true;
						} catch (removeBarricadeFail e1) {
							
						}
					}
					
				}
				if (pass) {
					ControlPane.labelUpdate();
					ControlPane2.labelUpdate();
					ItemPane.resetButtonsBackGroundColor();
					Main.gameActionNow=0;
					if (GameController.getPlayer1().getX() == 16 || GameController.getPlayer2().getLp() == 0) {
						Main.setScene(Main.primary, Main.scene3);
					} else if (GameController.getPlayer2().getX() == 0 || GameController.getPlayer1().getLp() == 0) {
						Main.setScene(Main.primary, Main.scene4);
					}
					//if()
					if(GameController.getTurn()%2==0) {
						if(!isNotLose(GameController.getPlayer1())) {
							Main.setScene(Main.primary, Main.scene4);
						}else if(!isNotLose(GameController.getPlayer2())) {
							Main.setScene(Main.primary, Main.scene3);
						}
					}else {
						if(!isNotLose(GameController.getPlayer2())) {
							Main.setScene(Main.primary, Main.scene3);
						}else if(!isNotLose(GameController.getPlayer1())) {
							Main.setScene(Main.primary, Main.scene4);
						}
					}
					GameController.increaseTurn();
					if (GameController.getTurn() % 2 == 1) {
						Entity ee=GameController.spawnSpecialTile();
						if(ee!=null) {
							FadeTransition T = new FadeTransition();
							T.setNode(FieldPane.getFieldCell(ee));
							T.setFromValue(0);
							T.setToValue(1);
							T.setDuration(Duration.millis(3000));
							T.play();
						}
					}
				}

			}
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile()||now.myEntity.isPlayer())) {
						//Image select = new Image(ClassLoader.getSystemResource("Selected.png").toString());
						// ImageView iv1 = new ImageView(select);
						//now.iv.setImage(select);
						
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
						FadeStart(now);
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
								FadeStart(now1);
								FadeStart(now2);
								FadeStart(now3);
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 1));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+1));
								FadeStart(now1);
								FadeStart(now2);
								FadeStart(now3);
							}
						}
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
						FadeStop(now);
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
								FadeStop(now1);
								FadeStop(now2);
								FadeStop(now3);
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x, y - 1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y + 1).isWhiteTile()) {
								now1=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y - 1));
								now2=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y));
								now3=((FieldCell) Main.fieldPane.getChildren().get(x * 17 + y+1));
								FadeStop(now1);
								FadeStop(now2);
								FadeStop(now3);
							}
						}
					}
				}
				//Main.fieldPane.setFieldPane(Main.fieldPane);
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
							image = new Image(ClassLoader.getSystemResource("Bomb.png").toString());
						else if (now.myEntity.isPlayer()) {
							int index = ((Player) entity).getIndex();
							if (index == 1)
								image =  new Image(ClassLoader.getSystemResource("Token1.png").toString());
							else if (index == 2)
								image =  new Image(ClassLoader.getSystemResource("Token2.png").toString());
							else if (index == 3)
								image =  new Image(ClassLoader.getSystemResource("Token3.png").toString());
							else if (index == 4)
								image =  new Image(ClassLoader.getSystemResource("Token4.png").toString());
							else if (index == 5)
								image =  new Image(ClassLoader.getSystemResource("Token5.png").toString());
							else if (index == 6)
								image =  new Image(ClassLoader.getSystemResource("Token6.png").toString());
							else
								image =  new Image(ClassLoader.getSystemResource("Token7.png").toString());
						} else if (now.myEntity.isSpecialTile()) {
							if(entity.getClass()==RandomTile.class)
								image = new Image(ClassLoader.getSystemResource("SpecialTile.png").toString());
							else if(entity.getClass()==RemoveAllBarricade.class)
								image = new Image(ClassLoader.getSystemResource("RemoveAllBarricade.png").toString());
							else if(entity.getClass()==RemoveAllSpecialTile.class)
								image = new Image(ClassLoader.getSystemResource("RemoveAllTile2.png").toString());
							else if(entity.getClass()==AddBomb.class)
								image = new Image(ClassLoader.getSystemResource("Get1Bomb.png").toString());
							else if(entity.getClass()==GetBarricade.class)
								image = new Image(ClassLoader.getSystemResource("Get1Barricade.png").toString());
							else if(entity.getClass()==GetHeal.class)
								image = new Image(ClassLoader.getSystemResource("Heal.png").toString());
							else if(entity.getClass()==MovePlayer.class)
								image = new Image(ClassLoader.getSystemResource("MovePlayer.png").toString());
							else if(entity.getClass()==MoveOtherPlayer.class)
								image = new Image(ClassLoader.getSystemResource("MoveOtherPlayer.png").toString());
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
	
	public static void FadeStart(FieldCell now) {
		now.fade.stop();
		now.fade.setFromValue(1);
		now.fade.setToValue(0.2);
		now.fade.setCycleCount(Animation.INDEFINITE);
		now.fade.setDuration(Duration.millis(500));
		now.fade.setAutoReverse(true);
		now.fade.play();
	}
	
	public static void FadeStop(FieldCell now) {
		now.fade.stop();
		now.fade.setFromValue(0.2);
		now.fade.setToValue(1);
		now.fade.setCycleCount(1);
		now.fade.setDuration(Duration.millis(500));
		now.fade.setAutoReverse(false);
		now.fade.play();
	}
	
	public static boolean isNotLose(Player p) {
		boolean t=GameController.checkbfs(p, p.getFinish());
		int [] dx= {0,2,0,-2};
		int [] dy= {-2,0,2,0};
		for(int i=0;i<4;i++) {
			int X=p.getX()+dx[i];
			int Y=p.getY()+dy[i];
			if(X<0||X>=17||Y<0||Y>=17) {
				continue;
			}
			if((!GameController.getCurrentMap().getEntity((X+p.getX())/2, (Y+p.getY())/2).isBarricadeTile())&&(!GameController.getCurrentMap().getEntity(X, Y).isPlayer())) {
				t=true;
			}
		}
		return t||p.getHaveExploding()!=0||p.getHaveRemoveBarricade()!=0;
		
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
