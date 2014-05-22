package models.products;

public class FlickrPhoto {
	private String photoId;
	private String photoTitle;
	private Double price;
	private String description;
	private String urlThumb;
	private String urlOriginal;
	
	public FlickrPhoto() {
	}

	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the photoTitle
	 */
	public String getPhotoTitle() {
		return photoTitle;
	}

	/**
	 * @param photoTitle the photoTitle to set
	 */
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the urlThumb
	 */
	public String getUrlThumb() {
		return urlThumb;
	}

	/**
	 * @param urlThumb the urlThumb to set
	 */
	public void setUrlThumb(String urlThumb) {
		this.urlThumb = urlThumb;
	}

	/**
	 * @return the urlOriginal
	 */
	public String getUrlOriginal() {
		return urlOriginal;
	}

	/**
	 * @param urlOriginal the urlOriginal to set
	 */
	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}


}
