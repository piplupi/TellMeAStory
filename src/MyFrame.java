import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyFrame extends JFrame {
    private JButton saveButton = new JButton("Save");
    private JButton clearButton = new JButton("Clear");
    private JButton findFileButton = new JButton("Find file");

    StoryButtonListener buttonListener = new StoryButtonListener();

    private JTextArea textArea = new JTextArea();
    private JTextField fileNameTextField = new JTextField();

    private JLabel lblinstr = new JLabel("Text content gets displayed below");

    public MyFrame() {
        setTitle("Storymaker");
        setSize(700, 400);
        setLocation(new Point(300, 200));
        setLayout(null);
        setResizable(true);

        initComponent();
    }

    private void initComponent() {

        textArea.setBounds(0, 90, 700, 240);
        lblinstr.setBounds(240, 55, 200, 20);

        saveButton.setBounds(20, 340, 80, 20);
        clearButton.setBounds(220, 340, 80, 20);
        findFileButton.setBounds(20, 10, 100, 20);

        add(textArea);
        add(saveButton);
        add(clearButton);
        add(findFileButton);
        add(lblinstr);


        saveButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
        findFileButton.addActionListener(buttonListener);
    }

    private String getFileName() {
        return fileNameTextField.getText();
    }


    /**
     * Finf specific text file
     *
     * @param fileName
     */
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


    /**
     * Write to the text file
     *
     * @param fileName
     */
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
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            if (sourceButton.equals(findFileButton)) {
                System.out.println("Find file button pressed");
                MyFrame m = new MyFrame();

                JDialog f = new JDialog(m, "Find File");

                // setsize of dialog
                f.setBounds(100, 100, 421, 200);
                f.setLocationRelativeTo(m);
                f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                f.getContentPane().setLayout(null);

                //all j components
                JPanel contentPanel = new JPanel();
                JButton findButton = new JButton("Find");
                JButton replaceButton = new JButton("Replace");
                JButton cancelButton = new JButton("Cancel");

                contentPanel.setBounds(0, 0, 405, 208);
                f.getContentPane().add(contentPanel);
                contentPanel.setLayout(null);

                fileNameTextField.setBounds(82, 21, 220, 30);
                contentPanel.add(fileNameTextField);
                fileNameTextField.setColumns(10);

                findButton.setBounds(200, 125, 89, 30);
                contentPanel.add(findButton);

                cancelButton.setBounds(306, 125, 89, 30);
                contentPanel.add(cancelButton);

                replaceButton.setBounds(200, 90, 89, 30);
                contentPanel.add(replaceButton);


                //cancel button
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });

                //find button
                findButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Find button pressed");
                        readFile(getFileName());

                    }
                });

                // replace text button
                replaceButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Replace button pressed");

                        MyFrame m = new MyFrame();

                        JDialog a = new JDialog(m, "Replace what?");

                        // setsize of dialog
                        a.setBounds(100, 100, 500, 500);
                        a.setLocationRelativeTo(m);
                        a.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        a.getContentPane().setLayout(null);

                        //all j components
                        JPanel contentPanel = new JPanel();

                        JTextField findA = new JTextField();
                        JTextField replaceA = new JTextField();

                        JTextField findB = new JTextField();
                        JTextField replaceB = new JTextField();

                        JButton findNextButton = new JButton("Find Next");
                        JLabel instrLabel = new JLabel("Find and replace country first and then the object");
                        JButton replaceAllButton = new JButton("Replace");

                        JButton cancelButton = new JButton("Cancel");

                        contentPanel.setBounds(0, 0, 505, 208);
                        a.getContentPane().add(contentPanel);
                        contentPanel.setLayout(null);

                        findA.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                if (findA.getText() != null) {
                                    findNextButton.setEnabled(true);
                                    replaceAllButton.setEnabled(true);
                                } else {
                                    findNextButton.setEnabled(false);
                                    replaceAllButton.setEnabled(false);
                                }
                            }
                        });

                        findB.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                if (findB.getText() != null) {
                                    findNextButton.setEnabled(true);
                                    replaceAllButton.setEnabled(true);
                                } else {
                                    findNextButton.setEnabled(false);
                                    replaceAllButton.setEnabled(false);
                                }
                            }
                        });

                        //find Object field
                        findA.setBounds(100, 21, 220, 30);
                        contentPanel.add(findA);
                        findA.setColumns(10);

                        JLabel findObjectLabel = new JLabel("Find:");
                        findObjectLabel.setLabelFor(findA);
                        findObjectLabel.setBounds(10, 20, 100, 30);
                        contentPanel.add(findObjectLabel);

                        //replace Object field
                        replaceA.setColumns(10);
                        replaceA.setBounds(100, 55, 220, 30);
                        contentPanel.add(replaceA);

                        JLabel replaceObjectLabel = new JLabel("Replace with:");
                        replaceObjectLabel.setLabelFor(replaceA);
                        replaceObjectLabel.setBounds(10, 55, 100, 30);
                        contentPanel.add(replaceObjectLabel);

                        //find Place field
                        findB.setBounds(100, 100, 220, 30);
                        contentPanel.add(findB);
                        findB.setColumns(10);

                        JLabel findPlaceLabel = new JLabel("Find:");
                        findPlaceLabel.setLabelFor(findB);
                        findPlaceLabel.setBounds(10, 100, 100, 30);
                        contentPanel.add(findPlaceLabel);

                        //replace Place field
                        replaceB.setColumns(10);
                        replaceB.setBounds(100, 134, 220, 30);
                        contentPanel.add(replaceB);

                        JLabel replacePlaceLabel = new JLabel("Replace with:");
                        replacePlaceLabel.setLabelFor(replaceB);
                        replacePlaceLabel.setBounds(10, 134, 100, 30);
                        contentPanel.add(replacePlaceLabel);


                        //cancel button
                        cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                a.dispose();
                            }
                        });
                        cancelButton.setBounds(340, 125, 89, 30);
                        contentPanel.add(cancelButton);

                        instrLabel.setBounds(6, 178, 300, 23);
                        contentPanel.add(instrLabel);

                        //replace button
                        replaceAllButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String patternA = findA.getText();
                                String replaceTextA = replaceA.getText();

                                String patternB = findB.getText();
                                String replaceTextB = replaceB.getText();


                                Pattern pA = Pattern.compile(patternA);
                                Matcher mA = pA.matcher(textArea.getText());

                                Pattern pB = Pattern.compile(patternB);
                                Matcher mB = pB.matcher(textArea.getText());


                                int currentposition = 0;
                                while (mA.find(currentposition) && mB.find(currentposition)) {
                                    textArea.select(mA.start(), mA.end());
                                    textArea.replaceSelection(replaceTextA);

                                    textArea.select(mB.start(), mB.end());
                                    textArea.replaceSelection(replaceTextB);

                                    //get position and matcher of new content
                                    currentposition = textArea.getCaretPosition();
                                    mA = pA.matcher(textArea.getText());

                                    currentposition = textArea.getCaretPosition();
                                    mB = pB.matcher(textArea.getText());


                                }
                                //set caret position at start
                                textArea.setCaretPosition(0);

                            }
                        });


                        replaceAllButton.setEnabled(false);
                        replaceAllButton.setBounds(340, 90, 89, 30);
                        contentPanel.add(replaceAllButton);


                        a.setVisible(true);


                    }
                });

                f.setVisible(true);


            } else if (sourceButton.equals(saveButton)) {
                System.out.println("Save button pressed");
                writeFile(getFileName());

            } else if (sourceButton.equals(clearButton)) {
                System.out.println("Clear button pressed");
                textArea.setText("");

            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }

}