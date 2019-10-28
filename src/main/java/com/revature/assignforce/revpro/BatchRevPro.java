package com.revature.assignforce.revpro;

import java.time.LocalDate;
import java.util.List;

import com.revature.assignforce.beans.Batch;

/**
 * 
 * @author Nick
 *
 * This Class is a prepared skeleton(no functionality) in anticipation for the integration of AssignForce into RevaturePro. 
 * The bellow methods are guidelines to help you identify the intended functionality we had in mind. Your batch may have a
 * shift in purpose by the time this project falls into your hands.
 *
 * The purpose of this class is to house methods for either retrieving and/or modifying information from the Revature Pro API.
 * Each method needs to handle both the communication with the Revature Pro API and the conversion of the data to 
 * AssignForce.
 *
 * Keep in mind that it is important that the information originating from RevPro needs to be identifiable as such and not
 * confused with information that is originating from within AssignForce.
 */
public class BatchRevPro 
{
	/**
	 * The purpose of this method is to retrieve all batch information from Revature Pro. It will need to handle the conversion of the 
	 * JSON Array returned by Revature Pro into the format used by this Service(Batch bean). This will probably be a simple get request
	 * that will be sent to the API to retrieve the information.
	 * 
	 * @return This will return a list of Batch objects.
	 */
	public List<Batch> getAll()
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve a single Batch from Revature Pro. It should be able to accomplish this with a simple GET
	 * request. It will also need to handle the conversion of the returned JSON string into a Batch object so that it can be ustilized by
	 * the service.
	 * 
	 * @param id : The id of the Batch information that needs to be retrieved.
	 * @return Returns the batch object that was retrieved from Revature Pro.
	 */
	public Batch getById(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to update batch information in Revature Pro. The information held by the service will need to be 
	 * converted into whatever form is needed by the API. Then a PUT request should be used to send the updated information to the API.
	 * 
	 * @param b : The Batch object that holds the updated information to be sent to the Revature Pro API.
	 * @return Returns the newly updated batch object.
	 */
	public Batch update(Batch b)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to insert new batch information into Revature Pro. The new batch information from Assign Force will
	 * need to be converted into a form acceptable by the Revature Pro API. A simple POST request to the API should probably be used 
	 * to send the information.
	 * 
	 * @param b : The Batch object that was just inserted into Revature Pro.
	 * @return Returns the newly added Batch object.
	 */
	public Batch create(Batch b)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to delete un-needed information in Revature Pro. It will send the id of the batch that needs to be
	 * deleted.
	 * 
	 * @param id : The id corresponding to the specific batch that needs to be deleted.
	 */
	public void delete(int id)
	{
		
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that correspond to a specific curriculum. What needs to be
	 * done in this method is entirely dependent on the nature of the API. If there is no API endpoint for this feature you will need 
	 * to get all of the batch information and then filter it here.
	 * 
	 * @param id : The id corresponding to the specific curriculum that is being used to filter results.
	 * @return Returns a list containing all batches that are associated with a specific curriculum.
	 */
	public List<Batch> getByCurriculum(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that correspond to a specific trainer. What needs to be
	 * done in this method is entirely dependent on the nature of the Revature Pro API. If there is no API end point that provides this
	 * information, this method will have to retrieve all of the batch information and filter it here.
	 * 
	 * @param id : The id associated with the trainer of the batches that need to be retrieved.
	 * @return Returns a list of all batches associated with a specific trainer.
	 */
	public List<Batch> getByTrainer(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that correspond to a specific trainer. What needs to be
	 * done in this method will be dependent on the nature of the API. If there is no end point that provides this information, this 
	 * method will need to first retrieve the batch information, then filter it.
	 * 
	 * @param id : The id corresponding to a specific location.
	 * @return Returns a list of Batch objects that correspond to a specific location.
	 */
	public List<Batch> getByLocation(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that are associated with a specific location and curriculum.
	 * What needs to be done in this method will be dependent on the Revature Pro API. If there are no API end points that provide this 
	 * information, this method will need to retrieve all batch information and the filter that information itself.
	 * 
	 * @param locationId : The id for a specific location.
	 * @param curriculumId : The id for a specific curriculum.
	 * @return This method will return a list of batches that are associated with the provided location and curriculum.
	 */
	public List<Batch> getByLocationAndCurriculum(int locationId, int curriculumId)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that take place between two provided dates. What needs to 
	 * be done in this method is dependent on what is provided by the Revature Pro API. If the API does not provide an end point that 
	 * provides this information, the method will need to retrieve all of the batches and perform the filtering itself.
	 * 
	 * @param startDate : The start of the time period that needs to be checked.
	 * @param endDate : The end of the time period that needs to be checked.
	 * @return This method returns a list of all batches that took place between to specific dates.
	 */
	public List<Batch> getAllBatchesStartingBetween(LocalDate startDate, LocalDate endDate)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that are associated to a specific trainer and which take
	 * place between two given dates. What needs to be done in this method will be dependent on what is provided by the Revature Pro API.
	 * If this information is not provided, the method will need to first get all of the batch information, then filter that information
	 * to achieve the desired result.
	 * 
	 * @param trainerId : The id of the trainer that the batches need to be associated with.
	 * @param startDate : The start of the time period in which the batches must take place.
	 * @param endDate : The end of the time period in which the batches must take place.
	 * @return This method returns a list of all batches associated with a specific trainer and that fall into a specified time period.
	 */
	public List<Batch> getAllByTrainerAndStratingBetween(int trainerId, LocalDate startDate, LocalDate endDate)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from Revature Pro that are at a specified location and which start in a
	 * specified time frame. What needs to be done in this method will depend on what the Revature Pro API provides. If the API 
	 * does not provide this specific information, the method will need to pull all Batches and perform the filtering itself.
	 * 
	 * @param locationId : The id associated with the location of a batch.
	 * @param startDate : The start of the time period the batch start dates must be within.
	 * @param endDate : The end of the time period the batch start dates must be within.
	 * @return This method will return a list of all batches from Revature Pro that share a specified location and which start at a
	 * specified time.
	 */
	public List<Batch> getAllByLocationAndStartingBetween(int locationId, LocalDate startDate, LocalDate endDate)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all batches from revature pro that share a specified curriculum and whose start date 
	 * falls within a specified time frame. What needs to be done in this method will depend on what exactly the Revature Pro API
	 * provides. If this specific query is not supported by the API, this method will need to perform the filtering of the batch data
	 * itself.
	 * 
	 * @param curriculumId : The id of the curriculum that will be associated with the batches.
	 * @param startDate : The beginning of the time period in which the batch start dates must fall.
	 * @param endDate : The end of the time period in which the batch start dates must fall.
	 * @return This method returns a list of all batches which have a certain curriculum and whose start dates fall within a specified
	 * time frame.
	 */
	public List<Batch> getAllByCurriculumAndStartingBetween(int curriculumId, LocalDate startDate, LocalDate endDate)
	{
		return null;
	}
}
