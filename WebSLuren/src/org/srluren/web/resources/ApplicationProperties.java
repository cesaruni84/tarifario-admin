/**
 * 
 */
package org.srluren.web.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author AETOS PERU S.A.C
 * 
 */
public class ApplicationProperties {

	private static Logger logger = Logger.getLogger(ApplicationProperties.class);

	private static String BUNDLE_NAME = "org.srluren.web.resources.config";

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ApplicationProperties() {

	}

	/**
	 * @param key
	 */
	public static String getString(String key) {

		String text = "";

		try {

			text = RESOURCE_BUNDLE.getString(key);

		} catch (MissingResourceException e) {
			logger.error("La llave " + key  + "no existe en el archivo properties. ", e);
			text = "";

		} catch (NullPointerException e) {
			logger.error("La llave es nula. ", e);
			text = "";
		}

		return text;

	}

}
