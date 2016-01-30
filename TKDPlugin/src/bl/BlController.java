package bl;

import java.util.ArrayList;

public class BlController {
    private static BlController controller;
    ArrayList<Level> levelList;
    private BlController() {
        // TODO Auto-generated constructor stub
    }
    
    public static BlController getInstance() {
        if(controller!=null) {
            controller = new BlController();
        }
        return controller;
    }
    
}
