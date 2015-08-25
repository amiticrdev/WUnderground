package com.weather.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.weather.model.WeatherData;

@Component
public class ZipcodeValidator
    implements Validator
{

    private Pattern pattern;
    private Matcher matcher;
    private final String ID_PATTERN = "[0-9]+";

    @Override
    public boolean supports(Class<?> myClass)
    {
        return WeatherData.class.equals(myClass);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        WeatherData weatherData = (WeatherData) target;

        if (weatherData.getZipCode().length() == 0)
        {
            ValidationUtils.rejectIfEmpty(errors, "zipCode", "value.required",
                                          "Zip Code is required");
        }
        else
        {
            // Zip code should contain numeric values only
            pattern = Pattern.compile(ID_PATTERN);
            matcher = pattern.matcher(weatherData.getZipCode().toString());
            if (!matcher.matches())
            {
                errors.rejectValue("zipCode", "zipCode.incorrect",
                                   new Object[] { weatherData.getZipCode() },
                                   "Zip Code should be a numeric value");
            }
            if (weatherData.getZipCode().length() != 5)
            {
                errors.rejectValue("zipCode", "zipCode.invalid",
                                   new Object[] { weatherData.getZipCode() },
                                   "Zip Code should be 5 character long");
            }
        }

    }
}