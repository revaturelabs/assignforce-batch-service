
package com.revature.testing;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;




import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;


/**
 * the BatchConstraintTest program checks that the validators on the Batch bean function as expected
 * @author jesuschavez
 *
 */
public class BatchConstraintTest {
	private static Validator validator;
	

	/*
	 * - Set up a ValidatorFactory instance before tests are run
	 * - Define class level Validator instance with validator of the factory
	 * - These will be used in the testing methods
	 */
	
	  @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	  
	  


	   /**
	   * This is the BatchIsNull method that checks that the amount of null constraints is equivalent to 7 plus the one custom constraint IsValidInterval (which checks that a 
	   * endDate is not before startDate, however it also returns a violation if one the dates or both are null ) 
	   */
	  @Test
	   public void BatchIsNull() {
	      Batch myBatch = new Batch();
	      myBatch.setName(null);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( myBatch );
	      
	      //validator.validate(new Batch());

	      
	      
	    for(ConstraintViolation<Batch> myConstraint: constraintViolations) {
	    	System.out.println(myConstraint.getMessage());
	    }
	 
	 
	      assertEquals( 5, constraintViolations.size() );
	     
	   }
	  
	  
	  
	  /**
	   * This is the stringNameSizeMinimum method that tests that the constraint that test to see if a string is "" works
	   */
	  @Test
	   public void stringNameSizeMinimum() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 3, 6, 5, skillSet,1,1, 1, 6);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );

	      
	    for(ConstraintViolation<Batch> myConstraint: constraintViolations) {
	    	System.out.println(myConstraint.getMessage());
	    }
	 
	      assertEquals( 1, constraintViolations.size() );
	      
	   }
	  
	  
	 
	  /**
	   * This is the curriculumMinimumTest method which tests that the curriculum id cannot be 0 or a negative number
	  //Added by ERIC and ADAM but putting the @MIN annotation in the batch.java for curriculum
	   */
	  @Test
	   public void curriculumMinimumTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Java",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 0, 6, 5, skillSet,1,1, 1, 6);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );


	    for(ConstraintViolation<Batch> myConstraint: constraintViolations) {
	    	System.out.println(myConstraint.getMessage());
	    }

	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "must be greater than or equal to 1",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );

	   }
	  
	  
	  /**
	   * This is the trainerMinimumTest method which tests that the trainer id cannot be 0 or a negative number 
	   	  //Added by ERIC and ADAM but putting the @MIN annotation in the batch.java for trainerid

	   */
	  @Test
	   public void trainerMinimumTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 1, -10, 5, skillSet,1,1, 1, 6);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );




	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "must be greater than or equal to 1",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );

	   }
	  
	  
	 
	  /**
	   * This is the locationMinimumTest method which tests that the location id cannot be a negative number
	  can still be 0
	  added by Adam and Eric 
	   */
	  @Test
	   public void locationMinimumTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 1, 1, 5, skillSet,-10,1, 0, 6);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );




	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "must be greater than or equal to 1",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );

	   }
	  
	  

	  
	  /**
	   * This is the classSizeMinimumTest method which tests to see if a class size is below 5 (under the allowed minimum) and if the contraint to avoid this
	   * happening works.
	   */
	  @Test
	   public void classSizeMinimumTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 1, 1, 5, skillSet, 1,1,1, -4);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );

	      
	    
	 
	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "must be greater than or equal to 5",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	      
	   }
	  
	  //Test that the constraint that checks that class size does not exceed 35 works 
	  
	  /**
	   * This is the classSizeMaximumTest method test that the constraint that checks that class size does not exceed 35 works 
	   */
	  @Test
	   public void classSizeMaximumTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), 1, 1, 5, skillSet, 1,1,1, 36);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );

	      
	    
	 
	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "must be less than or equal to 35",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	      
	   }
	  
	  
	 
	  /**
	   * This is the isValidIntervalTest method that IsValidInterval constraint is working fine.
	   *  It checks that endDate is not earlier than startDate 
	   */
	  @Test
	   public void isValidIntervalTest() {

	      SkillIdHolder s1 = new SkillIdHolder(1);
			SkillIdHolder s2 = new SkillIdHolder(2);
			SkillIdHolder s3 = new SkillIdHolder(3);
			SkillIdHolder s4 = new SkillIdHolder(4);
			SkillIdHolder s5 = new SkillIdHolder(5);
			HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
			skillSet.add(s1);
			skillSet.add(s2);
			skillSet.add(s3);
			skillSet.add(s4);
			skillSet.add(s5);
			Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2020,1,1), LocalDate.of(2019,12,21), 1, 1, 5, skillSet,1,1, 1, 35);

	      Set<ConstraintViolation<Batch>> constraintViolations =
	      validator.validate( b1 );

	      
	    
	 
	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
	 	         "Start date is not before end date",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	      
	   }
	  
	  
	 
	
	  
	 
	  


}

