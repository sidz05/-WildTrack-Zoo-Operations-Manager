package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalaryTableController implements Initializable{

    @FXML
    private TableView<DisplaySalaryData> eSalaryTable;

    @FXML
    private TableColumn<DisplaySalaryData, Integer> eid;

    @FXML
    private TableColumn<DisplaySalaryData, String> ename;

    @FXML
    private TableColumn<DisplaySalaryData, Integer> esalary;
    
    public void initialize(URL location, ResourceBundle resources) {
    	eid.setCellValueFactory(new PropertyValueFactory<>("DisplaySalaryDataId"));

    	ename.setCellValueFactory(new PropertyValueFactory<>("name"));

    	esalary.setCellValueFactory(new PropertyValueFactory<>("slides"));
    	

    	ObservableList<DisplaySalaryData> DisplaySalaryDataList = retrieveDisplaySalaryDataDetails();

    	eSalaryTable.setItems(DisplaySalaryDataList);

    	}
    private ObservableList<DisplaySalaryData> retrieveDisplaySalaryDataDetails() {

    	ObservableList<DisplaySalaryData> DisplaySalaryDataList = FXCollections.observableArrayList();

    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");

    	Statement statement = connection.createStatement();

    	ResultSet resultSet = statement.executeQuery("SELECT Zoo_Person.id, Zoo_Person.name , Zoo_Salary.salary FROM Zoo_Person JOIN Zoo_Salary ON Zoo_Person.position = Zoo_Salary.job_type")) {

    	while (resultSet.next()) {

    	int DisplaySalaryDataId = resultSet.getInt("id");
    	
    	String name = resultSet.getString("name");

    	String slides = resultSet.getString("salary");

    	
    	DisplaySalaryData DisplaySalaryData = new DisplaySalaryData( DisplaySalaryDataId, name, slides);

    	DisplaySalaryDataList.add(DisplaySalaryData);

    	}

    	} catch (SQLException e) {

    	e.printStackTrace();

    	}
    	
    	return DisplaySalaryDataList;
    }

}
