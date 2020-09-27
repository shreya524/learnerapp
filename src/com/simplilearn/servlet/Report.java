package com.simplilearn.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplileran.connection.DBConnection;

/**
 * Servlet implementation class Report
 */
@WebServlet("/report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("report.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// fetch data
		 Integer cid = Integer.parseInt(request.getParameter("cid"));
		
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
			//	String query = "insert into teacher(t_id,t_name,t_age ,t_email,t_salary,c_id) values(? , ?, ?, ?, ?,?)";
				String query ="select t_name as Teacher ,c_name as Class, sub_name as Subject,stu_name as Student_name  from teacher class c on t.c_id = c.c_id inner join subject  on s.t_id =s.t_id on c.c_id=st.c_id)";
				            		

				// 3. Create a statement
				PreparedStatement pstm = conn.getConnection().prepareStatement(query);

				// 4. set Parameter
				
				
				
				

				// 4. Execute Query
				ResultSet rs = pstm.executeQuery();
				out.print("<h3> Teacher added " + rs + "</h3>");

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


