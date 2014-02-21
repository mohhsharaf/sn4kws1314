package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Kid;

public class KidManageFriends {
	public static void page(Long kidId)
	{
		Kid kid = Kid.findById(kidId);
//		render(kid);
	}
	
	
	public static List<Kid> searchByName(String searchString)
	{
		List<Kid> resultList = new LinkedList<Kid>();
		
		for(Object kidObj: Kid.findAll())
		{
			Kid kid = (Kid) kidObj;
			
			if(kid.name.toLowerCase().indexOf(searchString.toLowerCase()) != -1)
			{
				resultList.add(kid);
			}
					
		}
		
		return resultList;
	}
	
	
	public static void addFriend(Kid kid1, Kid kid2)
	{
		System.out.println("HERE");
		if(areFriends(kid1, kid2))
			return;

		kid1.friends.add(kid2);
		kid2.friends.add(kid1);
//		
		kid1.save();
		kid2.save();
		
		System.out.println("Kid friends :" + kid1.friends.size());
	}
	
	public static void removeFriend(Kid kid1, Kid kid2)
	{
		if(!areFriends(kid1, kid2))
			return;
			
		
//		kid1.friendIds.remove(kid2.id);
//		kid2.friendIds.remove(kid1.id);
		
		kid1.friends.remove(kid2);
		kid2.friends.remove(kid1);
		
		kid1.save();
		kid2.save();
		
//		for(Long kidId: kid1.friendIds)
//		{
//			if(kidId.equals(kid2.id))
//			{
//				kid1.friendIds.remove(kidId);
//				break;
//			}
//		}
//		
//		kid1.save();
//		
//		for(Long kidId: kid2.friendIds)
//		{
//			if(kidId.ekid1.id)
//			{
//				kid2.friends.remove(kid);
//				break;
//			}
//		}
//		
//		kid2.save();
	}

	public static boolean areFriends(Kid kid1, Kid kid2)
	{
		return kid1.friends.contains(kid2);
		
//		for(Kid kid: kid1.friends)
//		{
//			if(kid.id == kid2.id)
//			{
//				return true;
//			}
//		}
//
//		return false;
	}
}

