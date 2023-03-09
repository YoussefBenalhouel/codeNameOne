package pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import pidev.entities.CategorieProduit;
import pidev.utils.Statics;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;



public class ServiceCategorieProdiut {
    ArrayList<CategorieProduit> categorie;
    ConnectionRequest req;
    boolean resultOk;
    public boolean test;
    //créer un attribut instance de type de la classe en question (static)
    public static ServiceCategorieProdiut instance = null;

    // rendre le constructeur private pour instancier une seule fois
    private ServiceCategorieProdiut() {
        req = new ConnectionRequest();
    }
    //s'il est null donc elle crée une instance
    public static ServiceCategorieProdiut getinstance(){
        if(instance == null){
            instance = new ServiceCategorieProdiut();
        }
        return instance;
    }







    //methode d'ajout
    public boolean addTask(CategorieProduit r){



        String nom=r.getNom();



        String Desc=r.getDescription();


        String url = Statics.BASE_URL+"categorie/produit/newAdd";
        String requestBody = "  {\"nom\":\""+nom+"\",\"description\":\""+Desc+"\"}  ";


        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.setRequestBody(requestBody);

        System.out.println(req.getRequestBody());
        req.setContentType("application/json");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;



    }




    public ArrayList<CategorieProduit> parseTasks(String jsonText) {
        try {
            categorie = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("map="+tasksListJson);

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("map="+list);


            for (Map<String, Object> obj : list) {
                CategorieProduit a = new CategorieProduit();
                float id = Float.parseFloat(obj.get("id").toString());

                System.out.println(id);

                a.setId((int)id);

                a.setNom(obj.get("nom").toString());

                categorie.add(a);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return categorie;
    }


    //methode d'affichage
    public ArrayList<CategorieProduit> getAllCategories(){
        String url = Statics.BASE_URL+"categorie/produit/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return categorie;
    }


}

