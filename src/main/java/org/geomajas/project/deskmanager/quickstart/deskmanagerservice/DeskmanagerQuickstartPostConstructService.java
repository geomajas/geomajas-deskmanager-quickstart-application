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
package org.geomajas.project.deskmanager.quickstart.deskmanagerservice;

import org.geomajas.geometry.service.WktException;
import org.geomajas.global.GeomajasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Oliver May
 * 
 *         Helper class to call some methods after constructing the context. This and
 *         {@link DeskmanagerQuickstartDatabaseProvisioningService} can't be combined into one class because
 *         {@link javax.annotation.PostConstruct} doesn't wrap transactions.
 */
@Component
public class DeskmanagerQuickstartPostConstructService {

	@Autowired
	private DeskmanagerQuickstartDatabaseProvisioningService provisioningService;

	@PostConstruct
	public void postConstruct() throws WktException, GeomajasException {
		provisioningService.createData();
	}
}
