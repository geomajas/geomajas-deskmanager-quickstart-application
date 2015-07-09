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
package org.geomajas.project.deskmanager.quickstart.geodesk.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.gwt2.client.GeomajasImpl;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.widget.MapLayoutPanel;

import java.util.List;

/**
 * General Application layout for this application.
 *
 * @author Oliver May
 */
public class ApplicationLayout extends ResizeComposite {

	private final MapPresenter mapPresenter;

	private final MapLayoutPanel mapLayoutPanel;

	private List<Double> resolutions;

	/**
	 * UI binder interface for this layout.
	 */
	interface MyUiBinder extends UiBinder<Widget, ApplicationLayout> {

	}

	private static final MyUiBinder UIBINDER = GWT.create(MyUiBinder.class);

	@UiField
	protected SimpleLayoutPanel mapPanel;

	/**
	 * Constructor.
	 */
	public ApplicationLayout(String geodeskId) {

		initWidget(UIBINDER.createAndBindUi(this));

		mapPresenter = GeomajasImpl.getInstance().createMapPresenter();

		mapLayoutPanel = new MapLayoutPanel();
		mapLayoutPanel.setPresenter(mapPresenter);
		mapPanel.add(mapLayoutPanel);

		if (geodeskId == null) {
			Window.alert("No geodesk given!");
		} else {
			GeomajasServerExtension.getInstance().initializeMap(mapPresenter, geodeskId, "mainMap");
		}
	}

}