package For_Shayan;

import java.util.HashMap;
import java.util.Map;

public class XmlPresenter {
    XmlObject object;

    XmlPresenter(XmlObject object) {
        this.object = object;
    }

    void build(XmlObject object) {
        object.getNameAttributePairs().forEach((key, value) -> generateAttribute(0, key, value));
    }


    private void generateAttribute(int depth, String key, String value) {
//        return "\t|".repeat(depth) +
//                "\u001b[32m" +
//                key +
//                "\u001b[0m" +
//                ": " +
//                "\u001b[34m" +
//                value +
//                "\u001b[0m";

        System.out.println("\t|".repeat(depth)
                + key + value);
    }
}







