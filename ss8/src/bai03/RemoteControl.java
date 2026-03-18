package bai03;

public class RemoteControl {
    private Command currentCommand;
    private Command lastCommand;

    public void setCommand(Command command) {
        currentCommand = command;
        System.out.println("Đã gán command!");
    }

    public void pressButton() {
        if (currentCommand != null) {
            currentCommand.execute();
            lastCommand = currentCommand;
        } else {
            System.out.println("Chưa có command!");
        }
    }

    public void undo() {
        if (lastCommand != null) {
            lastCommand.undo();
        } else {
            System.out.println("Không có gì để undo!");
        }
    }
}
