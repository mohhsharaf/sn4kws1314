package controllers;

import play.mvc.*;
import play.data.validation.*;
import models.*;

public class Index extends Controller {
    
//    @Before
//    static void addUser() {
//        Parent parent = connected();
//        if(parent != null) {
//            renderArgs.put("parentId", parent.id);
//        }
//    }
//    
//    static Parent connected() {
//        if(renderArgs.get("parentId") != null) {
//            return renderArgs.get("user", Parent.class);
//        }
//        String username = session.get("user");
//        if(username != null) {
//            return Parent.find("byUsername", username).first();
//        } 
//        return null;
//    }  
    

    public static void index() {
//        if(connected() != null) {
//        	Friends.index();
//        }
    	if(session.contains("parentId"))
    	{
    		KidLogin.page();
//    		ParentHome.page();
    	}
    	else
    	{
    		render();
    	}
    }
    
    public static void register() {
    	if(session.contains("parentId"))
    	{
    		ParentHome.page();
    	}
    	else
    	{
    		render();
    	}
    }
    
    public static void saveUser(@Valid Parent parent, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, parent.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", parent, verifyPassword);
        }
        parent.create();
        session.put("parentId", parent.id);
        flash.success("Welcome, " + parent.username);
        ParentHome.page();
    }
    
    public static void login(String username, String password) {
        Parent parent = Parent.find("byUsernameAndPassword", username, password).first();
        
        if(parent != null) {
            session.put("parentId", parent.id);
            flash.success("Welcome, " + parent.username);
            ParentHome.page();
//            Friends.index();         
        }
        // Login failure
        flash.put("username", username);
        flash.error("Login failed");
        index();
    }
    
    public static void logout() {
    	session.remove("parentId");
        session.clear();
        index();
    }

}