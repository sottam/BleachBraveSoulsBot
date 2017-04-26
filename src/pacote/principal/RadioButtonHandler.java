package pacote.principal;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonHandler implements ItemListener {

	@SuppressWarnings("static-access")
	@Override
	public void itemStateChanged(ItemEvent event) {

		if (Principal.getInterface().nq.isSelected()) {
			Principal.setQuestMODE(0);
		}

		if (Principal.getInterface().le.isSelected()) {
			Principal.setQuestMODE(1);
		}

		if (Principal.getInterface().lp.isSelected()) {
			Principal.setQuestMODE(2);
		}

		// -----------------------------------------------------------------

		if (Principal.getInterface().t1.isSelected()) {
			Principal.setSelectedTIME(1);
		}

		if (Principal.getInterface().t2.isSelected()) {
			Principal.setSelectedTIME(2);
		}

		if (Principal.getInterface().t3.isSelected()) {
			Principal.setSelectedTIME(3);
		}

		if (Principal.getInterface().t4.isSelected()) {
			Principal.setSelectedTIME(4);
		}
		
		if (Principal.getInterface().t5.isSelected()) {
			Principal.setSelectedTIME(5);
		}
		
		// -----------------------------------------------------------------

		if (Principal.getInterface().b1.isSelected()) {
			Principal.booster[1] = true;
		} else
			Principal.booster[1] = false;

		if (Principal.getInterface().b2.isSelected()) {
			Principal.booster[2] = true;
		} else
			Principal.booster[2] = false;

		if (Principal.getInterface().b3.isSelected()) {
			Principal.booster[3] = true;
		} else
			Principal.booster[3] = false;

		if (Principal.getInterface().b4.isSelected()) {
			Principal.booster[4] = true;
		} else
			Principal.booster[4] = false;

	}

}
