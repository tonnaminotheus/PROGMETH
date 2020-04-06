package gui;

import javafx.scene.control.Button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import logic.moveFail;
import logic.GameController;
import entity.base.Entity;
import interact.BlackTile;
import interact.WhiteTile;
import interact.SpecialTile;
import interact.Player;
import interact.ExplodingTile;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import entity.base.Entity;
import application.Main;

//You might need to do something to the following line
public class FieldCell extends Button {

	private Entity myEntity;
	private boolean isEmpty;

	public FieldCell(Entity entity) {

		this.setPrefWidth(40);
		this.setPrefHeight(40);
		this.setMinWidth(40);
		this.setMaxHeight(40);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Image image = null;
		System.out.println(entity.getClass());
		if (entity.isBlackTile())
			image = new Image("file:res/BlackTile35.png");
		else if (entity.isWhiteTile())
			image = new Image("file:res/WhiteTile35.png");
		else if (entity.isExplodingTile())
			image = new Image("file:res/BombTile35.png");
		else if (entity.isPlayer()) {
			int index = ((Player) entity).getIndex();
			if (index == 1)
				image = new Image("file:res/Player1Tile35.png");
			else if (index == 2)
				image = new Image("file:res/Player2Tile35.png");
			else if (index == 3)
				image = new Image("file:res/Player3Tile35.png");
			else if (index == 4)
				image = new Image("file:res/Player4Tile35.png");
			else if (index == 5)
				image = new Image("file:res/Player5Tile35.png");
			else if (index == 6)
				image = new Image("file:res/Player6Tile35.png");
			else
				image = new Image("file:res/Player7Tile35.png");
		} else if (entity.isSpecialTile()) {
			image = new Image("file:res/SpecialTile35.png");
		} else {
			image = new Image("file:res/BarricadeTile35.png");
		}
		ImageView iv = new ImageView();
		iv.setImage(image);
		this.setGraphic(iv);
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
									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
								}
							} catch (Exception ee) {
								System.out.println("can not place barricade there");
							}
						} else {
							System.out.println("That is not WhiteTile");
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
									if (GameController.getTurn() % 2 == 1)
										GameController.getPlayer1().setBarricade(haveBarricade - 1);
									else
										GameController.getPlayer2().setBarricade(haveBarricade - 1);
								} else {
									GameController.removeBarricade(now.getEntity().getX(), now.getEntity().getY());
								}
							} catch (Exception ee) {
								System.out.println("can not place barricade there");
							}
						} else {
							System.out.println("That is not WhiteTile");
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
							} catch (Exception ee) {
								System.out.println("can not move there");
							}
						} else {
							try {
								GameController.move(GameController.getPlayer2(), now.getEntity().getX(),
										now.getEntity().getY(), GameController.getPlayer2().getX(),
										GameController.getPlayer2().getY());
								pass = true;
							} catch (Exception ee) {
								System.out.println("can not move there");
							}
						}
					} else {
						System.out.println("That is not BlackTile or SpecialTile");
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
							e1.printStackTrace();
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
							if (GameController.getTurn() % 2 == 1)
								GameController.getPlayer1().setHaveRemoveBarricade(haveRemoveBarricade - 1);
							else
								GameController.getPlayer2().setHaveRemoveBarricade(haveRemoveBarricade - 1);
						} catch (Exception e1) {
							e1.printStackTrace();
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
				Image select = new Image("file:res/SelectedTile.png");
				ImageView iv1 = new ImageView(select);
				now.setGraphic(iv1);
				ControlPane.labelUpdate();
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				
				FieldCell now = (FieldCell) e.getSource();
				Image image;
				// System.out.println(entity.getClass());
				if (now.myEntity.isBlackTile())
					image = new Image("file:res/BlackTile35.png");
				else if (now.myEntity.isWhiteTile())
					image = new Image("file:res/WhiteTile35.png");
				else if (now.myEntity.isExplodingTile())
					image = new Image("file:res/BombTile35.png");
				else if (now.myEntity.isPlayer()) {
					int index = ((Player) entity).getIndex();
					if (index == 1)
						image = new Image("file:res/Player1Tile35.png");
					else if (index == 2)
						image = new Image("file:res/Player2Tile35.png");
					else if (index == 3)
						image = new Image("file:res/Player3Tile35.png");
					else if (index == 4)
						image = new Image("file:res/Player4Tile35.png");
					else if (index == 5)
						image = new Image("file:res/Player5Tile35.png");
					else if (index == 6)
						image = new Image("file:res/Player6Tile35.png");
					else
						image = new Image("file:res/Player7Tile35.png");
				} else if (now.myEntity.isSpecialTile()) {
					image = new Image("file:res/SpecialTile35.png");
				} else {
					image = new Image("file:res/BarricadeTile35.png");
				}
				ImageView iv = new ImageView();
				iv.setImage(image);
				now.setGraphic(iv);
				ControlPane.labelUpdate();
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
