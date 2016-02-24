package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int Width = 450;
	public static int Height = 440;

	private Font font;
	private JPanel showPanel;

	public MainFrame() {

		this.initComponent();

	}

	public void initComponent() {
		setLayout(null);
		// setType(Type.UTILITY);
		setTitle("Tool");
		setResizable(false);
		setFont(new Font("Calibri", Font.PLAIN, 14));
		setBackground(Color.WHITE);

		initBar();
		initShowPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenW = (int) screensize.getWidth();
		int screenH = (int) screensize.getHeight();
		setBounds((screenW - Width) / 2, (screenH - Height) / 2, Width, Height);
		setVisible(true);
	}

	private void initShowPanel() {
		showPanel = new JPanel();
		showPanel.setLayout(null);
		showPanel.setBounds(0, 0, Width, Height);
		showPanel.setOpaque(false);
		this.add(showPanel);
	}

	private void initBar() {
		font = new Font("Microsoft YaHei UI Light", Font.PLAIN, 14);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBorderPainted(false);
		menuBar.setFont(font);
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("功能选项");
		menu.setFont(font);
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		// menuBar.add(menu);

		JMenuItem rankItem = new MyMenuItem("排名");
		rankItem.setFont(font);
		rankItem.setHorizontalAlignment(SwingConstants.CENTER);
		rankItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 显示排名面板
				UIController.changePanel("rank");
			}
		});
		menu.add(rankItem);

		JMenuItem sumItem = new MyMenuItem("汇总");
		sumItem.setFont(font);
		sumItem.setHorizontalAlignment(SwingConstants.CENTER);
		sumItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 显示汇总面板
				UIController.changePanel("sum");
			}
		});
		menu.add(sumItem);
		menuBar.add(menu);
	}

	public JPanel getShowPanel() {
		return this.showPanel;
	}
}
