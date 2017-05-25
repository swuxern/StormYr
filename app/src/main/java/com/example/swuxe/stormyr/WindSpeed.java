package com.example.swuxe.stormyr;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(strict=false)
public class WindSpeed {
    @Attribute(required = false)
    String name;

    @Attribute(required = false)
    double mps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMps() {
        return mps;
    }
}
