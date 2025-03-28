package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAnimalController implements Initializable{

    @FXML
    private Button addAnimalBtn;

    @FXML
    private TextField adescription;

    @FXML
    private ChoiceBox<String> adisability;
 private String[] dis = {"Yes", "No"};
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	adisability.getItems().addAll(dis);
    }

    @FXML
    private DatePicker adob;

    @FXML
    private TextField aname;

    @FXML
    private TextField apetname;

    @FXML
    private TextField atype;

    @FXML
    private TextField aweight;
    
    @FXML
    private Label aWarning;

    @FXML
    void onAddAnimalClick(ActionEvent event) {
    	
    	String name = null;
        String petName = null;
     	String type = null;
     	LocalDate  dob;
     	String disability = null;
     	String description = null;
     	int animal_id = 0;
     	String weight=null;
     	
     	name = aname.getText();
     	petName = apetname.getText();
     	type = atype.getText();
     	dob = adob.getValue();
     	disability = (String) adisability.getValue();
     	description = adescription.getText();
     	weight = aweight.getText();
     	
     	String n1="1",n2="2",n3="3",n4="4",n5="5",n6="6";
     	
     	 try {
    		 if(name.length()==0||name.length()>=15){
    			 throw new Exception(n1);
    		 }else if(petName.length()==0||petName.length()>=10){
    			 throw new Exception(n2);
    		 }else if(type.length()>=10){
    			 throw new Exception(n3);
    		 }else if(description.length()>150){
    			 throw new Exception(n4);
    		 }else if(Integer.parseInt(weight)<0){
    			 throw new Exception(n5);
    		 }else {
     	 
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    		String sql1 = "INSERT INTO Zoo_Animal(animal_id ,name , petName , type , dob , weight , disability , description )"
    				+ "values("+"taid.NEXTVAL" +",'"+name+"', '"+petName+"' , '"+type+"' ,'"+ dob +"',"+ weight + ", '"+disability+"' , '"+description+"' )";	
    	    Statement st1 = con.createStatement();
    		int rs1 =  st1.executeUpdate(sql1);

            if(rs1==1) {
            	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.close();
            }else {
            	aWarning.setText("Animal enroll failure..");
            }
    		    		
    		st1.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
 }
    		 
    	 }catch(Exception a) {
    		if (a.getMessage()==n1) { aWarning.setText("Name must be between 3 to 15 characters");}
    		if (a.getMessage()==n2) { aWarning.setText("Pet Name must be between 3 to 10 characters");}
    		if (a.getMessage()==n3) { aWarning.setText("Type must be between 3 to 10 characters");}
    		if (a.getMessage()==n4) { aWarning.setText("Description must be less than 150 characters");}
    		if (a.getMessage()==n5) { aWarning.setText("Please enter integer value");}
    	 }
    }

}
