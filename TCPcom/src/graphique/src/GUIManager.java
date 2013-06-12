public class GUIManager 
{

    private static GUIManager instance;
    PanelAutomate panel_automate;
    PanelConsole panel_console;

    public static GUIManager getInstance() 
    {
        if (instance == null) 
        { 
            instance = new GUIManager();
        }
        return instance;
    }

   
  

    
    public void setPanelConsole(PanelConsole panelconsole) 
    {
        panel_console = panelconsole;
    }

    public PanelConsole getPanelConsole() 
    {
        return new PanelConsole();
    }

   
    private GUIManager() 
    {
    }
}