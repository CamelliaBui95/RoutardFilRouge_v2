package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Info;
import fr.routardfilrouge.routard.metier.InfoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoBean {
    private ObservableList<InfoType> infoTypes;
    private HashMap<Country, HashMap<InfoType, String>> infoCollection;
    ArrayList<Info> infoArrayList;
    public InfoBean() {
        infoArrayList = new ArrayList<>();
        infoTypes = FXCollections.observableArrayList();
        infoCollection = new HashMap<>();

        infoTypes.addAll(DAOFactory.getInfoDAO().getAllType());
        infoArrayList = DAOFactory.getInfoDAO().getAll();

        addInfoToCollection();
    }

    private void addInfoToCollection() {
        for(int i = 0; i < infoArrayList.size(); i++) {
            Info info = infoArrayList.get(i);

            Country country = info.getCountry();

            int idType = info.getIdType();
            InfoType type = infoTypes.get(idType - 1);

            String infoText = info.getInfoText();

            if(infoCollection.get(country) == null)
                infoCollection.putIfAbsent(country, new HashMap<>());

            infoCollection.get(country).put(type, infoText);
        }
    }

    public ObservableList<InfoType> getInfoTypes() {
        return infoTypes;
    }
    public HashMap<Country, HashMap<InfoType, String>> getInfoCollection() {
        return infoCollection;
    }

    public HashMap<InfoType, String> getInfoSlice(Country country) {
        return infoCollection.get(country);
    }
}
