
package org.premiumapp.weatheronclick.model;

import java.util.Arrays;

public final class WeatherOneDay {

    public final Coord coord;
    public final Weather weather[];
    public final String base;
    public final Main main;
    public final Wind wind;
    public final Clouds clouds;
    public final long dt;
    public final Sys sys;
    public final long id;
    public final String name;
    public final long cod;

    public WeatherOneDay(Coord coord, Weather[] weather, String base, Main main,Wind wind,
                         Clouds clouds, long dt, Sys sys, long id, String name, long cod){
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public static final class Coord {

        public final double lon;
        public final double lat;

        public Coord(double lon, double lat){
            this.lon = lon;
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public static final class Weather {

        public final long id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(long id, String main, String description, String icon){
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public static final class Main {
        public final double temp;
        public final double pressure;
        public final long humidity;
        public final double temp_min;
        public final double temp_max;
        public final double sea_level;
        public final double grnd_level;

        public Main(double temp, double pressure, long humidity,
                    double temp_min, double temp_max, double sea_level, double grnd_level){

            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.sea_level = sea_level;
            this.grnd_level = grnd_level;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", sea_level=" + sea_level +
                    ", grnd_level=" + grnd_level +
                    '}';
        }
    }

    public static final class Wind {

        public final double speed;
        public final double deg;

        public Wind(double speed, double deg){
            this.speed = speed;
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    public static final class Clouds {

        public final long all;

        public Clouds(long all){
            this.all = all;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    public static final class Sys {

        public final double message;
        public final String country;
        public final long sunrise;
        public final long sunset;

        public Sys(double message, String country, long sunrise, long sunset){
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "message=" + message +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherOneDay{" +
                "coord=" + coord +
                ", weather=" + Arrays.toString(weather) +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}
