package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteAnimalController {

    @FXML
    private TextArea detail;

    @FXML
    private TextField pid;
    
    @FXML
    private Label status;

    @FXML
    private Button pno;

    @FXML
    private Button psubmit;

    @FXML
    private Button pyes;

    @FXML
    void no(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
    }

    @FXML
    void psubmit(ActionEvent event) {
    	String name = null;
    	String petName = null;
    		String type = null;
    		String  dob = null;
    		String disability = null;
    		String description = null;
    		String animal_id = null;
    		String weight=null;
    		
    		animal_id = pid.getText();
    		int a = Integer.parseInt(animal_id);
    		
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");	
    		String sql1 = "SELECT * FROM Zoo_Animal WHERE animal_id="+a;	
    	    Statement st1 = con.createStatement();
    		ResultSet rs1 =  st1.executeQuery(sql1);
    	
    		while (rs1.next()) {
    			weight = rs1.getString("weight");
    			petName = rs1.getString("petName");
    			type = rs1.getString("type");
    			dob = rs1.getString("dob");
    			name = rs1.getString("name");
    			disability = rs1.getString("disability");
    			description = rs1.getString("description");
    	         
    			String data ="ID: " + a + "\n Name: "+ name +"\n Weight: "+ weight+"\n PetName: "+ petName +"\n Type: "+ type +"\n Dob: "+ dob +"\n Disability: "+ disability +"\n Description: "+ description ;
    			detail.setText(data);
    			status.setText("Data found Successfully");
    	    }	
    		    		
    		st1.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void yes(ActionEvent event) {
    	String id = pid.getText();
    	int a = Integer.parseInt(id);
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    		String sql = "DELETE FROM Zoo_Animal WHERE animal_id="+ a;	
    	    Statement st = con.createStatement();
    		int rs =  st.executeUpdate(sql);
    		if(rs==1) {
            	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.close();
            }else {
            	detail.setText("Animal deletion failure..");
            }
    		st.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }

}
