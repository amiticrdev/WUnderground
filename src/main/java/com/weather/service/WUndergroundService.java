package com.weather.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.weather.model.WeatherUndergroundObservation;

@Service
public class WUndergroundService
{
    final static Logger log = Logger.getLogger(WUndergroundService.class);

    private static final Gson gson = new Gson();

    private final String API_END_POINT = "http://api.wunderground.com/api/";

    @Value("${underground.weather.api.key}")
    private String apiKey;

    /**
     * @return WeatherUndergroundObservation
     */
    public WeatherUndergroundObservation getWeatherData(String zipCode)
    {
        String weatherURI = API_END_POINT + apiKey + "/conditions/q/" + zipCode + ".json";

        InputStream inputStream = fetchInputStream(weatherURI);

        if (inputStream == null) return null;

        Reader reader = new InputStreamReader(inputStream);

        WeatherUndergroundObservation weatherUndergroundObservation =
            gson.fromJson(reader, WeatherUndergroundObservation.class);

        return weatherUndergroundObservation;
    }

    /**
     * @param weatherURI
     * @return
     */
    private InputStream fetchInputStream(String weatherURI)
    {
        HttpClient httpClient = (HttpClient) HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(weatherURI);
        try
        {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK)
            {
                log.error("Error " + statusCode + " for URL " + weatherURI);
                return null;
            }
            log.info("Response received from the URL: " + weatherURI + ", with status code: "
                     + statusCode);
            HttpEntity httpEntity = httpResponse.getEntity();
            return ((org.apache.http.HttpEntity) httpEntity).getContent();
        }
        catch (IOException e)
        {
            httpGet.abort();
            log.error("Error for URL: " + weatherURI + " due to, " + e.getMessage());
        }
        return null;
    }
}
