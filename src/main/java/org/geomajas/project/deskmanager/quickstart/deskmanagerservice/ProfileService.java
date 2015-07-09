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

import org.geomajas.plugin.deskmanager.domain.security.Profile;
import org.geomajas.plugin.deskmanager.domain.security.dto.Role;
import org.geomajas.plugin.deskmanager.security.internal.DeskmanagerSecurityServiceImpl;
import org.geomajas.plugin.deskmanager.service.common.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author Oliver May
 *
 */
@Component
public class ProfileService implements org.geomajas.plugin.deskmanager.security.ProfileService {

	@Autowired
	private TerritoryService groupService;

	@Override
	public List<Profile> getProfiles(String token) {
		return getPredefinedProfiles();
	}

	@Override
	public Profile createGuestProfile() {
		Profile profile = new Profile();
		profile.setRole(Role.GUEST);
		return profile;
	}

	public List<Profile> getPredefinedProfiles() {
		ArrayList<Profile> profiles = new ArrayList<Profile>();

		Profile admin = new Profile();
		admin.setRole(Role.ADMINISTRATOR);
		admin.setId(UUID.randomUUID().toString());
		admin.setFirstName("Peter");
		admin.setSurname("The Administrator");
		admin.setTerritory(groupService.getByCode("ADMIN"));
		profiles.add(admin);

		Profile deskManagerEU = new Profile();
		deskManagerEU.setRole(Role.DESK_MANAGER);
		deskManagerEU.setId(UUID.randomUUID().toString());
		deskManagerEU.setFirstName("Peter");
		deskManagerEU.setSurname("The Administrator");
		deskManagerEU.setTerritory(groupService.getByCode("EU"));
		profiles.add(deskManagerEU);

		Profile deskManagerBE = new Profile();
		deskManagerBE.setRole(Role.DESK_MANAGER);
		deskManagerBE.setId(UUID.randomUUID().toString());
		deskManagerBE.setFirstName("Peter");
		deskManagerBE.setSurname("The Administrator");
		deskManagerBE.setTerritory(groupService.getByCode("BE"));
		profiles.add(deskManagerBE);

		Profile deskManagerNL = new Profile();
		deskManagerNL.setRole(Role.DESK_MANAGER);
		deskManagerNL.setId(UUID.randomUUID().toString());
		deskManagerNL.setFirstName("Peter");
		deskManagerNL.setSurname("The Administrator");
		deskManagerNL.setTerritory(groupService.getByCode("NL"));
		profiles.add(deskManagerNL);

		Profile deskManagerDE = new Profile();
		deskManagerDE.setRole(Role.DESK_MANAGER);
		deskManagerDE.setId(UUID.randomUUID().toString());
		deskManagerDE.setFirstName("Peter");
		deskManagerDE.setSurname("The Administrator");
		deskManagerDE.setTerritory(groupService.getByCode("DE"));
		profiles.add(deskManagerDE);

	
//		Profile deskConsulter = new Profile();
//		deskConsulter.setRole(Role.CONSULTING_USER);
//		deskConsulter.setId(UUID.randomUUID().toString());
//		deskConsulter.setFirstName("Stewart");
//		deskConsulter.setSurname("The Consultor");
//		deskConsulter.setTerritory(groupService.getByCode("BE"));
//		profiles.add(deskConsulter);
//
//		Profile deskEditor = new Profile();
//		deskEditor.setRole(Role.EDITING_USER);
//		deskEditor.setId(UUID.randomUUID().toString());
//		deskEditor.setFirstName("Meg");
//		deskEditor.setSurname("The Editor");
//		deskEditor.setTerritory(groupService.getByCode("BE"));
//		profiles.add(deskEditor);

		profiles.add(DeskmanagerSecurityServiceImpl.createGuestProfile());

		return profiles;
	}
}