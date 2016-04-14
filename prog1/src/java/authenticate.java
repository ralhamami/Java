import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="authenticate", urlPatterns = {"/authenticate"})
public class authenticate extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html.charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){
            String user = req.getParameter("user");
            String pass = req.getParameter("pass");
            if (!pass.equals("secret"))
                resp.sendRedirect("loginFailed.jsp");
            else{
                if (user.equalsIgnoreCase("sung"))
                    resp.sendRedirect("http://cs3.uco.edu");
                else if (user.equalsIgnoreCase("uco"))
                    resp.sendRedirect("http://www.uco.edu");
                else if (user.equalsIgnoreCase("cs"))
                    resp.sendRedirect("http://cs.uco.edu");
                else{
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect("welcome.jsp");
                }
            }
        }
    }
    
}
