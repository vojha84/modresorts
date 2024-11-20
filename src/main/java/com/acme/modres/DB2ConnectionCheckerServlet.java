package com.acme.modres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acme.modres.db.ModResortsStaffData;

import javax.servlet.annotation.WebServlet;

@WebServlet({"/resorts/db/connect"})
public class DB2ConnectionCheckerServlet extends HttpServlet {

    @Inject
    private ModResortsStaffData staffData;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        Connection dbConnection = null;
		try {
			dbConnection = staffData.getConnection();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					return;
				}
			}
		}
    }
}