package ShippingAPI;

public class Shipping {

    private Boolean status;
    private String message;
    private Double cost;

    public Shipping(Boolean status, String message, Double cost) {
        this.setStatus(status);
        this.setMessage(message);
        this.setCost(cost);
    }

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
}
