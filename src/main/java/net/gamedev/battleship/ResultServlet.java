package net.gamedev.battleship;

import net.gamedev.battleship.model.Record;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (
                Connection con = DriverManager.getConnection("jdbc:h2:file:~/Battleship", "sa", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM records ORDER BY WIN DESC, SHOTS")
        ) {
            List<Record> recordList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("NAME");
                boolean win = rs.getBoolean("WIN");
                int shots = rs.getInt("SHOTS");

                Record rec = new Record();
                rec.setName(name);
                rec.setWin(win);
                rec.setShots(shots);

                recordList.add(rec);
            }
            request.setAttribute("rec", recordList);
            request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);

        } catch (SQLException | ServletException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
