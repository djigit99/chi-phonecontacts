package dev.djigit.phonecontacts.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneCollectionValidator implements ConstraintValidator<PhoneCollection, Collection<String>> {

    @Override
    public void initialize(PhoneCollection constraintAnnotation) {

    }

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (String s : value) {
            if (!isSingleValid(s)) {
                return false;
            }
        }

        long count = value.stream().filter(v -> Collections.frequency(value, v) > 1)
                .count();
        return count <= 1;
    }

    private boolean isSingleValid(String number) {
        Pattern pattern = Pattern.compile("^\\+\\d{12}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}