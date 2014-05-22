package ShippingAPI;

import java.io.InputStream;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.*;

/**
 * This API request for flickr photo service using REST APIs
 * @author Mojtaba Karami
 *
 */
public class ShipRestApi {

    public Document getShippingCost(String city, int quantity){
        try{
            URL callUrl = new URL(Constants.URL);
            HttpURLConnection urlConnection = (HttpURLConnection)callUrl.openConnection();

            // headers
            urlConnection.setRequestMethod("POST");
            String urlParams = "city=" + city + "&quantity=" + quantity.toString();

            urlConnection.connect();
            InputStream urlStream = urlConnection.getInputStream();
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document response = db.parse(urlStream);
            urlConnection.disconnect();

            return response;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public List<FlickrPhoto> getPhotos(String searchText){
        try{
            String callUrlStr = Constants.REST_ENDPOINT+"?method="+Constants.SEARCH_METHOD+
            "&per_page="+Constants.DEFAULT_NUMBER+
            "&api_key="+Constants.API_KEY+
            "&text="+URLEncoder.encode(searchText,Constants.ENC)+
            "&extras="+URLEncoder.encode(Constants.EXTRAS,Constants.ENC);

            List<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
            Document response;
            response = getUrl(callUrlStr);
            if(response !=null){
                NodeList nl = response.getElementsByTagName("photo");

                for (int i = 0; i < nl.getLength(); i ++){
                    NamedNodeMap node = nl.item(i).getAttributes();
                    String tags = "";
                    tags = node.getNamedItem("tags").getTextContent();

                    String[] tagsSplit  = {};
                    if(tags.trim() != "")
                        tagsSplit = tags.split(" ");

                    FlickrPhoto photo = new FlickrPhoto();
                    photo.setPhotoId(node.getNamedItem("id").getTextContent());
                    photo.setPhotoTitle(node.getNamedItem("title").getTextContent());
                    photo.setDescription(tags);
                    try {
                        photo.setUrlOriginal(node.getNamedItem("url_o").getTextContent());
                    } catch(Exception e) {
                        photo.setUrlOriginal(node.getNamedItem("url_t").getTextContent());
                        //System.out.println(e.toString());
                        //System.out.println(e.getMessage());
                    }
                    photo.setUrlThumb(node.getNamedItem("url_t").getTextContent());
                    photo.setPrice(Constants.FIXED_PRICE+((tagsSplit.length) * Constants.PRICE_PER_TAG));
                    photos.add(photo);
                }
            }
            return photos;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
