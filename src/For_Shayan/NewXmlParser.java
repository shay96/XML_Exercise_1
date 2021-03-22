package For_Shayan;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewXmlParser implements XMLParser {
    // line helper
    private static final String META_DATA_HEADER = "<?";
    private static final String ANNOTATIONS = "<annotation";
    private static final String STARTER = "<";
    private static final String END_SAME_LINE = "/>";
    private static final String END = "</";

    // patterns & matchers
    private static final Pattern HEADER_PATTERN = Pattern.compile("<[a-zA-ZäöåÄÖÅ0-9]+", Pattern.CASE_INSENSITIVE);
    private static final Pattern ATTR_PATTERN = Pattern.compile("([a-zA-ZäöåÄÖÅ0-9]+)=(\\\"[a-zA-ZäöåÄÖÅ0-9-_ \\. ()]+\\\")", Pattern.CASE_INSENSITIVE);
    private static final Pattern END_TAG_PATTERN = Pattern.compile("<\\/|\\/>", Pattern.CASE_INSENSITIVE);
    private static final Pattern TEXT_CONTENT_PATTERN = Pattern.compile(">[a-zA-ZäöåÄÖÅ0-9]+(.*)", Pattern.CASE_INSENSITIVE);

    // colours for easy viewing
    public static final String RESET = "\u001b[0m";
    public static final String RED = "\u001b[31m";
    public static final String BLUE = "\u001b[34m";
    public static final String GREEN = "\u001b[32m";

    @Override
    public XmlObject parseFile(File file) {
        try {

            Scanner scanner = new Scanner(file);

            //      create variables:
            Stack<XmlObject.XmlObjectBuilder> unclosedXmlObjects = new Stack<>();
            XmlObject.XmlObjectBuilder nodeToBeReturned = new XmlObject.XmlObjectBuilder();
            String headerString = "";
            String textContent = "";
            Map<String, String> mapAttributes = new HashMap<>(100);
            List<XmlObject.XmlObjectBuilder> arrayOfObjects = new ArrayList<>();


//            Create logic:
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();


                //         create matchers:
                Matcher headerMatcher = HEADER_PATTERN.matcher(line);
                Matcher attrMatcher = ATTR_PATTERN.matcher(line);
                Matcher endTagMatcher = END_TAG_PATTERN.matcher(line); // may not need this
                Matcher textContentMatcher = TEXT_CONTENT_PATTERN.matcher(line);

                // skip unnecessary lines
                if (line.isEmpty()){
                    continue;
                }
                if (line.contains(META_DATA_HEADER) || line.contains(ANNOTATIONS)) {
                    // not important therefore skip!
                    System.out.println("Not important skipping");
                    continue;
                }

//          print line to match to double check
                System.out.println("PARSING: " + line);

                // Find the data
                if (headerMatcher.find()) {
                    headerString = headerMatcher.group();
                    System.out.println(GREEN + "header = " + headerString + RESET);
                    nodeToBeReturned.setHeader(headerString);
                }
                if (textContentMatcher.find()) {
                    textContent = textContentMatcher.group();
                    System.out.println("text = " + textContent);
                    nodeToBeReturned.setTexContent(textContent);
                }

                while (attrMatcher.find()) { //TODO could be a better way? SA.
                    mapAttributes.put(attrMatcher.group(1), attrMatcher.group(2));
                    nodeToBeReturned.setNameAttributePairs(mapAttributes);
                    System.out.println(BLUE + mapAttributes.toString() + RESET);
                }

                // TODO find out whether I need to build before I add them? SA
                // figure out whether node has children or not
                if (line.contains(STARTER) && line.contains(END_SAME_LINE)) {
                    // line is complete and has no children. Build and add to parent node
                    System.out.println(RED + "no children, add to parent" + RESET);
                    System.out.println("RETURN OBJECT");
                    unclosedXmlObjects.peek().addKiddies(nodeToBeReturned);
                } else if (line.contains(END) && !headerMatcher.find()) {
                    // close the top stack
                    System.out.println(RED + "Close the top stack" + RESET);
                    unclosedXmlObjects.pop();
                } else if (line.contains(STARTER) && unclosedXmlObjects.isEmpty()) {
                    // add to stack straight away
                    nodeToBeReturned.build();
                    unclosedXmlObjects.push(nodeToBeReturned);
                    System.out.println(RED + "add to stack" + RESET);
                } else {
                    // line has children and must be added to stack
                    // must add to parent first and then add to stack
                    System.out.println(RED + "Adding to parent then stack" + RESET);
                    unclosedXmlObjects.peek().addKiddies(nodeToBeReturned);
                    unclosedXmlObjects.push(nodeToBeReturned);
                }

                // once you reach the last line:
                if (!scanner.hasNextLine()) {
                    System.out.println("No new line. Line now is: " + line);
                    System.out.println("Checking if unclosed objects is empty");
                    try {
                        assert unclosedXmlObjects.isEmpty();
                    } catch (AssertionError e) {
                        System.out.println("ISSUE WITH XML OBJECT PLEASE CHECK FORMATTING");
                    }
                    // possibly create top layer and return this to use in the presenter?

                }

                // reset the nodeToBeReturned so that it can be used again
                nodeToBeReturned = new XmlObject.XmlObjectBuilder();
                // reset the map
                mapAttributes.clear();
                System.out.println(RED + "AT THE END OF THE LINE THE UNCLOSED OBJECTS ARE:" + RESET);
                System.out.println(unclosedXmlObjects.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("ERROR NO LINE FOUND. CHECK XML FORMAT");
        }


//        nodeToBeReturned;
//        loop over lines;
//        get header;
//        put header in header stack.
//        get attributes;
//        get text;
//        set all of the above on nodeToBe returned;
//
//        get closing header
//        from stack, i.e the top one;
//        if closing header = header;
//            build nodeToBeReturned and return it;
//        else
//            start child node
//            add child node to parent
//            do the above for next line;
        return null;
    }

//    private XmlObject buildNode(XmlObject.XmlObjectBuilder parent) {
//        // take in a builder and return an Xml object
//        return null;
//    }

}