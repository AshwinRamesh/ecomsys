<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


                    <div class="row">
                    <c:forEach items="${photos}" var="photo">
                        <div class="col-sm-3">
                            <div class="col-item">
                                <div class="photo">
                                    <img src="${photo.urlThumb}" class="img-responsive" alt="${photo.photoTitle}"/>
                                </div>
                                <div class="info">
                                    <div class="row">
                                        <div class="desc col-md-12" style="overflow:hidden;">
                                            <h5 style="max-height:45px;">${photo.photoTitle}</h5>
                                            <p style="max-height:30px;">${photo.description}<c:if test="${empty photo.description }">&nbsp;</c:if></p>
                                        </div>
                                    </div>
                                    <div class="separator clear-left">
                                        <p class="btn-add">
                                            <i class="fa fa-shopping-cart"></i><a href="#" class="hidden-sm" onclick="setXMLHttpRequest('ShoppingCart?action=addToCart&photoId='+${photo.photoId},'');return false; ">Add to cart</a>
                                        </p>
                                        <p class="btn-details">$${photo.price}</p>
                                    </div>
                                    <div class="clearfix">
                                    </div>
                                </div>
                            </div>
                        </div>
                     </c:forEach>
                     </div>
