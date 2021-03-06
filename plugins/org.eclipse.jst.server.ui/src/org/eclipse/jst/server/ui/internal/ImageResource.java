/*******************************************************************************
 * Copyright (c) 2003, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.server.ui.internal;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
/**
 * Utility class to handle image resources.
 */
public class ImageResource {
	// the image registry
	private static ImageRegistry imageRegistry;

	// map of image descriptors since these
	// will be lost by the image registry
	private static Map<String, ImageDescriptor> imageDescriptors;

	// base urls for images
	private static URL ICON_BASE_URL;

	static {
		try {
			String pathSuffix = "icons/";
			ICON_BASE_URL = JavaServerUIPlugin.getInstance().getBundle().getEntry(pathSuffix);
		} catch (Exception e) {
			if(Trace.SEVERE) {
				Trace.trace(Trace.STRING_SEVERE, "Could not set icon base URL", e);
			}
		}
	}

	private static final String URL_WIZBAN = "wizban/";

	public static final String IMG_WIZ_RUNTIME_TYPE = "wiz_runtimeType";

	/**
	 * Cannot construct an ImageResource. Use static methods only.
	 */
	private ImageResource() {
		// do nothing
	}

	/**
	 * Return the image with the given key.
	 *
	 * @param key java.lang.String
	 * @return org.eclipse.swt.graphics.Image
	 */
	public static Image getImage(String key) {
		if (imageRegistry == null)
			initializeImageRegistry();
		return imageRegistry.get(key);
	}

	/**
	 * Return the image descriptor with the given key.
	 *
	 * @param key java.lang.String
	 * @return org.eclipse.jface.resource.ImageDescriptor
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		if (imageRegistry == null)
			initializeImageRegistry();
		return imageDescriptors.get(key);
	}

	/**
	 * Initialize the image resources.
	 */
	protected static void initializeImageRegistry() {
		imageRegistry = new ImageRegistry();
		imageDescriptors = new HashMap<String, ImageDescriptor>();
		
		registerImage(IMG_WIZ_RUNTIME_TYPE, URL_WIZBAN + "new_runtime_wiz.png");
	}

	/**
	 * Register an image with the registry.
	 *
	 * @param key the key
	 * @param partialURL a partial URL
	 */
	private static void registerImage(String key, String partialURL) {
		try {
			ImageDescriptor id = ImageDescriptor.createFromURL(new URL(ICON_BASE_URL, partialURL));
			imageRegistry.put(key, id);
			imageDescriptors.put(key, id);
		} catch (Exception e) {
			if(Trace.WARNING) {
				Trace.trace(Trace.STRING_WARNING, "Error registering image " + key + " from " + partialURL, e);
			}
		}
	}
}