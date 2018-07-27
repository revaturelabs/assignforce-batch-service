package validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Hayden
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IsValidIntervalValidator.class)
public @interface IsValidInterval
{
    Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default {};

    String startDate() default "startDate";
    String endDate() default "endDate";
    
    String message() default "{Date invalid}";
}