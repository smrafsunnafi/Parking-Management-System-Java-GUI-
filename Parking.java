import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Parking {

    private String owner;
    private String vehicleNo;
    private String vehicleType;
    private String parkingType;
    private String notes;

    public Parking() {}

    public Parking(String owner, String vehicleNo, String vehicleType,
                   String parkingType, String notes) {
        this.owner = owner;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.parkingType = parkingType;
        this.notes = notes;
    }

    public void insertValues() {
        try {
            File file = new File("Data/parkingdata.txt");

            // Create folder if not exists
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            FileWriter fwriter = new FileWriter(file, true);
            fwriter.write("===================================\n");
            fwriter.write("Owner        : " + owner + "\n");
            fwriter.write("Vehicle No   : " + vehicleNo + "\n");
            fwriter.write("Vehicle Type : " + vehicleType + "\n");
            fwriter.write("Parking Type : " + parkingType + "\n");
            fwriter.write("Notes        : " + notes + "\n");
            fwriter.write("===================================\n\n");

            fwriter.close();

            JOptionPane.showMessageDialog(null, "Parking record saved successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while saving record!");
            e.printStackTrace();
        }
    }
}
