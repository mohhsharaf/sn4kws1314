package controllers;

import models.Kid;
import models.Parent;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class ParentEditKid extends Controller {
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
	
	
	public static void editName(Long kidId, String newName)
	{
		Kid kid = Kid.findById(kidId);
		kid.name = newName;
		
		kid.save();
		
		ok();
	}
	
	public static void editPassword(Long kidId, String newPassword)
	{
		Kid kid = Kid.findById(kidId);
		kid.password = newPassword;
		
		kid.save();
		
		ok();
	}
	
	
	public static void editPicture(Long kidId, Blob imageBlob)
	{
		Kid kid = Kid.findById(kidId);
		kid.image.imageBlob = imageBlob;
		
		kid.image.save();

		page(kidId);
	}
}

