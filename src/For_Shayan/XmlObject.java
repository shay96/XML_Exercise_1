package For_Shayan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlObject {

    private String header;
    private Map<String, String> nameAttributePairs;
    private String texContent;
    private List<XmlObjectBuilder> children;

    private XmlObject(String header, Map<String, String> nameAttributePairs, String texContent, List<XmlObjectBuilder> children) {
        this.header = header;
        this.nameAttributePairs = nameAttributePairs;
        this.texContent = texContent;
        this.children = children;
    }

    // TODO implement this. Think about what fields this class would have and how to handle hierarchy. Hint: Child/Parent.

    public List<XmlObjectBuilder> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public Map<String, String> getNameAttributePairs() {
        return nameAttributePairs;
    }

    public String getTexContent() {
        return texContent;
    }



    @Override
    public String toString() {
        return "XmlObject{" +
                "header='" + getHeader() + '\'' +
                ", attrPairs=" + getNameAttributePairs() +
                ", textContent='" + getTexContent() + '\'' +
                '}';
    }

    public String getHeader() {
        return header;
    }

    public static class XmlObjectBuilder {

        private String header;
        private Map<String, String> nameAttributePairs;
        private String textContent;
        private final List<XmlObjectBuilder> children = new ArrayList<XmlObjectBuilder>();

        public XmlObjectBuilder setHeader(String header) {
            this.header = header;
            return this;
        }

        public XmlObjectBuilder setNameAttributePairs(Map<String, String> nameAttributePairs) {
            this.nameAttributePairs = nameAttributePairs;
            return this;
        }

        public XmlObjectBuilder addKiddies(XmlObjectBuilder childObjectToBeAdded) {
            children.add(childObjectToBeAdded);
            return this;
        }

        public XmlObjectBuilder setTexContent(String texContent) {
            this.textContent = textContent;
            return this;
        }

        public String getHeader() {
            return header;
        }

        public Map<String, String> getNameAttributePairs() {
            return nameAttributePairs;
        }

        public String getTextContent() {
            return textContent;
        }

        public List<XmlObjectBuilder> getChildren() {
            return children;
        }

        public XmlObject build() throws Exception{
            validate();
            return new XmlObject(header, nameAttributePairs, textContent, children);

        }

        private void validate() throws Exception {
            if (header == null) {
                throw new Exception("Header not found exception");
            }
        }

        @Override
        public String toString() {
            return header;
        }

        public String getHeader() {
            return header;
        }

        public Map<String, String> getAttributes() {
            return nameAttributePairs;
        }

        public String getText() {
            return textContent;
        }

        public List<XmlObjectBuilder> getChildren() {
            return children;
        }
    }
}
