package net.gamedev.battleship;

import net.gamedev.battleship.model.Game;
import net.gamedev.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "PlacementServlet", urlPatterns = "/placement")
public class PlacementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openNext(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        player.getPlayerField().clear();
        var addresses = req.getParameterValues("addr");
        if (addresses == null) {
            req.setAttribute("incorrectShipsPlacement", true);
            openPlacement(req, resp);
            return;
        }
        var shipAddresses = Set.of(addresses);
        player.setShips(shipAddresses);
        if (!player.isPlayerFieldValid()) {
            req.setAttribute("incorrectShipsPlacement", true);
            openPlacement(req, resp);
            return;
        }
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
        game.checkGameStart();
        openNext(req, resp);
    }

    private void openNext(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        var opponent = game.opponentOf(player);
        if (!player.isPlayerFieldValid()) {
            openPlacement(req, resp);
        } else if (!opponent.isPlayerFieldValid()) {
            openPlacementAwait(req, resp);
        } else {
            openTurn(req, resp);
        }
    }

    private void openTurn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("turn");
    }

    private void openPlacementAwait(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/placement-await.jsp")
                .forward(req, resp);
    }


    private void openPlacement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/placement.jsp")
                .forward(req, resp);
    }
}
