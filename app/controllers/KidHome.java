package controllers;

import models.Kid;
import models.Parent;
import play.mvc.Controller;

public class KidHome extends Controller {
	public static void page()
	{
		Kid kid = null;
		if(session.get("kidId") != null)
		{
			Long kidId = Long.parseLong(session.get("kidId"));
			
			kid = Kid.findById(kidId);
		}
		
		if(kid == null)
		{
			ParentHome.page();
			return;
		}
				
		render(kid);
	}
}

