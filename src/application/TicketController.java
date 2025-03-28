package application;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TicketController implements Initializable{

    @FXML
    private Label taddress;

    @FXML
    private Label tadult;

    @FXML
    private Label tchildern;

    @FXML
    private Label tcontact;

    @FXML
    private Label tdate;

    @FXML
    private Label temail;

    @FXML
    private Label ticketId;

    @FXML
    private Label tname;

    @FXML
    private Label tprice;
    
    String guide = null;
    
    public void initialize(URL location, ResourceBundle resources) {
        // Perform your data insertion here
        m1();
    }
    
    int no=0;
    
    private void m1(){
    	int p1 = 0;
        int price = 0;
    	String address = null;
        String email = null;
        String adult = null;
        String children = null;
        
        String name = null;
        String contact = null;
        String issue_date = null;

    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    		
    		
    		String sql = "SELECT MAX( ticket_no ) AS max_id FROM Zoo_Ticket_temp";	
    	    Statement st = con.createStatement();
    		ResultSet rs =  st.executeQuery(sql);
    
    		while (rs.next()) {
		        int p = rs.getInt("max_id");
		        no=p;
		    }		    		
    		st.close();
  	    		String sql1 = "SELECT * FROM Zoo_Ticket_temp WHERE ticket_no="+no;	
	    	    Statement st1 = con.createStatement();
	    		ResultSet rs1 =  st1.executeQuery(sql1);
	    
	    		while (rs1.next()) {
			         p1 = rs1.getInt("ticket_no");
			         price = rs1.getInt("price");
			         address = rs1.getString("address");
			         email = rs1.getString("email");
			         adult = rs1.getString("adult");
			         children = rs1.getString("children");
			         guide = rs1.getString("guide");
			         name = rs1.getString("name");
			         contact = rs1.getString("contact");
			         issue_date = rs1.getString("issue_date");
			    }		    		
	    		st1.close();
	    	
    		con.close();
    		
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
    	ticketId.setText(no+"");
        tprice.setText(price+"");
        taddress.setText(address);
        temail.setText(email);
        tadult.setText(adult);
        tchildern.setText(children);
        tname.setText(name);
        tcontact.setText(contact);
        tdate.setText(issue_date);	
    		
   
    }

    @FXML
    void onCancelClick(ActionEvent event) {
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    		String sql = "DELETE FROM Zoo_Ticket_temp WHERE ticket_no="+ no;	
    	    Statement st = con.createStatement();
    		int rs =  st.executeUpdate(sql);
    		if(rs==1) 
    		{
    		    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			stage.close();
    		}
    		st.close();
    		con.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void onConfirmClick(ActionEvent event) {
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
			
						String sql1 = "INSERT INTO Zoo_Ticket(ticket_no , address , email , adult , children , guide , name , contact , issue_date ,  price)"
								+ "values( "+ "tid.NEXTVAL" +",'" + taddress.getText() +"','" + temail.getText() + "','"+ tadult.getText() +"','"+ tchildern.getText() +"','"+ guide +"','" + tname.getText() + "','" + tcontact.getText() + "'," + java.time.LocalDate.now() + "," + tprice.getText() + ")";
				    Statement st2 = con.createStatement();
					int rs2 =  st2.executeUpdate(sql1);
					st2.close();
					
					
					con.close();
					
					if(rs2==1) {
						
						try {
				    		Class.forName("oracle.jdbc.OracleDriver");
				    		Connection con1 = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
				    		String sql = "DELETE FROM Zoo_Ticket_temp WHERE ticket_no="+ no;	
				    	    Statement st = con1.createStatement();
				    		int rs =  st.executeUpdate(sql);
				    		if(rs==1) 
				    		{
				    		    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				    			stage.close();
				    		}
				    		st.close();
				    		con1.close();
				    	} catch (SQLException e) {
				    		e.printStackTrace();
				    	} catch (ClassNotFoundException e) {
				    		e.printStackTrace();
				    	}
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						stage.close();
		        		
					}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}



