<html>
	<head><%@include file="/common_jstl.jsp"%><%@include file="/includes/user-script.jsp"%>
		<title>User Registration</title>
	</head>
	<body ng-app="assignmentApp" ng-controller="assignmentController">
		<form id="fromData" name="fromData">
			<div style="width: 20%; float:left;">
			<input type="text" name="userId" ng-show="usrId"ng-model="userId" >
  				<div>
					<label>User Name</label>
					</div>
				
				<div>
					<input type="text"  class="text-style" name="userName" ng-model="userName" >
				</div>
				
				<div>
					<label>Password</label>
					</div>
				
				<div>
					<input type="password" name="password" ng-model="password">
				</div>
				
				<div>
					<label>Confirm Password</label>
					</div>
				
				<div>
					<input type="password" name="confirmPassword" ng-model="confirmPassword">
				</div>
				
				<div>
					<label>First Name</label>
					</div>
				
				<div>
					<input type="text" name="firstName" ng-model="firstName">
				</div>
				
				<div>
					<label>Last Name</label>
					</div>
				
				<div>
					<input type="text" name="lastName" ng-model="lastName">
				</div>
				
				<div>
					<label>Email</label>
					</div>
				
				<div>
					<input type="text" name="email" ng-model="email">
				</div>
				
				<div>
					<label>Phone Number</label>
					</div>
				
				<div>
					<input type="text" name="phoneNumber" ng-model="phoneNumber">
				</div>
				
				<div>
					<label>Loction</label>
					</div>
				
				<div>
					<input type="text" name="location" ng-model="location">
				</div>
				
				<div>
					<input type="button" class="btn btn-success" value="Save" ng-click="save()">
					<input type="button"class="btn btn-success"  value="Reset" ng-click="reset()">
				</div>
			</div>

			<div style="width: 80%; float:right">
  				<table class="table table-bordered table-condensed">
  					<tr>
  						<th>S#</th>
    					<th>User Name</th>
    					<th>Password</th>
    					<th>First Name</th>
    					<th>Last Name</th>
    					<th>Email</th>
    					<th>Phone Number</th>
    					<th>Location</th>
    					<th></th>
    
  					</tr>
  					<tr ng-repeat="usrDetails in userDetails">
  	  					<td>{{$index+1}}</td>
    						<td>{{usrDetails.userName}}</td>
    						<td>{{usrDetails.password}}</td>
    						<td>{{usrDetails.firstName}}</td>
   							<td>{{usrDetails.lastName}}</td>
    						<td>{{usrDetails.email}}</td>
    						<td>{{usrDetails.phoneNumber}}</td>
    						<td>{{usrDetails.location}}</td>
    						<td><a href="#" style="color: blue" id="{{usrDetails.userId}}" ng-click="editUser($event)">Edit</a>&nbsp <a href="#" id="{{usrDetails.userId}}" style="color: blue" ng-click="deleteUser($event)">Delete</a></td>
 					 </tr>
				</table>
			</div>
		
		</form>
	</body>
</html>
		