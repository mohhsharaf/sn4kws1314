//package controllers;
//
//import play.*;
//
//import play.mvc.*;
//import play.data.validation.*;
//
//import java.util.*;
//
//import models.*;
//
//public class Friends extends Index {
//    
//    @Before
//    static void checkUser() {
//        if(connected() == null) {
//            flash.error("Please log in first");
//            Index.index();
//        }
//    }   
//    
//    
//    public static void index() {
//        List<Relation> relations = Relation.find("byUser", connected()).fetch();
//        render(relations);
//    }
//
//    public static void list(String search, Integer size, Integer page) {
//        List<Friend> friends = null;
//        page = page != null ? page : 1;
//        if(search.trim().length() == 0) {
//        	friends = Friend.all().fetch(page, size);
//        } else {
//            search = search.toLowerCase();
//            friends = Friend.find("lower(name) like ?1 OR lower(city) like ?2", "%"+search+"%", "%"+search+"%").fetch(page, size);
//        }
//        render(friends, search, size, page);
//    }
//    
//    public static void show(Long id) {
//    	Friend friend = Friend.findById(id);
//        render(friend);
//    }
//    
//    public static void settings() {
//        render();
//    }
//    
//    public static void saveSettings(String password, String verifyPassword) {
//        Parent connected = connected();
//        connected.password = password;
//        validation.valid(connected);
//        validation.required(verifyPassword);
//        validation.equals(verifyPassword, password).message("Your password doesn't match");
//        if(validation.hasErrors()) {
//            render("@settings", connected, verifyPassword);
//        }
//        connected.save();
//        flash.success("Password updated");
//        index();
//    }
//    
//}
//
