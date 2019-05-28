<?php
session_start();
include ("./../database/connect.php");
 ini_set( 'display_errors', "1");

if(isset($_POST["submit"]))
{
	
 

$email= $_POST["email"];
$query="SELECT * FROM tbl_admin WHERE address='".$email."'";
$password = substr(str_shuffle(str_repeat('0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', mt_rand(1,10))),1,10);	
			
				$query= mysqli_query($conn,$query);
				if (mysqli_num_rows($query) == 1){
					
					require './../../class/class.phpmailer.php';
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
						echo  $password;
						$password = password_hash($password, PASSWORD_DEFAULT);
						$query = "UPDATE tbl_admin  SET  password= '{$password}' where address='{$email}'";
				        $query = mysqli_query($conn,$query);
						
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

	