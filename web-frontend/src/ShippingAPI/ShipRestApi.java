package ShippingAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.*;


public class ShipRestApi {

    public JSONObject getShippingCost(String city, int quantity){

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
            sb.append(in + "\n");
        }
        System.out.println(sb.toString());
        connection.disconnect();
    	JSONObject jObj = new JSONObject(sb.toString());
    	return jObj;
    	} catch (Exception e) {
    		return null;
    	}

    }
}
