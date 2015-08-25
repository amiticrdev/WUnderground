package com.weather.model;

public class WeatherUndergroundObservation
{

    private CurrentObservation current_observation;
    
    private WUndergroundError response;
    

    /**
     * @return the response
     */
    public WUndergroundError getResponse()
    {
        return response;
    }


    /**
     * @return the current_observation
     */
    public CurrentObservation getCurrent_observation()
    {
        return current_observation;
    }

}
