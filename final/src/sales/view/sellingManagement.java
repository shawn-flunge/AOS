package sales.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.Socket;
import java.sql.Connection;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.event.FrameLoadFinished;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import sales.connector.DBConnection;
import sales.controller.salesController;
import sales.domain.PieVO;
import sales.domain.startVO;
import sales.func.GoogleAPI;
import sales.persistence.Sale_pDAOImpl;

public class sellingManagement extends JPanel implements ActionListener{
	Connection con;
	String text;
	JLabel anl;
	JButton [] pBtn = new JButton[30];
	JPanel pan1;
	JPanel pan2;
	int[] prices = new int[30];
	int[] count = new int[30];
	int k;
	Sale_pDAOImpl pdao = new Sale_pDAOImpl();
	List<startVO> list;
	String [] cnames;
	int [] ccount;
	String title;
	//=====================================googleChart=====================================================	
	EngineOptions options;
	Engine engine;
	Browser browser;
	BrowserView view;
	
	Socket socket;
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void setView(BrowserView view) {
		this.view = view;
	}
	
	public BrowserView getView() {
		return view;
	}
	
	public sellingManagement() {
	
	}
	
	public void reload(){
		this.removeAll();
		
		con = DBConnection.getCon();
		
		pdao.setCon(con);
		
		list = pdao.readsB();
		
		con = DBConnection.getCon();
		
		pdao.setCon(con);
		
		text = pdao.anl();
		
		anl = new JLabel(text);
		
		k = list.size();
	//=====================================googleChart=====================================================	
		options=EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED).build();
		engine = Engine.newInstance(options);
		browser =  engine.newBrowser();
	
		view = BrowserView.newInstance(browser);
		title = "금일 판매량";
		
		cnames = new String[k];
		ccount = new int[k];
		
		for (int i = 0; i < ccount.length; i++) {
			cnames[i] = list.get(i).getName();
			ccount[i] = list.get(i).getSale_count();
		}
		
		browser.mainFrame().ifPresent(frame -> frame.loadHtml(new GoogleAPI().getPieChart(title, cnames, ccount)));
		browser.navigation().on(FrameLoadFinished.class, event -> {
		    String html = event.frame().html();
		    	System.out.println(html);
		});
	
		//=====================================googleChart=====================================================		
		
		setLayout(new BorderLayout(10, 10));
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan1.setLayout(new FlowLayout());
		pan2.setLayout(new BorderLayout());
		
		
		
		for (int i = 0; i < k; i++) {
			count[i] = list.get(i).getN_count();
			pBtn[i] = new JButton(list.get(i).getName());
			prices[i] = list.get(i).getPrice_out();
			pBtn[i].addActionListener(this);
			pan1.add(pBtn[i]);
		}
		
		pan2.add(view);
		pan2.setBounds(0, 100, 585, 200);
		add(pan1, "North");
		add(pan2, "Center");
		add(anl, "South");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
JButton btn = (JButton)e.getSource();
		
		con = DBConnection.getCon();
	      
		pdao.setCon(con);
	      
		pdao.updatestock(btn.getText());
	      
		con = DBConnection.getCon();
	      
		pdao.setCon(con);
	      
		list = pdao.readsB();
	      
		cnames = new String[list.size()];
		ccount = new int[list.size()];
	   
	      for (int i = 0; i < ccount.length; i++) {
	    	  cnames[i] = list.get(i).getName();
	    	  ccount[i] = list.get(i).getSale_count();
	        
	      }
	      
	      for (int i = 0; i < ccount.length; i++) {
	          if(list.get(i).getN_count() <= 3 && btn.getText().equals(cnames[i])){
	             if(list.get(i).getN_count() <= 0) {
	                JOptionPane.showMessageDialog(null, "매진되었습니다.");
	             }else {
	                JOptionPane.showMessageDialog(null, cnames[i] + "의 개수가 얼마남지 않았습니다. " + list.get(i).getN_count());
	             }
	          }
	       }
	      
	      browser.mainFrame().ifPresent(frame -> frame.loadHtml(new GoogleAPI().getPieChart(title, cnames,ccount)));
	      browser.navigation().on(FrameLoadFinished.class, event -> {
	          String html = event.frame().html();
	             System.out.println(html);
	      });
	      
	      
	      con = DBConnection.getCon();
	      
	      pdao.setCon(con);
	      
	      text = pdao.anl();
	      
	      anl.setText(text);
		
		
	}

	
}
