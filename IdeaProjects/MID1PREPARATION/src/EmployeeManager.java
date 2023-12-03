//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//
//public class PerformanceManagementSys {
//    public static List<String[]> readCSV(String filePath) throws Exception {
//        List<String[]> data = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                data.add(line.split(","));
//            }
//        }
//        return data;
//    }
//    private JFrame frame;
//    private JTable employeeTable, projectTable;
//    private DefaultTableModel employeeModel, projectModel;
//    private JTextArea reviewArea;
//
//    public PerformanceManagementSys() throws Exception {
//        frame = new JFrame("Performance Review Management System");
//        frame.setSize(1000, 800);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new GridLayout(3, 1));
//
//        // Read data
//        List<String[]> employeeData = readCSV("employee.csv");
//        List<String[]> projectData = readCSV("project.csv");
//
//        // Initialize tables
//        employeeModel = new DefaultTableModel(employeeData.toArray(new String[0][]), employeeData.get(0));
//        employeeTable = new JTable(employeeModel);
//
//        projectModel = new DefaultTableModel(projectData.toArray(new String[0][]), projectData.get(0));
//        projectTable = new JTable(projectModel);
//
//        // Review area
//        reviewArea = new JTextArea(10, 40);
//        JButton saveButton = new JButton("Save Review");
//        saveButton.addActionListener(e -> saveReview());
//
//        // Add components to frame
//        frame.add(new JScrollPane(employeeTable));
//        frame.add(new JScrollPane(projectTable));
//        frame.add(new JScrollPane(reviewArea));
//        frame.add(saveButton);
//
//        frame.setVisible(true);
//    }
//
//    private void saveReview() {
//        int selectedRow = employeeTable.getSelectedRow();
//        if (selectedRow >= 0) {
//            String employeeName = (String) employeeModel.getValueAt(selectedRow, 0); // Assuming name is in the first column
//            reviewArea.append("\nReview for " + employeeName + ": " + reviewArea.getText());
//        } else {
//            JOptionPane.showMessageDialog(frame, "Please select an employee to review.");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                new PerformanceManagementSys();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}




