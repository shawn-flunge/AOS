package sales.func;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class pBtnFunc extends MouseAdapter{
	private String name;
	private JButton btn;
	private int count;
	
	public pBtnFunc(String name, JButton btn, int count) {
		this.name = name;
		this.btn = btn;
		this.count = count;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(count == 0 ) {
			btn.setText("매진");
		}else {
			btn.setText(name);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(count == 0) {
			btn.setText(name + "매진");
		}else {
			btn.setText(name);
		}
	}
}
