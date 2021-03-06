package logic;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import domain.Flextur;
/**
 * 
 * Adapter for kilometer udregning
 * 
 * @author Juyoung Choi
 *
 */
public interface KilometerUdregningAdapter {

	Flextur getDistance(Flextur flextur) throws IOException, XPathExpressionException;

}