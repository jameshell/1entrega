<!DOCTYPE html>
<html>
    <style>
   body {padding-top: 40px;}
</style>
  <head>
    <title>Universidad Sergio Arboleda</title>
    
    <link href='css/bootstrap.min.css' rel='stylesheet'>
    <link href='css/main.css' rel='stylesheet'>
  </head>
  <body>
    <div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Please sign in</h3>
			 	</div>
			  	<div class="panel-body">
                                    <form accept-charset="UTF-8" role="form" action="loginControlador" method="post">
                              <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="E-mail" name="idAdmin" type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Password" name="contrasena" type="password" value="">
			    		</div>
			    		<div class="checkbox">
			    	    	<label>
			    	    		<input name="remember" type="checkbox" value="Remember Me"> Remember Me
			    	    	</label>
			    	    </div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>
         
    <script src='js/bootstrap.min.js'></script>
  </body>
</html>
