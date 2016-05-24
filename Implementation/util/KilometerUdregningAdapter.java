package util;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

public interface KilometerUdregningAdapter {

	String Distance(String Origin, String Destination) throws IOException, XPathExpressionException;

//	String run(String url) throws IOException;
	
	String Duration();

}