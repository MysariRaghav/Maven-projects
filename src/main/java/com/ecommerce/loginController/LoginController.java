package com.ecommerce.loginController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterNode.LinkState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ecommerce.DBConnection.LoginServices;
import com.ecommerce.service.CartServices;
import com.ecommerce.service.ListOfItems;
import com.ecommerce.service.cart;

@Controller	
@SessionAttributes({"savedSessionCart", "name"})
public class LoginController {
	
	boolean b=true;
	{
		System.out.println("Init");
	}
	
	@Autowired
	LoginServices valid;
	
	@Autowired
	CartServices serv;
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String sign(ModelMap model){
		return "signUp";
	}
	
	@RequestMapping(name="/signup",method=RequestMethod.POST)
	public String sign1(ModelMap model,@RequestParam String uname1, @RequestParam String pword1){
		if(valid.inserted(uname1, pword1)){
			model.put("message","new user created");
			return "index";
		}
		return "redirect:signup";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String UserLogin(){
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String UserLoginValidation(ModelMap model,@RequestParam String uname, @RequestParam String pword){
		if(valid.isValid(uname, pword)){
		model.put("uname", uname);
		model.put("pword", pword);
		return "landing";
		}
		else{
			return "redirect:login";
		}
	}
	
	
	
	@RequestMapping (value="/shoping-iteams",method=RequestMethod.GET)
	public String cartPage(ModelMap model, @RequestParam String name){//, @ModelAttribute("savedSessionCart") List<cart> savedSessionCart 
		//model.clear();
		
		ListOfItems loi= new ListOfItems();
		
		List<cart> savedSessionCart= (List<cart>) model.get("savedSessionCart");
		//System.out.println((savedSessionCart==null || savedSessionCart.isEmpty())?"isNull Or Empty":savedSessionCart.get(0));
		
		loi.setItemsList(savedSessionCart!=null?savedSessionCart:new ArrayList<cart>());
		model.addAttribute("listOfItems", loi);
		model.addAttribute("list", serv.retrivecart(name));
		List<cart> cart = new ArrayList<cart>();

		model.put("name",name);
		return "items"; 
		
	}
	
	@RequestMapping (value="/mycart")
	public String cartpop(ModelMap model, @ModelAttribute("listOfItems")ListOfItems loi){
		//System.out.println(loi.getItemsList());
		List<cart> cartList = loi.getItemsList();

		if(cartList==null)	
			cartList= new ArrayList<cart>();			
		
		if(model.get("savedSessionCart")!=null)
		{
			List<cart> savedSessionCart= (List<cart>) model.get("savedSessionCart");
			String itemTypeName=  (String) model.get("name");
			cartList.addAll((removeCurrentCategoryItems(itemTypeName, savedSessionCart)));
		}
		model.addAttribute("savedSessionCart",cartList);
		
    //Written by Praveen
		ListOfItems l2= new ListOfItems();
		model.addAttribute("cartlist",l2);
    
		System.out.println("hello");
				
		return "catitem";
		
		
	}

	private List<cart> removeCurrentCategoryItems(String currentItemType,List<cart> savedSessionCart) {
		// TODO Auto-generated method stub
		Iterator<cart> cartIterator = savedSessionCart.iterator();
		while(cartIterator.hasNext())
		{
			cart c=cartIterator.next();
			if(c.getCatagory().equals(currentItemType))
			{
				cartIterator.remove();
			}
		}
		return savedSessionCart;
	}

}
