package com.epam.spring.validator;


import com.epam.spring.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Value("${invalidUserMessage}")
    private String invalidUserMessage;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object objectToValidate, Errors errors) {
        User user = (User) objectToValidate;
        int length = user.getFirstName().length();
        if (length < 3 || length > 10) {
            errors.reject(invalidUserMessage);
        }

    }
}
