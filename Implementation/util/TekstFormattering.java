package util;

import javafx.scene.control.TextField;
/**
 * 
 * @author Juyoung Choi
 *
 */
public interface TekstFormattering {

	void formaterTekstfeltInput(TextField input);
	
	Double formaterStringTilDouble(String tekstinput);
	
	String formaterDoubleTilString(Double resultat);
}
