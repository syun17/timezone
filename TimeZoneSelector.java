import javax.swing.*;
import java.awt.*;
import java.util.TimeZone;

/**
 * タイムゾーンを選択するためのコンボボックスを表示するパネル
 */
public class TimeZoneSelector extends JPanel {
    public TimeZoneSelector(ClockPanel clockPanel, MapPanel mapPanel) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel label = new JLabel("タイムゾーンを選択:");
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(label);

        String[] zones = TimeZone.getAvailableIDs();
        JComboBox<String> comboBox = new JComboBox<>(zones);
        comboBox.setPreferredSize(new Dimension(400, 40));
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setSelectedItem(TimeZone.getDefault().getID());

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            TimeZone zone = TimeZone.getTimeZone(selected);
            clockPanel.setTimeZone(zone);    // 時計ラベル更新
        });

        add(comboBox);
    }
}
