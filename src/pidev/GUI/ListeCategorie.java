package pidev.GUI;

import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import pidev.entities.CategorieProduit;
import pidev.services.ServiceCategorieProdiut;

import java.util.ArrayList;

public class ListeCategorie extends Form {
    public ListeCategorie(Form previous){
        setTitle("Liste categorie");
        setLayout(BoxLayout.y());

        ArrayList<CategorieProduit> categorie = ServiceCategorieProdiut.getinstance().getAllCategories();


        for (CategorieProduit i :categorie){
            addCategorie(i);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, ev->previous.show());




    }

    public void addCategorie(CategorieProduit t) {
        CheckBox cb = new CheckBox(t.getNom());
        cb.setEnabled(false);
        if(t.getId()!=0){
            cb.setEnabled(true);

        }
        add(cb);


    }}