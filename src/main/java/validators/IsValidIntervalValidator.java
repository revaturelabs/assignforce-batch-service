package validators;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *
 * @author Hayden
 */
public class IsValidIntervalValidator implements ConstraintValidator<IsValidInterval, Object>
{
    // name of variables so we can reflect out the vlaues in the object
    private String start;
    private String end;

    @Override
    public void initialize(IsValidInterval constraintAnnotation) 
    {
        this.start = constraintAnnotation.startDate();
        this.end = constraintAnnotation.endDate();
    }    

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context)
    {
        final Class<?> clazz = obj.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        LocalDate startObj = null;
        LocalDate endObj = null;
        try
        {
        for (Field field : fields)
        {
            boolean accessibility = field.isAccessible();
            field.setAccessible(true);
            if (field.getName().equals(start))
            {
                startObj = (LocalDate)field.get(obj);
            }
            else if (field.getName().equals(end))
            {
                endObj = (LocalDate)field.get(obj);
            }
            field.setAccessible(accessibility);
        }
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        if(startObj == null || endObj == null) 
        	return false;
        
        return startObj.compareTo(endObj) < 0;
    }
}