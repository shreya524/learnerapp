package com.simplilearn.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplileran.connection.DBConnection;

/**
 * Servlet implementation class AddSubject
 */
@WebServlet("/add-new-subject")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("add-new-subject.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// fetch data
				Integer id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				Integer cid = Integer.parseInt(request.getParameter("cid"));
				Integer tid = Integer.parseInt(request.getParameter("tid"));
				
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				// Get Config
				InputStream ins = getServletContext().getResourceAsStream("/WEB-INF/configuration.properties");
				Properties props = new Properties();
				props.load(ins);

				// Create a connection
				try {
					DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("username"),
							props.getProperty("password"));
					if (conn != null) {

						// 2. Create a query
						// String query = "insert into eproduct(name,price)
						// values('"+name+"',"+price+")";
						String query = "insert into subject(sub_id,sub_name,c_id,t_id) values(? , ?, ?, ?)";

						// 3. Create a statement
						PreparedStatement pstm = conn.getConnection().prepareStatement(query);

						// 4. set Parameter
						
						
						pstm.setInt(1, id);
						pstm.setString(2, name);
						pstm.setInt(3, cid);
						pstm.setInt(4, tid);
						
						

						// 4. Execute Query
						int noOfRowsAffected = pstm.executeUpdate();
						out.print("<h3> Subject added " + noOfRowsAffected + "</h3>");

						pstm.close();
					}

					conn.closeConnection();
					out.print("<h3>DB Connection is closed !</h3>");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
}
