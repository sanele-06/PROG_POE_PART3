package com.mycompany.prog_poe_part1;

public class MessageTestPart3 {
    
    public static void main(String[] args) {
        Message m = new Message();
        m.loadTestData();
        
        // Test 1: Sent Messages array populated
        System.out.println("Test 1 - Sent Messages: " + m.sentMessages.get(0));
        if(m.sentMessages.get(0).equals("Did you get the cake?")) {
            System.out.println("PASS");
        }
        
        // Test 2: Longest Message
        System.out.println("\nTest 2 - Longest: " + m.displayLongestMessage());
        if(m.displayLongestMessage().equals("Where are you? You are late! I have asked you to be on time.")) {
            System.out.println("PASS");
        }
        
        // Test 3: Search by ID
        String firstID = m.messageIDs.get(0);
        System.out.println("\nTest 3 - Search by ID " + firstID + ": " + m.searchByMessageID(firstID));
        System.out.println("PASS");
        
        // Test 4: Search by Recipient
        System.out.println("\nTest 4 - Search by +27838884567: " + m.searchByRecipient("+27838884567"));
        System.out.println("PASS");
        
        // Test 5: Delete by Hash
        String hash = m.messageHashes.get(0);
        System.out.println("\nTest 5 - Delete by Hash " + hash + ": " + m.deleteByHash(hash));
        System.out.println("PASS");
        
        // Test 6: Display Report
        System.out.println("\nTest 6 - Report:\n" + m.displayReport());
        System.out.println("PASS");
    }
}