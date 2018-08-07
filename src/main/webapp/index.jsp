<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>
<div ng-app="mutualApp" ng-controller="mutualCtrl">
<form name="mutualForm" ng-submit="submitMutual()">

First Friend : <input type="text" ng-model="id1" name="id1" placeholder="Enter Id of first friend"/>
Second Friend : <input type="text" ng-model="id2" name="id2" placeholder="Enter Id of second friend"/>
<br><br>
<button type="submit">Submit</button>
</form>
<ul>
<li ng-repeat="x in output">
Account Id : {{x.accountId}}<br>

Name : {{x.name}}<br>

City : {{x.city}}<br>

</li>
</ul>
</div>
<script>

var app = angular.module("mutualApp",[]);
app.controller("mutualCtrl",function($scope,$http){
	$scope.id1="prasad@twitter.com";
	$scope.id2="ramesh@twitter.com";
	$scope.submitMutual=function(){
		$http.get("http://localhost:8080/mutualFriendsPrj/mutual/?id1="+$scope.id1+"&id2="+$scope.id2).then(
				 function(response){				 
					$scope.output=response.data; 
					if($scope.output.length<=0){
						alert('No Mutual Friends');
					}
				 },
				 function(response){
					 console.log("error");
				 }
				)
	}
	
})
</script>
</body>

</html>

