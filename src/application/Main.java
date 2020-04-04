package application;

import gui.ControlPane;
import gui.FieldPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		//try {
			//setScene1(primaryStage);
			setScene2(primaryStage);
		//}
		//catch(Exception e) {
			
		//}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setScene1(Stage primaryStage) {
		Pane pane = new Pane();
		
		
		//background
		Image image = new Image("file:res/testMenu.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		pane.getChildren().add(iv);
		
		//StartButton
		Image playI=new Image("file:res/Play.png");
		ImageView iv1=new ImageView(playI);
		iv1.setFitHeight(100);
		iv1.setFitWidth(350);
		iv1.setLayoutX(0);
		iv1.setLayoutY(0);
		Button playB=new Button("",iv1);
		pane.getChildren().add(playB);
		
		
		
		//ExitButton
		//exitButton = new Button("Exit");
		//exitButton.setPrefWidth(150);
		//exitButton.setLayoutX(570);
		//exitButton.setLayoutY(70);
		//pane.getChildren().add(exitButton);
		
		
		//CreateScene
		Scene scene = new Scene(pane,1280,720);
		primaryStage.setTitle("Quoridor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void setScene2(Stage primaryStage) {
		HBox root = new HBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		root.setPrefHeight(400);
		
		ControlPane controlPane = new ControlPane();
		FieldPane fieldPane = new FieldPane();
		
		
		root.getChildren().add(controlPane);
		root.getChildren().add(fieldPane);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Quoridor");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
