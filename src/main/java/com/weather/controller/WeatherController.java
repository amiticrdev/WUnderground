package com.weather.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weather.model.WeatherData;
import com.weather.model.WeatherUndergroundObservation;
import com.weather.service.WUndergroundService;
import com.weather.validation.ZipcodeValidator;

@Controller
public class WeatherController
{

    final static Logger log = Logger.getLogger(WeatherController.class);

    @Autowired
    @Qualifier("zipcodeValidator")
    private ZipcodeValidator validator;

    private WUndergroundService weUndergroundService;

    @Autowired
    public WeatherController(WUndergroundService wUndergroundService)
    {
        this.weUndergroundService = wUndergroundService;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(ZipcodeValidator validator)
    {
        this.validator = validator;
    }

    @ModelAttribute("weatherData")
    public WeatherData weatherForm()
    {
        return new WeatherData();
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ModelAndView weather(@ModelAttribute("weatherData") WeatherData weatherData)
    {
        return new ModelAndView("weather", "weatherForm", weatherData);
    }

    @RequestMapping(value = "/weatherReport", method = RequestMethod.POST)
    public ModelAndView showWeather(@ModelAttribute("weatherData") WeatherData weatherData,
                                    BindingResult result, Model model)
    {
        // Validation for weatherData
        validator.validate(weatherData, result);

        // Check if any validation error(s) occurred
        if (result.hasErrors())
        {
            log.error("The request to fetch the weather failed due to the following validation error(s):");

            List<ObjectError> validationErrors = result.getAllErrors();
            for (ObjectError objectError : validationErrors)
            {
                log.error("Validation Error: " + objectError.getDefaultMessage());
            }

            return new ModelAndView("weather", "weatherData", weatherData);
        }

        WeatherUndergroundObservation weatherUndergroundObservation =
            weUndergroundService.getWeatherData(weatherData.getZipCode());
        
        if (weatherUndergroundObservation == null)
        {
            log.error("weather information is not fetched");

            return new ModelAndView("weather", "weatherData", weatherData);
        }
        
       if( weatherUndergroundObservation.getResponse().getError() != null)
       {
           ObjectError zipCodeNotFoundError = new ObjectError("weatherData", "Specified zipCode does not found.");
           result.addError(zipCodeNotFoundError);
           return new ModelAndView("weather", "weatherData", weatherData);
       }
        

        return new ModelAndView("weatherReport", "currentObservation",
                                weatherUndergroundObservation.getCurrent_observation());
    }

}