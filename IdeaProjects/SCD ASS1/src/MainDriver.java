//import javax.swing.SwingUtilities;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.List;
//
//public class MainDriver {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ResourceFormSwing resourceFormSwing = new ResourceFormSwing();
//            TaskFormSwing taskFormSwing = new TaskFormSwing();
//            final SkillMatchingFormSwing[] skillMatchingFormSwing = new SkillMatchingFormSwing[1];
//
//            resourceFormSwing.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    List<ResourceFormSwing.Resource> resources = resourceFormSwing.getResources();
//                    taskFormSwing.setResources(resources);
//                    taskFormSwing.setVisible(true);
//                }
//            });
//
//            taskFormSwing.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    List<TaskFormSwing.Task> tasks = taskFormSwing.getTasks();
//                    skillMatchingFormSwing[0] = new SkillMatchingFormSwing(resourceFormSwing.getResources(), tasks);
//                    skillMatchingFormSwing[0].setVisible(true);
//                }
//            });
//
//            resourceFormSwing.setVisible(true);
//        });
//    }
//}
