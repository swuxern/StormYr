package com.example.swuxe.stormyr;

import android.os.AsyncTask;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;




public class GetData extends AsyncTask<String, Integer, WeatherData>{

    public AsyncResponse delegate = null;

    public interface AsyncResponse {
        void processFinish(WeatherData feed);
    }

    @Override
    protected void onPreExecute(){ super.onPreExecute(); }

    @Override
    protected WeatherData doInBackground(String... params) {
        String url = params[0];
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
        ResponseEntity<WeatherData> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, WeatherData.class);
        WeatherData weatherData = responseEntity.getBody();
        return weatherData;


    }

    @Override
    protected void onPostExecute(WeatherData feed) {
        delegate.processFinish(feed);
    }

}
