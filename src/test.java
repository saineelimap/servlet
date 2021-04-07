import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private String date11;

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String str=request.getParameter("name1");
		int id=Integer.parseInt(request.getParameter("idnum"));
//	    java.util.Date date1;
//		try {
//		Date	date11 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("dob");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  

//		int date1=Integer.parseInt(request.getParameter("dob"));
		
		String db=request.getParameter("dob");
		String num=request.getParameter("phnnum");
		int age1=Integer.parseInt(request.getParameter("age"));
		String em=request.getParameter("email");
		String pwd=request.getParameter("pass");
		
		//out.println("Welcome your details are:"+str+" "+id+""+ db+""+num+" "+age1+""+em+" "+pwd);
		try {
			//out.println("enetered");
			//out.println("Driver loading.....");
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		//out.println("connecting to db2....");
		Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","ITGUSR11","miracle11");
		//out.println("connected to db2");
		String str1="insert into servletDemo values(?,?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(str1);	
		
		pst.setString(1,str);
		pst.setInt(2, id);
		pst.setString(3,db);
		pst.setString(4, num);
		pst.setInt(5, age1);
		pst.setString(6, em);
		pst.setString(7,pwd);
		
		pst.executeUpdate();
		out.println("Registration Successful.");
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
