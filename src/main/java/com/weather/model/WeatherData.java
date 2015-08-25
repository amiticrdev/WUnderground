package com.weather.model;

public class WeatherData
    extends BaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = -6812394562769623853L;

    private String zipCode;

    /**
     * @return the zipCode
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

}
