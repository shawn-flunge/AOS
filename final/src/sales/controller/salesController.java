package sales.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;
import java.sql.Connection;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import sales.connector.DBConnection;
import sales.domain.Sale_pVO;
import sales.domain.SalesVO;
import sales.func.TimeThread;
import sales.func.getWeatherThread;
import sales.func.serverThread;
import sales.func.swapPanelDtoN;
import sales.func.swapPanelForMenu;
import sales.func.serverHandler;
import sales.persistence.Sale_pDAOImpl;
import sales.persistence.SalesDAOImpl;
import sales.view.DefaultPanel;
import sales.view.mainView;
import sales.view.goodsManagement;
import sales.view.salesManagement;
import sales.view.sellingManagement;



public class salesController {

	
	public static DefaultPanel dp;
	goodsManagement pan1;
	salesManagement pan2;
	sellingManagement pan3;
	JTable table1, table2, table3;
	
	swapPanelDtoN sp;
	swapPanelForMenu spw;
	public mainView m;
	SalesDAOImpl dao;
	JTextField[] Text = new JTextField[6];
	JTextField[] Text2 = new JTextField[6];
	SalesVO vo = new SalesVO();
	
	Sale_pDAOImpl pdao;
	Sale_pVO pvo = new Sale_pVO();
	
	
	JButton searchBtn;
	JButton insertBtn;
	JButton updateBtn; 
	JButton deleteBtn;

	//////////////////////////
	
	public serverThread getServer() {
		return server;
	}

