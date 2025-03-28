package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TicketBookingFormController implements Initializable {

    @FXML
    private TextField tAddress;
    
    @FXML
    private Button tBookTicketBtn;
 

    @FXML
    private TextField tAdult;


    @FXML
    private TextField tChildern;

    @FXML
    private TextField tEmail;

    @FXML
    private ChoiceBox<String> tGuide;
    
    private String[] needguide = {"Yes", "No"};
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tGuide.getItems().addAll(needguide);
    }

    @FXML
    private TextField tName;

    @FXML
    private TextField tNumber;
    
    @FXML
    private Label ticketNo;
    
    @FXML
    private Label tPrice;
    
    @FXML
    private Label tWarning;
    
    @FXML
    private Label price;
    
    
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

        
    @FXML
    void ontBookClick(ActionEvent event) {
    
        String Email = null;
        String Address = null;
    	
    	String Adult = null;
    	String Childern = null;
    	String Guide = null;
    	String Name = null;
    	String Contact = null;
    	int Price=0;
    	
    	 Address = tAddress.getText();
    	 Email = tEmail.getText();
    	 Adult = tAdult.getText();
    	 Childern = tChildern.getText();
    	 Guide = (String) tGuide.getValue();
    	 Name = tName.getText();
    	 Contact = tNumber.getText();
    	 
    	 String n1="1",n2="2",n3="3",n4="4",n5="5",n6="6";
    	 
    	 try {
    		 if(Name.length()<2 || Name.length()>30){
    			 throw new Exception(n1);
    		 }else if(Contact.length()!=10){
    			 throw new Exception(n2);
    		 }else if(Integer.parseInt(Adult)<0){
    			 throw new Exception(n3);
    		 }else if(Integer.parseInt(Childern)<0){
    			 throw new Exception(n4);
    		 }else if(Guide.isEmpty()){
    			 throw new Exception(n5);
    		 }else if(!isValidEmail(Email)){
    			 throw new Exception(n6);
    		 }else {
    			 try {
    					Class.forName("oracle.jdbc.OracleDriver");
    					Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "Zoo", "Radhey#123");
    						
    						if(Name=="" || Contact=="" || Email=="" || Adult=="" || Childern=="") {
    							tWarning.setText("warning: Please fill all * fields...");
    						}else {
    							
    							String sql = "Select * from Zoo_Ticket_Price";
    						    Statement st1 = con.createStatement();
    							ResultSet rs1 =  st1.executeQuery(sql);
    							 while (rs1.next()) {
    							        // Retrieve data from the result set
    							        int p = rs1.getInt("price");
    							        String name = rs1.getString("type");
    							        // Retrieve other columns similarly
    							        
    							        if(name.equals("adult")) {
    							        	Price += p*( Integer.parseInt(Adult));
    							        }else if(name.equals("child")) {
    							        	Price += p*( Integer.parseInt(Childern));
    							        }
    							        
    							    }
    							st1.close();
    							
    							
    						
    								String sql1 = "INSERT INTO Zoo_Ticket_temp (ticket_no , address , email , adult , children , guide , name , contact , issue_date ,  price)"
    										+ "values( "+ "tidtemp.NEXTVAL" +",'" + Address +"','" + Email + "','"+ Adult +"','"+ Childern +"','"+ Guide +"','" + Name + "','" + Contact + "','" + java.time.LocalDate.now() + "'," + Price + ")";
    						    Statement st2 = con.createStatement();
    							int rs2 =  st2.executeUpdate(sql1);
    							st2.close();
    							
    							
    							con.close();
    							
    							if(rs2==1) {
    								try {

    				        			Parent root = FXMLLoader.load(getClass().getResource("/Resources/Ticket.fxml"));
    				        			Scene scene = new Scene(root);
    				        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    				        			Stage stage1 = new Stage();
    				        	        Screen screen = Screen.getPrimary();
    				        	        Rectangle2D bounds = screen.getVisualBounds();

    				        	      
    				        	        double windowX = 610; // Set to the left side of the screen
    				        	        double windowY = 180; // Center vertically

    				        	        // Set the window properties
    				        	        stage1.setX(windowX);
    				        	        stage1.setY(windowY);
    				        	       
    				        	        
    				        			stage1.setScene(scene);
    				        			stage1.show();
    				        		
    				        		} catch(Exception e) {
    				        			e.printStackTrace();
    				        		}
    							}
    						}
    					
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		 }
    		 
    	 }catch(Exception a) {
    		if (a.getMessage()==n1) { tWarning.setText("Name must be of 3 to 30 characters");}
    		if (a.getMessage()==n2) { tWarning.setText("Please enter valid contact no");}
    		if (a.getMessage()==n3) { tWarning.setText("Please enter valid integer, Integer must be greater than or equal to 0");}
    		if (a.getMessage()==n4) { tWarning.setText("Please enter valid integer, Integer must be greater than or equal to 0");}
    		if (a.getMessage()==n5) { tWarning.setText("Please select one value");}
    		if (a.getMessage()==n6) { tWarning.setText("Please enter valid Email");}
    	 }
    	
    	
    	
    }
    
}

