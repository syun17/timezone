package Model;
public class City {
    public String name; // 都市名
    public double longitude; // 経度
    public double latitude; // 緯度

    City(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static City[] getCities() {
        return new City[]{
            new City("Asia/Tokyo", 139.6917, 35.6895),
            new City("America/New_York", -74.0060, 40.7128),
            new City("Europe/London", -0.1276, 51.5074),
            new City("Australia/Sydney", 151.2093, -33.8688),
            new City("Europe/Paris", 2.3522, 48.8566)
        };
    }

    public static String[] getCitiesName(City[] cities) {
        String[] citiesNames = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            citiesNames[i] = cities[i].name;
        }
        return citiesNames;
    }

    public static int[] getCoordinate(String cityName) {
        City[] cities = getCities();
        for (City city : cities) {
            if (city.name.equals(cityName)) {
                int lon = (int)city.longitude;
                int lat = (int)city.latitude;
                return new int[]{lon, lat};
            }
        }

        int [] defaultCoordinates = {0, 0}; // デフォルト座標
        defaultCoordinates[0] = (int)cities[0].longitude; // 経度
        defaultCoordinates[1] = (int)cities[0].latitude; // 緯度
        return defaultCoordinates;
    }
}
