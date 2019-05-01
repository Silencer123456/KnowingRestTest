package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Record;
import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.model.TypeOfData;
import kiv.zcu.dip.servlet.service.RecordService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/visualize")
public class VisualizeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String queryText = "test";
        if (request.getParameter("queryText") != null) {
            queryText = Jsoup.clean(request.getParameter("queryText"), Whitelist.basic());
        }

        Report report = RecordService.fetchReport(queryText, TypeOfData.patent, "", "queryLimit/1000");

        String plotJs = showPlot(report.getDocuments());

        request.setAttribute("plot", plotJs);

        request.getRequestDispatcher("/visualize.jsp").forward(request, response);
    }

    /**
     * Converts an integer ArrayList to integer array
     *
     * @param integers -- Integer ArrayList
     * @return -- Converted integer array
     */
    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    private String showPlot(List<Record> documents) {
        List<Integer> years = new ArrayList<>();
        for (Record record : documents) {
            int year = 0;
            try {
                year = Integer.parseInt(record.getYear());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            years.add(year);
        }

        int[] yearsArray = convertIntegers(years);

        Table yearTable = Table.create("Years")
                .addColumns(IntColumn.create("year", yearsArray));

        Plot.show(Histogram.create("Distribution of years", yearTable, "year"));

        return "";
    }
}
