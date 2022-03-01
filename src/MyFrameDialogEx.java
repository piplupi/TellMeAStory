import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class MyFrameDialogEx extends JFrame {

    private JButton aButton = new JButton("A");
    private JButton bButton = new JButton("B");
    private JButton cButton = new JButton("C");

    StoryButtonListener buttonListener = new StoryButtonListener();

    public MyFrameDialogEx() {
        setTitle("Dialogexample");
        setSize(200, 200);
        setLocation(new Point(300, 200));
        setLayout(null);
        setResizable(true);

        initComponent();
    }


    private void initComponent() {
        aButton.setBounds(50, 20, 80, 20);
        bButton.setBounds(50, 40, 80, 20);
        cButton.setBounds(50, 60, 80, 20);
        add(aButton);
        add(bButton);
        add(cButton);
        aButton.addActionListener(buttonListener);
        bButton.addActionListener(buttonListener);
        cButton.addActionListener(buttonListener);

    }

    class StoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            // Figure out which button was pressed
            if (sourceButton.equals(aButton)) {
                System.out.println("A button pressed");
                // create a dialog Box
                MyFrameDialogEx m = new MyFrameDialogEx();
                JDialog d = new JDialog(m, "dialog Box");

                // create a label
                JLabel l = new JLabel("this is a dialog box A");

                d.add(l);

                // setsize of dialog
                d.setSize(100, 100);

                // set visibility of dialog
                d.setVisible(true);


            } else if (sourceButton.equals(bButton)) {
                System.out.println("B button pressed");
                MyFrameDialogEx m = new MyFrameDialogEx();
                JDialog d = new JDialog(m, "dialog Box");

                // create a label
                JLabel l = new JLabel("this is a dialog box B");

                d.add(l);

                // setsize of dialog
                d.setSize(100, 100);

                // set visibility of dialog
                d.setVisible(true);

            } else if (sourceButton.equals(cButton)) {
                System.out.println("C button pressed");





            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }

}