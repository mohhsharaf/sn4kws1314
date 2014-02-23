package controllers;

import models.Post;
import models.UserImage;
import play.mvc.Controller;

public class RequestUtils extends Controller
{
	public static void renderUserImage(Long imageId)
	{
		UserImage image = UserImage.findById(imageId);
		response.setContentTypeIfNotSet(image.imageBlob.type());
		renderBinary(image.imageBlob.get());
	}
	
	
	public static void renderPostImage(Long postId)
	{
		Post post = Post.findById(postId);
		response.setContentTypeIfNotSet(post.imageBlob.type());
		renderBinary(post.imageBlob.get());
	}
}