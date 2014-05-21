package orderProducts.model;

public class OrderProduct {

    private String productId;
    private int orderId;
    private String productName;
    private String description;
    private int quantity;
    private double cost;

    public OrderProduct() {}

    public OrderProduct(String productId, int orderId, String productName, String description, int quantity, double cost) {
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setCost(cost);
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
