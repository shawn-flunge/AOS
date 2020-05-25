package sales.func;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sales.view.mainView;

public class swapPanelForMenu implements ActionListener{

	mainView m;
	
	public swapPanelForMenu(mainView m) {
		this.m=m;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if(e.getSource()==m.ItemOf1stMenu[0]) {
//			m.dp.setVisible(true);
//			m.pan1.setVisible(false);
//			m.pan2.setVisible(false);
//			m.pan3.setVisible(false);
//			m.frame.getContentPane().add(m.dp);
//		}
//		else if(e.getSource()==m.ItemOf1stMenu[1]) {
//			m.dp.setVisible(false);
//			m.pan1.setVisible(true);
//			m.pan2.setVisible(false);
//			m.pan3.setVisible(false);
//			m.frame.getContentPane().add(m.pan1);
//		}
//		else if(e.getSource()==m.ItemOf1stMenu[2]) {
//			m.dp.setVisible(false);
//			m.pan1.setVisible(false);
//			m.pan2.setVisible(true);
//			m.pan3.setVisible(false);
//			m.frame.getContentPane().add(m.pan2);
//		}
//		else if(e.getSource()==m.ItemOf1stMenu[3]) {
//			m.dp.setVisible(false);
//			m.pan1.setVisible(false);
//			m.pan2.setVisible(false);
//			m.pan3.setVisible(true);
//			m.frame.getContentPane().add(m.pan3);
//		}
//		
		
//		========================================== 두 방법 다 잘됨
		
		
		String cmd = e.getActionCommand();
		
		if(cmd=="초기화면")
		{
			m.getDp().setVisible(true);
			m.getPan1().setVisible(false);
			m.getPan2().setVisible(false);
			m.getPan3().setVisible(false);
			m.getPan3().getView().setVisible(false);
			m.getContentPane().add(m.getDp());
			
			
		}
		
		
//		switch (cmd) {
//		case "초기화면":
//			m.getDp().setVisible(true);
//			m.getPan1().setVisible(false);
//			m.getPan2().setVisible(false);
//			m.getPan3().setVisible(false);
//			m.getPan3().getView().setVisible(false);
//			m.getContentPane().add(m.getDp());
//			
//			break;
//
//		case "물품관리":
//			m.getDp().setVisible(false);
//			m.getPan1().setVisible(true);
//			m.getPan2().setVisible(false);
//			m.getPan3().setVisible(false);
//			m.getContentPane().add(m.getPan1());
//			m.setPan(1);
//			break;
//		case "영업관리":
//			m.getDp().setVisible(false);
//			m.getPan1().setVisible(false);
//			m.getPan2().setVisible(true);
//			m.getPan3().setVisible(false);
//			m.getContentPane().add(m.getPan2());
//			m.setPan(2);
//			break;
//		case "판매관리":
//			m.getPan3().reload();
//			
//			m.getDp().setVisible(false);
//			m.getPan1().setVisible(false);
//			m.getPan2().setVisible(false);
//			m.getPan3().setVisible(true);
//			m.getContentPane().add(m.getPan3());
//			m.setPan(3);
//			
//////			m.getPan3().setVisible(true);
//////			m.getContentPane().add(m.bi);
////			
////			m.setContentPane(m.bi);
////			m.bi.add(m.getPan3(),"Center");
//			
//			break;
//		}
		
		
	}

}
