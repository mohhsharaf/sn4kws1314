package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Kid;
import play.mvc.Controller;

public class KidSearchResults extends Controller {
	public static void search(String searchString)
	{
		page(searchString);
	}
	
	public static void page(String searchString)
	{

		List<Kid> kidList = KidManageFriends.searchByName(searchString);
		
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long kidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(kidId);
		}
		
		if(kid == null)
		{

			System.out.println(kidList.size());
			render(searchString, kidList);
			return;
		}
		
		
		List<Boolean> kidFriends = new LinkedList<Boolean>();
		for(Object kidObject : kidList)
		{
			Kid k = (Kid) kidObject;
			
			kidFriends.add(KidManageFriends.areFriends(kid, k));
		}

		System.out.println(kidList.size());
		render(searchString, kidList, kidFriends);
		
		
	}
	
	
	public static void addFriend(Long otherKidId)
	{
		Kid otherKid = Kid.findById(otherKidId);
		
		
//		if(session.get("kidId") != null)
//		{
//			Long kidId = Long.parseLong(session.get("kidId"));
//			
			Kid kid = Kid.findById(Long.parseLong(session.get("kidId")));
//		}
		
		System.out.println(kid);
		System.out.println(otherKid);
		
		try
		{
			System.out.println("JUST BEFORE");
			KidManageFriends.addFriend(kid, otherKid);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		KidHome.page();
	}
	
	public static void removeFriend(Long otherKidId)
	{
		Kid otherKid = Kid.findById(otherKidId);
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long kidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(kidId);
		}
		
		if(kid == null)
		{
			KidHome.page();
			return;
		}
		
		KidManageFriends.removeFriend(kid, otherKid);
		KidHome.page();
	}
}

