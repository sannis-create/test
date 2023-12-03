import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceForms {

    private JFrame frame;
    private JTextArea outputArea;
    private JButton allocateButton;

    // Making an instance of DeveloperTaskAllocator to work with our GUI
    private DeveloperTaskAllocator taskAllocator = new DeveloperTaskAllocator();

    public ResourceForms() {
        frame = new JFrame("Developer Task Allocator");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        allocateButton = new JButton("Allocate Tasks");
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform task allocation and update the output area
                String result = taskAllocator.allocateTasks();
                outputArea.setText(result);
            }
        });

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(allocateButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ResourceForms();
            }
        });
    }
}
