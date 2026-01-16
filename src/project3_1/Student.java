package project3_1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame implements ActionListener {
    private Container c;
    private JLabel titleL, rollL, lnL, phoneL, gpaL;
    private JTextField roll_Tf, lnTf, phoneTf, gpaTf;
    private JButton addBtn, updateBtn, deleteBtn, clearBtn, backBtn;
    private Font f;
    private JTable tbl;
    private DefaultTableModel model;
    private JScrollPane scroll;

    private String[] cols = {"Roll No", "Name", "Phone Number", "GPA"};
    private String[] rows = new String[4];
    private final String filePath = "students_data.txt"; // File to store data

    Student() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 780, 690);
        this.setTitle("Student Management System");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.LIGHT_GRAY);

        f = new Font("Arial", Font.BOLD, 16);

        // Labels and TextFields
        titleL = new JLabel("Student Registration");
        titleL.setFont(f);
        titleL.setBounds(140, 10, 250, 50);
        c.add(titleL);

        rollL = new JLabel("Enter Roll:");
        rollL.setBounds(10, 80, 140, 30);
        c.add(rollL);
        roll_Tf = new JTextField();
        roll_Tf.setBounds(110, 80, 200, 30);
        c.add(roll_Tf);

        lnL = new JLabel("Enter Name:");
        lnL.setBounds(10, 130, 150, 30);
        c.add(lnL);
        lnTf = new JTextField();
        lnTf.setBounds(110, 130, 200, 30);
        c.add(lnTf);

        phoneL = new JLabel("Phone Number:");
        phoneL.setBounds(10, 180, 150, 30);
        c.add(phoneL);
        phoneTf = new JTextField();
        phoneTf.setBounds(110, 180, 200, 30);
        c.add(phoneTf);

        gpaL = new JLabel("GPA:");
        gpaL.setBounds(10, 230, 150, 30);
        c.add(gpaL);
        gpaTf = new JTextField();
        gpaTf.setBounds(110, 230, 200, 30);
        c.add(gpaTf);

        // Buttons
        addBtn = new JButton("Add");
        addBtn.setBounds(400, 80, 100, 30);
        c.add(addBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(400, 130, 100, 30);
        c.add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(400, 180, 100, 30);
        c.add(deleteBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(400, 230, 100, 30);
        c.add(clearBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(400, 280, 100, 30);
        c.add(backBtn);

        // Table Setup
        tbl = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        tbl.setModel(model);
        tbl.setFont(f);
        tbl.setSelectionBackground(Color.GREEN);
        tbl.setBackground(Color.WHITE);
        tbl.setRowHeight(30);
        tbl.setAutoCreateRowSorter(true);

        scroll = new JScrollPane(tbl);
        scroll.setBounds(10, 360, 740, 265);
        c.add(scroll);

        // Listeners
        addBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        updateBtn.addActionListener(this);

        backBtn.addActionListener(e -> {
            dispose();
            // Assuming LogIn class exists as per your original code
            // LogIn frame = new LogIn();
            // frame.setVisible(true);
        });

        tbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int numOfRow = tbl.getSelectedRow();
                roll_Tf.setText(model.getValueAt(numOfRow, 0).toString());
                lnTf.setText(model.getValueAt(numOfRow, 1).toString());
                phoneTf.setText(model.getValueAt(numOfRow, 2).toString());
                gpaTf.setText(model.getValueAt(numOfRow, 3).toString());
            }
        });

        // Load existing data from file upon startup
        loadFromFile();
    }

    // Helper method to save all table data to a file
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) bw.write(","); // Use comma as delimiter
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + ex.getMessage());
        }
    }

    // Helper method to load data from file to the table
    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading file: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            rows[0] = roll_Tf.getText();
            rows[1] = lnTf.getText();
            rows[2] = phoneTf.getText();
            rows[3] = gpaTf.getText();
            model.addRow(rows);
            saveToFile(); // Save after adding
        } 
        else if (e.getSource() == clearBtn) {
            roll_Tf.setText("");
            lnTf.setText("");
            phoneTf.setText("");
            gpaTf.setText("");
        } 
        else if (e.getSource() == deleteBtn) {
            int numRow = tbl.getSelectedRow();
            if (numRow >= 0) {
                model.removeRow(numRow);
                saveToFile(); // Save after deleting
            } else {
                JOptionPane.showMessageDialog(null, "No Row has been selected.");
            }
        } 
        else if (e.getSource() == updateBtn) {
            int numberOfRow = tbl.getSelectedRow();
            if (numberOfRow >= 0) {
                model.setValueAt(roll_Tf.getText(), numberOfRow, 0);
                model.setValueAt(lnTf.getText(), numberOfRow, 1);
                model.setValueAt(phoneTf.getText(), numberOfRow, 2);
                model.setValueAt(gpaTf.getText(), numberOfRow, 3);
                saveToFile(); // Save after updating
            }
        }
    }

    public static void main(String[] args) {
        Student fr = new Student();
        fr.setVisible(true);
    }
}