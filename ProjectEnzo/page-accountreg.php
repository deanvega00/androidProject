
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta name="description" content="PHCLAPP">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="assets/css/normalize.css">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="assets/scss/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->

</head>
<body class="bg-dark">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    
                </div>
                <div class="login-form">
                   <form action="./page/action/create_account.php" method="post" class="form-horizontal">
                        <div class="form-group">
						<h3>Register Admin Account</h3>
					<br/>
						<div class="col-sm-6">
                            <label for="company" class="form-control-label">First Name</label>
                            <input type="text" id="Firstname" name="Firstname" placeholder="First Name" class="form-control" required>
                        </div>
                        <div class="col-sm-6">
                            <label for="vat" class=" form-control-label">Last Name</label>
                            <input type="text" id="Lastname" name="Lastname" placeholder="Last Name" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label for="address" class="form-control-label">Email</label>
                            <input type="email" id="email" name="address" placeholder="Enter Email" class="form-control" required>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-6">
                            <label for="password" class=" form-control-label">Birthday</label>
                            <input type="date" id="password" name="birthday" class="form-control" required>
                        </div>
                        <div class="col-sm-6">
                            <label for="password" class=" form-control-label"> Contact No.</label >
                            <input type="text" id="phone" name="phone" placeholder="+63" class="form-control" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <label for="Username" class=" form-control-label">Username</label><input type="text" id="Username" name="Username" placeholder="Username" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label for="password" class=" form-control-label">Password</label>
                            <input type="password" id="password" name="password" placeholder="Password" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
                        </div>
                        <div class="col-sm-12">
                            <label for="password" class=" form-control-label"> Confirm Password</label>
                            <input type="password" id="password2" name="password2" placeholder="Confirm Password" class="form-control" required>
                        </div>
						 <div class="col-sm-12">
                            <label for="password" class=" form-control-label">ADMIN TOKEN</label>
                            <input type="password" id="token" name="token" placeholder="ENTER ADMIN TOKEN" class="form-control" required>
                        </div>
				
					
                         <center>   <label for="password" class=" form-control-label"> <br></label>
           <p>Already have account ? <a href="./page-login.php"><b> Sign in</b></a>	</p>                 </center>
				 <input class="btn btn-primary btn-sm" type="submit" id="register" name="register" value="Register">
                 
						
					                            
                 
                        
	
                    </div>
					<br/>
<br/>
                        
						
                        						
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/plugins.js"></script>
    <script src="assets/js/main.js"></script>


<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-23581568-13');
</script>
</body>
</html>
