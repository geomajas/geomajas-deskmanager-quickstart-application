/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.project.deskmanager.quickstart.common.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.geomajas.plugin.deskmanager.client.gwt.common.GdmLayout;
import org.geomajas.plugin.deskmanager.client.gwt.common.GeodeskIdUtil;

/**
 * Gwt entry point that configures properties that are shared between the manager and geodesk entrypoints.
 *
 * @author Oliver May
 */
public class DeskmanagerQuickstartCommonEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		//Setup geodesk id retriever
		GdmLayout.geodeskIdUtil = new GeodeskIdUtil() {
			@Override
			public String parseGeodeskId(String url) {
				return com.google.gwt.user.client.Window.Location.getParameter("geodesk");
			}

			@Override
			public String buildGeodeskUrl(String geodeskId) {
				return "../geodesk/index.html?geodesk=" + geodeskId;
			}
		};
		//loading logo
		GdmLayout.loadingLogo = GWT.getModuleBaseURL() + "images/Geomajas-Logo-Large.png";
	}
}
