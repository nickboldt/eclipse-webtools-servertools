/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.server.http.core.internal.command;

import org.eclipse.wst.server.http.core.internal.HttpServer;
import org.eclipse.wst.server.http.core.internal.Messages;

public class ModifyURLPrefixCommand extends ServerCommand {
	protected String prefix;
	protected String oldPrefix;

	/**
	 * ModifyURLPrefixCommand constructor.
	 * 
	 * @param server a HTTP configuration
	 * @param prefix a new prefix
	 */
	public ModifyURLPrefixCommand(HttpServer server, String prefix) {
		super(server, Messages.configurationEditorActionModifyPort);
		this.prefix = prefix;
	}

	/**
	 * Execute the command.
	 */
	public void execute() {
		// find old prefix
		oldPrefix = server.getURLPrefix();

		// make the change
		server.setURLPrefix(prefix);
	}

	/**
	 * Undo the command.
	 */
	public void undo() {
		server.setURLPrefix(oldPrefix);
	}
}