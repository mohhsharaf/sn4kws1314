package controllers;

import models.Kid;
import models.Parent;
import play.mvc.Controller;

public class KidLogin extends Controller {
	public static void page()
	{
		Parent parent = null;
		if(session.contains("parentId"))
		{
					
			parent = Parent.findById(Long.parseLong(session.get("parentId")));
		}
		
		if(parent == null)
		{
			session.remove("parentId");
			
			Index.index();
			return;
		}
		
		if(parent.kids == null || parent.kids.size() == 0)
		{
			ParentHome.page();
			return;
		}
		
		
		render(parent);
	}
	
	public static void login(Long kidId, String kidPassword)
	{
		System.out.println(kidId + " " + kidPassword);
		Parent parent = null;
		if(session.contains("parentId"))
		{
					
			parent = Parent.findById(Long.parseLong(session.get("parentId")));
		}
		
		if(parent == null)
		{
			session.remove("parentId");
			
			Index.index();
			return;
		}
		
		Kid kid = Kid.findById(kidId);
		
		System.out.println("Kid :" + kid.name);
		
		if(kid.password.equals(kidPassword))
		{
			//
			System.out.println("Password okay !");
			session.put("kidId", kid.id);
			KidHome.page();
			return;
		}
	}
	
	public static void logout()
	{
		session.remove("kidId");
		ParentHome.page();
	}
}

