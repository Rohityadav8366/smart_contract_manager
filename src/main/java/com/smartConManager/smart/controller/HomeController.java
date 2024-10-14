package com.smartConManager.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartConManager.smart.dao.UserRepository;
import com.smartConManager.smart.helper.Message;
import com.smartConManager.smart.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Home - Smart Contract Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","About - Smart Contract Manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title","Register - Smart Contract Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("title","Login - Smart Contract Manager");
		return "login";
	}
	
	@RequestMapping("/donate")
	public String donate(Model model) {

		model.addAttribute("title","Donate - Smart Contract Manager");
		return "donate";
	}
	
	//handler for registering use
	@RequestMapping(value="/do-register",method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue="false") 
	boolean agreement,Model model, HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed yhe terms and conditions");
				throw new Exception("You have not agreed yhe terms and conditions");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.pmg");
			
			System.out.println("Agreement "+agreement);
			System.out.println("USER "+user);
			User result=this.userRepository.save(user);
			model.addAttribute("user",new User());
			
			session.setAttribute("message",new Message("Successfully Registered !!","alert-error"));
			return "signup";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("something went wrong!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
	}
}
