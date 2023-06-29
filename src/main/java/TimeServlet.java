import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    private static final String UTC = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = req.getParameter("timezone");
        if (timezone != null) {
            timezone = timezone.replaceAll(" ", "+");
        } else {
            timezone = UTC;
        }

        String currentTime = ZonedDateTime
                .now(ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ")) + timezone;
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(currentTime);
        resp.getWriter().close();
    }
}