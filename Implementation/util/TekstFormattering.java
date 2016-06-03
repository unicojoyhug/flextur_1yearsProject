package util;

import javafx.scene.control.TextField;
/**
 * tekstformattering interfaces 
 * den klasse kan bruges for at parse data type fra guilag til logiklag
 * @author Juyoung Choi
 *
 */
public interface TekstFormattering {

	void formaterTekstfeltInput(TextField input);
	
	Double formaterStringTilDouble(String tekstinput);
	
	String formaterDoubleTilString(Double resultat);
}
