package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Record;
import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.model.TypeOfData;
import kiv.zcu.dip.servlet.service.RecordService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.DataFrameReader;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.HistogramTrace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
