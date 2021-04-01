package For_Shayan;

public class Presenter2 {
    private XmlObject object;

    Presenter2(XmlObject object) {
        this.object = object;
    }

    public void display() {
        // presentation string
        StringBuilder output = new StringBuilder("\n");
        // recursively go through children and build presentation string
        build(output, 0, object);
        // print out the display
        System.out.println(output.toString());
    }

    // building the output string with all the content
    private void build(StringBuilder output, int depth, XmlObject object) {
        output.append("\n");
        output.append(generateHeader(depth, object.getHeader())).append("\n");
        // check to see if there are attributes
        if (object.getNameAttributePairs() != null) {
            object.getNameAttributePairs().forEach((key, value) -> output.append(generateAttribute(depth, key,
                    value)).append("\n"));
        }
        // check for text content
        if (object.getTexContent() != null) {
            output.append(generateText(depth, object.getTexContent()));
        }
        // repeat for each child.
        object.getChildren().forEach(child -> {
            try {
                build(output, depth + 1, child.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String generateHeader(int depth, String text) {
        return "\t|".repeat(depth) +
                "\u001b[31m" +
                text +
                "\u001b[0m";
    }

    private String generateAttribute(int depth, String key, String value) {
        return "\t|".repeat(depth) +
                "\u001b[32m" +
                key +
                "\u001b[0m" +
                ": " +
                "\u001b[34m" +
                value +
                "\u001b[0m";
    }

    private String generateText(int depth, String text) {
        StringBuilder output = new StringBuilder();
        output.append("\t|".repeat(depth))
                .append("\u001b[35m")
                .append(text)
                .append("\u001b[0m");
        return output.toString();
    }
}

