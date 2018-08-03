package com.revature.assignforce.validators;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Hayden Fields
 * Annotation to be applied to a class, is repeatable if class must validate 
 * multiple intervals and is validated by IsValidIntervalValidator.
 * @see IsValidIntervalValidator
 */
@Repeatable(IsValidInterval.List.class)
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IsValidIntervalValidator.class)
public @interface IsValidInterval
{
    /**
     * List for repeated annotations.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @interface List 
    {
        /**
         * @return An array of the interval constraints.
         */
        IsValidInterval[] value();
    }
    
    /**
     * https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-customconstraints.html
     * @return Setting groupings for this constraint annotation.
     */
    Class<?>[] groups() default {};

    /**
     * https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-customconstraints.html
     * @return Setting custom payloads for this constraint annotation.
     */
    Class<? extends Payload>[] payload() default {};
    
    /**
     * @return The field name of the start of the interval.
     */
    String startInterval() default "startInterval";
    /**
     * @return The field name of the end of the interval.
     */
    String endInterval() default "endInterval";
    
    /**
     * @return True if it is valid that the start bound and end bound can be 
     * the same value and false otherwise.
     */
    boolean inclusive() default false;

    /**
     * @return Result of validation if one of the bounds are null.
     */
    boolean ifNull() default false;

    /**
     * @return The message if the object is not valid.
     */
    String message() default "{Interval is invalid}";   
}
