package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String cmd = request.getParameter("cmd");
        if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY))
            cmd = process(cmd);
        Runtime.getRuntime().exec(cmd);
    }

    protected String process(String cmd) {
        return cmd.toLowerCase();
    }
}
