package com.example.swuxe.stormyr;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(strict=false)
public class WindDirection {
    @Attribute(required = false)
    double deg;

    @Attribute(required = false)
    String code;

    @Attribute(required = false)
    String name;


// Gettere og settere


    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
