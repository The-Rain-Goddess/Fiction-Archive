package com.rain.fiction_archive.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		mainComponentPane.getChildren().add(getMainVerticalBox());
		return mainComponentPane;
	}
	
	private VBox getMainVerticalBox(){
		VBox vbox = new VBox();
		vbox.getChildren().addAll(getMenuBar(), getMainBorderPane());
		return vbox;
	}
	
//MenuBar
	private MenuBar getMenuBar(){
		MenuBar menuBar = new MenuBar();
        
        // --- Menu File
        Menu menuFile = getMenuFileOption();
        
        // --- Menu Edit
        Menu menuEdit = getMenuEditOption();
 
        // --- Menu View
        Menu menuView = getMenuViewOption();
        
        menuBar.setMinWidth(WINDOW_MIN_WIDTH);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        menuBar.autosize();
        return menuBar;
	}
	
	private Menu getMenuFileOption(){
		Menu menu = new Menu("File");
		
		MenuItem add = getMenuItem("Add", new MenuAction(){
			@Override
			public void execute() {
				
			}
		});
		
		MenuItem remove = getMenuItem("Remove", new MenuAction(){
			@Override
			public void execute() {
				
			}
		});
		
	    menu.getItems().addAll(add, remove);
	    return menu;
	}
	
	private MenuItem getMenuItem(String name, MenuAction action){
		MenuItem item = new MenuItem(name,
            new ImageView(new Image("res/" + name.toLowerCase()+".png")));
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
			public void handle(ActionEvent t) {
                action.execute();
            }
        });
        return item;
	}
	
	private Menu getMenuEditOption(){
		Menu menu = new Menu("Edit");
		return menu;
	}
	
	private Menu getMenuViewOption(){
		Menu menu = new Menu("View");
		return menu;
	}

//Main border Pane
	private BorderPane getMainBorderPane(){
		BorderPane mainBorderPane = new BorderPane();
		mainBorderPane.setTop(getArchiveControls());
		mainBorderPane.setRight(getArchiveData());
		mainBorderPane.setLeft(getArchiveFileSystem());
		return new BorderPane();
	}
	
//Archive controls
	private HBox getArchiveControls(){
		return new HBox();
	}
	
//Archive Data
	private TabPane getArchiveData(){
		return new TabPane();
	}
	
//Archive File System
	private VBox getArchiveFileSystem(){
		return new VBox();
	}
}
