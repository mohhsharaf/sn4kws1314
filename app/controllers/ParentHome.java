package controllers;

import models.Kid;
import models.Parent;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class ParentHome extends Controller {
	public static void page()
	{
		session.remove("kidId");
		
		if(session.contains("parentId"))
		{
					
			Parent parent = Parent.findById(Long.parseLong(session.get("parentId")));
			if(parent != null)
			{
			
				System.out.println(parent.kids.size());
				
				render(parent);
				return;
			}
			
			session.remove("parentId");
		}
		
		Index.index();
	}
	
	public static void addKid(String kidName, String kidPassword)
	{		
		if(session.contains("parentId"))
		{
			Parent parent = Parent.findById(Long.parseLong(session.get("parentId")));
			if(parent != null)
			{
				
				Kid kid = new Kid(kidName, kidPassword, parent);
				kid.save();
				
				System.out.println("kid added !");
				ParentHome.page();
				return;
			}
			
			session.remove("parentId");
		}
		
		Index.index();
	}
	
	public static void removeKid(Long kidId)
	{
		Kid kid = Kid.findById(kidId);
		
		Parent parent = kid.parent;
		
		parent.kids.remove(kid);
		
		kid.delete();
		
		ParentHome.page();
	}
}

