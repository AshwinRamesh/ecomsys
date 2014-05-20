package orderProducts.model;

public class OrderProduct {

    private String productId;
    private int orderId;
    private String productName;
    private String description;
    private int quantity;

    public OrderProduct() {}

    public OrderProduct(String productId, int orderId, String productName, String description, int quantity) {
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setDescription(description);
        this.setQuantity(quantity);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
