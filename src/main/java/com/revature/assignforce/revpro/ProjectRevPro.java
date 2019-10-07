package com.revature.assignforce.revpro;

import java.util.List;

import com.revature.assignforce.beans.Project;

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
public class ProjectRevPro 
{
	/**
	 * The purpose of this method is retrieve a specific project from Revature Pro. It uses the id associated with the Project to retrieve
	 * this information. This should probably be sent as a GET Request. This method will need to also handle the conversion of data, 
	 * from the form that Assign Force uses to the form that Revature Pro uses.
	 * 
	 * @param id : The id associated with the specific project that needs to be retrieved.
	 * @return This method will return the Project object retrieved from Revature Pro.
	 */
	public Project getById(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve a specific project from Revature Pro using the project name. The method will need to handle
	 * the conversion of data between Assign Force and Revature Pro. It should probably utilize a GET request to communicate with the API.
	 * 
	 * @param name : The name of the project that needs to be retrieved.
	 * @return This method returns the project that matches the given project name.
	 */
	public Project getByName(String name)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to retrieve all projects from Revature Pro. The method will need to convert the returned JSON Array, 
	 * into a list of project objects. A simple GET request should probably be used.
	 * 
	 * @return This method returns a list of projects retrieved from Revature Pro.
	 */
	public List<Project> getAll()
	{
		return null;
	}
	
	
}
