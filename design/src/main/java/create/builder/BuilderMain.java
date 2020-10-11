package create.builder;

/**
 * TODO
 *
 * @author wty
 * @Date 2020/10/11 20:17
 */
public class BuilderMain {
    public static void main(String[] args) {
        Builder bld = new EconomicBuilder();
        Director director = new Director(bld);
        director.produceCar();
        Car car = director.getCar();
    }
}
interface CarPlan {
    void setWheel(String wheel);
    void setFrame(String frame);
    void setEngine(String engine);
    void setWidget(String widget);
}

class Car implements CarPlan {
    private String myWheel;
    private String myFrame;
    private String myEngine;
    private String myWidget;

    @Override
    public void setWheel(String wheel) {
        this.myWheel = wheel;
    }

    @Override
    public void setFrame(String frame) {
        this.myFrame = frame;
    }

    @Override
    public void setEngine(String engine) {
        this.myEngine = engine;
    }

    @Override
    public void setWidget(String widget) {
        this.myWidget = widget;
    }
}
interface Builder {
    void buildWheel();
    void buildFrame();
    void buildEngine();
    void buildWidget();
    Car getCar();
}
//一个廉价车生成器
class CheapBuilder implements Builder {
    Car cheapCar = new Car();
    @Override
    public Car getCar() {
        return cheapCar;
    }
    @Override
    public void buildWheel() {
        cheapCar.setWheel("cheap wheel");
    }
    @Override
    public void buildFrame() {
        cheapCar.setFrame("cheap frame");
    }
    @Override
    public void buildEngine() {
        cheapCar.setEngine("cheap engine");
    }
    @Override
    public void buildWidget() {
        cheapCar.setWidget("no widget");
    }
}
//一个平价车生成器
class EconomicBuilder implements Builder {
    Car economicCar = new Car();
    @Override
    public Car getCar() {
        return economicCar;
    }
    @Override
    public void buildWheel() {
        economicCar.setWheel("cheap wheel");
    }
    @Override
    public void buildFrame() {
        economicCar.setFrame("expensive frame");
    }
    @Override
    public void buildEngine() {
        economicCar.setEngine("cheap engine");
    }
    @Override
    public void buildWidget() {
        economicCar.setWidget("economic widget");
    }
}
class Director {
    private Builder builder;

    public Director(Builder bld) {
        builder = bld;
    }
    void produceCar() {
        //这里对步骤进行控制
        builder.buildFrame();
        builder.buildWidget();
        builder.buildWheel();
        builder.buildEngine();
    }
    Car getCar() {
        return builder.getCar();
    }
}
