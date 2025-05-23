import javax.swing.*;
import java.awt.*;
import java.util.TimeZone;

/**
 * 世界地図（日本中心画像）上に選択タイムゾーンの位置をマーキングするパネル
 */
public class MapPanel extends JPanel {

    /** この地図画像の中央に来る経度（日本の標準時子午線＝東経135°） */
    private static final double CENTER_LON_DEG = 135.0;

    /** マーカーの描画半径 */
    private static final int DOT_R = 6;

    /** 背景画像 */
    private final Image mapImg;

    /** 背景画像の実サイズ */
    private final int imgWidth, imgHeight;

    /** 表示したい経度（-180 ～ +180°）。NaN の間は描かない */
    private double markerLonDeg = Double.NaN;

    public MapPanel() {
        // プロジェクトルートに置いた日本中心の地図画像（Clock.png）を読み込む
        mapImg   = new ImageIcon("Clock.png").getImage();
        imgWidth = mapImg.getWidth(null);
        imgHeight = mapImg.getHeight(null);

        // パネルがレイアウトで潰れないように実画像サイズを希望
        setPreferredSize(new Dimension(imgWidth, imgHeight));

        // デフォルトはシステムタイムゾーン
        setTimeZone(TimeZone.getDefault());
    }

    /** タイムゾーンを受け取り、表示経度を計算して再描画 */
    public void setTimeZone(TimeZone zone) {
        long now = System.currentTimeMillis();
        double offsetHr = zone.getOffset(now) / (1000.0 * 60 * 60); // 夏時間補正込み
        double lon = offsetHr * 15;                                  // 子午線に変換

        // 例：UTC+14 → +210° は -150° と同一地点なので正規化
        if (lon > 180)  lon -= 360;
        if (lon < -180) lon += 360;

        markerLonDeg = lon;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 画像をリサイズせず中央配置
        int x0 = (getWidth()  - imgWidth)  / 2;
        int y0 = (getHeight() - imgHeight) / 2;
        g.drawImage(mapImg, x0, y0, this);


        // 修正する
        // マーカー描画（経度→X 座標：日本中心補正）
        if (!Double.isNaN(markerLonDeg)) {
            // 中心経度を基準に 0～1 の範囲へ折り畳む
            double shifted = markerLonDeg - CENTER_LON_DEG + 180.0;
            shifted = (shifted % 360 + 360) % 360;        // 常に 0～360
            double xRatio = shifted / 360.0;              // 0.0～1.0

            int x = (int) (xRatio * imgWidth) + x0;       // 画像内 → パネル内
            int y = y0 + imgHeight / 2;                   // 緯度 0° ≒ 画像縦中央

            g.setColor(Color.RED);
            g.fillOval(x - DOT_R, y - DOT_R, DOT_R * 2, DOT_R * 2);
            g.setColor(Color.BLACK);
            g.drawOval(x - DOT_R, y - DOT_R, DOT_R * 2, DOT_R * 2);
        }
    }
}
