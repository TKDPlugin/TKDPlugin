package ui;

import javax.swing.JPanel;

import bl.BlController;

/**
 * 负责控制界面跳转的类
 * @author 侍硕
 *
 */
public class UIController {
            private static MainFrame frame;
            private static BlController blController;
            private static RankPanel rankPanel;
            private static SumPanel sumPanel;
            
            private static void initPanels (){
            	   UIController.blController = BlController.getInstance();
            	   UIController.rankPanel = new RankPanel(blController);
            	   UIController.sumPanel = new  SumPanel(blController);
            	  
            }
            public static void setFrame(MainFrame mainFrame){
            	 UIController.frame = mainFrame;
            }
            
            public static void  changePanel(String type){
            	   JPanel  tempPanel  = new JPanel();
            	   if(type.equals("rank")){
            		   tempPanel = rankPanel;
            		    System.out.println("change to  rankPanel");
            	   }else{
            		   tempPanel = sumPanel;
            		   System.out.println("change to  sumPanel");
            	   }
            	   
            	   tempPanel.setBounds(0,0,frame.Width,frame.Height);
            //	   tempPanel.setOpaque(false);
                   
                   frame.getShowPanel().removeAll();
                   frame.getShowPanel().add(tempPanel);
                   frame.validate();
                   frame.repaint();
                
            }
            
            
            
            public static void  initFrame(){
            	    MainFrame mainFrame = new MainFrame();
            	    UIController.setFrame(mainFrame);
            	    UIController.initPanels();
            	  
            }
}
