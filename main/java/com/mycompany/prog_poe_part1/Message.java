//commit 3 - search by recipient 
package com.mycompany.prog_poe_part1;
import java.util.*;

public class Message {
    private String messageId;
    private String recipient;
    private String messageText;
    public List<String> sentMessages = new ArrayList<>();
    public List<String> disregardedMessages = new ArrayList<>();
    public List<String> storedMessages = new ArrayList<>();
    public List<String> messageHashes = new ArrayList<>();
    public List<String> messageIDs = new ArrayList<>();
    public List<String> recipients = new ArrayList<>();
    public List<String> senders = new ArrayList<>();
    public List<String> masterMessages = new ArrayList<>();
    public String formattedStored = "";
    public int count = 0;

    public Message(String messageId, String recipient, String messageText) {
        this.messageId = messageId;
        this.recipient = recipient;
        this.messageText = messageText;
    }
    public Message() {}

    public boolean checkMessageID() {
        return messageId != null && messageId.length() == 10;
    }
    public int checkRecipientCell() {
        if (recipient == null) return -1;
        if (recipient.length() > 13) return 1;
        if (!recipient.matches("^\\+.*")) return -1;
        return 0;
    }
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        return messageId.substring(0,2).toUpperCase() + ":" + count + ":" + words[0].toUpperCase() + words[words.length-1].toUpperCase();
    }
    public String getMessageDetails() {
        return "MessageID: " + messageId + " Recipient: " + recipient + " Message: " + messageText;
    }
    public String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }
    public String createMessageHash(String messageID, int count, String message) {
        String[] words = message.trim().split("\\s+");
        return messageID.substring(0,2).toUpperCase() + ":" + count + ":" + words[0].toUpperCase() + words[words.length-1].toUpperCase();
    }
    public void loadTestData() {
        sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear(); recipients.clear(); senders.clear(); masterMessages.clear();
        count = 0; formattedStored = "";
        addTest("+27834557896", "Did you get the cake?", "Sent", "Sender1");
        addTest("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "Sender2");
        addTest("+27834484567", "Yohoooo, I am at your gate.", "Disregard", "Sender3");
        addTest("0838884567", "It is dinner time !", "Sent", "Developer");
        addTest("+27838884567", "Ok, I am leaving without you.", "Stored", "Sender5");
    }
    private void addTest(String recipient, String message, String flag, String sender) {
        String messageID = generateMessageID();
        String hash = createMessageHash(messageID, count, message);
        messageIDs.add(messageID); messageHashes.add(hash);
        recipients.add(recipient); senders.add(sender); masterMessages.add(message);
        if (flag.equalsIgnoreCase("Sent")) sentMessages.add(message);
        else if (flag.equalsIgnoreCase("Disregard")) disregardedMessages.add(message);
        else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(message);
            formattedStored += "Sender: " + sender + ", Recipient: " + recipient + "\n";
        }
        count++;
    }
    public String displaySenderAndRecipient() { return formattedStored; }
    public String displayLongestMessage() {
        if (storedMessages.isEmpty()) return "";
        String longest = storedMessages.get(0);
        for (String m : storedMessages) if (m.length() > longest.length()) longest = m;
        return longest;
    }
    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) if (messageIDs.get(i).equals(id))
            return "Recipient: " + recipients.get(i) + " Message: " + masterMessages.get(i);
        return "Message ID not found";
    }
    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) if (recipients.get(i).equals(recipient)) sb.append(masterMessages.get(i)).append("\n");
        return sb.toString();
    }
    public String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String deletedMsg = masterMessages.get(i);
                messageIDs.remove(i); messageHashes.remove(i); recipients.remove(i); senders.remove(i); masterMessages.remove(i);
                storedMessages.remove(deletedMsg); sentMessages.remove(deletedMsg); disregardedMessages.remove(deletedMsg);
                return "Message: " + deletedMsg + " successfully deleted.";
            }
        }
        return "Hash not found";
    }
    public String displayReport() {
        StringBuilder sb = new StringBuilder("Full Report\n");
        for (int i = 0; i < messageIDs.size(); i++) {
            sb.append("Hash: ").append(messageHashes.get(i)).append("\n");
            sb.append("Recipient: ").append(recipients.get(i)).append("\n");
            sb.append("Message: ").append(masterMessages.get(i)).append("\n---\n");
        }
        return sb.toString();
    }
}
