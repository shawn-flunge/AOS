package sales.view;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sales.func.getEnvironment;





public class DefaultPanel extends JPanel {
	//========================= weather ================================		
		getEnvironment gete;
		public String str1; 
		public String str2; 
		public String str3;
		public String str4;
		public JLabel img, logoimg,ttimer;
		static public JLabel tmp, creator,point;
		static public JLabel weather; 
		public List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		public String haha;
		public ImageIcon icon;
		ImageIcon logo;
		//=========================  ================================	
		
		
		
		public JButton btn1 = new JButton(new ImageIcon("imgs/button1.png"));
		public JButton btn2 = new JButton(new ImageIcon("imgs/button2.png"));
		public JButton btn3 = new JButton(new ImageIcon("imgs/button3.png"));
	
		public JButton btn4 = new JButton("서버");
		public JButton btn5 = new JButton("send");
		
	
	public DefaultPanel() {
		
	
		setLayout(null);
		this.setBounds(0, 50, 800, 450);
		
		
		btn1.setBounds(0, 347, 200, 100);
		btn2.setBounds(0, 447, 200, 100);
		btn3.setBounds(0, 547, 200, 100);
		
		btn4.setBounds(10, 237, 100, 50);
		btn5.setBounds(110, 237, 100, 50);
		btn4.setBorderPainted(false);
		btn5.setBorderPainted(false);
		btn4.setContentAreaFilled(false);
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		//add(btn5);
		
		
		
		setBackground(new Color(255,255,255,255));
		//=============================== ====================
				gete = new getEnvironment();
				
				result= gete.getTownForecast("57", "125");
				
				
				str1 = result.get(0).get("시간").toString();
				str2 = result.get(0).get("온도").toString();
				str3 = result.get(0).get("pty").toString();
				str4 = result.get(0).get("날씨").toString();
				
				
				if(str3.equals("1")) {
					str4 = "비";
				}
				if(str3.equals("2")) {
					str4 = "비";
				}
				if(str3.equals("3")) {
					str4 = "눈";
				}
				if(str3.equals("4")) {
					str4 = "눈";
				}
				
				String haha = gete.hoho;
				
				Font font = new Font("돋움",Font.BOLD,12);
				
				System.out.println(haha);
				icon = new ImageIcon(haha); //이미지 아이콘 객체 생성

				logo = new ImageIcon("imgs/yuhan.PNG");

				logoimg = new JLabel(logo);
				img = new JLabel(icon);
				tmp = new JLabel("온도 : " + str2);
				point = new JLabel("현재시간 3시27분");
				ttimer = new JLabel();
				
				
				
				weather = new JLabel("날씨 : " + str4);
				creator = new JLabel("<html><h1>※포스기 주의사항※</h1>"
						+ "<br>1. 영업종료전 정산하기"
						+ "<br>2. 영업시작 시 재고확인"
						+ "<br>3. 올해도 고생한 친구들에게"
						+ "<br>4. 사랑한다고 말해주기"
						+ "</html>");
				creator.setBounds(40, 0, 250, 300);
				add(creator);
				
				img.setBounds(450, 40, 100,100);
				
				img.setVisible(true);
				add(img);
				
				ttimer.setOpaque(true);
				ttimer.setFont(font);
				ttimer.setForeground(Color.red);
				ttimer.setBackground(Color.BLACK);
				ttimer.setBounds(210, 0, 180, 15);
				add(ttimer);
				
				
				
				tmp.setFont(font);
				tmp.setBounds(465, 110, 100, 100);
				add(tmp);
				
				
				
				
				weather.setFont(font);
				weather.setBounds(465, 130, 100, 100);
				add(weather);
				
				logoimg.setBounds(180, 270, 400, 400);
				add(logoimg);
		
		
	}
	
	
	

	
	
}
