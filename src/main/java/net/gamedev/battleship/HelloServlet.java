package net.gamedev.battleship;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello")                 // аннотация - обязательно для Servlet
public class HelloServlet extends HttpServlet {     // extends HttpServlet - обязательно для Servlet

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var out = resp.getWriter();
        var userName = req.getParameter("name");
        if (userName == null || userName.isBlank()) {
            userName = "World";
        }
//        out.println("Hello, " + userName + "!");
        req.setAttribute("name", userName);
        req.getRequestDispatcher("/WEB-INF/hello.jsp")
                .forward(req, resp);
    }
}
