package models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class PostComment extends Model{
	@ManyToOne
	public Post post;
	
	@ManyToOne
	public Kid kid;
	
	public String text;
	
	public Date time;
	
	public PostComment(Post post, Kid kid, String text, Date time) {
		this.post = post;
		this.kid = kid;
		this.text = text;
		this.time = time;
	}
	
	
	@Override
	public boolean equals(Object other) {
		return this.id.equals(((PostComment) other).id);
	}

}
