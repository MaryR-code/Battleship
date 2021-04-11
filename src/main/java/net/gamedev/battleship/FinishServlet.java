package net.gamedev.battleship;

import net.gamedev.battleship.model.Game;
import net.gamedev.battleship.model.GameStatus;
import net.gamedev.battleship.model.Player;
import net.gamedev.battleship.model.Record;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
//        HttpSession session = req.getSession();
//        session.invalidate();
        openRegistration(req, resp);
    }

    private void openNext(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute(Player.ATTR);
        var game = (Game) req.getSession().getAttribute(Game.ATTR);
//        if (player.hasMoreShips()) {
//            openWin(req, resp);
//        } else {
//            openLoss(req, resp);
//        }
        if (game.isWinner(player)) {
            boolean win = true;
            saveResult(player, win);
            openWin(req, resp);
        } else {
            boolean win = false;
            saveResult(player, win);
            openLoss(req, resp);
        }
    }

    private void saveResult(Player player, boolean win) {
        String name = player.getName();
        int shots = player.getHistory().size();
        try (
                Connection con = DriverManager.getConnection("jdbc:h2:file:~/Battleship", "sa", "");
                PreparedStatement stmt =
                        con.prepareStatement("INSERT INTO records (NAME, WIN, SHOTS) VALUES(?, ?, ?)")
        ) {
            stmt.setString(1, name);
            stmt.setBoolean(2, win);
            stmt.setInt(3, shots);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
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
       resp.sendRedirect("registration");
    }
}
