package kiv.zcu.dip.servlet.servlet;

import kiv.zcu.dip.servlet.model.Report;
import kiv.zcu.dip.servlet.service.RecordService;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;

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

        Report report = RecordService.fetchReport(0, 50);

        showPlot();

        request.setAttribute("report", report);
        request.getRequestDispatcher("/hello.jsp").forward(request, response);

    }

    private void showPlot() {
        String[] animals = {"franta", "pepa", "honza", "franta"};
        double[] cuteness = {100, 100, 120, 150};

        Table cuteAnimals = Table.create("Cute Animals")
                .addColumns(
                        StringColumn.create("Animal types", animals),
                        DoubleColumn.create("rating", cuteness));

        Plot.show(Histogram.create("Distribution of prices", cuteAnimals, "rating"));
    }
}
