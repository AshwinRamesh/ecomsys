<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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
    <a class="navbar-brand" href="#">Flickr E-commerce System - Admin Interface</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <div class="col-sm-3 col-md-3">
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><form action="Logout" method="post" accept-charset="utf-8">
            <button type="submit" class="btn btn-sm btn-default">Logout</button>
          </form></li>
        </ul>
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->
</nav>
    <div class="container" style="margin-top:30px" id="container">
<div class="panel-group" id="accordion">
  <c:forEach items="${orders}" var="order">
      <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${order.orderId}">Order No. <span>${order.orderId}</span></a>
        </h4>
      </div>
      <div id="collapse${order.orderId}" class="panel-collapse collapse">
        <div class="panel-body">
          <div class="row checkout-items">
          <div class="col-md-8  col-md-offset-2">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Description</th>
                    <th>Quantity</th>
                    <th>Cost</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="product" items="${order.products}">
                    <tr>
                      <td>${product.productId}</td>
                      <td>${product.productName}</td>
                      <td>${product.description}</td>
                      <td>${product.quantity}</td>
                      <td>${product.cost}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
              <h4 class="checkout-total">Sub Total: $<span>${order.cost}</span></h4>
              <h4 class="checkout-total">Shipping: $<span>${order.shipCost}</span></h4>
              <h4 class="checkout-total">Total: $<span>${order.finalCost}</span></h4>
              <h4 class="checkout-total">Status: <span id="orderStatus${order.orderId}">${order.status}</span></h4>
              <form action="UpdateOrder" id="updateOrderForm${order.orderId}" method="post" accept-charset="utf-8">
              <label>Status:</label>
              <input type="hidden" name="order_id" value="${order.orderId}">
              <select name="status">
                <option value="processing">Processing</option>
                <option value="shipping">Shipping</option>
                <option value="delivered">Delivered</option>
              </select>
              <button type="submit">Update</button>
              </form>
          </div>
        </div>
      </div>
    </div>
    </div>
    <script>
      $("#updateOrderForm${order.orderId}").submit(function(e)
      {
          var postData = $(this).serializeArray();
          var formURL = $(this).attr("action");
          $.ajax(
          {
              url : formURL,
              type: "POST",
              data : postData,
              success:function(data, textStatus, jqXHR)
              {
                  if (data.status) {
                    $("#orderStatus${order.orderId}").text($("#updateOrderForm${order.orderId} option:selected" ).text());
                    alert("Updated  Status");
                  } else {
                    alert("Failed to update status.");
                  }
              },
              error: function(jqXHR, textStatus, errorThrown)
              {
                  alert("Failed to update status.");
              }
          });
          e.preventDefault(); //STOP default action
          //e.unbind(); //unbind. to stop multiple form submit.
      });
</script>
  </c:forEach>

  </div>
    <div class="modal fade" id="cartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:auto;">
      <div class="modal-dialog cart-modal">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">My Shopping Cart</h4>
              </div>
              <div class="modal-body" id="cartBody">
              </div></div></div>
    </div>


    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/ajaxAPI.js"></script>
    <script src="js/misc.js"></script>
</body>
</html>
