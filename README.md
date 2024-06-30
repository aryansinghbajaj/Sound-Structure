# Sound Structure: A Music Player Implementation

The "Sound Structure" project is a simple yet comprehensive music player designed to manage and play songs efficiently using linked list and stack data structures. This Java-based application focuses on maintaining a playlist, managing recently played tracks, and providing various functionalities typically expected from a basic music player.

## Project Description

The Sound Structure music player leverages two fundamental data structures: **Doubly Linked Lists** and **Stacks**. These data structures are used to organize and manipulate the playlist and the recently played tracks, respectively. The application offers a user-friendly command-line interface to interact with the playlist, allowing users to add, delete, search, and sort songs, as well as keep track of recently played songs.

### Features

- **Add New Song**: Users can add new songs to the end of the playlist. Each song is also appended to a file named `playlist.txt` to persist the playlist.

- **Delete Song**: Songs can be deleted either by searching for the song name or by specifying the position in the playlist. The application updates the file and linked list accordingly.

- **Display Playlist**: The entire playlist can be displayed, showing all the songs in the order they were added.

- **Count Total Songs**: Users can count the total number of songs in the playlist.

- **Search Song**: The application provides functionality to search for a song by its name within the playlist.

- **Play Song**: Users can choose a song to play from the playlist. The song is then added to a stack representing the recently played tracks.

- **Recently Played List**: The list of recently played tracks can be displayed, showing the most recently played song first.

- **Last Played Song**: The most recently played song can be quickly retrieved and displayed.

- **Sort Playlist**: The songs in the playlist can be sorted alphabetically.

- **Add From File**: The playlist can be populated from an existing file (`playlist.txt`), adding each song from the file into the linked list.

- **Exit**: The application can be exited gracefully.

### Application of Data Structures

#### Doubly Linked List:

- **Playlist Management**: The playlist is implemented as a doubly linked list where each song is a node. This allows for efficient insertion, deletion, and traversal operations. Each node points to the next and previous nodes, making it easy to navigate forward and backward through the list.

- **File Synchronization**: The doubly linked list is synchronized with a file (`playlist.txt`), ensuring that the playlist is persisted across sessions. Songs are appended to the file as they are added to the linked list.

#### Stack:

- **Recently Played Tracks**: A stack is used to manage the recently played songs. The stack allows for quick access to the most recently played song and maintains the order of the last played tracks.

- **Avoiding Duplicates**: The push operation checks for duplicates to ensure that the same song is not added consecutively to the recently played stack.
