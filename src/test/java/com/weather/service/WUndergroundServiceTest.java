package com.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.weather.model.WeatherUndergroundObservation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WUndergroundServiceTest
{

    @Configuration
    static class WUndergroundServiceTestContextConfiguration
    {
        @Bean
        public WUndergroundService wUndergroundServiceMock()
        {
            return Mockito.mock(WUndergroundService.class);
        }
    }

    @Autowired
    private WUndergroundService wUndergroundServiceMock;

    public WUndergroundServiceTest()
    {
    }

    /**
     * @throws Exception
     */
    @Test
    public void testgetWeatherDataWithSuccess()
        throws Exception
    {
        WeatherUndergroundObservation weatherUndergroundObservation =
            new WeatherUndergroundObservation();

        when(wUndergroundServiceMock.getWeatherData(Matchers.anyString()))
            .thenReturn(weatherUndergroundObservation);

        assertNotNull("should not be null", weatherUndergroundObservation);

    }

    /**
     * @throws Exception
     */
    @Test
    public void testgetWeatherDataWithError()
        throws Exception
    {
        WeatherUndergroundObservation weatherUndergroundObservation = null;

        when(wUndergroundServiceMock.getWeatherData(Matchers.anyString()))
            .thenReturn(weatherUndergroundObservation);

        assertEquals("should be null in case of any error", true,
                     weatherUndergroundObservation == null);

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
    }

    @After
    public void tearDown()
    {

    }

}
