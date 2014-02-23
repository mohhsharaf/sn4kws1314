package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Kid extends Model{
	public String name;
	public String password;
	
	@ManyToOne
	public Parent parent;
	
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Kid> friends;// = new ArrayList<Kid>();
	
	@OneToOne
	public UserImage image;
	
	@OneToMany(mappedBy = "kid", cascade = CascadeType.ALL)
	public List<Post> posts;
	
	
//	@OneToMany(mappedBy = "kid", cascade = CascadeType.ALL)
//	public List<PostComment> comments;
	
	public Kid(String name, String password, Parent parent, Blob imageBlob)
	{
		this.name = name;
		this.password = password;
		this.parent = parent;
		
		if (imageBlob == null)
		{
			Blob userImageBlob = new Blob();
			try
			{
				userImageBlob.set(new FileInputStream(
						"public/images/user-image.jpg"), "jpg");
				
				System.out.println();				System.out.println();				System.out.println();				System.out.println();				System.out.println();
				System.out.println(userImageBlob);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			this.image = new UserImage(userImageBlob);
			this.image.save();
		}
	}

	public Kid(String name, String password, Parent parent)
	{
		this(name, password, parent, null);
	}

	
	@Override
	public boolean equals(Object other) {
		return this.id.equals(((Kid)other).id);
	}
	
	

}
