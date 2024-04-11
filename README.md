I don't know why untitled and Sudoku are showing. I don't see them in my local repo. 

About JavaFX. I did add dependency, just like that tutorial website wanted: https://openjfx.io/openjfx-docs/
I tried maven, problem with fx. I tried Intelij, more problems with fx. At one point I generated a brand new project and tried moving the sudoku-solving logic to the new one, but that messed up the checkstyle.
After 5h we arrived at a point where we can run the basic grid in FX.

For you to run it:
1. download JavaFX 21. Then in the Intelij project enter RUN -> Edit Configurations -> SudokuGui -> ModifyOptions -> Add VM '--module-path "C:\Program Files\Java\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml'
2. download the Gluton Scene Builder. Right-click on the fxml file, open with Scene Builder and paste path to .exe here.

That SHOULD do it. Probably. Pretty sure. For like 73%.

Also, I didn't add UT for FX yet. Mainly bc we're displaying grid only. And I don't know how can we even test the GUI. But the task only said 'start the study of FX'. So I think we started it??

If you happen to find a better solution on how to integrate FX into it, do let me know. 13 websites (reddit included) and me are out of options.
