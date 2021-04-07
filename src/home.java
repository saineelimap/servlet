

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//System.out.println(":::::::::::::::;");
		int empid=Integer.parseInt(request.getParameter("idnum"));
		//System.out.println("+++++++++++");
		String empname=request.getParameter("name");
		//System.out.println("_____________");
		String empln=request.getParameter("lname");
		//System.out.println("1111111111111111111111");
		String empUname=empname+empid;
		//System.out.println("222222222222222222");
		String emppwd=request.getParameter("password1");
		//System.out.println("33333333333333333333");
		String empdesig=request.getParameter("designation");
		//System.out.println("444444444444444444444");
		String empemail=request.getParameter("email");
		//System.out.println("555555555555555555");
		String empnum=request.getParameter("phnnum");
		//System.out.println("6666666666666666666666666");
	
		
		
		try {
			
			System.out.println("Driver loading.....");
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		//out.println("connecting to db2....");
		Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","ITGUSR11","miracle11");
       // out.println("connected to db2");
		String str1="insert into servletEmployee values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(str1);	
		
		pst.setInt(1, empid);
		pst.setString(2, empname);
		pst.setString(3, empln);
		pst.setString(4, empUname);
		pst.setString(5, emppwd);
		pst.setString(6, empdesig);
		pst.setString(7, empemail);
		pst.setString(8, empnum );
		
		
		
		
		pst.executeUpdate();
		//out.println("Registration Successful.");
		//out.println("Hello "+empname+"your user name is"+empUname+"and password is "+emppwd);

		if(empdesig.equals("employee")) {
			
			
			RequestDispatcher rd=request.getRequestDispatcher("employee.html");
			//out.println("Hello "+empname+"your user name is"+empUname+"and password is "+emppwd);
			rd.forward(request, response);
			
			
			
			
		}
		else if(empdesig.equals("manager")) {
			RequestDispatcher rd=request.getRequestDispatcher("manager.html");					
			rd.forward(request, response);
			out.println("hello");
			
			
			
		}else if(empdesig.contentEquals("human resource")) {
			RequestDispatcher rd=request.getRequestDispatcher("hrpage.html");
			rd.forward(request, response);
			
		}	
		
		else{
			out.println("sorry please check designation and relogin");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
		
		
		con.close();
		
	}catch(Exception e) {
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
