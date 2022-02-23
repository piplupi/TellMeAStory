import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class Insert {

    public static void main(String[] args) {


        MyFrame f = new MyFrame();
        f.setVisible(true);

                //StoryElements story = new StoryElements("bottle", "bed");
//
//        //StoryElements story = new StoryElements("","");

        MyFrame frame = new MyFrame("","");

        try {
           // FileWriter myWriter = new FileWriter("C:\\Users\\sherany\\downloads\\Story");

            FileWriter myWriter = new FileWriter("C:\\Workspace\\UNASAT\\Blok2.1\\TellMeAStory");

            //myWriter.write(String.format("there was once a cursed %s in a %s", story.getObject(), story.getPlace()));

            //myWriter.write("Files in Java might be tricky!");

            myWriter.write(String.format("there was once a cursed %s in a %s", frame.getTxtObjectInput(), frame.getTxtPlaceInput()));

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//        StoryStarter beginStory = new StoryStarter(story);
//        System.out.println(beginStory.getElements());
    }

