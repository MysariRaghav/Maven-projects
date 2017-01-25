package com.ecommerce.loginController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
@SessionAttributes({"savedSessionCart", "name", "sessionWishList"})
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
		
		Map<cart, Integer> savedSessionCart= (Map<cart, Integer>) model.get("savedSessionCart");
		
		loi.setItemsList(savedSessionCart!=null?new ArrayList<cart>(savedSessionCart.keySet()):new ArrayList<cart>());
		
		model.addAttribute("listOfItems", loi);
		model.addAttribute("list", serv.retrivecart(name));

		model.put("name",name);
		return "items"; 
		
	}
	
	@RequestMapping (value="/mycart")
	public String cartpop(ModelMap model, @ModelAttribute("listOfItems")ListOfItems loi){
		//System.out.println(loi.getItemsList());
		List<cart> cartItems = loi.getItemsList();
		Map<cart, Integer> mapCart = new HashMap<cart, Integer>();

		if(cartItems==null)	
			cartItems= new ArrayList<cart>();			
		
		/*if(model.get("savedSessionCart")==null)
		{
				model.addAttribute("savedSessionCart", new LinkedHashSet<cart>(loi.getItemsList()));			
		}
		else{
			((LinkedHashSet<cart>)(model.get("savedSessionCart"))).addAll(loi.getItemsList());	
		}	*/
		if(model.get("savedSessionCart")!=null)
		{
			mapCart= (Map<cart, Integer>) model.get("savedSessionCart");
		}
		for (cart temp : cartItems) {
			Integer count = mapCart.get(temp);
			mapCart.put(temp, (count == null) ? 1 : count + 1);
		}
		model.addAttribute("savedSessionCart", mapCart);
		
		ListOfItems cartList= new ListOfItems();
		cartList.setItemsList(new ArrayList<cart>());
		model.addAttribute("cartList",cartList);
				
		return "catitem";
		
		
	}

	private List<cart> removeCurrentCategoryItems(String currentItemType, List<cart> savedSessionCart) {
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
	
	
	
	@RequestMapping (value="/manipulateCart", params={"save for latter", "!delete from cart"})
	public String cartWishList(ModelMap model, @ModelAttribute("cartList")ListOfItems cartList){
		
		List<cart> checkedCartList= cartList.getItemsList();
		if(checkedCartList==null)	
			checkedCartList = new ArrayList<cart>();
		
		if(model.get("sessionWishList")==null)
		{
			model.addAttribute("sessionWishList", new LinkedHashSet<cart>());			
		}
		((LinkedHashSet<cart>)(model.get("sessionWishList"))).addAll(checkedCartList); 

		cartDelete(model, cartList);
		cartList.setItemsList(new ArrayList<cart>());
		model.addAttribute("cartList", cartList);
		return "wishList";
	}
	
	@RequestMapping (value="/manipulateCart", params={"!save for latter", "delete from cart"})
	public String cartDelete(ModelMap model, @ModelAttribute("cartList")ListOfItems cartList){
		
		List<cart> checkedCartList= cartList.getItemsList();
		if(checkedCartList==null)	
			checkedCartList = new ArrayList<cart>();
			
		
		Map<cart, Integer> savedSessionCart= (Map<cart, Integer>) model.get("savedSessionCart");
		
		if(savedSessionCart!=null)
		{
			savedSessionCart.keySet().removeAll(checkedCartList);
		}
		
		model.addAttribute("cartList",cartList);
		return "catitem";
	}
	
	@RequestMapping (value="/manipulateWishListCart", params={"!save for latter", "delete from cart"})
	public String wishListCartDelete(ModelMap model, @ModelAttribute("cartList")ListOfItems cartList){
		
		List<cart> checkedCartList= cartList.getItemsList();
		if(checkedCartList==null)	
			checkedCartList = new ArrayList<cart>();
		
		Set<cart> savedSessionCart= (Set<cart>) model.get("sessionWishList");
		
		if(savedSessionCart!=null)
		{
			savedSessionCart.removeAll(checkedCartList);
		}
		
		model.addAttribute("cartList",cartList);
		return "wishList";
	}
	

}
