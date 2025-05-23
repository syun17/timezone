import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日本時間と選択タイムゾーンの時間を表示するパネル
 */
public class ClockPanel extends JPanel {
    private JLabel jstLabel; // 日本時間用ラベル
    private JLabel localLabel; // 選択タイムゾーン用ラベル
    private TimeZone selectedZone; // 選択されたタイムゾーン

    public ClockPanel() {
        setLayout(new GridLayout(2, 1)); // 縦に2行で表示

        // ラベルを初期化してフォント設定
        jstLabel = new JLabel();
        localLabel = new JLabel();
        jstLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        localLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        // 左寄せに配置
        jstLabel.setHorizontalAlignment(SwingConstants.LEFT);
        localLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // パネルにラベル追加
        add(jstLabel);
        add(localLabel);

        // 初期タイムゾーンをシステムのデフォルトに
        selectedZone = TimeZone.getDefault();

        // 時間更新タイマー（1秒ごと）
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    /**
     * 選択タイムゾーンの変更を受け取る
     */
    public void setTimeZone(TimeZone zone) {
        this.selectedZone = zone;
        updateTime();
    }

    /**
     * ラベルを更新（日本時間 + 選択タイムゾーン）
     */
private void updateTime() {
    Date now = new Date();

    // 日本時間表示（固定）
    SimpleDateFormat jstFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    jstFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
    jstLabel.setText("日本時間: " + jstFormat.format(now));

    // 選択タイムゾーン表示（2行表示）
    SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    localFormat.setTimeZone(selectedZone);
    
    // HTMLを使用してテキストを2行に分割
    localLabel.setText("<html>現地時間 (" + selectedZone.getID() + ")<br>" + localFormat.format(now) + "</html>");
}

}
