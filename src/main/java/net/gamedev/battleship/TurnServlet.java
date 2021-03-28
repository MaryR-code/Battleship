package net.gamedev.battleship;

import net.gamedev.battleship.model.Game;
import net.gamedev.battleship.model.GameStatus;
import net.gamedev.battleship.model.Player;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TurnServlet", urlPatterns = "/turn")
public class TurnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openNext(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        if (game.getCurrentPlayer() == player) {
            var addr = req.getParameter("addr");
            game.fire(addr);
        } else {
            resp.sendError(400);
            return;
        }
        openNext(req, resp);
    }

    private void openNext(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        if (game.getStatus() == GameStatus.FINISHED) {
            openFinish(req, resp);
        } else if (game.getCurrentPlayer() == player) {
            openTurn(req, resp);
        } else {
            openTurnAwait(req, resp);
        }
    }

    private void openTurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/turn.jsp")
                .forward(req, resp);

    }

    private void openTurnAwait(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/turn-await.jsp")
                .forward(req, resp);
    }

    private void openFinish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("finish");
    }
}
