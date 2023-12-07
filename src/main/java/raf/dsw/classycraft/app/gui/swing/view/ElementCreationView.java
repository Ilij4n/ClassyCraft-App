package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.controller.CreateInterClassAction;
import raf.dsw.classycraft.app.controller.stateControllers.AddInterClassAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Attribute;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Method;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class ElementCreationView extends JFrame {

    private JRadioButton radioBtnKlasa = new JRadioButton("Klasa");
    private JRadioButton radioBtnInterfejs = new JRadioButton("Interfejs");
    private JRadioButton radioBtnEnum = new JRadioButton("Enum");
    private JTextArea textAreaElementi = new JTextArea(10, 15);
    private JTextField tfImeElementa = new JTextField(20);
    private JLabel labelOdabir = new JLabel("Odabir:");
    private JLabel labelNapisati = new JLabel("Napisati Elemente");
    private static boolean isShowing = false;
    private ClassyDiagramView classyDiagramView;
    private Point2D point2D;
    private String name;
    private boolean goNext = true;
    private Set<ClassContent> classContents = new LinkedHashSet<>();


    public ElementCreationView(ClassyDiagramView classyDiagramView, Point2D point2D){
        init();
        isShowing = true;
        this.classyDiagramView = classyDiagramView;
        this.point2D = point2D;
    }

    private void init(){
        setTitle("Dodaj Element");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 500);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioBtnKlasa);
        buttonGroup.add(radioBtnEnum);
        buttonGroup.add(radioBtnInterfejs);

        //radioBtnKlasa.setSelected(true);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Prvi red
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(labelOdabir, gbc);

        gbc.gridx = 1;
        mainPanel.add(radioBtnKlasa, gbc);

        gbc.gridx = 2;
        mainPanel.add(radioBtnInterfejs, gbc);

        gbc.gridx = 3;
        mainPanel.add(radioBtnEnum, gbc);


        // Drugi red
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Ime Elementa"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        mainPanel.add(tfImeElementa, gbc);

        // Treci red
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(labelNapisati, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(textAreaElementi), gbc);

        // Cetvri red
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Potvrdi !"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        /*
            Kontroler na ovom dugmetu ce u zavisnosti od izbora elementa u radiobutonima
            da proveri ispravnost formata ulaza i da parsuje podatke u slucaju da su dobri
            i da ih nekako prosledi do miskliknut metode u AddElementState.
            U suprotnom ako format nije dobar, izbacuje gresku
         */
        JButton button = new JButton("Nacrtaj!");
        button.setAction(new CreateInterClassAction(this));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(button, gbc);

        getContentPane().add(mainPanel);
        // NE BRISTI OVU METODU, ONA DRZI FUNKCIONALNOST IZ NEKOG RAZLOGA
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                isShowing = false;
            }
        });

        addPlaceholder("/*Napisati input u sledecem formatu*/\nPolja:\nvidljivost Tip imePromenljive\n /*... (ostala polja)*/\nMetode:\nvidljivost Tip ime\n/*... (ostale metode)*/\n ", textAreaElementi);


        setLocationRelativeTo(null);
        //setVisible(true);
        setResizable(false);
    }
    public static boolean pokazanSam(){
        return isShowing;
    }


    public void postaviPolja(InterClass c){
        if(c instanceof Klasa) radioBtnKlasa.setSelected(true);
        else if (c instanceof Interfejs)radioBtnInterfejs.setSelected(true);
        else radioBtnEnum.setSelected(true);

        tfImeElementa.setText(c.getName());
        StringBuilder builder = new StringBuilder();
        boolean flag1 = false;
        boolean flag2 = false;
        for (ClassContent content: c.getContentSet()){
            if(content instanceof Attribute && !flag1){
                builder.append("Polja:\n");
                flag1 = true;
            }
            else if(content instanceof Method && !flag2){
                builder.append("Metode:\n");
                flag2 = true;
            }
            System.out.println("Usao u edit");
            if(flag2)builder.append(content.toString().replace("()",""));
            else builder.append(content.toString());

            builder.append("\n");
        }
        textAreaElementi.setText(builder.toString());

    }

    public Set<ClassContent> vratiPoljaIMetode(){
        String input = textAreaElementi.getText();
        String[] linije = input.split("\n");
        String poljeIliMetoda = "";


       // Za proveru pravilnog inputa vidjlivost

        Set<String> vrednostiVidljivosti = new HashSet<>();
        vrednostiVidljivosti.add("+");
        vrednostiVidljivosti.add("-");
        vrednostiVidljivosti.add("#");
        vrednostiVidljivosti.add("~");

        if(linije.length==0||linije[0].equalsIgnoreCase("/*Napisati input u sledecem formatu*/"))return classContents;
        try{
            for(String s : linije){
                if(s.equalsIgnoreCase("Polja:")&&!radioBtnInterfejs.isSelected()){
                    poljeIliMetoda = s;
                    continue;
                }
                if(s.equalsIgnoreCase("Metode:")&&!radioBtnEnum.isSelected()){
                    poljeIliMetoda = s;
                    continue;
                }
                if(poljeIliMetoda.equalsIgnoreCase("Polja:")){
                    String podLinije[] = s.split(" ");
                    //ovde mogu ici linije za proveru podLinije[0] aka vidljivosti, ako nije + - ~ onda baci exception
                    if(!(vrednostiVidljivosti).contains(podLinije[0]))throw new Exception();
                    classContents.add(new Attribute(podLinije[0],podLinije[1],podLinije[2]));
                }
                if(poljeIliMetoda.equalsIgnoreCase("Metode:")){
                    String podLinije[] = s.split(" ");
                    ////ovde mogu ici linije za proveru podLinije[0] aka vidljivosti, ako nije + - ~ onda baci exception
                    if(!(vrednostiVidljivosti).contains(podLinije[0]))throw new Exception();
                    classContents.add(new Method(podLinije[0],podLinije[1],podLinije[2]));
                }
            }
        }catch (Exception e){
            Set<ClassContent> errorSet = new LinkedHashSet<>();
            errorSet.add(new Method("error","error","error"));
            return errorSet;
        }

        return classContents;
    }

    public List<String> parsujUVezu(){
        String input = textAreaElementi.getText();
        String lines[] = input.split("\n");

        List<String> idemooo = new ArrayList<>();

        Set<String> vrednostiVidljivosti = new HashSet<>();
        vrednostiVidljivosti.add("+");
        vrednostiVidljivosti.add("-");
        vrednostiVidljivosti.add("#");
        vrednostiVidljivosti.add("~");

        Set<String> vrednostiKardinaliteta = new HashSet<>();
        vrednostiKardinaliteta.add("1..1");
        vrednostiKardinaliteta.add("1..n");
        vrednostiKardinaliteta.add("n..1");
        vrednostiKardinaliteta.add("m..n");

        if(!radioBtnInterfejs.isSelected() && !radioBtnEnum.isSelected()){
            idemooo.add("");
            idemooo.add("");
            return idemooo;
        }

        boolean parsePolje = true;
        boolean parseKardinalitet = true;

        try {
            for(String s : lines){
                String podLinije[] = s.split(" ");
                if(podLinije.length == 2 && parsePolje){
                    if(!vrednostiVidljivosti.contains(podLinije[0])){
                        System.out.println("usao error");
                        throw new Exception();
                    }
                    System.out.println("usao vidljivost");
                    idemooo.add(podLinije[0]+" "+podLinije[1]);
                    parsePolje = false;
                }
                if(podLinije.length == 1 && parseKardinalitet){
                    System.out.println("usao kardinalitet");
                    if(!vrednostiKardinaliteta.contains(podLinije[0])){
                        throw new Exception();
                    }
                    idemooo.add(podLinije[0]);
                    parseKardinalitet = false;
                }
            }
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add("//neispravan format//");
            return errorList;
        }
        return idemooo;
    }

    public void sakrijZaEdit(){
        radioBtnKlasa.hide();
        radioBtnInterfejs.hide();
        radioBtnEnum.hide();
        labelOdabir.hide();
    }

    public void addPlaceholder(String placeholder, JTextArea textArea) {
        textArea.setText(placeholder);
        textArea.setForeground(java.awt.Color.GRAY);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText(placeholder);
                    textArea.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }
}
