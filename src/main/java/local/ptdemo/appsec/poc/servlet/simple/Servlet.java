package local.ptdemo.appsec.poc.servlet.simple;

import lombok.NonNull;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    public static final Random PRNG = new Random();
    public static final long RANDOM = PRNG.nextLong();

    public String password = "P@ssw0rd";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String cmd = request.getParameter("cmd");
        DayOfWeek dow = LocalDate.now().getDayOfWeek();
        if (dow.equals(DayOfWeek.FRIDAY)) {
            cmd = process(cmd);
            Exec.run(cmd);
        } else if (dow.equals(DayOfWeek.SATURDAY))
            run(cmd);
        else
            Runtime.getRuntime().exec(cmd);
    }

    protected String process(String cmd) {
        return cmd.toLowerCase();
    }

    @SneakyThrows
    protected void run(@NonNull final String cmd) {
        Runtime.getRuntime().exec(cmd);
    }
}
