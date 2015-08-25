package com.weather.validation;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.weather.model.WeatherData;

public class ZipcodeValidatorTest
{
    private ZipcodeValidator zipcodeValidator;

    public ZipcodeValidatorTest()
    {
    }

    /**
     * @throws Exception
     */
    @Test
    public void testValidateValidZipCode()
        throws Exception
    {
        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("12345");

        Errors errors = new BeanPropertyBindingResult(weatherData, "validWeatherData");
        zipcodeValidator.validate(weatherData, errors);

        assertEquals(false, errors.hasErrors());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testValidateZipCodeRequired()
        throws Exception
    {
        // set an empty value for the zip code

        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("");

        Errors errors = new BeanPropertyBindingResult(weatherData, "invalidWeatherData");
        zipcodeValidator.validate(weatherData, errors);
        assertEquals("Zip Code is required", errors.getFieldError("zipCode").getDefaultMessage());

    }

    /**
     * @throws Exception
     */
    @Test
    public void testValidateZipCodeNumericValue()
        throws Exception
    {
        // set a string value for the zip code
        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("abcd");

        Errors errors = new BeanPropertyBindingResult(weatherData, "invalidWeatherData");
        zipcodeValidator.validate(weatherData, errors);
        assertEquals("Zip Code should be a numeric value", errors.getFieldError("zipCode")
            .getDefaultMessage());

        // set a 5 character string value for the zip code
        WeatherData weatherData1 = new WeatherData();
        weatherData1.setZipCode("abcde");

        Errors errors1 = new BeanPropertyBindingResult(weatherData1, "invalidWeatherData");
        zipcodeValidator.validate(weatherData1, errors1);
        assertEquals("Zip Code should be a numeric value", errors1.getFieldError("zipCode")
            .getDefaultMessage());

    }

    /**
     * @throws Exception
     */
    @Test
    public void testValidateZipCodeCharacterLength()
        throws Exception
    {
        // set a 6 digit value for the zip code
        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("123456");

        Errors errors = new BeanPropertyBindingResult(weatherData, "invalidWeatherData");
        zipcodeValidator.validate(weatherData, errors);
        assertEquals("Zip Code should be 5 character long", errors.getFieldError("zipCode")
            .getDefaultMessage());

        // set a 4 digit value for the zip code
        WeatherData weatherData1 = new WeatherData();
        weatherData1.setZipCode("1234");

        Errors errors1 = new BeanPropertyBindingResult(weatherData1, "invalidWeatherData");
        zipcodeValidator.validate(weatherData1, errors1);
        assertEquals("Zip Code should be 5 character long", errors1.getFieldError("zipCode")
            .getDefaultMessage());

    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        zipcodeValidator = new ZipcodeValidator();
    }

    @After
    public void tearDown()
    {

    }

}
