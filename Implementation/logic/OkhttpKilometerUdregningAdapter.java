package logic;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkhttpKilometerUdregningAdapter implements KilometerUdregningAdapter {

	private static final String API_KEY = "AIzaSyBoOejgRYqOuDSldGnIDetXOEthJc-CdoM";
	OkHttpClient client = new OkHttpClient();
	public String duration; //why public?

	/* (non-Javadoc)
	 * @see util.KilometerUdregningAdapter#Distance(java.lang.String, java.lang.String)
	 */
	@Override
	public String getDistance(String Origin, String Destination) throws IOException, XPathExpressionException {
//		KilometerUdregningAdapter request = new KilometerUdregningAdapterImpl();
		String url_request = "https://maps.googleapis.com/maps/api/distancematrix/xml?origins=" + Origin
				+ "&destinations=" + Destination + "&mode=driving&language=da-DK&key=" + API_KEY;
		String response = run(url_request);
		String distance = XMLparse(response);
		// System.out.println(response);
		return distance;
	}
	@Override
	public String getDuration(){
		return duration;
		
	}
	
	
	private String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	private String XMLparse(String xml) throws XPathExpressionException {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
		String distance = xpath.evaluate("/DistanceMatrixResponse/row/element/distance/text", doc);
		String duration = xpath.evaluate("/DistanceMatrixResponse/row/element/duration/text", doc);
		this.duration = duration;
		// System.out.println("Distance er = " + distance);
		return distance;
	}
}
