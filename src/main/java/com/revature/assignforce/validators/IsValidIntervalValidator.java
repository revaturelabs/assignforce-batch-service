package com.revature.assignforce.validators;

import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator that is used along with IsValidInterval annotation to validate
 * that the start of an interval is before the end of an interval.
 * @author Hayden Fields
 * @see IsValidInterval
 */
public class IsValidIntervalValidator implements ConstraintValidator<IsValidInterval, Object>
{
    // start and end of the interval
    private String start;
    private String end;
    // if true then <= is used for validation else <
    private boolean inclusive;
    // is the value that is returned if either bound is null
    private boolean ifNull;
    
    /**
     * Extract the parameters from the annotation.
     * @param constraintAnnotation an instance of the annotation 
     */
    @Override
    public void initialize(IsValidInterval constraintAnnotation) 
    {
        this.start = constraintAnnotation.startInterval();
        this.end = constraintAnnotation.endInterval();
        this.inclusive = constraintAnnotation.inclusive();
        this.ifNull = constraintAnnotation.ifNull();
    }    

    /**
     * Determines If the object contains the specified valid interval.
     * @param obj An instance of the class that the annotation was bound to.
     * @param context Currently not explicitly used.
     * @return True if the start of the interval is before the end of the 
     * interval if either bound is null returns ifNull.
     * @throws IllegalArgumentException If either of the field names specified
     * are not on obj.
     */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) throws IllegalArgumentException
    {
        // variables for start and end of the interval
        Comparable startObj = null;
        Comparable endObj = null;
        
        // flahs for detecting if the given object has the required fields
        boolean startGotten = false;
        boolean endGotten = false;
        
        final Class<?> clazz = obj.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        try // neither exception should ever happen
        {
            // cheack each field
            for (Field field : fields)
            {
                boolean accessibility = field.isAccessible();
                field.setAccessible(true);

                // reflect out the fields in the object
                if (field.getName().equals(start))
                {
                    startObj = (Comparable)field.get(obj);
                    startGotten = true;
                }
                else if (field.getName().equals(end))
                {
                    endObj = (Comparable)field.get(obj);
                    endGotten = true;
                }

                field.setAccessible(accessibility);
            }

            // error one of the specified fields was not in the object
            if (!startGotten || !endGotten)
            {
                String errorMessage = 
                    String.format("In %s @isValidInterval, is given a"
                                + " field name that does not exist in %s.%s%s", 
                                    obj.getClass(), obj.getClass(),
                                    startGotten?"":String.format(" Field %s does exist for start.", start),
                                    endGotten?"":String.format(" Field %s does exist. for end", end));
                throw new IllegalArgumentException(errorMessage);
            }
        } 
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            // illegal argument when object does not have field 
            // will never happen since we get the Class and Field objects
            // from the same object
            
            // illegal access when access is more restrictive then private
            throw new IllegalArgumentException(ex);
        }
        
        // both objects exist but one of them is null behavior is specified by 
        // the annotation since could be caught by a not null constraint
        if (startObj == null || endObj == null) return ifNull;
        
        if (inclusive)
            return startObj.compareTo(endObj) <= 0;
        return startObj.compareTo(endObj) < 0;
    }
}
