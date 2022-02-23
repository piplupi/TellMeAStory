import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class MyFrame extends JFrame {

    public String txtObjectInput;
    public String txtPlaceInput;

    //private JButton btnSubmit = new JButton("Submit");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton clearButton = new JButton("Clear");

    StoryButtonListener buttonListener = new StoryButtonListener();

    private JTextArea textArea = new JTextArea();

    private JTextField fileNameTextField = new JTextField();
//    private JTextField txtobject = new JTextField();
//    private JTextField txtplace = new JTextField();

    private JTextField getTxtObjectInput = new JTextField();
    private JTextField getTxtPlaceInput = new JTextField();


    private JLabel lblfile = new JLabel("File :");
    private JLabel lblobject = new JLabel("Object :");
    private JLabel lblplace = new JLabel("Place :");

    public MyFrame() {
        setTitle("Storymaker");
        setSize(400, 400);
        setLocation(new Point(300, 200));
        setLayout(null);
        setResizable(false);

        initComponent();
    }

    public MyFrame(String txtObjectInput, String txtPlaceInput) {
        this.txtObjectInput = txtObjectInput;
        this.txtPlaceInput = txtPlaceInput;
    }


    private void initComponent() {

        fileNameTextField.setBounds(100, 10, 100, 20);
        getTxtObjectInput.setBounds(100, 35, 100, 20);
        getTxtPlaceInput.setBounds(100, 65, 100, 20);

        lblfile.setBounds(20, 10, 100, 20);
        lblobject.setBounds(20, 35, 100, 20);
        lblplace.setBounds(20, 65, 100, 20);

        textArea.setBounds(0, 90, 400, 240);

        saveButton.setBounds(20, 340, 80, 20);
        loadButton.setBounds(120, 340, 80, 20);
        clearButton.setBounds(220, 340, 80, 20);

        add(lblfile);
        add(lblobject);
        add(lblplace);

        add(fileNameTextField);
        add(getTxtObjectInput);
        add(getTxtPlaceInput);

        add(textArea);

        add(saveButton);
        add(loadButton);
        add(clearButton);

        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
    }

    private String getFileName() {
        return fileNameTextField.getText();
    }

//    public String getTxtObjectInput() {return getTxtObjectInput.getText(); }
//
//    public void setTxtObjectResult(String o) {
//        lblobject.setText(o);
//    }
//
//    public String getTxtPlaceInput() {
//        return getTxtPlaceInput.getText();
//    }
//
//    public void setTxtPlaceResult(String p) {
//        lblplace.setText(p);
//    }


    public String getTxtObjectInput() {
        return txtObjectInput;
    }

    public void setTxtObjectResult(String txtObjectInput) {
        this.txtObjectInput = txtObjectInput;
    }

    public String getTxtPlaceInput() {
        return txtPlaceInput;
    }

    public void setTxtPlaceInput(String txtObjectInput) {
        this.txtPlaceInput = txtPlaceInput;
    }


    private void readFile(String fileName) {
        Scanner inFile = null;

        try {
            // file reader
            inFile = new Scanner(new FileReader(fileName));

            // clear the text area
            textArea.setText("");

            // copy file
            while (inFile.hasNextLine()) {
                textArea.append(inFile.nextLine());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
    }

    private void writeFile(String fileName) {
        PrintWriter outFile = null;

        try {
            // file writer
            outFile = new PrintWriter(new FileWriter(fileName));

            outFile.print(textArea.getText());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (outFile != null) {
                outFile.close();
            }
        }
    }

    class StoryButtonListener implements ActionListener {

        /**
         * This needs to be defined since this class implements the ActionListener interface
         *
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            // Figure out which button was pressed
            if (sourceButton.equals(loadButton)) {
                System.out.println("Load button pressed");
                readFile(getFileName());
            } else if (sourceButton.equals(saveButton)) {
                System.out.println("Save button pressed");
                writeFile(getFileName());
            } else if (sourceButton.equals(clearButton)) {
                System.out.println("Clear button pressed");

                // clear the text area
                textArea.setText("");
            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }
}