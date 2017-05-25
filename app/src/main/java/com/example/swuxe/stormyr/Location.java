package com.example.swuxe.stormyr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



@Root(strict = false)
public class Location {

    @Element(required = false)
    String name;

    @Element(required = false)
    String type;

    @Element(required = false)
    String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
