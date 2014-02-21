package controllers;

import java.awt.Image;

import models.UserImage;
import net.sf.oval.guard.Post;

import org.h2.engine.User;

import play.mvc.Controller;

public class RequestUtils extends Controller
{
	public static void renderUserImage(Long imageId)
	{
		UserImage image = UserImage.findById(imageId);
		response.setContentTypeIfNotSet(image.imageBlob.type());
		renderBinary(image.imageBlob.get());
	}
}