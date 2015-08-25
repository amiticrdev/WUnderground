package com.weather.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.weather.model.CurrentObservation;
import com.weather.model.WUndergroundError;
import com.weather.model.WeatherData;
import com.weather.model.WeatherUndergroundObservation;
import com.weather.service.WUndergroundService;
import com.weather.validation.ZipcodeValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WeatherControllerTest

{
    @Configuration
    static class WeatherControllerTestContextConfiguration
    {
        @Bean
        public WUndergroundService wUndergroundServiceMock()
        {
            return Mockito.mock(WUndergroundService.class);
        }

        @Bean
        public BindingResult bindingResult()
        {
            return Mockito.mock(BindingResult.class);
        }

        @Bean
        public WeatherUndergroundObservation weatherUndergroundObservationMock()
        {
            return Mockito.mock(WeatherUndergroundObservation.class);
        }
    }

    @Autowired
    private WUndergroundService wUndergroundServiceMock;

    @Autowired
    private WeatherUndergroundObservation weatherUndergroundObservationMock;

    private ZipcodeValidator validator;

    @Autowired
    private BindingResult bindingResult;

    private WeatherController WeatherController;

    public WeatherControllerTest()
    {
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
        validator = new ZipcodeValidator();
        WeatherController = new WeatherController(wUndergroundServiceMock);
        WeatherController.setValidator(validator);
    }

    @After
    public void tearDown()
    {

    }

    /**
     * @throws Exception
     */
    @Test
    public void testShowWeatherWithSuccess()
        throws Exception
    {
        when(wUndergroundServiceMock.getWeatherData(Matchers.anyString()))
            .thenReturn(weatherUndergroundObservationMock);

        when(weatherUndergroundObservationMock.getCurrent_observation())
            .thenReturn(new CurrentObservation());
        
        
        when(weatherUndergroundObservationMock.getResponse())
        .thenReturn(new WUndergroundError());

        Model model = new ExtendedModelMap();

        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("12345");

        when(bindingResult.hasErrors()).thenReturn(false);

        ModelAndView modelAndView = WeatherController.showWeather(weatherData, bindingResult, model);
        
        assertEquals("weatherReport", modelAndView.getViewName());

    }
    
    
    /**
     * @throws Exception
     */
    @Test
    public void testShowWeatherWithValidationError()
        throws Exception
    {
        when(wUndergroundServiceMock.getWeatherData(Matchers.anyString()))
            .thenReturn(weatherUndergroundObservationMock);

        when(weatherUndergroundObservationMock.getCurrent_observation())
            .thenReturn(new CurrentObservation());
        
        
        when(weatherUndergroundObservationMock.getResponse())
        .thenReturn(new WUndergroundError());

        Model model = new ExtendedModelMap();

        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("1234");

        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = WeatherController.showWeather(weatherData, bindingResult, model);
        
        assertEquals("weather", modelAndView.getViewName());

    }
    
    
    /**
     * @throws Exception
     */
    @Test
    public void testShowWeatherWithAPIError()
        throws Exception
    {
        // consider getWeatherData returns null after the API call
        when(wUndergroundServiceMock.getWeatherData(Matchers.anyString()))
            .thenReturn(null);

        Model model = new ExtendedModelMap();

        WeatherData weatherData = new WeatherData();
        weatherData.setZipCode("1234");

        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = WeatherController.showWeather(weatherData, bindingResult, model);
        
        assertEquals("weather", modelAndView.getViewName());

    }
    
}
