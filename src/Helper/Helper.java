package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Abbrechen");
		UIManager.put("OptionPane.noButtonText", "Nein");
		UIManager.put("OptionPane.okButtonText", "Ok");
		UIManager.put("OptionPane.yesButtonText", "Ja");
	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Bitte füllen Sie alle Felder aus";
			break;
		case "success":
			msg = "Es ist erfolgreich !";
			break;
		default:
			msg = str;
		}

		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg = "Möchten Sie das verwirklichen?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Vorsicht !", JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		} else {
			return false;
		}
	}
}
