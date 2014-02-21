package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class UserImage extends Model{
	public Blob imageBlob;
	
	public UserImage(Blob imageBlob)
	{
		this.imageBlob = imageBlob;
	}
}
