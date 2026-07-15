package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    public static final Random PRNG = new Random();
    public static final long RANDOM = PRNG.nextLong();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
    }
}
