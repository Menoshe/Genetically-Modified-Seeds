/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticallymodifiedseeds;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author robby
 */
public class GeneticallyModifiedSeeds extends JFrame {

    public static final String[] SKULLS = {"Marathon", "Famine", "Bankrupt", "Purist", "Lightning", "Rock", "Swarm", "Toxin", "Final Destination", "Furor", "Wrath", "Undying", "Stone", "Endless", "High Tide", "Destiny", "Dearth"};
    public static GeneticallyModifiedSeeds instance;
    public static boolean updatingSkulls;
    public static Seed currentSeed;
    public static JTextField field;
    public static JPanel seedContainer;
    public static JPanel skullContainer;
    public static MCheckBox[] skullBoxes;

    public void update() {
        if (currentSeed != null) {
            field.setText(currentSeed.getSeed());
            System.out.println(currentSeed.getSeed());
            updatingSkulls = true;
            for (int i = 0; i < currentSeed.getSkulls().length; i++) {
                skullBoxes[i].setSelected(currentSeed.getSkulls()[i]);
            }
            updatingSkulls = false;
        }
        seedContainer.updateUI();
        skullContainer.updateUI();
    }

    public GeneticallyModifiedSeeds() {
        setLayout(new BorderLayout(5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 240);
        setLocation(400, 200);
        setTitle("Genetically Modified Seeds");

        seedContainer = new JPanel();
        add(seedContainer, BorderLayout.NORTH);

        field = new JTextField(15);
        seedContainer.add(field);

        JButton loadSeed = new JButton("Load Seed");
        loadSeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSeed = new Seed(field.getText());
                System.out.println(currentSeed.getSeed());
                update();
            }
        });
        seedContainer.add(loadSeed);
        seedContainer.updateUI();

        skullContainer = new JPanel();
        skullContainer.setLayout(new GridLayout(10, 2));
        add(skullContainer, BorderLayout.CENTER);

        for (int i = 0; i < SKULLS.length; i++) {
            skullBoxes[i] = new MCheckBox(SKULLS[i]);
            skullBoxes[i].index = i;
            skullBoxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(currentSeed != null && !updatingSkulls)
                    {
                        MCheckBox source = (MCheckBox) e.getSource();
                        boolean[] skulls = currentSeed.getSkulls();
                        skulls[source.index] = e.getStateChange() == ItemEvent.SELECTED;
                        currentSeed = currentSeed.getWithSkulls(skulls);
                        update();
                    }
                }
            });
            skullContainer.add(skullBoxes[i]);
        }

        update();
    }

    public static void main(String[] args) {
        skullBoxes = new MCheckBox[17];
        instance = new GeneticallyModifiedSeeds();
    }

}
