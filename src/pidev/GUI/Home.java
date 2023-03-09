package pidev.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class Home extends Form {
    public Home(){

        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Crud"));
        //ajouter deux boutons d'ajout et d'affichage
        Button b1 = new Button("Ajouter catégorie");
        Button b2 = new Button("Afficher catégories");
        //quand on clique sur le bouton de l'ajout, il va nous rediriger vers le formulaire de l'ajout
        b1.addActionListener(l -> new AjouterCategorie(this).show());
        //quand on clique sur le bouton de l'affichage il va nous rediriger vers la liste des categories
        b2.addActionListener(l -> new ListeCategorie(this).show());
        addAll(b1,b2);

    }

}
