package com.rain.fiction_archive.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rain.fiction_archive.Main;
import com.rain.fiction_archive.files.Fandom;
import com.rain.fiction_archive.files.Fiction;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{
	private final static int WINDOW_MIN_WIDTH = 1000;
	private final static int WINDOW_MIN_HEIGHT = 700;
	private final static Insets DEFAULT_INSETS = new Insets(10,10,10,10);
	private TabPane tabs;
	
	public void begin(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		AnchorPane mainComponentPane = getMainComponentPane();
		Scene mainScene = new Scene(mainComponentPane);
		mainComponentPane.prefHeightProperty().bind(mainScene.heightProperty());
		mainComponentPane.prefWidthProperty().bind(mainScene.widthProperty());  
		
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
		return mainBorderPane;
	}
	
//Archive controls
	private HBox getArchiveControls(){
		return new HBox();
	}
	
//Archive Data
	private AnchorPane getArchiveData(){
		AnchorPane anchor = new AnchorPane();
		
		//tabs
		tabs = new TabPane();
		tabs.setPrefWidth(WINDOW_MIN_WIDTH * 2 / 3);
		tabs.getTabs().addAll(getTabs());
		
		//controls
		HBox controls = new HBox();
		controls.getChildren().addAll(getAddTabButton());
		
		//formatting
		anchor.getChildren().addAll(tabs, controls);
		AnchorPane.setTopAnchor(controls, 3.0);
        AnchorPane.setRightAnchor(controls, 5.0);
        AnchorPane.setTopAnchor(tabs, 1.0);
        AnchorPane.setRightAnchor(tabs, 1.0);
        AnchorPane.setLeftAnchor(tabs, 1.0);
        AnchorPane.setBottomAnchor(tabs, 1.0);
        
		return anchor;
	}
	
	private List<Tab> getTabs() {
		List<Tab> tabs = new ArrayList<>();
		List<Fandom> data = Main.getMasterDataAsList();
		for(int i = 0; i < data.size(); i++) {
			tabs.add(getFandomTab(data.get(i)));
		} return tabs;
	}
	
	private Tab getFandomTab(Fandom f){
		Tab tab = new Tab();
		tab.setText(f.getName());
		tab.setContent(setupFandomDataTable(f.getFictions()));
		return tab;
	}
	
	private VBox setupFandomDataTable(List<Fiction> dataForTable){
		VBox container = new VBox();
		HBox controls = new HBox();
		controls.setAlignment(Pos.TOP_RIGHT);
		final TextField search = new TextField();
		controls.getChildren().addAll(new Label("Search"), search);
		
		final TableView<Fiction> table = new TableView<Fiction>();
		table.setId("Data Table");
		
		//ititialize columns
		table.getColumns().setAll(getTableColumns());
		
		if(dataForTable!=null){
			//sort and filter the data
			SortedList<Fiction> sortedData = getSortedData(search, dataForTable);
			
			// Bind the SortedList comparator to the TableView comparator
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			// Show the Data
			table.setItems(sortedData);
		}
		container.getChildren().addAll(controls, table);
		VBox.setMargin(controls, DEFAULT_INSETS);
		return container;
	}
	
	private List<TableColumn<Fiction, ?>> getTableColumns(){
		//Column 1: Name
		TableColumn<Fiction, String> nameCol = new TableColumn<Fiction, String>("Name");
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		
		//Column 2: Author
		TableColumn<Fiction, String> authorCol = new TableColumn<Fiction, String>("Author");
		authorCol.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
		
		return Arrays.asList(nameCol, authorCol);		
	}
	
	private SortedList<Fiction> getSortedData(TextField search, List<Fiction> list){
		//get the data
		List<Fiction> data = list;
				
		//wrap the ObservableList in a FilteredList
		FilteredList<Fiction> filteredData = new FilteredList<Fiction>(FXCollections.observableList(data), p -> true);
		
		//set the filter Predicate whenever the filter changes
		search.textProperty().addListener((observable, oldValue, newValue)-> {
			filteredData.setPredicate(media -> {
				//If filter is empty, display all
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				if(media.getName().toLowerCase().contains(lowerCaseFilter))
					return true;
				else if(media.getAuthor().toString().toLowerCase().contains(lowerCaseFilter))
					return true;
				
				else return false;
			});
		});
		
		//wrap the filtered list in a sorted list
		SortedList<Fiction> sortedData = new SortedList<Fiction>(filteredData);
		
		return sortedData;
	}
	
	private Button getAddTabButton(){
		Button add = new Button();
		add.setGraphic(new ImageView(new Image("res/add.png")));
		add.setOnAction((ActionEvent e) -> {
			showAddFandomWindow();
		});
		return add;
	}
	
	private void showAddFandomWindow(){
		//create new Stage
		Stage addWindowRootStage = new Stage();
		addWindowRootStage.setTitle("ADD New Fandom");
		GridPane componentLayout = getAddWindowLayout();
		
		//add all window components to componentLayout
		componentLayout.getChildren().addAll(getAddWindowComponents(addWindowRootStage));
		
		//create Scene
		Scene scene = new Scene(componentLayout,550,300);
		
		//set Screen to show Scene
		addWindowRootStage.setScene(scene);
		addWindowRootStage.show();
	}
	
	private GridPane getAddWindowLayout(){
		GridPane componentLayout = new GridPane();
		componentLayout.setPadding(new Insets(10, 10, 10, 10));
		componentLayout.setVgap(5);
		componentLayout.setHgap(5);
		return componentLayout;
	}
	
	private List<Control> getAddWindowComponents(Stage root){
		return new ArrayList<Control>();
	}
	
//Archive File System
	private VBox getArchiveFileSystem(){
		return new VBox();
	}
}
