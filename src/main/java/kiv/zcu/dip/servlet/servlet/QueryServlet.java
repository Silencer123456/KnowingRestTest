package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.model.TypeOfData;
import kiv.zcu.dip.servlet.service.RecordService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String queryText = "test";
        if (request.getParameter("queryText") != null) {
            queryText = Jsoup.clean(request.getParameter("queryText"), Whitelist.basic());
        }

        Report report = RecordService.fetchReport(queryText, TypeOfData.patent, "", "query/1");

        request.setAttribute("docs", report.getDocuments());
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }
}
