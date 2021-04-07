

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searching1
 */
@WebServlet("/searching1")
public class searching1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searching1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String empuid = request.getParameter("uid");
		String empfname = request.getParameter("fname");
		String emplname = request.getParameter("lname");
		try {

			out.println("Driver loading.....");
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			// out.println("connecting to db2....");
			Connection con = DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb", "ITGUSR11","miracle11");
			
			
			String str="select * from searchinguser where 2+1=3";
			
		     Statement stm = con.createStatement();  
		       String str1;
		       
		            
		            if(!empuid.isEmpty()&&empuid!=null)
		            {
		              
		                str1 = " and ID = "+empuid;
		                str = str+str1;
		            }
		            
		            if(!empfname.isEmpty()&&empfname!=null)
		            {
		                str1 = " and FIRSTNAME like '"+empfname+"%'";
		                str = str+str1;
		            }
		            
		            if(!emplname.isEmpty()&&emplname!=null)
		            {
		                str1 = " and LASTNAME like '"+emplname+"%'";
		                str = str+str1;
		            }
		            
		            
		            
		            ResultSet rs = stm.executeQuery(str);
		          while(rs.next()) {
		            out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
		              }
			
			
			
			
			
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
