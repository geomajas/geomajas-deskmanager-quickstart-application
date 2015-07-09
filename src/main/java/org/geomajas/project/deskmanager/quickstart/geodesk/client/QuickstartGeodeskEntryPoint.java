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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import org.geomajas.command.CommandResponse;
import org.geomajas.gwt.client.command.CommandCallback;
import org.geomajas.gwt.client.command.GwtCommand;
import org.geomajas.gwt.client.command.TokenRequestHandler;
import org.geomajas.gwt.client.command.event.TokenChangedEvent;
import org.geomajas.gwt.client.command.event.TokenChangedHandler;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.plugin.deskmanager.client.gwt.common.GdmLayout;
import org.geomajas.plugin.deskmanager.command.usernamepasswordsecurity.dto.RetrieveRolesRequest;
import org.geomajas.plugin.deskmanager.command.usernamepasswordsecurity.dto.RetrieveRolesResponse;

/**
 * @author Oliver May
 */
public class QuickstartGeodeskEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final String geodeskId = GdmLayout.geodeskIdUtil.parseGeodeskId("");
		GeomajasServerExtension.getInstance().getCommandService().setTokenRequestHandler(new TokenRequestHandler() {
			@Override
			public void login(final TokenChangedHandler tokenChangedHandler) {
//				Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
//					@Override
//					public void execute() {
//						Window.alert("Selected token test");
//						tokenChangedHandler.onTokenChanged(new TokenChangedEvent("test"));
//					}
//				});

				RetrieveRolesRequest request = new RetrieveRolesRequest();
				request.setGeodeskId(geodeskId);
				GwtCommand command = new GwtCommand(RetrieveRolesRequest.COMMAND);
				command.setCommandRequest(request);
				GeomajasServerExtension.getInstance().getCommandService().execute(command,
						new CommandCallback<CommandResponse>() {
					@Override
					public void execute(CommandResponse response) {
						if (response instanceof RetrieveRolesResponse) {
							if (((RetrieveRolesResponse) response).getRoles().keySet().size() > 0) {
								tokenChangedHandler.onTokenChanged(new TokenChangedEvent(((RetrieveRolesResponse)
										response).getRoles().keySet().iterator().next()));
							} else {
								Window.alert("Insufficient rights");
							}
						}
					}
				});

			}
		});

		ApplicationLayout layout = new ApplicationLayout(geodeskId);
		RootLayoutPanel.get().add(layout);
	}
}
