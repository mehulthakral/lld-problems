package printer;

public class OutputPrinter {

    public void printWithNewLine(String str) {
        System.out.println(str);
    }

    public void welcome() {
        printWithNewLine("Welcome to ParkingLot!");
    }

    public void end() {
        printWithNewLine("Thanks for visiting ParkingLot!");
    }

    public void fileNotFound() {
        printWithNewLine("Input File Not Found");
    }

    public void exception(String e) {
        printWithNewLine("Got Exception - " + e);
    }

    public void park(Integer slotNum) {
        printWithNewLine("Parked car at slot - " + slotNum);
    }

    public void noFreeSlotFound() {
        printWithNewLine("No free slot found to park");
    }
}
