<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg"   role="document">
        <div class="modal-content ">
            <form action="./page/action/create_law.php" method="post" class="form-horizontal">

			<div class="modal-header">
                    <h5 class="modal-title">Create Law</h5>
                </div>
                <div class="modal-body">
                            <div class="form-group">
							<label for="Category" class="form-control-label">Category</label>

                                    <?php
                              $sql = "SELECT * FROM tbl_category";
                                $result = $conn->query($sql);
                                
                                if ($result->num_rows > 0) {
                                  echo'<select name="code">';
                                    while($row = $result->fetch_assoc()) {
                                        $cid =$row["category_id"];
                                        $cname=$row["category_name"];
                                      
                                         echo '<option class="form-control" value="'.$cid.'">' .$cname.'</option>';
                                }}
                                    echo'</select>';
                                    
                                        ?>


                            </div>
                        <div class="form-group"><label for="Category" class=" form-control-label">Law Name</label><input type="text" id="name" name="name" placeholder="Name" class="form-control" required></div>
                        <div class="form-group"><label for="Detal" class=" form-control-label">Law Details</label><textarea id="Detail" name="Detail" placeholder="Detail" class="form-control" rows="7" required></textarea></div>                        
                      
                  
               
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger btn-sm float-right" data-dismiss="modal"><i class="fa fa-sign-out"></i> Close </button>
                    <button type="submit" class="btn btn-primary btn-sm float-right"><i class="fa fa-dot-circle-o"></i> Save </button>
                </div>
        </form>
        </div>
    </div>
</div>
