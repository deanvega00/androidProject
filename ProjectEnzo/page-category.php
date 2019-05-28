<?php 
session_start();
include ("./page/database/connect.php");
include ('./include/page-header.php');
if (!isset($_SESSION["id"])){header("location: ./page-login.php");}

$code = null;
$search=null;
if(isset($_POST["code"]))
{
$code = $_POST["code"];
}
if(isset($_POST["txtsearch"]))
{
$search = $_POST["txtsearch"];
}
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
                        <li class="active">Category table</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Category</strong>
				   <form method="POST" action="">
                        <input type="text" name="txtsearch" placeholder="Search">
										<input type="submit" class="btn btn-primary btn-sm" name="search" value="Search">
					</form>
						
						</div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Category Code</th>
                                        <th scope="col">Category Name</th>                                       
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
									
								if($search == ''){
										$sql="SELECT * FROM tbl_category";
								
								
								}else{
									$sql="SELECT * FROM tbl_category
										where category_name like '%".$search."%'";
								}
								
									$search='';
									
									
                                   
                                $result = $conn->query($sql);

                                if ($result->num_rows > 0) {
                                    while($row = $result->fetch_assoc()) {
                                        $id =$row["category_id"];
                                        $code=$row["category_code"];
                                        $name=$row["category_name"];
                                
                                    echo '<tr>';
                                    
                                    echo"<td>{$id}</td>";
                                    echo"<td>{$code}</td>";
                                    echo"<td>{$name}</td>";
                                    
                                    echo'<td>';
                                     echo "<button class='btn btn-primary btn-xs' data-toggle='modal' data-target='#edit{$id}'>";
                                     echo '<i class="fa fa-pencil">';
                                     echo '</i>';
                                     echo "<button class='btn btn-danger btn-xs' data-toggle='modal' data-target='#delete{$id}'>"; 
                                     echo '<i class="fa fa-trash">';
                                     echo '</i>';
                                     echo'</button>';
    
                                        
                                   echo '</td>';
                                    include ("./page/modal/modal_category.php"); 
                              echo'</tr>';
                                    }
                                }


                                   ?>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header">
                            <form action="./page/action/create_category.php" method="POST">
                            <strong class="card-title">Category</strong>
                        </div>
                        <div class="card-body">
                            <div class="form-group"><label for="company" class=" form-control-label">Category Code</label><input type="text" id="code" name="code" placeholder="Code" class="form-control" required></div>
                        <div class="form-group"><label for="Category" class=" form-control-label">Category Name</label><input type="text" id="name" name="name" placeholder="Name" class="form-control" required></div>
                         <div class="form-group">
                            <button type="submit" class="btn btn-success btn-lg btn-block"><i class="fa fa-pencil-square-o"></i>Add</button>
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


   