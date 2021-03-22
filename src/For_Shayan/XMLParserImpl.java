package For_Shayan;

import For_Shayan.XmlObject.XmlObjectBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParserImpl implements XMLParser {
    private static final String META_DATA_HEADER = "<?";
    private static final String ANNOTATIONS = "<annotation";
    private static final String STARTER = "<";
    private static final String END_EMPTY = "/>";
    private static final String END = "</";
    private static final String ASSIGNMENT = "=";

    // pattern finders
    private static final Pattern HEADER_PATTERN = Pattern.compile("<[a-zA-ZäöåÄÖÅ0-9]+", Pattern.CASE_INSENSITIVE);
    private static final Pattern ATTR_PATTERN = Pattern.compile("([a-zA-ZäöåÄÖÅ0-9]+)=(\\\"[a-zA-ZäöåÄÖÅ0-9-_ ]+\\\")", Pattern.CASE_INSENSITIVE);
    private static final Pattern END_TAG_PATTERN = Pattern.compile("<(?:[^>\"']|\"[^\"]\"|'[^']')*>", Pattern.CASE_INSENSITIVE);
    private static final Pattern TEXT_CONTENT_PATTERN = Pattern.compile(">[a-zA-ZäöåÄÖÅ0-9]+(.*)", Pattern.CASE_INSENSITIVE);


    public XmlObject parseFile(File file) {

        XmlObject topLayer = null;
        try {
            Scanner scanner = new Scanner(file);
            Stack<XmlObjectBuilder> unclosedHeadersStack = new Stack<>();

            // creating variables
            String headerString = "";
            String textContent = "";
            Boolean shouldRun = true;

            // Starting project
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // creating matchers
                    Matcher headerMatcher = HEADER_PATTERN.matcher(line);
                    Matcher attrMatcher = ATTR_PATTERN.matcher(line);
                    Matcher endTagMatcher = END_TAG_PATTERN.matcher(line); // may not need this
                    Matcher textContentMatcher = TEXT_CONTENT_PATTERN.matcher(line);

                // node to be returned
                XmlObjectBuilder nodeToBeReturned = new XmlObjectBuilder();

                // skip copyright and annotations
                if (line.contains(META_DATA_HEADER) || line.contains(ANNOTATIONS)) {
                    continue;
                }

                if(headerMatcher.find()) {
                    headerString = headerMatcher.group();
                    nodeToBeReturned.setHeader(headerString);




                    // this is a header opening
                    // add header to headersToBeClosed
                    // make new builder
                    // set all the things.
                    // if stack is empty put this guy in the stack
                    // if stack is not empty
                    // get the last dude from the stack
                    // set this guy as last dudes child
                    // then add this guy to stack.
                } else if(headerMatcher.find() && endTagMatcher.find()) {
                    // no children build node and add it to parent.
                } else if (endTagMatcher.find() && !headerMatcher.find()) {
                    // pop the last header from the stack
                    // make sure its header the same as endTagHeader
                    // add it to parent.`
                }

                // clean code


            } // end of while scanner has next



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    return null;
    } // END OF PARSE FILE
} // END OF XMLParserIMpl

//        return childrenObjects;

//        xmlObj = <H n1=a1 n2=a2> content </H>;
//        content = text + xmlObj;
//
//        xmlObj = <H n1=a1 n2=a2 />
