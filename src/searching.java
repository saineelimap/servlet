
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
 * Servlet implementation class searching
 */
@WebServlet("/searching")
public class searching extends HttpServlet {
	// String a,b,c,d,e,f;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searching() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			Connection con = DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb", "ITGUSR11",
					"miracle11");

			if (empuid.isEmpty() && !empfname.isEmpty() && emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str1 = "select * from searchinguser where FIRSTNAME LIKE ?";
				PreparedStatement pst4 = con.prepareStatement(str1);

				pst4.setString(1, empfname + "%");

				ResultSet rs4 = pst4.executeQuery();
				while (rs4.next()) {
					out.println(rs4.getInt(1) + " " + rs4.getString(2) + " " + rs4.getString(3) + " " + rs4.getInt(4)
							+ " " + rs4.getString(5) + " " + rs4.getString(6));

				}

			}
			if (empuid.isEmpty() && empfname.isEmpty() && !emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str2 = "select * from searchinguser where LASTNAME LIKE ?";
				PreparedStatement pst1 = con.prepareStatement(str2);

				pst1.setString(1, emplname + "%");

				ResultSet rs1 = pst1.executeQuery();
				while (rs1.next()) {
					out.println(rs1.getInt(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " " + rs1.getInt(4)
							+ " " + rs1.getString(5) + " " + rs1.getString(6));

				}

			}

			if (!empuid.isEmpty() && empfname.isEmpty() && emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str3 = "select * from searchinguser where id LIKE ?";
				PreparedStatement pst2 = con.prepareStatement(str3);

				pst2.setString(1, empuid + "%");

				ResultSet rs2 = pst2.executeQuery();
				while (rs2.next()) {
					out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getString(3) + " " + rs2.getInt(4)
							+ " " + rs2.getString(5) + " " + rs2.getString(6));

				}

			}
			if (empuid.isEmpty() && empfname.isEmpty() && emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str4 = "select * from searchinguser";
				PreparedStatement pst3 = con.prepareStatement(str4);

				ResultSet rs3 = pst3.executeQuery();
				while (rs3.next()) {
					out.println(rs3.getInt(1) + " " + rs3.getString(2) + " " + rs3.getString(3) + " " + rs3.getInt(4)
							+ " " + rs3.getString(5) + " " + rs3.getString(6));

				}

			}
			if (!empuid.isEmpty() && !empfname.isEmpty() && !emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str5 = "select * from searchinguser where id=? and FIRSTNAME=? and LASTNAME=?";
				PreparedStatement pst5 = con.prepareStatement(str5);

				pst5.setString(1, empuid);
				pst5.setString(2, empfname);
				pst5.setString(3, emplname);

				ResultSet rs5 = pst5.executeQuery();
				while (rs5.next()) {
					out.println(rs5.getInt(1) + " " + rs5.getString(2) + " " + rs5.getString(3) + " " + rs5.getInt(4)
							+ " " + rs5.getString(5) + " " + rs5.getString(6));

				}

			}
			if (empuid.isEmpty() && !empfname.isEmpty() && !emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str6 = "select * from searchinguser where FIRSTNAME LIKE ? and LASTNAME LIKE ?";
				PreparedStatement pst6 = con.prepareStatement(str6);

				// pst5.setString(1, empuid);
				pst6.setString(1, empfname + "%");
				pst6.setString(2, emplname + "%");

				ResultSet rs6 = pst6.executeQuery();
				while (rs6.next()) {
					out.println(rs6.getInt(1) + " " + rs6.getString(2) + " " + rs6.getString(3) + " " + rs6.getInt(4)
							+ " " + rs6.getString(5) + " " + rs6.getString(6));

				}

			}

			if (!empuid.isEmpty() && !empfname.isEmpty() && emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str7 = "select * from searchinguser where  id=? and FIRSTNAME LIKE ?";
				PreparedStatement pst7 = con.prepareStatement(str7);

				// pst5.setString(1, empuid);
				pst7.setString(1, empuid);
				pst7.setString(2, empfname + "%");

				ResultSet rs7 = pst7.executeQuery();
				while (rs7.next()) {
					out.println(rs7.getInt(1) + " " + rs7.getString(2) + " " + rs7.getString(3) + " " + rs7.getInt(4)
							+ " " + rs7.getString(5) + " " + rs7.getString(6));

				}

			}
			if (!empuid.isEmpty() && empfname.isEmpty() && !emplname.isEmpty()) {
				out.println("222222222222222222222222");

				String str8 = "select * from searchinguser where  id=? and LASTNAME LIKE ?";
				PreparedStatement pst8 = con.prepareStatement(str8);

				// pst5.setString(1, empuid);
				pst8.setString(1, empuid);
				pst8.setString(2, emplname + "%");

				ResultSet rs8 = pst8.executeQuery();
				while (rs8.next()) {
					out.println(rs8.getInt(1) + " " + rs8.getString(2) + " " + rs8.getString(3) + " " + rs8.getInt(4)
							+ " " + rs8.getString(5) + " " + rs8.getString(6));

				}

			}
			
			
			

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
