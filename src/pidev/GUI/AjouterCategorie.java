package pidev.GUI;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import pidev.entities.CategorieProduit;
import pidev.services.ServiceCategorieProdiut;

public class AjouterCategorie  extends Form{
    public AjouterCategorie(Form previous){

        setTitle("Ajouter categorie");
        setLayout(BoxLayout.y());
        TextField tfnom = new TextField("","Nom de catégorie");
        TextField tfDescription = new TextField("","Description de catégorie");
        Button btnadd =new Button("add categorie");


        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //tester si le champs nom est vide
                if(tfnom.getText().length()==0 && tfDescription.getText().length()==0){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{




                    CategorieProduit c = new CategorieProduit(tfnom.getText(),tfDescription.getText());
                    if(ServiceCategorieProdiut.getinstance().addTask(c)){
                        Dialog.show("Alert","ajouté avec succés","ok",null);
                    }else {
                        Dialog.show("Alert","erreur ","ok",null);
                    }

                }
            }
        });
        addAll(tfnom,tfDescription,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, ev->previous.show());


    }


}
