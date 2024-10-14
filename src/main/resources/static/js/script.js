console.log("this is script file");

// First request to the server to create order
const paymentStart = () => {
    console.log("payment now");

    // Get the amount value using jQuery
    let amount = $("#payment_field").val();

    console.log(amount);

    // Check if the amount is valid (not empty and a positive number)
    if (!amount || isNaN(amount) || parseFloat(amount) <= 0) {
       // alert("Please enter a valid amount.");
        swal("Failed !!","Please enter a valid amount!!","error!!");
        return;
    }

    // Proceed with the order creation request to the server
    console.log("Proceeding with payment for INR " + amount);
    
    
    //we will use ajax to send request to server to create order
    $.ajax(
		{
			url:'/user/create_order',
			data:JSON.stringify({amount:amount,info:'order_request'}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response){
				//invoked when error
				console.log(response)
				if(response.status=="created"){
					//open payment form
					
					
					let options={
						key:'rzp_test_S3dgas530xmw9k',
						amount:response.amount,
						currency:'INR',
						name:'PaymentIntegration',
						description:'Donate',
						
						order_id:response.id,
						handler:function(response){
							console.log(response.razorpay_payment_id);
							console.log(response.razorpay_order_id);
							console.log(response.razorpay_signature);
							console.log("payment successful !!");
							swal("Good jobs!","congrates!! payment successful!!","success");
						},
						prefill:{
							name:"",
							email:"",
							contact:"",
						},
						nates:{
							address:"Razorpay Corporate Office",
						},
						theme:{
							color:"#3399cc",
						},
					};
					
					let rzp=new Razorpay(options);
					rzp.on("payment.failed",function(response){
						console.log(response.error.code);
						console.log(response.error.description);
						console.log(response.error.source);
						console.log(response.error.step);
						console.log(response.error.reason);
						console.log(response.error.metadata.order_id);
						console.log(response.error.metadata.payment_id);
						swal("Failed !!","Oops payment failed!!","error!!");
					});
					rzp.open();
					
				}
			},
			error:function(error){
				//invoked when error
				console.log(error)
				alert("something went wrong!!")
			}
		}
		
	)
};
