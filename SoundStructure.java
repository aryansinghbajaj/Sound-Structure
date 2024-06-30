import java.io.*;
import java.util.Scanner;
//declaring a doublylinked list for each song
class Node {
    String song;
    Node next;
    Node prev;

    Node(String song) {
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}

public class SoundStructure {
    static Node top = null;//pointing to the top of the recently played songs stack
    static Node top1 = null;//it is used to traverse the recently played songs stack
//this method appends a new song to the 'playlist.txt' file
    public static void toFile(String song) {
        //here the append mode means that if the file does not exist it will be created , it the file exists then the new content will be added at the end without deleting the existing content
        try (FileWriter fw = new FileWriter("playlist.txt", true)) {
            fw.write(song + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();//this prints the stack trace of  the exception to the consolle ,which provides detailed information about the error , including the exact line where it occurred and the method calls that lead to the exception it helps in debugginh
        }
    }
//this method is used to add a new song at the end of the doubly linked list representing the playlist and saving the song to the file
    public static void addNode(Node first) {
        //here  first is the first node of the doubly linked list
        Scanner scanner = new Scanner(System.in);
        //traverse the end of the linked list
        while (first.next != null) {
            first = first.next;
        }
        //asking the user to enter the strong name
        System.out.println("\nEnter Song name: ");
        String song = scanner.nextLine();
        Node newNode = new Node(song);
        //add a new node to end of the list
        first.next = newNode;
        newNode.prev = first;
        //this method writes the song name to the 'playlist.txt' file appending it to the existing content . this step ensures that the song is saved
        toFile(song);
    }
//this method is used to add  a song at the end of the doubly linked list this method is similar to the addNode method but instead of taking the song name from user input , we take the song name directly as a parameter fron  file
    public static void addNodeFromFile(Node first, String song) {
        while (first.next != null) {
            first = first.next;
        }
        Node newNode = new Node(song);
        first.next = newNode;
        newNode.prev = first;
    }
//this method is used to delete a specified song from the playlist by rewriting the file without the given song . it uses a temporary file temp.txt to get a playlist without the song to be deleted
    public static void deleteFromFile(String song) {
        File playlistFile = new File("playlist.txt");//create a file representing the existing file
        File tempFile = new File("temp.txt");//create a file to store the updated playlist without the song to be deleted

        try (BufferedReader br = new BufferedReader(new FileReader(playlistFile));//file reader help us to read the file and the buffer reader help us to read the file line by line
        //filewriter help us to update the playlist without the song to be deleted
             FileWriter fw = new FileWriter(tempFile)) {
            String line;//declares a variable to hold each line read from playlist.txt file
            boolean songFound = false;// a flag to track whether we found the required song or not
            //line = br.readLine() reads the next line from the file and assigns it to the line
            while ((line = br.readLine()) != null) {
                if (!line.equals(song)) {
                    fw.write(line + "\n");//since this song is not the one to be deleted 
                } else {
                    //sets the flag to be true indicating the song to be deleted is found
                    songFound = true;
                }
            }
            if (!songFound) {
                System.out.println("There is no song with the name you entered.");
            } else {
                System.out.println("Song has been deleted.");
            }
        }
        //catching the error which might occur during file reading and writing 
        catch (IOException e) {
            System.out.println("An error occurred while reading/writing the file.");
            //print the stack trace of the exception , providing the step where the error has occured to help us for debugging
            e.printStackTrace();
        }
        //deletes the original file if it exists 
        if (playlistFile.delete()) {
            //it updates the playList file with the playlist not having the song required to be deleted
            tempFile.renameTo(playlistFile);
        }
    }
//this method help us to delete the last node in the playlist file
    public static void deleteNode(Node first) {
        while ((first.next).next != null) {
            first = first.next;
        }
        Node toDelete = first.next;
        first.next = null;
        System.out.println("Deleted");
    }
//function to print all songs in doubly linked list
    public static void printList(Node first) {
        System.out.println("\nPlaylist Name: ");
        while (first.next != null) {
            System.out.println(first.song);
            first = first.next;
        }
        System.out.println(first.song);
    }
//function to count the number of nodes in the playlist
    public static void countNodes(Node first) {
        int count = 0;
        while (first.next != null) {
            first = first.next;
            count++;
        }
        count++;
        System.out.println("\nTotal songs: " + count);
    }
//it is the method to delete  a  node from a specifc position in the doubly linked list . it updates the file by removing the corresponding song from it
    public static Node deleteAtPosition(Node pointer, int pos) {
        //if the first element is required to be deleted
        if (pos == 1) {
            deleteFromFile(pointer.song);
            pointer = pointer.next;
            if (pointer != null) pointer.prev = null;
            System.out.println("\nThe list is updated. Use the display function to check.");
            return pointer;
        }
        //travering to the specific position
        Node prevNode = null;
        int index = 1;

        while (pointer != null && index < pos) {
            prevNode = pointer;
            pointer = pointer.next;
            index++;
        }
        //handles when the request is out of the bond
        if (pointer == null) {
            System.out.println("Position out of bounds.");
            return null;
        }
        //deleting the song and updating the playlist
        deleteFromFile(pointer.song);
        if (pointer.next != null) {
            pointer.next.prev = prevNode;
        }
        if (prevNode != null) {
            prevNode.next = pointer.next;
        }

        System.out.println("\nThe list is updated. Use the display function to check.");
        return prevNode != null ? prevNode : pointer.next;
    }
    //this method help us to search a song in the playlist doubly linked list and to figure out whether that song is present in the playlist or not
    public static void searchSong(Node first) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter song to be searched: ");
        String song = scanner.nextLine();
        //initialise the boolean variable to be false
        boolean found = false;
        //the loop that iterates through the entire linked list and breaks when they find the required song
        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\nSong Found");
                found = true;
                break;
            }
            first = first.next;
        }
    //deals with the case when the required song is not found
        if (!found) {
            System.out.println("\nSong Not found");
        }
    }
    //the create method initializes or resets the top node of a stack structure which is used to track the recently played songs
    //it basically initializes the program or when you want to clear the stack of recently played songs
    public static void create() {
        top = null;
    }
   //this method adds a new song to the top of the stack of the recently played songs . it ensures avoiding duplication of 
    public static void push(String song) {
        Node newNode = new Node(song);
        if (top == null) {
            top = newNode;
        } else if (!top.song.equals(song)) {
            newNode.next = top;
            top = newNode;
        }
    }
    //function to display the list of recently played tracks from the stack
    public static void display() {
        top1 = top;
        if (top1 == null) {
            System.out.println("\nNo recently played tracks.");
            return;
        }
        System.out.println("\nRecently played tracks:");
        while (top1 != null) {
            System.out.println(top1.song);
            top1 = top1.next;
        }
    }

    public static void play(Node first) {
        Scanner scanner = new Scanner(System.in);
        //display the list of the songs from the starting node
        printList(first);
        System.out.println("\nChoose song you wish to play: ");
        String song = scanner.nextLine();
        //initializing the variable to check whether user input is available in the  playlist
        boolean found = false;
       //iterates through the linked list to find the song to be played
        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\nNow Playing......" + song);
                push(song);
                found = true;
                break;
            }
            first = first.next;
        }
        //keeps a  track of the condition when the element is not found
        if (!found) {
            System.out.println("\nSong Not found");
        }
    }
     //calls the display function which displays all the recently played function in the stack
    public static void recent() {
        display();
    }
    //this function help us to find the most recently played song from the song structure
    public static void topElement() {
        if (top == null) {
            System.out.println("\nNo last played tracks.");
            return;
        }
        System.out.println("\nLast Played Song - " + top.song);
    }
    //this function help us to sort the linked list alphabatically
    public static void sort(Node head) {
        if (head == null) return;

        Node current = head, index;
        String temp;

        while (current != null) {
            index = current.next;
           //algorithm for sorting the songs in the linked list
            while (index != null) {
                if (current.song.compareTo(index.song) > 0) {
                    temp = current.song;
                    current.song = index.song;
                    index.song = temp;
                }
                index = index.next;
            }
            current = current.next;
        }
    }
