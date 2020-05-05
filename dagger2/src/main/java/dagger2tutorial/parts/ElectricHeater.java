package dagger2tutorial.parts;

public class ElectricHeater implements Heater {
    private boolean heating;

    @Override
    public void on() {
        System.out.println("~ ~ ~ heating ~ ~ ~");
        this.heating = true;
    }

    @Override
    public void off() {
        this.heating = false;
    }

    @Override
    public boolean isHot() {
        return heating;
    }
}