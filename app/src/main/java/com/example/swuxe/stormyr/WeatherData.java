package com.example.swuxe.stormyr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

    @Root(strict=false)

    public class WeatherData {
        @Element(required = false)
        Forecast forecast;
        @Element(required = false)
        Location location;

        public Forecast getForecast() {
            return forecast;
        }

        public void setForecast(Forecast forecast) {
            this.forecast = forecast;
        }


        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }
