import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
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

import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyFrame extends JFrame {

    public String txtObjectInput;
    public String txtPlaceInput;

    //private JButton btnSubmit = new JButton("Submit");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton clearButton = new JButton("Clear");
    private JButton replaceButton = new JButton("Edit");

    StoryButtonListener buttonListener = new StoryButtonListener();

//    ReplaceDialogButtonListener replaceListener = new ReplaceDialogButtonListener();

    private JTextArea textArea = new JTextArea();

    private JTextField fileNameTextField = new JTextField();
//    private JTextField txtobject = new JTextField();
//    private JTextField txtplace = new JTextField();

    private JTextField getTxtObjectInput = new JTextField();
    private JTextField getTxtPlaceInput = new JTextField();


    private JLabel lblfile = new JLabel("File :");
    private JLabel lblobject = new JLabel("Object :");
    private JLabel lblplace = new JLabel("Place :");

    private JLabel lblinstr = new JLabel("Enter Story input, then save");
    private Object MyFrame;

    public MyFrame() {
        setTitle("Storymaker");
        setSize(500, 400);
        setLocation(new Point(300, 200));
        setLayout(null);
        setResizable(true);

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

        lblinstr.setBounds(210, 35, 200, 20);

        textArea.setBounds(0, 90, 800, 240);

        saveButton.setBounds(20, 340, 80, 20);
        loadButton.setBounds(120, 340, 80, 20);
        clearButton.setBounds(220, 340, 80, 20);
        replaceButton.setBounds(320, 340, 80, 20);


        add(lblfile);
        add(lblobject);
        add(lblplace);

        add(lblinstr);

        add(fileNameTextField);
        add(getTxtObjectInput);
        add(getTxtPlaceInput);

        add(textArea);

        add(saveButton);
        add(loadButton);
        add(clearButton);
        add(replaceButton);

        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
        replaceButton.addActionListener(buttonListener);

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
                textArea.setText("");

//            } else if (sourceButton.equals(replaceButton)) {
//                System.out.println("Replace button pressed");
//                //replace method
////                JButton replaceButton = new JButton("text");
////                replaceButton.addActionListener(new ActionListener() {
////                    public void actionPerformed(ActionEvent e) {
////                        new Replace();
////                    }        add(lblinstr);
////                private JButton replaceButton = new JButton("Replace");
////                replaceButton.addActionListener(new ActionListener() {
////                    public void actionPerformed(ActionEvent e) {
//////                        new Replace();
////                        });
//////
////                    ReplaceDialog example = new ReplaceDialog(MyFrame, );
////                    example.setVisible(true);
//                replaceButton.addActionListener(this);
//                add(replaceButton);
//                new Find(textArea);
            } else if (sourceButton.equals(replaceButton)) {
                System.out.println("Replace button pressed");

                MyFrame m = new MyFrame();

                JDialog a = new JDialog(m, "Replace");

                // create a label

                // setsize of dialog
                a.setBounds(100, 100, 421, 247);
                a.setLocationRelativeTo(m);
//                a.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                a.getContentPane().setLayout(null);

                //all j components
                JPanel contentPanel = new JPanel();
                JTextField findField = new JTextField();
                JTextField replaceField = new JTextField();
                JButton findNextButton = new JButton("Find Next");
                JCheckBox wrapAroundButton = new JCheckBox("Wrap around");
                JButton replaceAllButton = new JButton("ReplaceB");
                JButton cancelButton = new JButton("Cancel");

                contentPanel.setBounds(0, 0, 405, 208);
                a.getContentPane().add(contentPanel);
                contentPanel.setLayout(null);

                findField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(findField.getText() != null) {
                            findNextButton.setEnabled(true);
                            replaceButton.setEnabled(true);
                            replaceAllButton.setEnabled(true);
                        }else {
                            findNextButton.setEnabled(false);
                            replaceButton.setEnabled(false);
                            replaceAllButton.setEnabled(false);
                        }
                    }
                });

                findField.setBounds(82, 21, 220, 30);
                contentPanel.add(findField);
                findField.setColumns(10);

                JLabel findFieldLabel = new JLabel("Find what:");
                findFieldLabel.setLabelFor(findField);
                findFieldLabel.setBounds(10, 20, 62, 30);
                contentPanel.add(findFieldLabel);


                //replace field
                replaceField.setColumns(10);
                replaceField.setBounds(82, 55, 220, 30);
                contentPanel.add(replaceField);

                JLabel replaceFieldLabel = new JLabel("Replace with:");
                replaceFieldLabel.setLabelFor(replaceField);
                replaceFieldLabel.setBounds(10, 55, 72, 30);
                contentPanel.add(replaceFieldLabel);


                //cancel button
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        a.dispose();
                    }
                });
                cancelButton.setBounds(306, 125, 89, 30);
                contentPanel.add(cancelButton);

                //wordwrap button
                wrapAroundButton.setBounds(6, 178, 117, 23);
                contentPanel.add(wrapAroundButton);

//                //replace button
//                replaceButton.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        int currentposition;
//                        String pattern = findField.getText();
//                        String editorText=textArea.getText();
//                        String replaceText = replaceField.getText();
//
//                        Pattern p = Pattern.compile(pattern);
//                        //when wrap around button is selected :: round again and again if pattern is there in the editor text
//                        if(wrapAroundButton.isSelected()) {
//                            try {
//                                Matcher m = p.matcher(editorText);
//                                if(!m.find()) {
//                                    JOptionPane.showMessageDialog(a, "Cannot find '"+pattern+"'","Notepad",JOptionPane.INFORMATION_MESSAGE);
//                                }else {
//                                    //if selected replace it otherwise select next
//                                    if(a.getSelectedText() != null) {
//                                        a.replaceSelection(replaceText);
//                                    }
//                                    editorText = a.getText();
//                                    currentposition = a.getCaretPosition();
//
//                                    m=p.matcher(editorText);
//                                    if(m.find(currentposition)) {
//                                        textArea.setCaretPosition(m.end());
//                                        textArea.select(m.start(),m.end());
//                                    }else {
//                                        //if not find again search from starting position  (0)
//                                        m=p.matcher(editorText);
//                                        if(m.find(0)) {
//                                            textArea.setCaretPosition(m.end());
//                                            textArea.select(m.start(),m.end());
//                                        }
//                                    }
//                                }
//                            }catch(Exception exp) {
//                                JOptionPane.showMessageDialog(a, "Error :find");
//                            }
//                            //when wrap button is not selected
//                        }else {
//                            try {
//                                //if selected replace it otherwise select next
//                                if(textArea.getSelectedText() != null) {
//                                    textArea.replaceSelection(replaceText);
//                                }
//                                editorText = textArea.getText();
//                                currentposition = textArea.getCaretPosition();
//                                Matcher m = p.matcher(editorText);
//                                if(m.find(currentposition)) {
//                                    textArea.setCaretPosition(m.end());
//                                    textArea.select(m.start(),m.end());
//                                }else {
//                                    JOptionPane.showMessageDialog(a, "Cannot find '"+pattern+"'","Notepad",JOptionPane.INFORMATION_MESSAGE);
//                                }
//                            }catch(Exception exp) {
//                                JOptionPane.showMessageDialog(a, "Error :find");
//                            }
//                        }
//
//                    }
//                });
                replaceButton.setEnabled(false);
                replaceButton.setBounds(306, 55, 89, 30);
                contentPanel.add(replaceButton);


                //replace button
                replaceAllButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String pattern = findField.getText();
                        String repalceText = replaceField.getText();
                        Pattern p = Pattern.compile(pattern);
                        Matcher m = p.matcher(textArea.getText());
                        //start from starting and replace all matching with replace Text
                        int currentposition = 0;
                        while(m.find(currentposition)) {
                            textArea.select(m.start(),m.end());
                            textArea.replaceSelection(repalceText);
                            //get position and matcher of new content
                            currentposition = textArea.getCaretPosition();
                            m = p.matcher(textArea.getText());
                        }
                        //set caret position at start
                        textArea.setCaretPosition(0);
                    }
                });
                replaceAllButton.setEnabled(false);
                replaceAllButton.setBounds(306, 90, 89, 30);
                contentPanel.add(replaceAllButton);


                // set visibility of dialog
                a.setVisible(true);

            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }

}
//
//    //find option in edit menu
//    JMenuItem findEdit = new JMenuItem("Find...");
//		findEdit.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            new FindDialog(frmNotepad,editorPane);
//        }
//    });
//		findEdit.setEnabled(false);
//		findEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
//		editMenu.add(findEdit);
//
//    //replace option in edit menu
//    JMenuItem replaceEdit = new JMenuItem("Replace...");


//		replaceButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            new ReplaceDialog(frmNotepad,editorPane);
//        }
//    });
//		replaceEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//		replaceEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
//		editMenu.add(replaceEdit);

//            class ReplaceButtonListener implements ActionListener {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            }
//
//        }


//      public replaceButton() {
//            setDefaultCloseOperation(EXIT_ON_CLOSE);
//            setSize(100, 100);
//            setLocation(100, 100);
//
//            JButton button1 = new JButton("button1");
//            button1.addActionListener(this);
//            add(button1);
//
//            setVisible(true);
//        }
//    private JButton replaceButton = new JButton("Replace");


//        replaceButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e){
//                Replace();
//            }
//        });
