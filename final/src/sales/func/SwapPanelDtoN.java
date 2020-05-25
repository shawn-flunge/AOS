package sales.func;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sales.view.mainView;


public class swapPanelDtoN implements ActionListener{
	mainView m;
	
	public swapPanelDtoN(mainView m) {
		
		this.m=m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==m.getDp().btn1) {
			m.getDp().setVisible(false);
			m.getPan1().setVisible(true);
			m.getPan2().setVisible(false);
			m.getPan3().setVisible(false);
			m.getContentPane().add(m.getPan1());
			m.setPan(1);
		}
		else if(e.getSource()==m.getDp().btn2) {
			m.getDp().setVisible(false);
			m.getPan1().setVisible(false);
			m.getPan2().setVisible(true);
			m.getPan3().setVisible(false);
			m.getContentPane().add(m.getPan2());
			m.setPan(2);
		}
		else if(e.getSource()==m.getDp().btn3) {

			m.getPan3().reload();
			
			m.getDp().setVisible(false);
			m.getPan1().setVisible(false);
			m.getPan2().setVisible(false);
			m.getPan3().setVisible(true);
			m.getContentPane().add(m.getPan3());
			m.setPan(3);
		}
		
	}

}
