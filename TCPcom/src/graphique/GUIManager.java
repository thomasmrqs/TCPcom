package graphique;

import java.net.SocketException;

public class GUIManager {

    private static GUIManager instance;
    ClientAutomatePanel panel_automate;
    ClientConsolePanel panel_console;

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public void setPanelConsole(ClientConsolePanel panelconsole) {
        panel_console = panelconsole;
    }

    public ClientConsolePanel getPanelConsole() throws SocketException {
        return new ClientConsolePanel();
    }

    private GUIManager() {
    }
}