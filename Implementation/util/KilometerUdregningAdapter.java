package util;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

public interface KilometerUdregningAdapter {

	String getDistance(String Origin, String Destination) throws IOException, XPathExpressionException;

//	String run(String url) throws IOException;
	
	String getDuration();

}