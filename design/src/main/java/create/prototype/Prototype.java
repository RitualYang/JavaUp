package create.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型模式
 * 作用: 用原型实例指定创建对象的种类,并且通过拷贝这些原型创建新的对象
 *
 * @author wty
 * @date 2020/10/10 22:47
 */
public class Prototype {

    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key","value");
        FileCopy fileProtoType = new FileCopy("我是文件名", 1234, stringStringHashMap);
        FileCopy clone1 = (FileCopy)fileProtoType.clone();
        FileCopy clone2 = (FileCopy)fileProtoType.clone();
        clone1.show();
        clone2.show();
    }

}
@Data
@AllArgsConstructor
class File implements Cloneable {
    private String fileName;
    private Integer fileSize;
    private Map<String ,String> scores;

    @Override
    protected File clone() {
        File file = null;
        try {
            // 克隆基本类型
            file = (File) super.clone();
            // 克隆复杂类型
            file.scores = (Map<String, String>) ((HashMap)this.scores).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return file;
    }
}
class FileCopy extends File {
    public FileCopy(String fileName, Integer fileSize, Map<String, String> scores) {
        // 构造函数执行
        super(fileName, fileSize, scores);
    }

    public void show(){
        System.out.println("----文件信息----");
        System.out.println("文件名：" + getFileName() + " ,文件大小：" + getFileSize() + " ," + getScores());
    }

}
