package View;
import javax.swing.*;
import java.awt.*;

/**
 * アプリケーションのメインウィンドウを構築するクラス
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("時差計算ソフト");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        setSize(800, 450); // ← サイズを明示的に指定
        setLocationRelativeTo(null); // 中央表示

        ClockPanel clockPanel = new ClockPanel();
        MapPanel mapPanel = new MapPanel();  // 地図パネル
        TimeZoneSelector tzSelector = new TimeZoneSelector(clockPanel, mapPanel);

        // 時計と地図を並べて表示（左：時計、右：地図）
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(clockPanel, BorderLayout.WEST);
        topPanel.add(mapPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(tzSelector, BorderLayout.SOUTH);

        setVisible(true); // ウィンドウ表示
    }

}
