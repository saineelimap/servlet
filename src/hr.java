

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
 * Servlet implementation class hr
 */
@WebServlet("/hr")
public class hr extends HttpServlet {
	String xy, yz; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		String x1=request.getParameter("username");
		String y1=request.getParameter("userpassword");
		//String e=request.getParameter("name1");
		
		try {
			
			System.out.println("Driver loading.....");
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		//out.println("connecting to db2....");
		Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","ITGUSR11","miracle11");
       // out.println("connected to db2");
		String str1="select userName,password from servletEmployee where userName=? and DESIGNATION=?";
		PreparedStatement pst=con.prepareStatement(str1);	
		
		pst.setString(1,x1);
		pst.setString(2,"human resource");
		
		
		
		
	ResultSet rs=	pst.executeQuery();
	
	while(rs.next()) {
	 xy=rs.getString("userName");
	 yz=rs.getString("password");
	//out.println(c+" "+d);
		
	}
	//out.println(x+" "+y);
		//out.println(p+" "+q);
	if(x1.equals(xy)&&y1.equals(yz) ){
		
		String str13="select id,name,lastName,userName,password,designation,email,phoneNumber from servletEmployee where userName=? and DESIGNATION=?";
		PreparedStatement pst3=con.prepareStatement(str13);	
		
		pst3.setString(1,x1);
		pst3.setString(2,"human resource");
		
	ResultSet rs3=	pst3.executeQuery();
	while(rs3.next()) {
	out.println(rs3.getInt(1)+" "+rs3.getString(2)+" "+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+" "+rs3.getString(6)+" "+rs3.getString(7)+" "+rs3.getString(8));
	//out.println(c+" "+d);
		
	}
		
		//RequestDispatcher rd=request.getRequestDispatcher(str1);
		//rd.forward(request, response);
		
	}
	else {
		
		RequestDispatcher rd=request.getRequestDispatcher("hr.html");
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
