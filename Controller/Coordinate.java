package Controller;
import Model.City;

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

        coordinates = City.getCoordinate(city);

        // 経度のデータが-180~180と仮定0~360を返す
        if (coordinates[0] >= -leftX) {
            coordinates[0] = coordinates[0] + leftX;
        } else {
            coordinates[0] = 360 + leftX + coordinates[0];
        }

        // 緯度のデータが-90~90と仮定0~180を返す
        if (coordinates[1] >= 0) {
            coordinates[1] = 90 - coordinates[1];
        } else {
            coordinates[1] = -coordinates[1];
            coordinates[1] = coordinates[1] + 90;
        }

        return coordinates;
    }
}
