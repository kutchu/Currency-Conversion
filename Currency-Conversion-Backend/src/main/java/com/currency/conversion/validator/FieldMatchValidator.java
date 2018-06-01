package com.currency.conversion.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ksrivas on 6/1/2018.
 */
@Slf4j
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldValue;
    private String secondFieldValue;
    private String message;
    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldValue = constraintAnnotation.first();
        secondFieldValue = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        try{
            final Object firstObj = BeanUtils.getProperty(value, firstFieldValue);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldValue);

            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception e){
            log.error("Exception occurred during validation : {} ", ExceptionUtils.getStackTrace(e));
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldValue)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
