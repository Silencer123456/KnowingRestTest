package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.model.TypeOfData;
import kiv.zcu.dip.servlet.service.RecordService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
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

@WebServlet("/query")
public class QueryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //PrintWriter out = response.getWriter();
        //out.println("<h3>Hello World!</h3>");

        String queryText = "test";
        if (request.getParameter("queryText") != null) {
            queryText = Jsoup.clean(request.getParameter("queryText"), Whitelist.basic());
        }

        Report report = RecordService.fetchReport(queryText, TypeOfData.patent, "");

        String plotJs = showPlot();

        request.setAttribute("plot", plotJs);

        request.setAttribute("docs", report.getDocuments());
        request.getRequestDispatcher("/hello.jsp").forward(request, response);
    }

    private String showPlot() {
        /*String[] animals = {"franta", "pepa", "honza", "franta"};
        double[] cuteness = {100, 100, 120, 150};

        Table cuteAnimals = Table.create("Cute Animals")
                .addColumns(
                        StringColumn.create("Animal types", animals),
                        DoubleColumn.create("rating", cuteness));

        Plot.show(Histogram.create("Distribution of prices", cuteAnimals, "rating"));*/

        Object[] x = {"sheep", "cows", "fish", "tree sloths"};
        double[] y = {1, 4, 9, 16};

        BarTrace trace = BarTrace.builder(x, y).build();

        Plot.show(new Figure(trace));

        return new Figure(trace).asJavascript("test");
    }
}
