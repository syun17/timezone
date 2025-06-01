package View;
import javax.swing.*;

import Model.City;

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

        City[] cities = City.getCities(); // 都市情報を取得
        String[] cityNames = City.getCitiesName(cities); // 都市名の配列を取得
        JComboBox<String> comboBox = new JComboBox<>(cityNames); // 利用可能なタイムゾーンIDを取得してコンボボックスに設定
        comboBox.setPreferredSize(new Dimension(400, 40));
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setSelectedItem(TimeZone.getDefault().getID());

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            TimeZone zone = TimeZone.getTimeZone(selected);
            clockPanel.setTimeZone(zone);    // 時計ラベル更新
            mapPanel.setMap(selected);
        });

        add(comboBox);
    }
}
