package sales.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import sales.domain.SalesVO;



public class goodsManagement extends JPanel {
	
	String [] cols = {"물품코드", "물품명", "입고가", "출고가"};
	public JButton pan1_btn = new JButton("panel 1");
	
	JPanel [] control = new JPanel[3];
	JPanel [] inputP = new JPanel[2];
	List<SalesVO> list;
	JTable table;
	DefaultTableModel model;
	
	JLabel [] labels = new JLabel[cols.length];
	JTextField [] Text = new JTextField[cols.length];
	
	JButton searchBtn = new JButton("검색");
	JButton insertBtn = new JButton("삽입");
	JButton deleteBtn = new JButton("삭제");
	JButton updateBtn = new JButton("수정");
	
	public JTable getTable() {
		return table;
	}

	public JTextField[] getText() {
		return Text;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JButton getInsertBtn() {
		return insertBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JButton getUpdateBtn() {
		return updateBtn;
	}

	
	
	public goodsManagement() {
		// TODO Auto-generated constructor stub
		
//		pan1_btn.setBounds(10, 10, 100,50);
//		add(pan1_btn);
		
		
		setLayout(new BorderLayout(10, 10));
		
		control[0] = new JPanel();
		control[1] = new JPanel();
		control[2] = new JPanel();
		
		

		control[0].setBounds(0, 0, 200, 200);
		
		
		control[0].setLayout(new BorderLayout());
		control[1].setLayout(new BorderLayout());
		control[2].setLayout(new FlowLayout());
		
		inputP[0] = new JPanel();
		inputP[1] = new JPanel();
		inputP[0].setLayout(new GridLayout(4,1));
		inputP[1].setLayout(new GridLayout(4,1));
		
		for (int i = 0; i < cols.length; i++) {
			labels[i] = new JLabel(cols[i] + " : ");
			Text[i] = new JTextField(15);
			inputP[0].add(labels[i]);
			inputP[1].add(Text[i]);
		}
		
		control[2].add(searchBtn);
		control[2].add(insertBtn);
		control[2].add(updateBtn);
		control[2].add(deleteBtn);

		model = new DefaultTableModel(cols,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(model);
				
		Font font = new Font("돋움", 1, 15);
				 
		
		table.getTableHeader().setReorderingAllowed(false);		// 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false);			// 컬럼 크기 조절 불가
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setFont(font);
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollpane = new JScrollPane(table);
		control[0].add(inputP[0], "West");
		control[0].add(inputP[1], "East");
		control[1].add(scrollpane, "Center");
		
		add(control[0], "North");
		add(control[1], "Center");
		add(control[2], "South");
		
		setBounds(50, 50, 700, 700);
	}
	
	public void setList(List<SalesVO> list) {
		this.list = list;
	}
	
	public void setTable() {
		model.setRowCount(list.size());
		
		SalesVO vo = null;
		
		for (int i = 0; i < list.size(); i++) {
			vo=list.get(i);
			table.setValueAt(vo.getProduct_id(), i, 0);
			table.setValueAt(vo.getName(), i, 1);
			table.setValueAt(vo.getPrice_in(), i, 2);
			table.setValueAt(vo.getPrice_out(), i, 3);
		}
	}
	
}
