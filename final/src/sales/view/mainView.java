package sales.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import sales.func.swapPanelDtoN;
import sales.func.swapPanelForMenu;

public class mainView extends JFrame {

	
	private DefaultPanel dp;
	private goodsManagement pan1;
	private salesManagement pan2;
	private sellingManagement pan3;
	
	public JMenuBar mb = new JMenuBar();
	public JMenu menu;
	public JMenuItem ItemOf1stMenu = new JMenuItem();
	
	public int pan;
	
	String menuStr = "메뉴";
	String menuItemStr1 = "초기화면";
	
	public swapPanelDtoN spfunc = new swapPanelDtoN(this);
	public swapPanelForMenu spwfunc = new swapPanelForMenu(this);
	
	public DefaultPanel getDp() {
		return dp;
	}

	public goodsManagement getPan1() {
		return pan1;
	}

	public salesManagement getPan2() {
		return pan2;
	}

	public sellingManagement getPan3() {
		return pan3;
	}

	public int getPan() {
		return pan;
	}
	
	public void setPan(int pan) {
		this.pan = pan;
	}
	

	
	
	
	
	public mainView(DefaultPanel dp, goodsManagement pan1, salesManagement pan2, sellingManagement pan3) {
		
		this.dp = dp;
		this.pan1=pan1;
		this.pan2=pan2;
		this.pan3=pan3;
		
		
		setLayout(null);
		
		dp.setBounds(0, 0, 585, 700);
		pan1.setBounds(0, 50, 585, 550);
		pan2.setBounds(0, 50, 585, 550);
		pan3.setBounds(0, 50, 585, 550);

//===================== menu bar ===============================
		
		menu = new JMenu(menuStr);
		mb.add(menu);
		
	
		ItemOf1stMenu = new JMenuItem(menuItemStr1);
		menu.add(ItemOf1stMenu);
		menu.addSeparator();	
		
		ItemOf1stMenu.addActionListener(spwfunc);
	
		
		setJMenuBar(mb);
		
//========================= menu bar ==============================
		
//========================= button ================================
		//spfunc = new swapPanelDtoN(this);
		
		dp.btn1.addActionListener(spfunc);
		dp.btn2.addActionListener(spfunc);
		dp.btn3.addActionListener(spfunc);
		
//========================= button ================================
		
		
		

		
		getContentPane().add(dp);
		
		setResizable(false);
		setTitle(" hi");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,0 , 600, 700);
		setVisible(true);
	}
	
	
	

}
