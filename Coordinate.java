/**
 * 座標に関するクラス
 */

public class Coordinate {

    /**
     * 指定された都市の座標を取得するメソッド
     */
    public static int[] getCoordinate(String city) {
        int leftX = 30;
        int[] coordinates = new int[2];
        double lon = 151.0; // 経度
        double lat = -33.0; // 緯度

        // 経度のデータが-180~180と仮定0~360を返す
        if (lon >= -leftX) {
            lon = lon + leftX;
        } else {
            lon = 360 + leftX + lon;
        }

        // 緯度のデータが-90~90と仮定0~180を返す
        if (lat >= 0) {
            lat = 90 - lat;
        } else {
            lat = -lat;
            lat = lat + 90;
        }

        coordinates[0] = (int)lon;
        coordinates[1] = (int)lat;

        return coordinates;
    }
}
