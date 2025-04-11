package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String cmd = request.getParameter("cmd");
        Runtime.getRuntime().exec(cmd);
    }
}
