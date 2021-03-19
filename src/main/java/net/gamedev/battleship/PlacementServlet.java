package net.gamedev.battleship;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlacementServlet", urlPatterns = "/placement")
public class PlacementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openPlacement(req, resp);
    }

    private void openPlacement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/placement.jsp")
                .forward(req, resp);
    }
}
