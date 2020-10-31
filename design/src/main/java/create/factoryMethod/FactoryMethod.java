package create.factoryMethod;

/**
 * TODO
 *
 * @author wty
 * @date 2020/10/11 20:08
 */
public class FactoryMethod {
    public static void main(String[] args) {
        //客户要产品A
        Factory mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();

        //客户要产品B
        Factory mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
    }
}
abstract class Factory{
    public abstract Product Manufacture();
}
abstract class Product{
    public abstract void Show();
}
//具体产品A类
class  ProductA extends  Product{
    @Override
    public void Show() {
        System.out.println("生产出了产品A");
    }
}
//具体产品B类
class  ProductB extends  Product{

    @Override
    public void Show() {
        System.out.println("生产出了产品B");
    }
}
//工厂A类 - 生产A类产品
class  FactoryA extends Factory{
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}

//工厂B类 - 生产B类产品
class  FactoryB extends Factory{
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}