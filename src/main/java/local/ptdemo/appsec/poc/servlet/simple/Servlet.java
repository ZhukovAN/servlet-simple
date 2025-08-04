package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    public String password = "P@ssw0rd";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        // Do nothing
    }
}
