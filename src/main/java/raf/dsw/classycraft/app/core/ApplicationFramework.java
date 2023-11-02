package raf.dsw.classycraft.app.core;

import lombok.Getter;
import raf.dsw.classycraft.app.MessageGenerator.MessageGenerator;
import raf.dsw.classycraft.app.MessageGenerator.MessageGeneratorImp;
import raf.dsw.classycraft.app.core.model.ClassyRepository;
import raf.dsw.classycraft.app.core.model.ClassyRepositoryImp;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
@Getter
public class ApplicationFramework {

    private static ApplicationFramework instance;


    //buduca polja za model celog projekta

    /*
        posto nam je Framework singleton, ClassyRepo ce se instancirati samo jednom
        te cemo samim tim zagarantovano imati samo jednu instancu roota iz naseg Repoa
     */
    private ClassyRepository repository = new ClassyRepositoryImp();
    private MessageGenerator messageGenerator = new MessageGeneratorImp();

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
