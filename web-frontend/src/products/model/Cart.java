/**
 * 
 */
package products.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mojtaba
 *
 */
public class Cart {
	
	private String sessId = "";
	private List<FlickrPhoto> items = new ArrayList<FlickrPhoto>();

	/**
	 * 
	 */
	public Cart() {

	}

	/**
	 * @return the sessId
	 */
	public String getSessId() {
		return sessId;
	}

	/**
	 * @param sessId the sessId to set
	 */
	public void setSessId(String sessId) {
		this.sessId = sessId;
	}

	/**
	 * @return the items
	 */
	public List<FlickrPhoto> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<FlickrPhoto> items) {
		this.items = items;
	}

	public void addItem(String photoId,List<FlickrPhoto> photos){
		for(FlickrPhoto fp : photos)
			if(fp.getPhotoId().equals(photoId)){
				items.add(fp);
			}
	}
	
	public void removeItem(String photoId,List<FlickrPhoto> photos){
		for(FlickrPhoto fp : photos)
			if(fp.getPhotoId().equals(photoId)){
				items.remove(fp);
			}
	}

}
