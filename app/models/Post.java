package models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Post extends Model{
	@ManyToOne
	public Kid kid;
	
	public String text;
	public Blob imageBlob;
	public Date time;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	public List<PostComment> comments;
	
	
	public Post(Kid kid, String text, Blob imageBlob, Date time)
	{
		this.kid = kid;
		this.text = text;
		this.imageBlob = imageBlob;
		this.time = time;
	}
	
	public static List<Post> getPostsForWall(Kid kid)
	{
		List<Post> allPosts = new LinkedList<Post>();
		
		allPosts.addAll(kid.posts);
		
		for(Kid kidFriend : kid.friends)
		{
			allPosts.addAll(kidFriend.posts);
			System.out.println("friend :" +  kidFriend.name + " " + kidFriend.posts.size());
			System.out.println("ALL SIZE :" + allPosts.size());
		}
		
		Collections.sort(allPosts, new Comparator<Post>() {

			@Override
			public int compare(Post o1, Post o2) {
				return o2.time.compareTo(o1.time);
			}
		});
		
		return allPosts;
	}
	
}
