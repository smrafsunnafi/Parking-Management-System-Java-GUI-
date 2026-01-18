import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FrameParking extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JTextField tfName, tfVno;
    private JComboBox<String> cbType;
    private JRadioButton rbHourly, rbDaily;
    private ButtonGroup bg;
    private JButton btSave, btExit;
    private JTextArea taNotes, taRecords;
    private JScrollPane scroll;
    private Font f1, f2;

    public FrameParking() {

        setTitle("Parking Management System");
        setBounds(300, 80, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(180, 220, 250));

        f1 = new Font("Cambria", Font.BOLD, 28);
        f2 = new Font("Cambria", Font.PLAIN, 18);

        label1 = new JLabel("Parking System");
        label1.setBounds(260, 10, 300, 40);
        label1.setFont(f1);
        label1.setForeground(Color.BLUE);
        panel.add(label1);

        label2 = new JLabel("Owner Name:");
        label2.setBounds(50, 80, 150, 30);
        label2.setFont(f2);
        panel.add(label2);

        tfName = new JTextField();
        tfName.setBounds(200, 80, 200, 30);
        tfName.setFont(f2);
        panel.add(tfName);

        label3 = new JLabel("Vehicle No:");
        label3.setBounds(50, 130, 150, 30);
        label3.setFont(f2);
        panel.add(label3);

        tfVno = new JTextField();
        tfVno.setBounds(200, 130, 200, 30);
        tfVno.setFont(f2);
        panel.add(tfVno);

        label4 = new JLabel("Vehicle Type:");
        label4.setBounds(50, 180, 150, 30);
        label4.setFont(f2);
        panel.add(label4);

        String[] types = {"Select", "Car", "Bike", "Truck"};
        cbType = new JComboBox<>(types);
        cbType.setBounds(200, 180, 200, 30);
        cbType.setFont(f2);
        panel.add(cbType);

        label5 = new JLabel("Parking Type:");
        label5.setBounds(50, 230, 150, 30);
        label5.setFont(f2);
        panel.add(label5);

        rbHourly = new JRadioButton("Hourly");
        rbHourly.setBounds(200, 230, 100, 30);
        rbHourly.setFont(f2);
        panel.add(rbHourly);

        rbDaily = new JRadioButton("Daily");
        rbDaily.setBounds(320, 230, 100, 30);
        rbDaily.setFont(f2);
        panel.add(rbDaily);

        bg = new ButtonGroup();
        bg.add(rbHourly);
        bg.add(rbDaily);

        label6 = new JLabel("Extra Notes:");
        label6.setBounds(50, 280, 150, 30);
        label6.setFont(f2);
        panel.add(label6);

        taNotes = new JTextArea();
        taNotes.setBounds(200, 280, 250, 100);
        taNotes.setFont(f2);
        panel.add(taNotes);

        btSave = new JButton("Save");
        btSave.setBounds(200, 410, 120, 30);
        btSave.setFont(f2);
        btSave.addActionListener(this);
        panel.add(btSave);

        btExit = new JButton("Exit");
        btExit.setBounds(340, 410, 120, 30);
        btExit.setFont(f2);
        btExit.addActionListener(this);
        panel.add(btExit);

        label7 = new JLabel("Parking Records");
        label7.setBounds(520, 80, 200, 30);
        label7.setFont(f2);
        panel.add(label7);

        taRecords = new JTextArea();
        taRecords.setFont(f2);
        taRecords.setEditable(false);

        scroll = new JScrollPane(taRecords);
        scroll.setBounds(480, 120, 280, 400);
        panel.add(scroll);

        add(panel);

        loadRecords();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btExit) {
            System.exit(0);
        }

        if (ae.getSource() == btSave) {

            String owner = tfName.getText().trim();
            String vno = tfVno.getText().trim();
            String type = cbType.getSelectedItem().toString();
            String parkType = rbHourly.isSelected() ? "Hourly" :
                              rbDaily.isSelected() ? "Daily" : "";
            String notes = taNotes.getText().trim();

            if (owner.isEmpty() || vno.isEmpty() || type.equals("Select") || parkType.isEmpty() || notes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all information!");
                return;
            }

            Parking p = new Parking(owner, vno, type, parkType, notes);
            p.insertValues();

            tfName.setText("");
            tfVno.setText("");
            cbType.setSelectedIndex(0);
            bg.clearSelection();
            taNotes.setText("");

            loadRecords();
        }
    }

    private void loadRecords() {
        taRecords.setText("");

        try {
            File file = new File("Data/parkingdata.txt");
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                taRecords.append(line + "\n");
            }

            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading records!");
        }
    }

    public static void main(String[] args) {
        new FrameParking();
    }
}
