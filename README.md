# QuizGenie AI  - An Android Quiz Application with Kotlin And Gemini

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Description

QuizGenie AI  is an Android application that allows users to take multiple-choice quizzes. The application dynamically fetches questions and answers from the Gemini API, providing an interactive and engaging quiz experience. It features a countdown timer, score tracking, and a clean, user-friendly interface.

## Key Features

-   **Dynamic Quizzes:** Fetches quiz questions and answers in real-time from the Gemini API.
-   **Multiple-Choice Format:** Presents users with multiple-choice questions, each with three answer options.
-   **Countdown Timer:** Includes a timer for each question to add a time-based challenge.
-   **Score Tracking:** Tracks the user's score and displays it at the end of the quiz.
-   **Progress Bar:** Shows the time remaining for each question using a progress bar.
-   **Quit Option:** Allows users to quit the quiz at any time.
-   **User-Friendly Interface:** Clean and intuitive design.
-   **Error Handling:** Displays informative error messages if there are issues fetching quizzes.
- **Navigate to score screen**: Navigate to a screen that display the score of the user.

## Technologies Used

-   **Kotlin:** The primary programming language for Android development.
-   **Android SDK:** The core framework for building Android applications.
-   **Jetpack Libraries:**
    -   `androidx.appcompat:appcompat:1.7.0`: Provides compatibility for Material Design components.
    -   `androidx.core:core-ktx:1.16.0`: Core Kotlin extensions.
    -   `androidx.activity:activity:1.10.1`: Framework for Activities.
    - `androidx.lifecycle:lifecycle-runtime:2.6.2` : lifecycle components.
    -  `androidx.cardview:cardview:1.0.0` : CardView component.
    - `androidx.lifecycle:lifecycle-viewmodel:2.6.2` : Lifecycle view model component.
    - `androidx.fragment:fragment:1.5.4`: Fragment component.
-   **Google AI SDK:** `com.google.ai.client:generativeai:0.1.0` to interact with the Gemini API.
-   **JSON:** Used for parsing and handling data received from the Gemini API.
- **Coroutines:** Used to make calls to the Gemini API asynchronously.

## Setup Instructions

1.  **Clone the Repository:**
2.  Replace `[repository URL]` with the actual URL of your Git repository.

2.  **Open in Android Studio:**
    -   Open Android Studio.
    -   Select "Open an Existing Project."
    -   Navigate to the cloned repository directory and select it.

3.  **Sync Gradle:**
    -   Once the project is opened, Gradle will start syncing automatically. If it doesn't, click "Sync Project with Gradle Files" in the toolbar.

4.  **Add Gemini API Key:**
    -   Replace `"AIzaSyBjCq_BzwhlaoV3O7mhaF4JC1jwIgHzNXE"` with your API Key in `Questions.kt` file.

5.  **Build and Run:**
    -   Connect an Android device or start an emulator.
    -   Click "Run" (green play button) to build and run the app.

## Usage Instructions

1.  **Start the Quiz:** Launch the app to begin the quiz.
<img width="184" alt="Start" src="https://github.com/user-attachments/assets/6e7f9070-370e-4ff4-ac34-25e2dd4c9e58" />


3.  **Answer Questions:** Read each multiple-choice question and select your answer from the provided options (A, B, or C).
   <img width="576" alt="timer" src="https://github.com/user-attachments/assets/cd604397-92bf-4147-91b5-688650112513" />

5.  **Time Limit:** Be aware of the countdown timer for each question.
   <img width="184" alt="time" src="https://github.com/user-attachments/assets/b4422ab0-7c45-427a-85d3-5496feaf8eca" />

7.  **Next Question:** After answering, move on to the next question.
  <img width="184" alt="next" src="https://github.com/user-attachments/assets/1c62883e-a389-478d-9f03-e30660e99778" />

9. **Quit:** If you want to exit the quiz, click on the quit button.
<img width="184" alt="quit" src="https://github.com/user-attachments/assets/2ce6ff86-c2e3-4e1a-998a-626d51380e4b" />

11.  **View Score:** At the end of the quiz, your total score will be displayed, and you will be navigate to score screen:
<img width="185" alt="score" src="https://github.com/user-attachments/assets/53cba652-cb38-4714-9dfa-b40ee434f07d" />



## Project Structure

-   `app/src/main/java/com/example/quiz_danoun/`: Contains the main source code for the application.
    -   `Questions.kt`: The main activity for displaying questions and managing the quiz flow.
    -  `Score.kt`: display the score of the user.
    -  `MainActivity.kt`: the luncher activity.
-   `app/src/main/res/`: Contains the resources for the application.
    -   `layout/`: Contains XML layout files for the user interface.
    - `values`: contains values like theme and colors.

## Contributing

Contributions to Quiz\_Danoun are welcome! Here's how you can contribute:

1.  **Fork the repository.**
2.  **Create a new branch** (`git checkout -b feature/AmazingFeature`).
3.  **Commit your changes** (`git commit -m 'Add some AmazingFeature'`).
4.  **Push to the branch** (`git push origin feature/AmazingFeature`).
5.  **Open a pull request.**

## License

This project is licensed under the [Apache 2.0 License](https://opensource.org/licenses/Apache-2.0).

## Contact

If you have any questions or suggestions, feel free to contact me at y.danoun.pro@gmail.com.

