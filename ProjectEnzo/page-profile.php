<?php 
session_start();
include ("./page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}

?>
<div id="right-panel" class="right-panel">
    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Dashboard</h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li><a href="#">Dashboard</a></li>
                        <li class="active">Profile</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            <form action="./page/action/profile.php" method="POST">
                            <strong class="card-title">Profile</strong>
                        </div>
                        <div class="card-body">
                            <div class="form-group col-lg-6"><label for="Name" class=" form-control-label">Name</label><input type="text" id="code" name="fname" value="<?php  echo $_SESSION['name'];  ?>" class="form-control" required></div>
                        <div class="form-group col-lg-6"><label for="Last" class=" form-control-label">Last Name</label><input type="text" id="name" name="lname" value="<?php  echo $_SESSION['lastname'];  ?>" class="form-control" required></div>
                         <div class="form-group col-lg-12"><label for="Address" class=" form-control-label">Address</label><input type="text" id="name" name="address" value="<?php  echo $_SESSION['address'];  ?>"  class="form-control" required></div>
						 <div class="form-group col-lg-6"><label for="Birthday" class=" form-control-label">Birthday</label><input type="date" id="name" name="bday"  value="<?php  echo $_SESSION['birthday'];  ?>" class="form-control" required></div>
						 <div class="form-group col-lg-6"><label for="Contact" class=" form-control-label">Contact</label><input type="text" id="name" name="contact" value="<?php  echo $_SESSION['contact'];  ?>"  class="form-control" required></div>
						 <div class="form-group col-lg-12"><label for="Password" class=" form-control-label">Password</label><input type="password" id="name" name="password" class="form-control"></div>
						 
						 
						 
						 <div class="form-group col-sm-12">
                            <button type="submit" class="btn btn-primary btn-lg btn-block"><i class="fa fa-pencil-square-o"></i>Update</button>
                            </form>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<?php 
include ('./include/page-footer.php');
?>


   