//this function help us to file the playlist with the songs filled in the playlist.txt file
    public static void addPlaylist(Node start) {
        //file reader help us to read the file and bufferreader help us to read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader("playlist.txt"))) {
            String line;//variable to store each file in the line
            //loop that help us read each line from the file
            while ((line = br.readLine()) != null) {
                addNodeFromFile(start, line);
            }
            System.out.println("Playlist Added");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();//help us in debugging
        }
    }
//this function help us to delete the song enetered by the user
    public static void deleteBySearch(Node start) {
        Scanner scanner = new Scanner(System.in);
        printList(start);
        System.out.println("\nChoose song you wish to delete: ");
        String song = scanner.nextLine();
        boolean found = false;
        //algorithm to delete the song from the playlist
        while (start != null) {
            if (start.song.equals(song)) {
                System.out.println("\nSong Found");
                //deleting the song when found in the playlsit
                deleteFromFile(start.song);
                //updating the pointers
                if (start.prev != null) start.prev.next = start.next;
                if (start.next != null) start.next.prev = start.prev;
                found = true;
                break;
            }
            start = start.next;
        }
        //handling the case when the input given by the user is not there in the playlist
        if (!found) {
            System.out.println("\nSong Not found");
        }
    }
  //function giving you the  choice to delete the position either by searching the element to be deleted or mentioning the position
    public static void deleteMenu(Node start) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which type of delete do you want?\n1. By Search\n2. By Position");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                deleteBySearch(start);
                break;
            case 2:
                System.out.println("Enter the position of the song: ");
                int pos = scanner.nextInt();
                deleteAtPosition(start, pos);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Node start = new Node("");
        Node hold = start;

        System.out.println("\t\t\t**WELCOME TO SOUND_STRUCTURE**");
        System.out.println("\n**Please use '_' for space.");
        System.out.println("\n\nEnter your playlist name: ");
        start.song = scanner.nextLine();

        create();

        do {
            System.out.println("\n1. Add New Song\n2. Delete Song\n3. Display Entered Playlist\n4. Total Songs\n5. Search Song\n6. Play Song\n7. Recently Played List\n8. Last Played\n9. Sorted Playlist\n10. Add From File\n11. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNode(start);
                    break;
                case 2:
                    deleteMenu(start);
                    break;
                case 3:
                    printList(start);
                    break;
                case 4:
                    countNodes(hold);
                    break;
                case 5:
                    searchSong(start);
                    break;
                case 6:
                    play(start);
                    break;
                case 7:
                    recent();
                    break;
                case 8:
                    topElement();
                    break;
                case 9:
                    sort(start.next);
                    printList(start);
                    break;
                case 10:
                    addPlaylist(start);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }
}

