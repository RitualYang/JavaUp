package create.abstractFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author wty
 * @Date 2020/10/11 20:23
 */
public class AbstractFactoryMain {
    public static void main(String[] args) throws IOException {
        // 创建AbstractFactory，实际类型是FastFactory:
        AbstractFactory factory = new FastFactory();
// 生成Html文档:
        HtmlDocument html = factory.createHtml("#Hello\nHello, world!");
        html.save(Paths.get(".", "fast.html"));
// 生成Word文档:
        WordDocument word = factory.createWord("#Hello\nHello, world!");
        word.save(Paths.get(".", "fast.doc"));
    }
}
interface AbstractFactory {
    // 创建Html文档:
    HtmlDocument createHtml(String md);
    // 创建Word文档:
    WordDocument createWord(String md);
}
// Html文档接口:
interface HtmlDocument {
    String toHtml();
    void save(Path path) throws IOException;
}

// Word文档接口:
interface WordDocument {
    void save(Path path) throws IOException;
}
class FastHtmlDocument implements HtmlDocument {
    public FastHtmlDocument(String md) {

    }

    @Override
    public String toHtml() {
        return null;
    }

    @Override
    public void save(Path path) throws IOException {

    }
}

class FastWordDocument implements WordDocument {
    public FastWordDocument(String md) {

    }

    @Override
    public void save(Path path) throws IOException {

    }
}
class FastFactory implements AbstractFactory {
    @Override
    public HtmlDocument createHtml(String md) {
        return new FastHtmlDocument(md);
    }
    @Override
    public WordDocument createWord(String md) {
        return new FastWordDocument(md);
    }
}
