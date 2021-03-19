package net.gamedev.battleship;

import net.gamedev.battleship.model.GameManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var mgr = new GameManager();
        sce.getServletContext().setAttribute(GameManager.ATTR, mgr);
    }
}
