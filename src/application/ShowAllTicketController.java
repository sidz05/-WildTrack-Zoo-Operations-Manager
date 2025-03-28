package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowAllTicketController {
	 @FXML
	    private TableColumn<showAllTickets, Integer> tno;

    @FXML
    private TableColumn<showAllTickets, Integer> tcontact;

    @FXML
    private TableColumn<showAllTickets, String> tdate;

    @FXML
    private TableColumn<showAllTickets, String> temail;

    @FXML
    private TableColumn<showAllTickets, String> tguide;

    @FXML
    private TableView<showAllTickets> ticketTable;

    @FXML
    private TableColumn<showAllTickets, String> tname;

   
    @FXML
    private TableColumn<showAllTickets, String> tprice;
    
    public void initialize() {

    	// Set up the table columns
    	tno.setCellValueFactory(new PropertyValueFactory<>("tno"));

    	tname.setCellValueFactory(new PropertyValueFactory<>("name"));

    	temail.setCellValueFactory(new PropertyValueFactory<>("slides"));

    	tcontact.setCellValueFactory(new PropertyValueFactory<>("chlorination"));

    	tdate.setCellValueFactory(new PropertyValueFactory<>("date"));

    	tguide.setCellValueFactory(new PropertyValueFactory<>("startTime")); 

    	tprice.setCellValueFactory(new PropertyValueFactory<>("endTime")); 

    	

    	ObservableList<showAllTickets> showAllTicketsList = retrieveshowAllTicketsDetails();

    	ticketTable.setItems(showAllTicketsList);

    	}

    	private ObservableList<showAllTickets> retrieveshowAllTicketsDetails() {

    	ObservableList<showAllTickets> showAllTicketsList = FXCollections.observableArrayList();

    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");

    	Statement statement = connection.createStatement();

    	ResultSet resultSet = statement.executeQuery("SELECT * FROM Zoo_Ticket")) {

    	while (resultSet.next()) {

    	int tno = resultSet.getInt("ticket_no");

    	String name = resultSet.getString("name");

    	String slides = resultSet.getString("email");

    	String chlorination = resultSet.getString("contact");

    	String date = resultSet.getString("issue_date");

    	String startTime = resultSet.getString("guide");

    	int endTime = resultSet.getInt("price");
    	
    	

    	showAllTickets showAllTickets = new showAllTickets( tno, name, slides, chlorination, date, startTime, endTime);

    	showAllTicketsList.add(showAllTickets);

    	}

    	} catch (SQLException e) {

    	e.printStackTrace();

    	}

    	return showAllTicketsList;

    	}

}
