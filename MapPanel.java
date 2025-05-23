import javax.swing.*;
import java.awt.*;
import java.util.TimeZone;

/**
 * 世界地図（日本中心画像）上に選択タイムゾーンの位置をマーキングするパネル
 */
public class MapPanel extends JPanel {

    /** マーカーの描画半径 */
    private static final int DOT_R = 3;

    /** 背景画像 */
    private final Image mapImg;

    /** 背景画像の実サイズ */
    private final int imgWidth, imgHeight;


    public MapPanel() {
        // プロジェクトルートに置いた日本中心の地図画像（Clock.png）を読み込む
        mapImg   = new ImageIcon("img/Map3.png").getImage();
        imgWidth = mapImg.getWidth(null);
        imgHeight = mapImg.getHeight(null);

        // パネルがレイアウトで潰れないように実画像サイズを希望
        setPreferredSize(new Dimension(imgWidth, imgHeight));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 画像をリサイズせず中央配置
        int x0 = (getWidth()  - imgWidth)  / 2;
        int y0 = (getHeight() - imgHeight) / 2;
        g.drawImage(mapImg, x0, y0, this);
        

        // 座標取得
        int[] coordinates = Coordinate.getCoordinate("");
        int lon = coordinates[0];
        int lat = coordinates[1];


        //画像サイズに合わせて座標を変換
        lon = lon * imgWidth / 360;
        lat = lat * imgHeight / 180;

        // マーカーを描画
        g.setColor(Color.RED);
        g.fillOval(lon - DOT_R, lat - DOT_R, DOT_R * 2, DOT_R * 2);
        g.setColor(Color.BLACK);
        g.drawOval(lon - DOT_R, lat - DOT_R, DOT_R * 2, DOT_R * 2);
    }
}
