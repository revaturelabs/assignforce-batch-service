package com.revature.testing;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Batch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import validators.IsValidInterval;
import validators.IsValidIntervalValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IsValidIntervalValidatorTest {
	
	@Configuration
	static class IsValidIntervalValidatorTestContextConfiguration {
		
		@Bean
		public IsValidIntervalValidator isValidIntervalValidator() {
			return Mockito.mock(IsValidIntervalValidator.class);
		}
		
	}
	
	@Autowired
	private IsValidIntervalValidator isValidIntervalValidator;
	
	
	private static Validator validator;
	
	  @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	  
	  @Test
	   public void BatchIsNull() {
	      Batch myBatch = new Batch();
	      myBatch.setName(null);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( myBatch );

	      for(ConstraintViolation<Batch> myConstraint: constraintViolations) {
	    	  System.out.println(myConstraint.getMessage());
	      }
	  }
	
	
	@Test
	public void testRun() {
		/*Boolean flag = false;
		
		isValidIntervalValidator.initialize(null);
		Mockito.doNothing().when(isValidIntervalValidator).initialize(null);
		
		Mockito.when(isValidIntervalValidator.isValid(null, null)).thenReturn(flag);
		
		System.out.println(isValidIntervalValidator.isValid(null, null));*/
		
		//IsValidIntervalValidator iv = new IsValidIntervalValidator();
		
		//ConstraintValidator cv = new ConstraintValidatorContext();
		
		//iv.initialize(null);
		
		//assertTrue(flag);
	}

}
