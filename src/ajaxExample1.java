

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ajaxExample1
 */
@WebServlet("/ajaxExample1")
public class ajaxExample1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxExample1() {
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
		
		String num=request.getParameter("val");
	//System.out.println(num);
		
	
		
	try {
	System.	out.println("Driver loading.....");
		Class.forName("com.ibm.db2.jcc.DB2Driver");			
	System.out.println("connecting to db2....");
	Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","ITGUSR11","miracle11");
		System.out.println("connected to db2");
	
			String str="select * from ajaxuser where ID=?";
			PreparedStatement ps= con.prepareStatement(str);
			ps.setString(1, num);
			//System.out.println(num);
		ResultSet rs=ps.executeQuery();
	//System.out.println("hloooo");
		while(rs.next()) {
		out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			
		}
	 //System.out.println("jjjjjjjjjjjjjjjjjj");		
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
