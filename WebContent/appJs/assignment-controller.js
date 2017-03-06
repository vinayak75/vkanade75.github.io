assignmentApp.controller("assignmentController", ["$scope", "$filter", "$location", "assignmentService", function($scope, $filter, $location, assignmentService) {
	var APP_REGEX_FORM_MOBILE = /^\d{10}$/;
	var APP_REGEX_FORM_NAME_PERSON = /^[a-zA-Z\s.]+$/;
	var APP_REGEX_FORM_EMAIL = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var APP_REGEX_FORM_LOCATION = /^[a-zA-Z\s.]+$/;
	var gFlag=false;
    $scope.reset = function() {
    	$scope.userName=null;
    	$scope.password=null;
    	$scope.confirmPassword=null;
    	$scope.firstName=null;
    	$scope.lastName=null;
    	$scope.email=null;
    	$scope.phoneNumber=null;
    	$scope.location=null;
    };
    function validateForm(){
    	gFlag=false;
    	var userName=$scope.userName;
    	var password=$scope.password;
    	var confirmPassword=$scope.confirmPassword;
    	var firstName=$scope.firstName;
    	var lastName=$scope.lastName;
    	var email=$scope.email;
    	var phone=$scope.phoneNumber;
    	var location=$scope.location;
    	
    	if(!userName){
    		gFlag=true;
    		alertify.error("Please enter user name");
    	}
    	else if(!APP_REGEX_FORM_NAME_PERSON.test(userName)){
    		gFlag=true;
    		alertify.error("Please enter valid user name");
    	}
    	
    	if(!password){
    		gFlag=true;
    		alertify.error("Please enter password");
    	}else if(password.length<6){
    		gFlag=true;
    		alertify.error("Please enter minimum 6 character/digit password");
    	}
    	if(!confirmPassword){
    		gFlag=true;
    		alertify.error("Please enter confirm password");
    	}else if(confirmPassword.length<6){
    		gFlag=true;
    		alertify.error("Please enter minimum 6 character/digit confirm password");
    	}
    	if(confirmPassword && password){
    		if(confirmPassword!=password){
    			gFlag=true;
        		alertify.error("Password not match");
    		}
    	}
    	if(!phone){
    		gFlag=true;
    		alertify.error("Please enter phone number");
    	}
    	else if(phone.length>10 || phone.length<10 || !APP_REGEX_FORM_MOBILE.test(phone)){
    		gFlag=true;
    		alertify.error("Please enter valid phone number");
    	}
    	
    	if(!firstName){
    		gFlag=true;
    		alertify.error("Please enter first name");
    	}
    	else if(!APP_REGEX_FORM_NAME_PERSON.test(firstName)){
    		gFlag=true;
    		alertify.error("Please enter valid first number");
    	}
    	
    	if(!lastName){
    		gFlag=true;
    		alertify.error("Please enter last name");
    	}
    	else if(!APP_REGEX_FORM_NAME_PERSON.test(lastName)){
    		gFlag=true;
    		alertify.error("Please enter valid last number");
    	}
    	
    	if(!email){
    		gFlag=true;
    		alertify.error("Please enter email id");
    	}
    	else if(!APP_REGEX_FORM_EMAIL.test(email)){
    		gFlag=true;
    		alertify.error("Please enter valid email id");
    	}
    	
    	if(!location){
    		gFlag=true;
    		alertify.error("Please enter last name");
    	}
    	else if(!APP_REGEX_FORM_LOCATION.test(location)){
    		gFlag=true;
    		alertify.error("Please enter valid last number");
    	}
    	
    };
    $scope.save = function() {
    	validateForm();
    	if(!gFlag){
    		
    		var userId="NA";
    		if($scope.userId){
        		userId=$scope.userId;
        	}
    		var jsonData={
    			'userId':userId,
    			'userName':$scope.userName,
    	    	'password':$scope.password,
    	    	'firstName':$scope.firstName,
    	    	'lastName':$scope.lastName,
    	    	'email':$scope.email,
    	    	'phoneNumber':$scope.phoneNumber,
    	    	'location':$scope.location
    			
    		};
    		assignmentService.saveUser(jsonData,function(responseData)
   				 {
    				if(responseData.error=='false'){
    					alertify.success("User details successfully saved");
    					 $scope.reset();
    				}
    				$scope.userDetails=responseData.userDetails;
   				 });
    		
    	}
    	
    };
    $scope.editUser = function(event) {
    	var userId=event.target.id;
    	assignmentService.getUserById(userId,function(responseData) {
    		$scope.userId=responseData.userDetails.userId;
			$scope.userName=responseData.userDetails.userName;
			$scope.password=responseData.userDetails.password;
			$scope.confirmPassword=responseData.userDetails.password;
			$scope.firstName=responseData.userDetails.firstName;
			$scope.lastName=responseData.userDetails.lastName;
			$scope.email=responseData.userDetails.email;
			$scope.phoneNumber=responseData.userDetails.phoneNumber;
			$scope.location=responseData.userDetails.location;
		 });
    };
    $scope.deleteUser = function(event) {
    	var userId=event.target.id;
    	 alertify.confirm("Confrmation","Are you sure,you want to delete this user details ?", function(e) 
          		{
    		 		assignmentService.deleteUser(userId,function(responseData) {
    	   			$scope.userDetails=responseData.userDetails;
    	  		});
      			},function () {});
    };
    $scope.getAllUser = function() {
    	assignmentService.getAllUser(function(responseData) {
    		$scope.userDetails=responseData.userDetails;
  		});
    };
    $scope.getAllUser();
}]);