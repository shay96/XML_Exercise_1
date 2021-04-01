package For_Shayan;

import org.junit.jupiter.api.BeforeAll;
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

    private XMLParser xmlParser;

    // in an interview-- give you a fourth file and now run program on that
    // don't only write a program that works for these two files PARSE ANY XML file


    @BeforeEach
    public void setUpClass() {
        this.catalogOfXmlToBePresented = "/Users/shayanaghaei/Documents/BahmanExercise/src/For_Shayan/";
        this.testFileLiv = new File(catalogOfXmlToBePresented + "Liv.xml");
        this.testFileLivWithError = new File(catalogOfXmlToBePresented + "LivWithErrors.xml");
        this.testFileDataAnimals = new File(catalogOfXmlToBePresented + "data_animals.xml");
    }
    void init(){
        xmlParser = new NewXmlParser();
    }

    @Test
    public void xmlParserTestLiv() {


        // TODO make this test compile and run.
        XmlObject myTopLevelXmlObject = xmlParser.parseFile(testFileLiv);
//
//        Presenter2 xmlPresenter;
//
//        xmlPresenter.display();
    }

    @Test
    public void xmlParserTestLivWithErrors(){
        XmlObject topLayer = xmlParser.parseFile(testFileLivWithError);
        if (topLayer != null){
            new Presenter2(topLayer).display();

        } else fail("Parsing Failed");
    }

    @Test
    public void xmlParserTestDataAnimals(){
        XmlObject topLevel = xmlParser.parseFile(testFileDataAnimals);
        if (topLevel != null){
            new Presenter2(topLevel).display();
        } else fail("Parsing failed");
    }
// write a new @ test for the other files
    // tests on intermediate objects.
// presenter interface
}
