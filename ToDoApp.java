import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class ToDoApp extends JFrame {
    private DefaultListModel<String> tasks;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton, deleteButton, completeButton;
    private JLabel taskCounter;

    public ToDoApp() {
        setTitle("To-Do List");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Model & List
        tasks = new DefaultListModel<>();
        taskList = new JList<>(tasks);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Input
        taskInput = new JTextField();
        taskInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        completeButton = new JButton("Complete");

        // Disable add button initially
        addButton.setEnabled(false);

        // Counter
        taskCounter = new JLabel("Tasks: 0");
        taskCounter.setHorizontalAlignment(SwingConstants.RIGHT);
        taskCounter.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10));

        // Listeners
        taskInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { toggleAdd(); }
            public void removeUpdate(DocumentEvent e) { toggleAdd(); }
            public void changedUpdate(DocumentEvent e) { toggleAdd(); }
            private void toggleAdd() {
                addButton.setEnabled(!taskInput.getText().trim().isEmpty());
            }
        });

        taskInput.addActionListener(e -> addTask());

        addButton.addActionListener(e -> addTask());

        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete selected task?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    tasks.remove(index);
                    updateCounter();
                }
            } else {
                showMessage("Select a task to delete.");
            }
        });

        completeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                String task = tasks.get(index);
                if (!task.startsWith("[Done] ")) {
                    tasks.set(index, "[Done] " + task);
                }
            } else {
                showMessage("Select a task to mark as complete.");
            }
        });

        // Layouts
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(taskCounter, BorderLayout.SOUTH);

        // Add to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasks.addElement(task);
            taskInput.setText("");
            updateCounter();
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void updateCounter() {
        taskCounter.setText("Tasks: " + tasks.size());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
