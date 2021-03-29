package net.gamedev.battleship;

import net.gamedev.battleship.model.Game;
import net.gamedev.battleship.model.GameStatus;
import net.gamedev.battleship.model.Player;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FinishServlet", urlPatterns = "/finish")
public class FinishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openNext(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
        game = null;
        req.getSession().setAttribute(Game.ATTR, game);
        openRegistration(req, resp);
    }

    private void openNext(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        if (player.hasMoreShips()) {
            openWin(req, resp);
        } else {
            openLoss(req, resp);
        }
    }

    private void openWin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/finish-win.jsp").
                forward(req, resp);
    }

    private void openLoss(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/finish-loss.jsp").
                forward(req, resp);
    }

    private void openRegistration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.getRequestDispatcher("/WEB-INF/registration.jsp")
       //         .forward(req, resp);
        resp.sendRedirect("registration");
    }
}
