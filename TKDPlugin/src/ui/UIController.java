package ui;

/**
 * 负责控制界面跳转的类
 * @author 侍硕
 *
 */
public class UIController {
            private static MainFrame frame;
            
            public static void setFrame(MainFrame mainFrame){
            	 UIController.frame = mainFrame;
            }
            
            public static void main(String[] args){
            	
            	    MainFrame mainFrame = new MainFrame();
            	    UIController.setFrame(mainFrame);
            	    
            }
}
