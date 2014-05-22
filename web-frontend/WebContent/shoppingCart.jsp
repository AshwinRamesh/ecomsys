<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

                <div class="row">
                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                    	<c:set var="len" value="${fn:length(items)}"/>
                    	<c:if test="${len>0}">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product</th><th>Quantity</th><th class="text-center">Price</th><th class="text-center">Total</th><th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${items}" var="item">
                                <tr>
                                    <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${item.urlThumb}" style="width: 72px; height: 72px;"> </a>
                                        <div class="media-body">
                                            <h4 class="media-heading">${item.photoTitle}</h4>
                                            <h5 class="media-heading">Product ID: ${item.photoId}</h5>
                                        </div>
                                    </div></td>
                                    <td class="col-sm-1 col-md-1" style="text-align: center">
                                    <input disabled class="form-control" id="quantity" value="1">
                                    </td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong>$${item.price}</strong></td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong>$${item.price}</strong></td>
                                    <td class="col-sm-1 col-md-1">
                                    <button type="button" class="btn btn-danger" onclick="setXMLHttpRequest('ShoppingCart?action=removeFromCart&photoId=${item.photoId}','cartBody');">
                                        <span class="glyphicon glyphicon-remove"></span> Remove
                                    </button></td>
                                </tr>
                            </c:forEach>
                                                            <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><h3>Total</h3></td>
                                    <td class="text-right"><h3><strong>$${total }</strong></h3></td>
                                </tr>
        </tbody></table><a href="Checkout" class="btn btn-success">Checkout <span class="glyphicon glyphicon-play"></span></a></c:if><c:if test="${len == 0}">Your Shopping cart is Empty.</c:if></div>
        </div>
