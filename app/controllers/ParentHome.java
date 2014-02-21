package controllers;

import models.Kid;
import models.Parent;
import models.UserImage;
import play.mvc.Controller;

public class ParentHome extends Controller {
	public static void page()
	{
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
	
	public static void addKid(String kidName, String kidPassword, UserImage kidPicture)
	{
		System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
		System.out.println("KID PICTURE :" + (kidPicture == null) + " " + (kidPicture.imageBlob == null));
//		System.out.println("KID PICTURE :" + (kidPictureBlob == null));
		
		if(session.contains("parentId"))
		{
			Parent parent = Parent.findById(Long.parseLong(session.get("parentId")));
			if(parent != null)
			{
				
//				Kid kid = new Kid(kidName, kidPassword, parent, kidPicture.imageBlob);
				Kid kid = new Kid(kidName, kidPassword, parent, kidPicture.imageBlob);
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
