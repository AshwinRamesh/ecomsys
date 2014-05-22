<!DOCTYPE html>
<html>
<head>
    <title>Flickr Ecommerce System - Login</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Flickr E-commerce System</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <div class="col-sm-3 col-md-3">
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="MyOrders">My Orders</a></li>
          <li><form action="Logout" method="post" accept-charset="utf-8">
            <button type="submit" class="btn btn-sm btn-default">Logout</button>
          </form></li>
        </ul>
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->
</nav>
<div class="container">
    <div class="row checkout-items">
        <div class="col-md-7">
            <h2>Shopping Cart</h2>
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Product ID</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1325</td>
                  <td>2</td>
                  <td>$1.00</td>
                  <td>$2.00</td>
                </tr>
                <tr>
                  <td>2325</td>
                  <td>1</td>
                  <td>$2.00</td>
                  <td>$2.00</td>
                </tr>
                <tr>
                  <td>3346</td>
                  <td>4</td>
                  <td>$4.00</td>
                  <td>$16.00</td>
                </tr>
              </tbody>
            </table>

            <h4 class="checkout-total">Sub Total: $50.00</h4>
            <h4 id="shipCost" class="checkout-total">Shipping:</h4>
            <h4 id="finalCost" class="checkout-total">Total:</h4>
        </div>
        <div class="col-md-4">
            <h2>Address</h2>
            <form class="form-horizontal" role="form" action="Checkout">
              <div class="form-group">
                <label for="address1" class="col-sm-3 control-label">Address 1</label>
                <div class="col-sm-10 col-md-8">
                  <input class="form-control" name="add1" id="checkout-address-1" placeholder="5 John St">
                </div>
              </div>
              <div class="form-group">
                <label for="address1" class="col-sm-3 control-label">Address 2</label>
                <div class="col-sm-10 col-md-8">
                  <input class="form-control" name="add2" id="checkout-address-1" placeholder="Inner Newcastle">
                </div>
              </div>
              <div class="form-group">
                <label for="address1" class="col-sm-3 control-label">City</label>
                <div class="col-sm-8 col-md-4">
                  <input class="form-control" name="city" id="checkout-address-1" placeholder="Newcastle">
                </div>
              </div>
              <div class="form-group">
                <label for="address1" class="col-sm-3 control-label">Postcode</label>
                <div class="col-sm-4 col-md-3">
                  <input class="form-control" name="postcode" id="checkout-address-1" placeholder="2204">
                </div>
              </div>

              <div class="form-group">
                <div class="col-sm-offset-8 col-sm-4">
                  <button type="submit" class="btn btn-primary">Place Order</button>
                </div>
              </div>
            </form>
        </div>
    </div>
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
