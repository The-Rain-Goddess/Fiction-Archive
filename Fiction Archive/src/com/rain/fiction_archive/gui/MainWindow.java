package com.rain.fiction_archive.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindow extends Application{
	private final static int WINDOW_MIN_WIDTH = 1000;
	private final static int WINDOW_MIN_HEIGHT = 700;
	
	public void begin(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		AnchorPane mainComponentPane = getMainComponentPane();
		
		Scene mainScene = new Scene(mainComponentPane);
		rootStage.setScene(mainScene);
		rootStage.show();
	}
	
	private AnchorPane getMainComponentPane(){
		AnchorPane mainComponentPane = new AnchorPane();
		mainComponentPane.setMinWidth(WINDOW_MIN_WIDTH);
		mainComponentPane.setMinHeight(WINDOW_MIN_HEIGHT);
		return mainComponentPane;
	}
}
