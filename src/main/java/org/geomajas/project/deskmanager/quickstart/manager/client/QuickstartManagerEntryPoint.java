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

package org.geomajas.project.deskmanager.quickstart.manager.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import org.geomajas.gwt.client.command.CommunicationExceptionCallback;
import org.geomajas.gwt.client.command.GwtCommandDispatcher;
import org.geomajas.gwt.client.command.TokenRequestHandler;
import org.geomajas.gwt.client.command.event.TokenChangedEvent;
import org.geomajas.gwt.client.command.event.TokenChangedHandler;
import org.geomajas.gwt.client.i18n.I18nProvider;
import org.geomajas.gwt.client.util.CrocEyeNotificationHandler;
import org.geomajas.gwt.client.util.Log;
import org.geomajas.gwt.client.util.Notify;
import org.geomajas.plugin.deskmanager.client.gwt.common.StaticUserApplicationConfiguration;
import org.geomajas.plugin.deskmanager.client.gwt.common.UserApplicationRegistry;
import org.geomajas.plugin.deskmanager.client.gwt.common.util.DeskmanagerLayout;
import org.geomajas.plugin.deskmanager.client.gwt.manager.ManagerApplicationLoader;

/**
 * @author Oliver May
 */
public class QuickstartManagerEntryPoint implements EntryPoint {

	private void init() {
		initLayout();
		UserApplicationRegistry reg = UserApplicationRegistry.getInstance();
		reg.register(new StaticUserApplicationConfiguration("quickstart_userapplication_id", "quickstart_userapplication_id"));

		//Register crock eye notificator.
		Notify.getInstance().setHandler(CrocEyeNotificationHandler.getInstance());
		GwtCommandDispatcher.getInstance().setCommandExceptionCallback(CrocEyeNotificationHandler.getInstance());
		GwtCommandDispatcher.getInstance().setCommunicationExceptionCallback(new CommunicationExceptionCallback() {

			@Override
			public void onCommunicationException(Throwable error) {
				//Hide communication errors from the user, but report to server (try once)
				String msg = I18nProvider.getGlobal().commandCommunicationError() + ":\n" + error.getMessage();
				Log.logError(msg, error);
			}
		});
	}

	//default layout setting of manager application
	private void initLayout() {
		DeskmanagerLayout.subTabDefaultHeight = 20;
		DeskmanagerLayout.tabBarLeftThickness = 120;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		init();
//		Cookies.setCookie("skin_name", "Enterprise");
//		Cookies.setCookie("skin", "Enterprise");
		final Layout layout = new Layout();
		layout.setHeight100();
		layout.setWidth100();

		HLayout header = new HLayout();
		header.setWidth100();

		ManagerApplicationLoader managerApplicationLoader = ManagerApplicationLoader.getInstance();
		managerApplicationLoader.setTokenRequestHandler(new TokenRequestHandler() {
			@Override
			public void login(TokenChangedHandler tokenChangedHandler) {
				tokenChangedHandler.onTokenChanged(new TokenChangedEvent("test"));
			}
		});
		managerApplicationLoader.loadManager(layout, null, header);
		layout.draw();
	}

}
