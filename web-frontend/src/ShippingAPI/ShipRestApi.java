package ShippingAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import org.json.JSONObject;



public class ShipRestApi {

    public Shipping getShippingCost(String city, int quantity){

    	try {
    	String urlParameters = "city=" + city + "&quantity=" + Integer.toString(quantity);
    	URL url = new URL(Constants.URL+"?"+urlParameters);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setInstanceFollowRedirects(false);
    	connection.setRequestMethod("POST");
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	connection.setRequestProperty("charset", "utf-8");
    	//connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
    	connection.setUseCaches (false);

        BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        String in = "";
        StringBuffer sb = new StringBuffer();

        while ((in = br.readLine()) != null) {
            sb.append(in);
        }
        String s = sb.toString();
        try{
        	connection.disconnect();
        } catch (Exception e) {}
    	JSONObject jObj = new JSONObject(s);
    	Shipping ship = new Shipping(jObj.getBoolean("status"), jObj.getString("message"), jObj.getDouble("cost"));
    	return ship;
    	} catch (Exception e) {
    		return null;
    	}

    }
}
