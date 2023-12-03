class Car {
    // Methods implementation and class/Instance members
    private String color;
    private int maxSpeed;
    public void carInfo(){
        System.out.println("Car Color= "+color + " Max Speed= " + maxSpeed);
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
class Engine {
    public void start(){
        System.out.println("Engine Started:");
    }
    public void stop(){
        System.out.println("Engine Stopped:");
    }
}
class Maruti extends Car{
    //Maruti extends Car and thus inherits all methods from Car (except final and static)
    //Maruti can also define all its specific functionality
    public void MarutiStartDemo(){
        Engine MarutiEngine = new Engine();
        MarutiEngine.start();
    }
}
public class isHas {
    public static void main(String[] args) {
        Maruti myMaruti = new Maruti();
        myMaruti.setColor("RED");
        myMaruti.setMaxSpeed(180);
        myMaruti.carInfo();
        myMaruti.MarutiStartDemo();
    }
}
