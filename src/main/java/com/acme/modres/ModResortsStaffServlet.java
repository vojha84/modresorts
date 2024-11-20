package com.acme.modres;

import com.acme.modres.db.ModResortsStaffData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resorts/staff")
public class ModResortsStaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ModResortsStaffData staffData;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        // Call to lib
        PrintWriter writer = response.getWriter();
        for (String staff : staffData.getStaffData() ) {
            writer.println(staff);
        }
        writer.flush();
    }
}