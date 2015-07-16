package ralph.lydia.processor;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileValidator {

	//	ErrorReporter errorReporter;

	private String errorString = " was not valid against the schema. Moving to 'Invalid' folder...";
	private String validString = " was valid. Processing...";

	private String failureMessage;

	public boolean validateXmlFile(File xmlFile){

		Source xmlFileSource = new StreamSource(xmlFile);
		try{
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(loadXSD());

			Validator validator = schema.newValidator();
			validator.validate(xmlFileSource);
			System.out.println(xmlFileSource.getSystemId() + validString);
			return true;
		} catch (SAXException e) {
			this.setFailureMessage(xmlFileSource.getSystemId() + errorString);
			this.appendFailureMessage("\nReason: " + e.getMessage());
			System.out.println(this.getFailureMessage());
			return false;
		}
		catch (IOException e){
			this.setFailureMessage("Could not analyse xmlFileSource " + xmlFileSource);
			this.appendFailureMessage("\nReason: " + e.getMessage());
			System.out.println(this.getFailureMessage());
			return false;
		}
	}

	private void setFailureMessage(String message){
		this.failureMessage = message;
	}

	public String getFailureMessage(){
		return this.failureMessage;
	}

	private void appendFailureMessage(String message){
		this.setFailureMessage(this.failureMessage + message);
	}

	private static Source loadXSD(){
		try{
			FileInputStream in = new FileInputStream("ElectionResultXmlSchema.xsd");
			Source xsd = new StreamSource(in);

			return xsd;
		} catch(FileNotFoundException e){
			System.out.println("Didn't find a file: " + e.getMessage());
			return null;

		}
	} 
}