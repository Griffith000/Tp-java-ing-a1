package Profil;
import java.util.ArrayList;

public class Data {
    ArrayList<Profil> data = new ArrayList<Profil>();
    public void PushData(Profil listItem) {
        this.data.add(listItem);
    }

    public Profil RechercheSelonPseudo(String Pseudo) {
        for (Profil someData: this.data){
            if (someData.getPseudo().equalsIgnoreCase(Pseudo)){
                return someData;
            }
        }
        return null;
    }
}
