package orders.model;

public class Order {

    private int orderId;
    private int userId;
    private String status;
    private String shippingAddress1;
    private String shippingAddress2;
    private String city;
    private String postCode;
    private Double finalCost;

    public Order() {}

    public Order(int userId, String status, String ship1, String ship2, String city, String postcode, Double cost) {
        this.setUserId(userId);
        this.setStatus(status);
        this.setShippingAddress1(ship1);
        this.setShippingAddress2(ship2);
        this.setCity(city);
        this.setPostCode(postcode);
        this.setFinalCost(cost);

    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId(){
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

}
