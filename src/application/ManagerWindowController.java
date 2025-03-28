package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManagerWindowController {

    @FXML
    private Button addAnimalBtn;

    @FXML
    private Button addEmpBtn;

    @FXML
    private Button deleteAnimalBtn;

    @FXML
    private Button deleteEmpBtn;
    
    @FXML
    private TableColumn<DisplayPersonData, Integer> pid;

    @FXML
    private TableColumn<DisplayPersonData, String> pname;

    @FXML
    private TableColumn<DisplayPersonData, String> pposition;

    @FXML
    private TableView<DisplayPersonData> ptable;
    
    @FXML
    private TableColumn<DisplayAnimalData, Integer> aid;

    @FXML
    private TableColumn<DisplayAnimalData, String> aname;

    @FXML
    private TableColumn<DisplayAnimalData, String> apetname;

    @FXML
    private TableView<DisplayAnimalData> atable;
    
    @FXML
    private Button sanim;

    @FXML
    private Button semp;
    
    @FXML
    private Label totalCollection;
    
    int totalprice=0;

    @FXML
    void OnSalaryClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/SalaryTable.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void OnShowAllTicketsClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/ShowAllTickets.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onCollectionCLick(ActionEvent event) {
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    		String sql = "Select Sum(price) As tprice from Zoo_Ticket";	
    	    Statement st = con.createStatement();
    		ResultSet rs =  st.executeQuery(sql);
    		while (rs.next()) {
		        int p = rs.getInt("tprice");
		        totalprice= p;
		    }		    	
    		st.close();
    		con.close();
    		totalCollection.setText(""+totalprice);
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void ShowAnimalData(ActionEvent event) {
    	aid.setCellValueFactory(new PropertyValueFactory<>("DisplayAnimalDataId"));

    	aname.setCellValueFactory(new PropertyValueFactory<>("name"));

    	apetname.setCellValueFactory(new PropertyValueFactory<>("slides"));
    	

    	ObservableList<DisplayAnimalData> DisplayAnimalDataList = retrieveDisplayAnimalDataDetails();

    	atable.setItems(DisplayAnimalDataList);

    	}
    private ObservableList<DisplayAnimalData> retrieveDisplayAnimalDataDetails() {

    	ObservableList<DisplayAnimalData> DisplayAnimalDataList = FXCollections.observableArrayList();

    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");

    	Statement statement = connection.createStatement();

    	ResultSet resultSet = statement.executeQuery("SELECT * FROM Zoo_Animal")) {

    	while (resultSet.next()) {

    	int DisplayAnimalDataId = resultSet.getInt("animal_id");
    	
    	String name = resultSet.getString("name");

    	String slides = resultSet.getString("petName");

    	
    	DisplayAnimalData DisplayAnimalData = new DisplayAnimalData( DisplayAnimalDataId, name, slides);

    	DisplayAnimalDataList.add(DisplayAnimalData);

    	}

    	} catch (SQLException e) {

    	e.printStackTrace();

    	}
    	
    	return DisplayAnimalDataList;
    }

    @FXML
    void ShowEmployeedata(ActionEvent event) {
    	pid.setCellValueFactory(new PropertyValueFactory<>("DisplayPersonDataId"));

    	pname.setCellValueFactory(new PropertyValueFactory<>("name"));

    	pposition.setCellValueFactory(new PropertyValueFactory<>("slides"));
    	

    	ObservableList<DisplayPersonData> DisplayPersonDataList = retrieveDisplayPersonDataDetails();

    	ptable.setItems(DisplayPersonDataList);

    	}
    private ObservableList<DisplayPersonData> retrieveDisplayPersonDataDetails() {

    	ObservableList<DisplayPersonData> DisplayPersonDataList = FXCollections.observableArrayList();

    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");

    	Statement statement = connection.createStatement();

    	ResultSet resultSet = statement.executeQuery("SELECT * FROM Zoo_Person")) {

    	while (resultSet.next()) {

    	int DisplayPersonDataId = resultSet.getInt("id");
    	
    	String name = resultSet.getString("name");

    	String slides = resultSet.getString("position");

    	
    	DisplayPersonData DisplayPersonData = new DisplayPersonData( DisplayPersonDataId, name, slides);

    	DisplayPersonDataList.add(DisplayPersonData);

    	}

    	} catch (SQLException e) {

    	e.printStackTrace();

    	}
    	
    	return DisplayPersonDataList;
    }

    
    public void initialize() {

    	// Set up the table columns

    	
    	
    	

    	}

    @FXML
    void onAddAnimalBtnClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/AddAnimal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onAddEmpBtnClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/AddEmployee.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onDeleteAnimalBtnClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/DeleteAnimal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onDeleteEmpBtnClick(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/DeleteEmployee.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = new Stage();
			stage1.setTitle("Zoo Management System");
			stage1.setScene(scene);
			stage1.show();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

   

}
