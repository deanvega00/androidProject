<div class="modal fade" id="delete<?php echo $id;?>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form method="POST" action="./page/action/delete_category.php?id=<?php echo $id;?>">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Delete Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Delete</button>
      </div>
    </form>
    </div>
  </div>
</div>

<div class="modal fade" id="edit<?php echo $id;?>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
       <form method="POST" action="./page/action/update_category.php?id=<?php echo $id;?>">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Edit Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       
                <div class="card-body">
                    <div class="form-group"><label for="company" class=" form-control-label">Category Code</label><input type="text" id="code" name="code" value="<?php echo $code; ?>" class="form-control" required></div>
                    <div class="form-group"><label for="Category" class=" form-control-label">Category Name</label><input type="text" id="name" name="name" value="<?php echo $name; ?>" class="form-control" required></div>
      </div>
      
   
  </div>
  <div class="modal-footer">
        <button type="button" class="btn btn-danger btn-sm float-right" data-dismiss="modal"><i class="fa fa-sign-out"></i> Close </button>
                <button type="submit" class="btn btn-primary btn-sm float-right"><i class="fa fa-dot-circle-o"></i> Save </button>
      </div>
  </form>
  </div>
  </div>
</div>