
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * This Conversation class contains a chatbot where the 
 * user can type anything and the chat will generate responses.
 */
class Conversation implements Chatbot {

  /**
 * Stores the conversation between user and bot into
 * a transcript.
 */
  private ArrayList<String> transcript;
  // Attributes 

  /**
   * Constructor 
   * Initilizes the transcript list
   */
  Conversation() {
    transcript = new ArrayList<>();
  }

  /**
   * Starts and runs the conversation with the user.
   */
  public int chat() {

    Scanner scanner = new Scanner(System.in);  // Scanner Object
    System.out.println("How many rounds?"); //User picks amount of rounds
    int numRounds = scanner.nextInt();
    scanner.nextLine(); //reads next line of input
    
    System.out.println("Hello! What are you thinking about?");
    String userResponse = scanner.nextLine();
    transcript.add(userResponse);

    for (int i = 0; i < numRounds; i++) {

        String chatResponse = respond(userResponse);
        System.out.println(chatResponse);
        transcript.add(chatResponse);

        if(i < numRounds - 1){
          userResponse = scanner.nextLine();
          transcript.add(userResponse);

        }
    }
    
    scanner.close();
    return numRounds;
  }

  /**
   * Prints transcript of conversation between user and bot.
   */
  public void printTranscript() {
    System.out.println("TRANSCRIPT:");
    for (int i = 0; i < transcript.size(); i++) { 
      System.out.println(transcript.get(i));
    }
  }
  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {

    String[] mirrorWords = {"I", "me", "am", "you", "my", "your"};
    String[] replacementWords = {"you", "you", "are", "I", "your", "my"};

    
    inputString = inputString.replaceAll("[!?\\.]", ""); //removes punctuation
    String[] userSplit = inputString.split(" "); //splits user input into substrings

    //this replaces mirrored words.
    for(int i = 0; i < userSplit.length; i++) {
      for(int j = 0; j < mirrorWords.length; j++) {
        if (userSplit[i].equals(mirrorWords[j])) {
          userSplit[i] = replacementWords[j];
          break;  
      }
    }
  }
  
  //Puts sentence back together with mirrored words.
  String mchatResponse = String.join(" ", userSplit);

  //If the word had mirror words, return that
  if (!mchatResponse.equalsIgnoreCase(inputString)) {
    return mchatResponse + "?";
  }else{
      //If no mirroed words, give random response.
        String[] responses = {
          "For sure...",
          "Understood.",
          "Why is that?",
          "How Cool!",
          "That is fascinating!",
          "Super!"

        };

        Random chatresponse = new Random(); 
        int randomIndex = chatresponse.nextInt(responses.length);
        return responses[randomIndex];
    }
  }

   /**
   * Main method, runs the program with all previous
   * methods in the order wanted.
   */
  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
