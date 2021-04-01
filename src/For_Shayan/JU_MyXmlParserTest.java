package For_Shayan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

// MAKE THIS TEST WORK!!!

public class JU_MyXmlParserTest {

    private String catalogOfXmlToBePresented; // needs to find the file path
    private File testFileLiv; //
    private File testFileLivWithError;
    private File testFileDataAnimals;

    // in an interview-- give you a fourth file and now run program on that
    // don't only write a program that works for these two files PARSE ANY XML file


    @BeforeEach
    public void setUpClass() {
        this.catalogOfXmlToBePresented = "/Users/shayanaghaei/Documents/BahmanExercise/src/For_Shayan/";
        this.testFileLiv = new File(catalogOfXmlToBePresented + "Liv.xml");
        this.testFileLivWithError = new File(catalogOfXmlToBePresented + "LivWithErrors.xml");
        this.testFileDataAnimals = new File(catalogOfXmlToBePresented + "data_animals.xml");
    }

    @Test
    public void xmlParserTestLiv() {


        // TODO make this test compile and run.
        XMLParser xmlParser = new NewXmlParser();
        XmlObject myTopLevelXmlObject = xmlParser.parseFile(testFileLiv);
//
//        Presenter2 xmlPresenter;
//
//        xmlPresenter.display();
    }

    //TODO write tests for the other files data_animals.xml LivWithErrors.xml

    @Test
    public void xmlParserTestLivWithErrors(){
        XMLParser xmlParser = new NewXmlParser();
        XmlObject topLayer = xmlParser.parseFile(testFileLivWithError);
        if (topLayer != null){
            new Presenter2(topLayer).display();

        } else fail("Parsing Failed");
    }

    @Test
    public void xmlParserTestDataAnimals(){
        XMLParser xmlParser = new NewXmlParser();
        XmlObject topLevel = xmlParser.parseFile(testFileDataAnimals);
        if (topLevel != null){
            new Presenter2(topLevel).display();
        } else fail("Parsing failed");
    }
// write a new @ test for the other files
    // tests on intermediate objects.
// presenter interface
}
