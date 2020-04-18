

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logic
 */
@WebServlet("/logic")
public class logic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("uname");
		name = name.trim();
		String name2 = null;
		// 1
		System.out.println(name);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			          "jdbc:mysql://localhost:3306/ATM?user=root&password=root");
			        Statement stmt = con.createStatement();
			        
			        String sql ="select * from accountinfo where userID = '"+name+"'";
			        ResultSet rs = stmt.executeQuery(sql);

		   if (rs.next()) {
			        		
			   				   	name2 ="sucessful";
			        		
			        }else {
			        			
			        			name2 ="unsucessful";
			        			
			        	  }
		   
            switch(name2){
					
					case "sucessful":
						  name2 ="sucessful";
						  name = name.trim();
						  
						  out.println("<html><head><title>Succeful</title></head><body bgcolor=\"#3BB9FF\" ></style>");
						  out.println("<center><H5 style =\"color:green \">Successfully Logged In</H5></center><br>");
				  		  out.println("<form action=\"http://localhost:8080/finalchek/logic\" method =\"post\"></center>");
				  		  out.println("<center><input type=\"hidden\" name=\"uname\" value=\" "+ name + "\" </input>");
						  out.println("<center>Enter Your Amount: <input type=\"text\" name = \"amount\"</center><br><br>");
						  out.println("<center><input type=\"submit\" value =\"deposit\" name=\"submit\"></center>");
						  out.println("<center><input type=\"submit\" value =\"withdraw\" name=\"submit\"></center>");
						  out.println("</form></body></html>");
						  
						 
						  
						  
						  String sql1 ="select * from accountinfo where userID = '"+name+"'";
					      ResultSet rs1 = stmt.executeQuery(sql1);
					      while(rs1.next()) {
					    	  		float bal = rs1.getFloat(3);
					    	  		String holder = rs1.getString(2);
					    	  		//2
					    	  		System.out.println(bal);
					    	  		String typ = request.getParameter("submit");
					    	  		String amount = request.getParameter("amount");
					    	  		float entered = Float.parseFloat(amount);
					    	  		if(entered < 0) {
					    	  			out.println("<center><H5 style =\"color:red\">Incorrect Amount</H5></center>");
					    	  			break;
					    	  			
					    	  		}
					    	  		//3
					    	  		System.out.println(amount);
					    	  		//4
					    	  		System.out.println(typ);
					    	  			if(typ.equals("deposit")) {
					    	  				
					    	  				
					    	  				String sql2 ="UPDATE ACCOUNTINFO SET BALANCE = BALANCE + "+entered+" WHERE userID = "+name+"";
					    	  				final int rs2 =stmt.executeUpdate(sql2);
					    	  				String sql5 ="select * from accountinfo where userID = '"+name+"'";
				  					        ResultSet rs5 = stmt.executeQuery(sql5);
				  					        while(rs5.next()) {
				  					    	  		float check2  = rs5.getFloat(3);
				  					    	  	    System.out.println(check2);
				  					    	  	    out.println("<center><p style =\"color:green\">"+holder+" Deposited successfully </p></center>");
				  					    	  	    out.println("<center><H5 style =\"color:green \">Balance : "+check2+"$</H5 ></center>");  
				  					        				  }
					    	  			}
					    	  			
					    	  			else {
					    	  				if(bal < entered) {
					    	  					
					    	  					out.println("<center><H5 style =\"color:red \">Insufficient Funds,try again </H5></center>");
					    	  					
					    	  				
					    	  				}else{
					    	  					
					    	  					String sql4 ="UPDATE ACCOUNTINFO SET BALANCE = BALANCE - "+entered+" WHERE userID = "+name+"";
						    	  				final int rs4 =stmt.executeUpdate(sql4);
					    	  					
					    	  					String sql3 ="select * from accountinfo where userID = '"+name+"'";
					  					        ResultSet rs3 = stmt.executeQuery(sql3);
					  					        while(rs3.next()) {
					  					    	  		float check1  = rs3.getFloat(3);
					  					    	  	    System.out.println(check1);
					  					    	  	    out.println("<center><H5 style =\"color:green \">"+holder+" Withdraw Amount successfully </H5></center>");
					  					    	  	    out.println("<center><H5 style =\"color:red \">Balance : "+check1+"$</H5></center>");  
					  					        				  }
					    	  					
					    	  					
						    	  				
						    	  			    
						    	  				
					    	  					
					    	  					
					    	  				}
					    	  				
					    	  				
					    	  			}
					    	  }
						  
						 
					     
						 
						  
						
						  
				    	  return;
				    	
					case "unsucessful":	
						  name2 ="unsucessful";
						  out.println("<html><head><title>unsuccful</title></head><body bgcolor=\"#3BB9FF\">");
						  out.println("<center><H5 style =\"color:red \" >Your Authentication Failed try again</H5></center><br>");
				  		  out.println("<form action=\"http://localhost:8080/finalchek/logic\" method =\"post\">");
				 		  out.println("<center>USERID <input type=\"text\" name = \"uname\"</center><br>");
				 		  out.println("<center><input type=\"submit\" value=\"Login\"></center>");
				 		  out.println("</form></body></html>");
						 
				 		  return;
						
					}
					 
			        
			        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		 
		  
	}

}
