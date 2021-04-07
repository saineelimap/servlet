

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class employee
 */
@WebServlet("/employee")
public class employee extends HttpServlet {
	String c,d;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		String a=request.getParameter("username");
		String b=request.getParameter("userpassword");
		//String e=request.getParameter("name1");
		
		try {
			
			System.out.println("Driver loading.....");
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		//out.println("connecting to db2....");
		Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","ITGUSR11","miracle11");
       // out.println("connected to db2");
		String str1="select userName,password from servletEmployee where userName=? and DESIGNATION=?";
		PreparedStatement pst=con.prepareStatement(str1);	
		
		pst.setString(1,a);
		pst.setString(2,"employee");
		
		
		
		
	ResultSet rs=pst.executeQuery();
	
	while(rs.next()) {
	 c=rs.getString("userName");
	 d=rs.getString("password");
	//out.println(c+" "+d);
		
	}
	out.println(a+" "+b);
		out.println(c+" "+d);
	if(a.equals(c)&&b.equals(d) ){
		
		String str11="select id,name,lastName,userName,password,designation,email,phoneNumber from servletEmployee where userName=? and DESIGNATION=?";
		PreparedStatement pst1=con.prepareStatement(str11);	
		
		pst1.setString(1,a);
		pst1.setString(2,"employee");
	ResultSet rs1=	pst1.executeQuery();
	while(rs1.next()) {
	out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(5)+" "+rs1.getString(6)+" "+rs1.getString(7)+" "+rs1.getString(8));
	//out.println(c+" "+d);
		
	}
		
		//RequestDispatcher rd=request.getRequestDispatcher(str1);
		//rd.forward(request, response);
		
	}
	else {
		
		RequestDispatcher rd=request.getRequestDispatcher("employee.html");
		rd.include(request, response);
		out.println("please check credentials.");
		
	}
	
	
	
		con.close();
		
	}catch(Exception ex) {
		ex.printStackTrace();
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
