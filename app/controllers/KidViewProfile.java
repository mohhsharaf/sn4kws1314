package controllers;

import models.Kid;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class KidViewProfile extends Controller {
	public static void page(Long kidId)
	{		
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long sessionKidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(sessionKidId);
		}
		
		if(kid == null)
		{
			ParentHome.page();
			return;
		}
		
		Kid tkid = Kid.findById(kidId);
		
		
		if(tkid.equals(kid))
		{
			KidMyPosts.page();
			return;
		}
		
		boolean areFriends = KidManageFriends.areFriends(kid, tkid);
				
		render(kid, tkid, areFriends);
	}
	
	
	public static void addFriend(Long tkidId)
	{
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long sessionKidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(sessionKidId);
		}
		
		if(kid == null)
		{
			ParentHome.page();
			return;
		}
		
		Kid tkid = Kid.findById(tkidId);
		
		KidManageFriends.addFriend(kid, tkid);
		
		page(tkidId);
	}	
	
	public static void removeFriend(Long tkidId)
	{
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long sessionKidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(sessionKidId);
		}
		
		if(kid == null)
		{
			ParentHome.page();
			return;
		}
		
		Kid tkid = Kid.findById(tkidId);
		
		KidManageFriends.removeFriend(kid, tkid);
		
		page(tkidId);
	}
}

