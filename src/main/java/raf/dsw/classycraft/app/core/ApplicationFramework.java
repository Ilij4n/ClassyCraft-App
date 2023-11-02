package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.core.model.ClassyRepository;
import raf.dsw.classycraft.app.core.model.ClassyRepositoryImp;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    /*
        posto nam je Framework singleton, ClassyRepo ce se instancirati samo jednom
        te cemo samim tim zagarantovano imati samo jednu instancu roota iz naseg Repoa
     */
    private ClassyRepository repository = new ClassyRepositoryImp();

    //buduca polja za model celog projekta

    private ApplicationFramework(){

    }

    public void initialize(){
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
