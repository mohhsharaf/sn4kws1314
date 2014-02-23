package controllers;

import models.Kid;
import models.Parent;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class ParentViewKidFriends extends Controller {
	public static void page(Long kidId)
	{
		Parent parent = null;
		if(session.contains("parentId"))
		{			
			parent = Parent.findById(Long.parseLong(session.get("parentId")));
		}
		
		if(parent == null)
		{
			session.remove("parentId");
			ParentHome.page();
			return;
		}
		
		Kid kid = Kid.findById(kidId);
		
		render(parent, kid);
	}
	
	
	public static void removeFriend(Long kidId, Long otherKidId)
	{
		Kid kid = Kid.findById(kidId);
		Kid otherKid = Kid.findById(otherKidId);

		kid.friends.remove(otherKid);
		otherKid.friends.remove(kid);
		
		kid.save();
		otherKid.save();
		
		page(kidId);
	}
}

