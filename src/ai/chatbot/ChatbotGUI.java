package ai.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChatbotGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private HashMap<String, String> knowledgeBase;

    public ChatbotGUI() {
        initKnowledgeBase();
        createGUI();
    }

    private void initKnowledgeBase() {
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hi", "Hello! How can I assist you?");
        knowledgeBase.put("hello", "Hi there! What can I do for you?");
        knowledgeBase.put("how are you", "I'm just a bot, but I'm functioning as expected!");
        knowledgeBase.put("what is your name", "I'm a simple Java chatbot built for learning.");
        knowledgeBase.put("bye", "Goodbye! Have a nice day.");
        knowledgeBase.put("help", "You can greet me, ask for my name, status, or say 'bye' to exit.");
        knowledgeBase.put("what can you do", "I can answer basic questions and simulate a chatbot experience.");
        knowledgeBase.put("who created you", "I was created as a Java-based educational project.");
    }

    private void createGUI() {
        frame = new JFrame("🤖 AI Chatbot - Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Header
        JLabel header = new JLabel(" Java Chatbot - Interactive AI", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(new Color(0, 102, 204));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(header, BorderLayout.NORTH);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        chatArea.setBackground(Color.WHITE);
        chatArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 153, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Event listeners
        sendButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());

        frame.setVisible(true);
    }

    private void processInput() {
        String userInput = inputField.getText().trim().toLowerCase();
        inputField.setText("");

        if (userInput.isEmpty()) return;

        chatArea.append("👤 You: " + userInput + "\n");
        String response = getResponse(userInput);
        chatArea.append("🤖 Bot: " + response + "\n\n");
    }

    private String getResponse(String input) {
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "I'm sorry, I don't understand that. Type 'help' to see what I can do.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotGUI::new);
    }
}
