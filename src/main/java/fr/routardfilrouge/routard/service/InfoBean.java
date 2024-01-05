package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Info;
import fr.routardfilrouge.routard.metier.InfoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoBean {
    private ObservableList<InfoType> infoTypes;
    private HashMap<String, HashMap<InfoType, String>> infoCollection;
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

            if(infoCollection.get(country.getIsoCode()) == null)
                infoCollection.putIfAbsent(country.getIsoCode(), new HashMap<>());

            infoCollection.get(country.getIsoCode()).put(type, infoText);
        }
    }

    public ObservableList<InfoType> getInfoTypes() {
        return infoTypes;
    }
    public HashMap<String, HashMap<InfoType, String>> getInfoCollection() {
        return infoCollection;
    }

    public HashMap<InfoType, String> getInfoSlice(Country country) {
        return infoCollection.get(country.getIsoCode());
    }
    public boolean postInfoType(InfoType type) {
        boolean isPosted = DAOFactory.getInfoDAO().postType(type);
        if(isPosted)
            infoTypes.setAll(DAOFactory.getInfoDAO().getAllType());
        return isPosted;
    }

    public boolean postInfo(Info info) {
        return DAOFactory.getInfoDAO().post(info);
    }

    public void updateInfoCollectionForCountry(Country country) {
        ArrayList<Info> infos = DAOFactory.getInfoDAO().getLike(country);
        HashMap<InfoType, String> updatedSlice = new HashMap<>();
        for (Info value : infos) {
            int idType = value.getIdType();
            InfoType type = infoTypes.get(idType - 1);

            String text = value.getInfoText();

            updatedSlice.put(type, text);
        }

        if(infoCollection.get(country.getIsoCode()) == null)
            infoCollection.put(country.getIsoCode(),new HashMap<>());

        infoCollection.put(country.getIsoCode(), updatedSlice);
    }


}
