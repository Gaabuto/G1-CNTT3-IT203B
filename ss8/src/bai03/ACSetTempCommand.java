package bai03;

public class ACSetTempCommand implements Command {
    private AirConditioner ac;
    private int newTemp;
    private int oldTemp;

    public ACSetTempCommand(AirConditioner ac, int temp) {
        this.ac = ac;
        this.newTemp = temp;
    }

    public void execute() {
        oldTemp = ac.getTemperature(); // lưu nhiệt độ cũ
        ac.setTemperature(newTemp);
    }

    public void undo() {
        ac.setTemperature(oldTemp);
        System.out.println("Undo: quay lại " + oldTemp);
    }
}
