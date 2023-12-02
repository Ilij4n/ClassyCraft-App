package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.CreateInterClassAction;
import raf.dsw.classycraft.app.controller.stateControllers.AddInterClassAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

@Getter
@Setter
public class ElementCreationView extends JFrame {

    private JRadioButton radioBtnKlasa = new JRadioButton("Klasa");
    private JRadioButton radioBtnInterfejs = new JRadioButton("Interfejs");
    private JRadioButton radioBtnEnum = new JRadioButton("Enum");
    private JTextArea textAreaElementi = new JTextArea(10, 15);
    private JTextField tfImeElementa = new JTextField(20);
    private static boolean isShowing = false;
    private ClassyDiagramView classyDiagramView;
    private Point2D point2D;
    private String name;


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

        radioBtnKlasa.setSelected(true);

        // GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust insets as needed

        // Prvi red
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Odabir:"), gbc);

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
        mainPanel.add(new JLabel("Napisati Elemente"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(textAreaElementi), gbc);

        // Cetvri red
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset gridwidth
        mainPanel.add(new JLabel("Potvrdi !"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3; // Span three columns
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                isShowing = false;
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public static boolean jedini(){
        return isShowing;
    }
}