//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//
//public class EmployeeManager {
//
//    private JFrame frame;
//    private JTextField firstNameField, lastNameField, positionField, yearField;
//    private JComboBox<String> employeeCategoryBox, genderBox;
//    private JTable employeeTable;
//    private JButton addButton, viewButton, updateButton, deleteButton, backButton;
//
//    public EmployeeManager() {
//        frame = new JFrame("Manage Employees");
//        frame.setSize(700, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//
//        // Label and Fields
//        addLabel("First Name", 20, 50, 100, 30);
//        firstNameField = addTextField(130, 50, 200, 30);
//
//        addLabel("Last Name", 350, 50, 100, 30);
//        lastNameField = addTextField(460, 50, 200, 30);
//
//        addLabel("Employee Category", 20, 90, 130, 30);
//        employeeCategoryBox = addComboBox(new String[]{"permanent", "temporary"}, 160, 90, 170, 30);
//
//        addLabel("Position", 350, 90, 100, 30);
//        positionField = addTextField(460, 90, 200, 30);
//
//        addLabel("Gender", 20, 130, 100, 30);
//        genderBox = addComboBox(new String[]{"Male", "Female", "Other"}, 130, 130, 200, 30);
//
//        addLabel("Year of Joining", 350, 130, 120, 30);
//        yearField = addTextField(480, 130, 180, 30);
//
//        // Table
//        DefaultTableModel model = new DefaultTableModel(new Object[]{"Emp Code", "First Name", "Last Name", "Position Name", "Category", "Date Of Joining", "Email"}, 0);
//        employeeTable = new JTable(model);
//        JScrollPane sp = new JScrollPane(employeeTable);
//        sp.setBounds(20, 170, 640, 100);
//        frame.add(sp);
//
//        // Buttons
//        addButton = addButton("Add", 20, 280, 80, 30);
//        viewButton = addButton("View", 110, 280, 80, 30);
//        updateButton = addButton("Update", 200, 280, 80, 30);
//        deleteButton = addButton("Delete", 290, 280, 80, 30);
//        backButton = addButton("Back", 580, 280, 80, 30);
//
//        frame.setVisible(true);
//    }
//
//    private JLabel addLabel(String text, int x, int y, int width, int height) {
//        JLabel label = new JLabel(text);
//        label.setBounds(x, y, width, height);
//        frame.add(label);
//        return label;
//    }
//
//    private JTextField addTextField(int x, int y, int width, int height) {
//        JTextField textField = new JTextField();
//        textField.setBounds(x, y, width, height);
//        frame.add(textField);
//        return textField;
//    }
//
//    private JComboBox<String> addComboBox(String[] items, int x, int y, int width, int height) {
//        JComboBox<String> comboBox = new JComboBox<>(items);
//        comboBox.setBounds(x, y, width, height);
//        frame.add(comboBox);
//        return comboBox;
//    }
//
//    private JButton addButton(String text, int x, int y, int width, int height) {
//        JButton button = new JButton(text);
//        button.setBounds(x, y, width, height);
//        frame.add(button);
//        return button;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new EmployeeManager());
//    }
//}
//


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private JFrame frame;
    private JTextField firstNameField, lastNameField, positionField, yearField;
    private JComboBox<String> employeeCategoryBox, genderBox;
    private JTable employeeTable;
    private DefaultTableModel model;
    private JButton addButton, viewButton, updateButton, deleteButton, backButton;
    private final String filePath = "employees1.txt";

    public EmployeeManager() throws IOException {
        frame = new JFrame("Manage Employees");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        initComponents();
        frame.setVisible(true);
    }

    private void initComponents() throws IOException {
        // Label and Fields

        addLabel("First Name", 20, 50, 100, 30);
        firstNameField = addTextField(130, 50, 200, 30);

        addLabel("Last Name", 350, 50, 100, 30);
        lastNameField = addTextField(460, 50, 200, 30);

        addLabel("Employee Category", 20, 90, 130, 30);
        employeeCategoryBox = addComboBox(new String[]{"permanent", "temporary"}, 160, 90, 170, 30);

        addLabel("Position", 350, 90, 100, 30);
        positionField = addTextField(460, 90, 200, 30);

        addLabel("Gender", 20, 130, 100, 30);
        genderBox = addComboBox(new String[]{"Male", "Female", "Other"}, 130, 130, 200, 30);

        addLabel("Year of Joining", 350, 130, 120, 30);
        yearField = addTextField(480, 130, 180, 30);

        // Table
        model = new DefaultTableModel(new Object[]{"Emp Code", "First Name", "Last Name", "Position Name", "Category", "Date Of Joining", "Email"}, 0);
        employeeTable = new JTable(model);
        JScrollPane sp = new JScrollPane(employeeTable);
        sp.setBounds(20, 170, 640, 100);
        frame.add(sp);
        loadEmployeesFromFile();

        // Buttons
        addButton = addButton("Add", 20, 280, 80, 30);
        viewButton = addButton("View", 110, 280, 80, 30);
        updateButton = addButton("Update", 200, 280, 80, 30);
        deleteButton = addButton("Delete", 290, 280, 80, 30);
        backButton = addButton("Back", 580, 280, 80, 30);

        addButton.addActionListener(e -> addEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
    }

    private JLabel addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        frame.add(label);
        return label;
    }

    private JTextField addTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        frame.add(textField);
        return textField;
    }

    private JComboBox<String> addComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, width, height);
        frame.add(comboBox);
        return comboBox;
    }

    private JButton addButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        frame.add(button);
        return button;
    }


    private void loadEmployeesFromFile() throws IOException {
        model.setRowCount(0); // Clear existing rows
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(","));
            }
        }
    }

    private void addEmployee() {
        String[] employeeData = new String[]{
                firstNameField.getText(),
                lastNameField.getText(),
                (String) employeeCategoryBox.getSelectedItem(),
                positionField.getText(),
                (String) genderBox.getSelectedItem(),
                yearField.getText()
        };

        model.addRow(employeeData);

        try {
            saveEmployeesToFile();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving employee data.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            try {
                saveEmployeesToFile();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error deleting employee data.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select an employee to delete.");
        }
    }

    private void saveEmployeesToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new EmployeeManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
