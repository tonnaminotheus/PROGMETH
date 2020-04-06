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
import logic.GameController;
import entity.base.Entity;
import interact.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import application.Main;
import gui.ControlPane;

//You might need to do something to the following line
public class FieldCell extends Pane {

	private Entity myEntity;
	private boolean isEmpty;
	ImageView iv;

	public FieldCell(Entity entity) {

		this.setPrefWidth(50);
		this.setPrefHeight(50);
		// this.setMinWidth(40);
		// this.setMaxHeight(40);
		// this.setBackground(new Background(new BackgroundFill(Color.WHITE,
		// CornerRadii.EMPTY, Insets.EMPTY)));
		// this.setBorder(new Border(
		// new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
		// BorderWidths.DEFAULT)));
		Image image = null;
		System.out.println(entity.getClass());
		if (entity.isBlackTile())
			image = new Image("file:res/BlackTile.png");
		else if (entity.isWhiteTile()) {
			this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
			image = new Image("file:res/SpecialTile.png");
		} else {
			this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
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
									String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.noti = playermessage;

									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
								}
							} catch (Exception ee) {
								System.out.println("can not place barricade there");
								String playermessage = "Can not place barricade there";
								ControlPane.noti = playermessage;
							}
						} else {
							System.out.println("That is not WhiteTile");
							String playermessage = "That is not WhiteTile";
							ControlPane.noti = playermessage;
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
									String playermessage = GameController.getTurn() % 2 == 1
											? "Player 1 placed a barricade"
											: "Player 2 placed a barricade";
									ControlPane.noti = playermessage;
									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
								}
							} catch (Exception ee) {
								System.out.println("can not place barricade there");
								String playermessage = "Can not place barricade there";
								ControlPane.noti = playermessage;
							}
						} else {
							System.out.println("That is not WhiteTile");
							String playermessage = "That is not WhiteTile";
							ControlPane.noti = playermessage;
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
								String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 moved"
										: "Player 2 moved";
								ControlPane.noti = playermessage;
							} catch (Exception ee) {
								System.out.println("can not move there");
								String playermessage = "Can not move there";
								ControlPane.noti = playermessage;
							}
						} else {
							try {
								GameController.move(GameController.getPlayer2(), now.getEntity().getX(),
										now.getEntity().getY(), GameController.getPlayer2().getX(),
										GameController.getPlayer2().getY());
								pass = true;
								String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 moved"
										: "Player 2 moved";
								ControlPane.noti = playermessage;
							} catch (Exception ee) {
								System.out.println("can not move there");
								String playermessage = "Can not move there";
								ControlPane.noti = playermessage;
							}
						}
					} else {
						System.out.println("That is not BlackTile or SpecialTile or Bomb");
						String playermessage = "Can not move there";
						ControlPane.noti = playermessage;
					}
				} else if (Main.gameActionNow == 4) {
					int haveBomb = GameController.getTurn() % 2 == 1 ? GameController.getPlayer1().getHaveExploding()
							: GameController.getPlayer2().getHaveExploding();
					FieldCell now = (FieldCell) e.getSource();
					if (haveBomb > 0) {
						try {
							GameController.placeBomb(now.getEntity().getX(), now.getEntity().getY());
							pass = true;
							String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 placed a bomb"
									: "Player 2 placed a bomb";
							ControlPane.noti = playermessage;
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveExploding(haveBomb - 1);
							else
								GameController.getPlayer2().setHaveExploding(haveBomb - 1);
						} catch (Exception e1) {
							e1.printStackTrace();
							String playermessage = "Can not place bomb there";
							ControlPane.noti = playermessage;
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
							String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 removed a barricade"
									: "Player 2 removed a barricade";
							ControlPane.noti = playermessage;
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveRemoveBarricade(haveRemoveBarricade - 1);
							else
								GameController.getPlayer2().setHaveRemoveBarricade(haveRemoveBarricade - 1);
						} catch (Exception e1) {
							e1.printStackTrace();
							String playermessage = "That tile is not barricade";
							ControlPane.noti = playermessage;
						}
					}
				}

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
				}

			}
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile())) {
						Image select = new Image("file:res/Selected.png");
						// ImageView iv1 = new ImageView(select);
						now.iv.setImage(select);
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
												new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 17))
										.setBackground(new Background(
												new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x , y-1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x , y+1).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x*17+y-1)).setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x*17+y)).setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x*17+y+1)).setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						}
						// now.setBackground(
						// new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY,
						// Insets.EMPTY)));
					}
				}
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				FieldCell now = (FieldCell) e.getSource();
				if (Main.gameActionNow == 3 || Main.gameActionNow == 4) {
					if (!(now.myEntity.isWhiteTile() || now.myEntity.isBarricadeTile())) {
						Image image = null;
						// System.out.println(entity.getClass());
						if (now.myEntity.isBlackTile())
							image = new Image("file:res/BlackTile.png");
						else if (now.myEntity.isWhiteTile()) {
							now.setBackground(
									new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
							image = new Image("file:res/SpecialTile.png");
						} else {
							now.setBackground(
									new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
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
												new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y)).setBackground(new Background(
										new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x * 17 + y + 17))
										.setBackground(new Background(
												new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
							}
						} else {
							if (GameController.getCurrentMap().getEntity(x , y-1).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x, y).isWhiteTile()
									&& GameController.getCurrentMap().getEntity(x , y+1).isWhiteTile()) {
								((Region) Main.fieldPane.getChildren().get(x*17+y-1)).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x*17+y)).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
								((Region) Main.fieldPane.getChildren().get(x*17+y+1)).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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

	public int getSymbol() {
		if (isEmpty) {
			return -1;
		}
		return myEntity.getSymbol();
	}

}
