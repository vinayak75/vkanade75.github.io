assignmentApp.factory("assignmentService", ["$http", function($http) {
    return {
    	saveUser: function (jsonData,callback) 
  		{		
    			$http.post('saveUser.json',jsonData).success(function(responseData){
  				if(callback!=undefined)
  				callback(responseData);
  				});
  		},
  		getUserById: function (userId,callback) 
  		{
  				$http.post('getUserById.json?userId='+userId).success(function(responseData){
  				if(callback!=undefined)
  				callback(responseData);
  				});
  		},
  		deleteUser: function (userId,callback) 
  		{
  				$http.post('deleteUser.json?userId='+userId).success(function(responseData){
  				if(callback!=undefined)
  				callback(responseData);
  				});
  		},
  		getAllUser: function (callback) 
  		{
  				$http.post('getAllUser.json').success(function(responseData){
  				if(callback!=undefined)
  				callback(responseData);
  				});
  		},
    };
}]);