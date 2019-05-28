<?php
include ("./page/database/connect.php");

if(isset($_POST["submit"]))
{
	
 

$email= $_POST["email"];
$query="SELECT * FROM tbl_admin WHERE address='".$email."'";
$password = substr(str_shuffle(str_repeat('0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', mt_rand(1,10))),1,10);	
			
				$query= mysqli_query($conn,$query);
				if (mysqli_num_rows($query) == 1){
					
					require 'class/class.phpmailer.php';
					$mail = new PHPMailer;
					$mail->IsSMTP();								//Sets Mailer to send message using SMTP
					$mail->Host = 'mx1.hostinger.ph';		//Sets the SMTP hosts of your Email hosting, this for Godaddy
					$mail->Port = '587';								//Sets the default SMTP server port
					$mail->SMTPAuth = true;							//Sets SMTP authentication. Utilizes the Username and Password variables
					$mail->Username = 'admin@phclproject.tech';					//Sets SMTP username
					$mail->Password = 'DNNQRELYx65d';					//Sets SMTP password
					$mail->SMTPSecure = '';							//Sets connection prefix. Options are "", "ssl" or "tls"
					$mail->From = 'admin@phclproject.tech';					//Sets the From email address for the message
					$mail->FromName = 'PHCL TECH';				//Sets the From name of the message
					$mail->AddAddress($email, 'Test');		//Adds a "To" address
					//$mail->AddCC($_POST["email"], $_POST["name"]);	//Adds a "Cc" address
					$mail->WordWrap = 50;							//Sets word wrapping on the body of the message to a given number of characters
					$mail->IsHTML(true);							//Sets message type to HTML				
					$mail->Subject = 'Password Reset';				//Sets the Subject of the message
					$mail->Body = 'Your new password is '.$password;				//An HTML or plain text message body
					if($mail->Send())								//Send an Email. Return true on success or false on error
					{
						echo 'Email Sent';
						
						$new_password = password_hash($password, PASSWORD_DEFAULT);
						$query = "UPDATE tbl_admin  SET  password= '{$new_password}' where address='{$email}'";
				        $query = mysqli_query($conn,$query);
						$new_password='';
						$email='';
						$password='';
						
					}
					else
					{
						echo 'Error Sending Mail';
					}
		}
				else{
					echo 'record not found';
				}
		
	}


?>


<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>PHCLPROJECT.TECH</title>
    <meta name="description" content="PHCL ANDROID PROJECT">
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
                
                <div class="login-form">
                   <form method="POST" action="./page/action/forgot.php">
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" class="form-control" name="email" placeholder="Username" required>
                        </div>
                        <button type="submit" name="submit" class="btn btn-primary btn-flat m-b-15">Submit</button>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/plugins.js"></script>
    <script src="assets/js/main.js"></script>


</body>
</html>
