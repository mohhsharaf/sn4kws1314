package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Parent extends Model {
    
    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;
    
    @Required
    @MaxSize(100)
    public String age;
    
    @Required    
    public String email;
    
    @Required
    @MaxSize(15)
    @MinSize(3)
    public String password;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    public List<Kid> kids;
  
    public Parent(String username, String age, String email, String password) {
    	this.username = username;
    	this.age = age;
    	this.username = email;    	
        this.password = password;
    }

    public String toString()  {
        return "Parent(" + username + ")";
    }    
}
