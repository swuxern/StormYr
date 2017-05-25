package com.example.swuxe.stormyr;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import static android.R.attr.required;

/**
 * Created by tim on 23.02.2017.
 */
@Root(strict=false)
public class Forecast {
    @ElementList(name="tabular")
    List<Time> timeList;

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }


}
