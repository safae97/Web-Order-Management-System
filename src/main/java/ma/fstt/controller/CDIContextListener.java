package ma.fstt.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@WebListener
public class CDIContextListener implements ServletContextListener {

    private Weld weld;
    private WeldContainer container;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        weld = new Weld();
        container = weld.initialize();
        System.out.println("Weld container initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (container != null) {
            container.shutdown();
            System.out.println("Weld container shut down.");
        }
    }
}
