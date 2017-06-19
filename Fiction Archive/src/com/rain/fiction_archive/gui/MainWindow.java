package com.rain.fiction_archive.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

import com.rain.fiction_archive.Main;
import com.rain.fiction_archive.files.Domain;
import com.rain.fiction_archive.files.Fandom;
import com.rain.fiction_archive.files.FandomAttributes;
import com.rain.fiction_archive.files.Fiction;
import com.rain.fiction_archive.files.FictionIdentificationAttributes;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private Button addFiction;
	
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
		addFiction = new Button();
		
		//tabs
		tabs = new TabPane();
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs.setPrefWidth(WINDOW_MIN_WIDTH * 8 / 10);
		tabs.getTabs().addAll(getTabs());
		
		//controls
		HBox controls = new HBox();
		controls.getChildren().addAll(addFiction, getAddTabButton());
		
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
		if(data.size()!=0){ 
			updateAddFictionButton(data.get(0));
			for(int i = 0; i < data.size(); i++) {
				tabs.add(getFandomTab(data.get(i)));
			} return tabs;
		} else{ //initial setup conditions
			Tab t = new Tab("Initial");
			t.setContent(new TableView<Fandom>());
			return Arrays.asList(t);
		}
	}
	
	private void updateAddFictionButton(Fandom fandom){
		if(!fandom.getName().equals("All")){
			this.addFiction.setVisible(true);
			this.addFiction.setText("Add to " + fandom.getName());
			addFiction.setGraphic(new ImageView(new Image("res/add.png")));
			addFiction.setOnAction((ActionEvent e) -> {
				showAddFictionWindow(fandom);
			});
		} else {
			this.addFiction.setVisible(false);
		}
	}
	
	private void showAddFictionWindow(Fandom fandom){
		System.out.println("Adding fiction to " + fandom.getName());

		//create new Stage
		Stage addFictionWindowRootStage = new Stage();
		addFictionWindowRootStage.setTitle("ADD New Fiction");
		GridPane componentLayout = getAddFandomWindowLayout();
		
		//add all window components to componentLayout
		componentLayout.getChildren().addAll(getAddFictionWindowComponents(addFictionWindowRootStage, fandom));
		
		//create Scene
		Scene scene = new Scene(componentLayout,450,150);
		
		//set Screen to show Scene
		addFictionWindowRootStage.setScene(scene);
		addFictionWindowRootStage.show();
	}
	
	private List<Control> getAddFictionWindowComponents(Stage root, final Fandom fandom){
		List<Control> windowComponents = new ArrayList<>();
		
		//Name
		final Label titleLabel = new Label("Title: ");
		final TextField titleField = new TextField();
		titleField.setPromptText("Title *");
		titleField.setPrefColumnCount(30);
		//titleField.setFocusTraversable(false);
		
		//Author
		final Label authorLabel = new Label("Author: ");
		final TextField authorField = new TextField();
		authorField.setPromptText("Author *");
		
		//Word Count
		final Label domainLabel = new Label("Domain: ");
		final TextField domainField = new TextField();
		domainField.setPromptText("Ao3, FFNet, etc... *");
		
		final ComboBox<Domain> domainBox = new ComboBox<>();
		domainBox.getItems().addAll(Domain.values());
		
		//required
		final Label required = new Label("* Required");
		required.setFocusTraversable(false);
		
		//submit
		Button submit = new Button("Lookup");
		submit.setOnAction((ActionEvent e) -> {
			if(	   !titleField.textProperty().getValue().equals("")
				&& !authorField.textProperty().get().equals("")
				&& domainBox.getValue() != null){
				System.out.println("All conditions met");
				root.close();
				Future<Fiction> future = Main.getThreadPool()
													.submit(
														new FictionSearch(
															new FictionIdentificationAttributes()
																.setAuthor(authorField.textProperty().get())
																.setTitle(titleField.textProperty().get())
																.setDomain(domainBox.getValue())));
				Fiction fiction = null;
				try {
					fiction = future.get();
					System.out.println(fiction);
				} catch (Exception x) {
					x.printStackTrace();
					String message = "Unable to find " 
									+ titleField.textProperty().get() 
									+ " by " 
									+ authorField.textProperty().get();
					System.out.println(message);
					displayErrorMessage(message);
				}
				if(fiction != null){
					System.out.println("Adding fiction: " + fiction + "\n to Fandom " + fandom.getName() + "\n");
					fandom.getFictions().add(fiction);
					Main.getMasterDataAsList().get(0).getFictions().add(fiction);
				}
				this.updateTabs(fandom);
			}
		});
		
		submit.setOnKeyPressed((KeyEvent e) -> {
			if(!titleField.textProperty().getValue().equals("")
					&& !authorField.textProperty().get().equals("")
					&& domainBox.getValue() != null
					&& e.getCode() == KeyCode.ENTER){
					System.out.println("All conditions met");
			}
		});
		
		//constraints
		GridPane.setConstraints(titleLabel, 0, 0);
		GridPane.setConstraints(titleField, 1, 0);
		GridPane.setConstraints(submit, 1, 3);
		GridPane.setHalignment(submit, HPos.CENTER);
		
		GridPane.setConstraints(authorLabel, 0, 1);
		GridPane.setConstraints(authorField, 1, 1);
		
		GridPane.setConstraints(domainLabel, 0, 2);
		GridPane.setConstraints(domainBox, 1, 2);
		
		GridPane.setConstraints(required, 0, 3);
		
		windowComponents.addAll(
					Arrays.asList(	titleLabel, titleField,
									authorLabel, authorField,
									domainLabel, domainBox, 
									required, submit));
		return windowComponents;
	}
	
	private void displayErrorMessage(String message){
		Stage errorWindow = new Stage();
		VBox componentLayout = new VBox();
		Label errorLabel = new Label(message);
		VBox.setMargin(errorLabel, new Insets(10,10,10,10));
		componentLayout.getChildren().addAll(errorLabel);
		Scene scene = new Scene(componentLayout);
		errorWindow.setScene(scene);
		errorWindow.show();
	}
	
	private Tab getFandomTab(Fandom f){
		Tab tab = new Tab();
		tab.setText(f.getName());
		tab.setContent(setupFandomDataTable(f.getFictions()));
		tab.setOnSelectionChanged((Event e)->{
			updateAddFictionButton(f);
		});
		return tab;
	}
	
	private VBox setupFandomDataTable(List<Fiction> dataForTable){
		VBox container = new VBox();
		HBox controls = new HBox();
		controls.setAlignment(Pos.TOP_RIGHT);
		final TextField search = new TextField();
		controls.getChildren().addAll(new Label("Search"), search);
		HBox.setMargin(search, new Insets(0,10,0,10));
		
		final TableView<Fiction> table = new TableView<Fiction>();
		table.setId("Data Table");
		table.setMinHeight(WINDOW_MIN_HEIGHT-150);
		
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
		
		//Column 3: Word Count
		TableColumn<Fiction, String> lengthCol = new TableColumn<Fiction, String>("Word Count");
		lengthCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.format(Locale.US, "%,d", cellData.getValue().getWordCount())));
		
		//Column 4: Chapter Count
		TableColumn<Fiction, Number> chapterCol = new TableColumn<Fiction, Number>("Chapters");
		chapterCol.setCellValueFactory(cellData -> cellData.getValue().getChapterCountProperty());
		
		//Column 5: Chapter Bookmark
		TableColumn<Fiction, Number> bookmarkedCol = new TableColumn<Fiction, Number>("Bookmarked");
		bookmarkedCol.setCellValueFactory(cellData -> cellData.getValue().getBookmarkChapterProperty());
		
		//Column 6: Favorite Count
		TableColumn<Fiction, Number> favoritesCol = new TableColumn<Fiction, Number>("Favorites");
		favoritesCol.setCellValueFactory(cellData -> cellData.getValue().getFavoriteCountProperty());
		
		//Column 7: Follow Count
		TableColumn<Fiction, Number> followsCol = new TableColumn<Fiction, Number>("Follows");
		followsCol.setCellValueFactory(cellData -> cellData.getValue().getFollowCountProperty());
		
		//Column 8: Review Count
		TableColumn<Fiction, Number> reviewsCol = new TableColumn<Fiction, Number>("Reviews");
		reviewsCol.setCellValueFactory(cellData -> cellData.getValue().getReviewCountProperty());
		
		//Column 9: Personal Rating
		TableColumn<Fiction, Number> ratingCol = new TableColumn<Fiction, Number>("Personal Rating");
		ratingCol.setCellValueFactory(cellData -> cellData.getValue().getBookmarkChapterProperty());
		
		return Arrays.asList(nameCol, authorCol, bookmarkedCol, chapterCol, lengthCol,
								followsCol, favoritesCol, reviewsCol,
								ratingCol);		
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
		Button add = new Button("Add Fandom");
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
		GridPane componentLayout = getAddFandomWindowLayout();
		
		//add all window components to componentLayout
		componentLayout.getChildren().addAll(getAddFandomWindowComponents(addWindowRootStage));
		
		//create Scene
		Scene scene = new Scene(componentLayout,475,50);
		
		//set Screen to show Scene
		addWindowRootStage.setScene(scene);
		addWindowRootStage.show();
	}
	
	private GridPane getAddFandomWindowLayout(){
		GridPane componentLayout = new GridPane();
		componentLayout.setPadding(new Insets(10, 10, 10, 10));
		componentLayout.setVgap(5);
		componentLayout.setHgap(5);
		return componentLayout;
	}
	
	private List<Control> getAddFandomWindowComponents(Stage root){
		List<Control> controls = new ArrayList<>();
		
		//Name
		final Label nameLabel = new Label("Name: ");
		final TextField nameField = new TextField();
		nameField.setPromptText("Name *");
		nameField.setPrefColumnCount(30);
		//nameField.setEditable(false);
		//nameField.setFocusTraversable(false);
		Button addButton = new Button("Submit");
		addButton.setOnAction((ActionEvent e) -> {
			if(!nameField.getText().equals("")){
				FandomAttributes attrs = new FandomAttributes()
											.setName(nameField.getText())
											.setPath(Main.getHomeDir() + nameField.getText());
				onSubmit(attrs);
				root.close();
			}
		});
		
		addButton.setOnKeyPressed((KeyEvent e) ->{
			if(!nameField.getText().equals("") && e.getCode() == KeyCode.ENTER){
				onSubmit(
							new FandomAttributes()
								.setName(nameField.getText())
									.setPath(Main.getHomeDir() + nameField.getText())
										);
				root.close();
			}
		});
		
		GridPane.setConstraints(addButton, 2, 0);
		GridPane.setConstraints(nameField, 1, 0);
		GridPane.setConstraints(nameLabel, 0, 0);
		
		
		controls.addAll(Arrays.asList(nameLabel, nameField, addButton));
		return controls;
	}
	
	private void onSubmit(FandomAttributes attrs){
		Fandom newFandom = new Fandom(attrs, Main.getNextUUID());
		System.out.println("Added new Fandom: " + newFandom.getName());
		Main.getMasterArchiveDataMap().put(newFandom.getUUID(), newFandom);
		
		updateTabs(newFandom);
	}
	
	@SuppressWarnings("unused")
	private void updateTabs(){
		tabs.getTabs().clear();
		tabs.getTabs().addAll(getTabs());
	}
	
	private void updateTabs(Fandom fandom){
		tabs.getTabs().clear();
		tabs.getTabs().addAll(getTabs());
		selectSpecificTab(fandom);
	}
	
	private void selectSpecificTab(Fandom fandom){
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		Tab tabToSelect = getTabToSelect(fandom);
		if(tabToSelect != null)
			selectionModel.select(tabToSelect);
	}
	
	private Tab getTabToSelect(Fandom fandom){
		List<Tab> list = tabs.getTabs();
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getText().equals(fandom.getName()))
				return list.get(i);
		} return null;
	}
	
//Archive File System
	private VBox getArchiveFileSystem(){
		return new VBox();
	}
}
