package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.AdministrativeRequirement;
import fr.routardfilrouge.routard.metier.Country;

import java.util.ArrayList;

public class AdministrativeReqBean {
    public void fetchAdministrativeReqsForCountry(Country country) {
        ArrayList<AdministrativeRequirement> adminReqs = DAOFactory.getAdministrativeRequirementDAO().getLike(country);

        country.setAdministrativeReqs(adminReqs);
    }
}
