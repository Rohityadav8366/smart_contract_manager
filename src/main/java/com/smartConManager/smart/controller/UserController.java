package com.smartConManager.smart.controller;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.smartConManager.smart.dao.MyPaymentOrderrepo;
import com.smartConManager.smart.dao.UserRepository;
import com.smartConManager.smart.model.MyOrder;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MyPaymentOrderrepo myPaymentOrderRepo;
	
	//creating order for payment
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String,Object> data, Principal principal) throws Exception
	{
		System.out.println(data);
		int amt =Integer.parseInt(data.get("amount").toString());
		RazorpayClient client=new RazorpayClient("rzp_test_S3dgas530xmw9k", "nPIsxmRVgpWPr3pGpEA4Kdlh");
		JSONObject ob=new JSONObject();
		ob.put("amount",amt*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_25425"); 
		
		//creating new order
		Order order=client.orders.create(ob);
		System.out.println(order);
		
	//Save the order in database
		
		MyOrder myOrder=new MyOrder();
		
		myOrder.setAmount(order.get("amount")+"");
		myOrder.setOrderId(order.get("id"));
		myOrder.setPaymentId(null);
		myOrder.setStatus("created");
		//myOrder.setUser(this.userRepository.getUserByUserName(principal.getName()));
		myOrder.setReceipt(order.get("receipt"));
		//myOrder.setDateAndTime(order.get)
		this.myPaymentOrderRepo.save(myOrder);
		
		
		return order.toString();
	}

}
