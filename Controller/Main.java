package Controller;
import javax.swing.SwingUtilities;

import View.MainFrame;

public class Main {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
