package controllers;

import java.util.Date;
import java.util.List;

import models.Kid;
import models.Post;
import models.PostComment;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class KidMyPosts extends Controller {
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
				
		List<Post> wallPosts = kid.posts;
		
		render(kid, wallPosts);
	}
	
	public static void newPost(String postText, Blob postImageBlob)
	{
		System.out.println("TEXT :" + postText);
		System.out.println("TEXT :" + postImageBlob);
		
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
		
		Post post = new Post(kid, postText, postImageBlob, new Date());
		
		post.save();
		
		page();
	}
	
	public static void newComment(Long kidPostId, String commentText)
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
		
		Post post = Post.findById(kidPostId);
		
		PostComment comment = new PostComment(post, kid, commentText, new Date());
		
		comment.save();
		
		page();
	}

}

