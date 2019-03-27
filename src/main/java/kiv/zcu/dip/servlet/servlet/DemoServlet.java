package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Record;
import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.service.RecordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //PrintWriter out = response.getWriter();
        //out.println("<h3>Hello World!</h3>");

        Report report = RecordService.fetchReport(0, 50);

        request.setAttribute("report", report);
        request.getRequestDispatcher("hello.jsp").forward(request, response);
    }
}