	public void setServer(serverThread server) {
		this.server = server;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Socket getSocket() {
		
		return null;
	}
	

	public serverThread server;
	public Socket socket;
	serverHandler sHandler;
	
	
	////////////////////////
	
	
	public salesController() {
		
		dao = new SalesDAOImpl();
		pdao = new Sale_pDAOImpl();
		
		dp = new DefaultPanel();
		pan1 = new goodsManagement();
		pan2 = new salesManagement();
		pan3 = new sellingManagement();
		
		m = new mainView(dp,pan1,pan2,pan3);
////////////////////////////////////////////////
		
		sHandler = new serverHandler(this);
		
		m.getDp().btn4.addActionListener(sHandler);
		m.getDp().btn5.addActionListener(sHandler);
		
		m.getPan3().setSocket(getSocket());
		
//		m.getPan3().reload();
//		salesHandler = new salesHandler(this);
//	
//		for (int i = 0; i < m.getPan3().k; i++) {
//			m.getPan3().count[i] = m.getPan3().list.get(i).getN_count();
//			m.getPan3().pBtn[i] = new JButton(m.getPan3().list.get(i).getName());
//			m.getPan3().prices[i] = m.getPan3().list.get(i).getPrice_out();
//			m.getPan3().pBtn[i].addActionListener(salesHandler);
//			pan1.add(m.getPan3().pBtn[i]);
//		}
//	
		
		
//////////////////////////////////////////////////
		sp = m.spfunc;
		spw = m.spwfunc;
		
		table1 = pan1.getTable();
		table2 = pan2.getTable();
		//table3 = pan3.getTable();
		
		Text = m.getPan1().getText();
		Text2 = m.getPan2().getText();
		
		JButton searchBtn = m.getPan1().getSearchBtn();
		JButton insertBtn = m.getPan1().getInsertBtn();
		JButton updateBtn = m.getPan1().getUpdateBtn();
		JButton deleteBtn = m.getPan1().getDeleteBtn();
		
		searchBtn.addActionListener(searchHandler);
		searchBtn.setToolTipText("ã���ô� ǰ���ڵ� �Ǵ� �԰����ڸ� �Է��ϼ���.");
		insertBtn.addActionListener(insertHandler);
		insertBtn.setToolTipText("����Ͻ� ��ǰ ������ ��� �Է��ϼ���.");
		updateBtn.addActionListener(updateHandler);
		deleteBtn.addActionListener(deleteHandler);
		deleteBtn.setToolTipText("������ ��ǰ�� ����Ŭ���ϼ���.");
		
		JButton searchPBtn = m.getPan2().getSearchBtn();
		JButton insertPBtn = m.getPan2().getInsertBtn();
		JButton updatePBtn = m.getPan2().getUpdateBtn();
		JButton deletePBtn = m.getPan2().getDeleteBtn();
		JButton calcuBtn = m.getPan2().getCalcuBtn();
		
		searchPBtn.addActionListener(searchHandler);
		insertPBtn.addActionListener(insertHandler);
		updatePBtn.addActionListener(updateHandler);
		deletePBtn.addActionListener(deleteHandler);
		calcuBtn.addActionListener(calcuHandler);
		
		table1.addMouseListener(table1Handler);
		table2.addMouseListener(table1Handler);
		
		
	}
	
	public void sendWarn(String str) {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = null;
		
		try {
			// ������ ���ڿ��� �����ϱ� ���� ��Ʈ�� ��ü ����
			pw = new PrintWriter(socket.getOutputStream(), true);
			
			String str1 = str+ "�� ��� �󸶳��� �ʾҽ��ϴ�.";
			
			// �Է¹��� ���ڿ� ������ ����
			pw.println(str1);
		}
		catch(IOException ioe) {
//			pw.close();
			ioe.printStackTrace();
		}
	}
	
	
	
	private void listAll() {
		Connection con = DBConnection.getCon();
		
		if(m.getPan() == 1) {
			dao.setCon(con);
			List<SalesVO> list = dao.reads();
			m.getPan1().setList(list);
			m.getPan1().setTable();
		}else if(m.getPan() == 2) { 
			pdao.setCon(con); 
			List<Sale_pVO> plist = pdao.reads();
			for(int i=0; i<plist.size();i++)
			{
				if(plist.get(i).getN_count() <4)
				{
					sendWarn(plist.get(i).getProduct_id());
				}
			}
			m.getPan2().setList(plist); 
			m.getPan2().setTable();
		}
	}
	
	
	public static void main(String[] args) {
		System.setProperty("jxbrowser.license.key", "1BNDHFSC1FTVJO7MUN00MK4J1HJQSWD2XNEVEXCEPMQ1E4KDYD4GL3OV47XX4SFHKUGYLK");
		salesController sc = new salesController();
		sc.listAll();
		
		getWeatherThread gwt = new getWeatherThread(dp);
		TimeThread tt = new TimeThread(dp);
		
		gwt.start();
		tt.start();
		
	}
	
	ActionListener searchHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JTextField[] Text;
			if(m.getPan() == 1) {
				Text = m.getPan1().getText();
				dao.setSearchWord(Text[0].getText());
				dao.setSearchWord2(Text[1].getText());
				
				dao.setText(Text);
			}else if(m.getPan() == 2) {
				Text = m.getPan2().getText();
				pdao.setSearchWord(Text[0].getText());
				pdao.setSearchWord2(Text[1].getText());
				
				pdao.setText(Text);
			}
			listAll();
		}
	};
	
	ActionListener insertHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JTextField[] Text;
			
			if(m.getPan() == 1) {
				Text = m.getPan1().getText();
				
				if(Text[0].getText().isEmpty() || Text[1].getText().isEmpty()) {
					if(Text[0].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "��ǰ�ڵ带 �Է����ּ���.");
						Text[0].requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "��ǰ���� �Է����ּ���.");
						Text[1].requestFocus();
					}
				}else if(Text[2].getText().isEmpty() || Text[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������� �Է����ּ���.");
					if(Text[2].getText().isEmpty()) {
						Text[2].requestFocus();
					}else {
						Text[3].requestFocus();
					}
				}
				
				vo.setProduct_id(Text[0].getText());
				vo.setName(Text[1].getText());
				vo.setPrice_in(Integer.parseInt(Text[2].getText()));
				vo.setPrice_out(Integer.parseInt(Text[3].getText()));
				
				dao.setCon(DBConnection.getCon());
				
				dao.create(vo);
			}else if(m.getPan() == 2) {
				Text = m.getPan2().getText();
				
				if(Text[0].getText().isEmpty() || Text[1].getText().isEmpty()) {
					if(Text[0].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "�Ǹ����ڸ� �Է����ּ���.");
						Text[0].requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "��ǰ�ڵ带 �Է����ּ���.");
						Text[1].requestFocus();
					}
				}else if(Text[2].getText().isEmpty() || Text[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "���� �� ������� �Է����ּ���.");
					if(Text[2].getText().isEmpty()) {
						Text[2].requestFocus();
					}else {
						Text[3].requestFocus();
					}
				}
				
				pvo.setSale_date(Text[0].getText());
				pvo.setProduct_id(Text[1].getText());
				pvo.setS_count(Integer.parseInt(Text[2].getText()));
				pvo.setN_count(Integer.parseInt(Text[3].getText()));
				
				pdao.setCon(DBConnection.getCon());
				
				pdao.create(pvo);
			}
			listAll();
			
		}
	};
	
	ActionListener updateHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField[] Text;
			
			if(m.getPan() == 1) {
				Text = m.getPan1().getText();
				if(Text[0].getText().isEmpty() || Text[1].getText().isEmpty()) {
					if(Text[0].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "��ǰ�ڵ带 �Է����ּ���.");
						Text[0].requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "��ǰ���� �Է����ּ���.");
						Text[1].requestFocus();
					}
				}else if(Text[2].getText().isEmpty() || Text[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������� �Է����ּ���.");
					if(Text[2].getText().isEmpty()) {
						Text[2].requestFocus();
					}else {
						Text[3].requestFocus();
					}
				}
				
				vo.setProduct_id(Text[0].getText());
				vo.setName(Text[1].getText());
				vo.setPrice_in(Integer.parseInt(Text[2].getText()));
				vo.setPrice_out(Integer.parseInt(Text[3].getText()));
				
				dao.setCon(DBConnection.getCon());
				
				dao.update(vo);
			}else if(m.getPan() == 2) {
				Text = m.getPan2().getText();
				if(Text[0].getText().isEmpty() || Text[1].getText().isEmpty()) {
					if(Text[0].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "��ǰ�ڵ带 �Է����ּ���.");
						Text[0].requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "��ǰ���� �Է����ּ���.");
						Text[1].requestFocus();
					}
				}else if(Text[2].getText().isEmpty() || Text[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������� �Է����ּ���.");
					if(Text[2].getText().isEmpty()) {
						Text[2].requestFocus();
					}else {
						Text[3].requestFocus();
					}
				}
				
				pvo.setSale_date(Text[0].getText());
				pvo.setProduct_id(Text[1].getText());
				pvo.setS_count(Integer.parseInt(Text[2].getText()));
				pvo.setN_count(Integer.parseInt(Text[3].getText()));
				
				pdao.setCon(DBConnection.getCon());
				
				
				pdao.update(pvo);
				
			}
			listAll();
			
		}
	};
	
	ActionListener deleteHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField[] Text;
			if(JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "��������", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			
				if(m.getPan() == 1) {
					Text = m.getPan1().getText();
					vo.setProduct_id(Text[0].getText());
					
					Connection con = DBConnection.getCon();		//������ DB ���� ����
					dao.setCon(con);							//DB����
					dao.delete(Text[0].getText(),Text[1].getText());
				}else if(m.getPan() == 2) {
					Text = m.getPan2().getText();
					pvo.setProduct_id(Text[0].getText());
					
					Connection con = DBConnection.getCon();		//������ DB ���� ����
					pdao.setCon(con);							//DB����
					pdao.delete(Text[1].getText(),Text[0].getText());	
				}
				listAll();	
			}
			
		}
	};
	
	ActionListener calcuHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Connection con = DBConnection.getCon();
			
			pdao.setCon(con);
			
			pdao.calcu();
			
			listAll();
		}
	};
	
	MouseAdapter table1Handler = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JTextField [] Text;
			if(e.getClickCount() == 2) {	//���콺�� Ŭ���� Ƚ���� �˾Ƴ��� �Լ�
					if(m.getPan()==1)
					{
					int row = table1.getSelectedRow();
					Text = m.getPan1().getText();
					
					for (int i = 0; i < Text.length; i++) {
						Text[i].setText(table1.getValueAt(row, i).toString());
					}
				}else if(m.getPan()==2)
				{
					int row = table2.getSelectedRow();
					Text = m.getPan2().getText();
					
					for (int i = 0; i < Text.length; i++) {
						Text[i].setText(table2.getValueAt(row, i).toString());
					}
				}
			}
		}
	};
	
	
	

}
