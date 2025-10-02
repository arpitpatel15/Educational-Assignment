// Prototype Interface
interface Prototype extends Cloneable {
    Prototype clone();
}

class Document implements Prototype {
    private String content;

    public Document(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void show() {
        System.out.println("Document Content: " + content);
    }

    @Override
    public Prototype clone() {
        return new Document(this.content); 
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        Document doc1 = new Document("Original Document");
        doc1.show();

        Document doc2 = (Document) doc1.clone();
        doc2.show();

        doc2.setContent("Cloned Document Modified");
        doc2.show();

        doc1.show();
    }
}
