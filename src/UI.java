import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Deviltech on 03.11.2015.
 */
public class UI extends Application{

    Sequence inputSequence = new Sequence(myLabels.AREA_INPUT);
    Sequence outputSequence = new Sequence(myLabels.AREA_OUTPUT);

    TextArea inputArea = new TextArea(myLabels.AREA_INPUT);
    TextArea outputArea = new TextArea(myLabels.AREA_OUTPUT);

    boolean isRNA = false;
    double sliderPos = 50;


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Graphics
        VBox mainBox = new VBox();
        TextArea inputArea = new TextArea(myLabels.AREA_INPUT);
        TextArea outputArea = new TextArea(myLabels.AREA_OUTPUT);
        Label sliderLabel = new Label(myLabels.LABEL_SLIDER);

        Font monospaced = Font.font("Monospaced", 12);

        HBox firstBox = new HBox();
        HBox secondBox = new HBox();
        HBox thirdBox = new HBox();
        HBox fourthBox = new HBox();

        Button buttonFlip = new Button(myLabels.BUTTON_FLIP);

        Button buttonFilter = new Button(myLabels.BUTTON_FILTER);
        Button buttonUpper = new Button(myLabels.BUTTON_UPPER);
        Button buttonLower = new Button(myLabels.BUTTON_LOWER);

        ToggleButton buttonRNA = new ToggleButton(myLabels.BUTTON_TO_RNA);
        Button buttonReverse = new Button(myLabels.BUTTON_REVERSE);
        Button buttonComplementary = new Button(myLabels.BUTTON_COMPLEMENTARY);

        Button buttonReverseComplementary = new Button(myLabels.BUTTON_REVERSE_COMPLEMENTARY);
        Button buttonGCContent = new Button(myLabels.BUTTON_GC_CONTENT);
        Button buttonLength = new Button(myLabels.BUTTON_LENGTH);

        Button buttonClear = new Button(myLabels.BUTTON_CLEAR);

        Slider slider = new Slider();

        inputArea.setFont(monospaced);
        outputArea.setFont(monospaced);

        // set on change listener for Text area
        inputArea.textProperty().addListener((observable, oldValue, newValue)-> {
            inputSequence.setMySequence(newValue);
        });

        outputArea.textProperty().addListener((observable, oldValue, newValue)-> {
            outputSequence.setMySequence(newValue);

        });

        buttonRNA.selectedProperty().addListener((obervable, oldValue, newValue)->{
            isRNA = newValue;
        });

        slider.valueProperty().addListener((observable, oldValue, newValue)-> {
            sliderPos = Math.max(1, slider.getValue());
            outputArea.setText(scale(outputSequence.getMySequence()));
        });

        // Set ButtonActions
        //flip
        buttonFlip.setOnAction((value) -> {
            String oldInput = inputArea.getText();
            String oldOutput = outputArea.getText();

            inputArea.setText(oldOutput);
            outputArea.setText(oldInput);
        });

        // filter
        buttonFilter.setOnAction((value) -> outputArea.setText(scale(inputSequence.filter())));
        buttonUpper.setOnAction((value) ->  outputArea.setText(scale(outputSequence.toUpper())));
        buttonLower.setOnAction((value) ->  outputArea.setText(scale(outputSequence.toLower())));

        buttonRNA.setOnAction((value) ->  outputArea.setText(scale(outputSequence.toRNA(isRNA))));
        buttonReverse.setOnAction((value) ->  outputArea.setText(scale(outputSequence.reverse())));
        buttonComplementary.setOnAction((value) -> outputArea.setText(scale(outputSequence.complementary(isRNA))));

        buttonReverseComplementary.setOnAction((value) -> outputArea.setText(scale(outputSequence.reverseComplementary(isRNA))));
        buttonGCContent.setOnAction((value) -> outputArea.setText(scale(outputSequence.GCContent())));
        buttonLength.setOnAction((value) -> outputArea.setText(scale(outputSequence.length())));

        buttonClear.setOnAction((value) -> {
            inputArea.setText("");
            outputArea.setText("");
        });

        // Set slider
        slider.setMin(0);
        slider.setMax(50);
        slider.setValue(45);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(10);

        //Build scene
        //firstBox.setSpacing(10);
        //firstBox.setPadding(new Insets(0, 20, 10, 20));

        buttonFilter.setMaxWidth(Double.MAX_VALUE);

        mainBox.setSpacing(10);
        mainBox.setPadding(new Insets(0, 20, 10, 20));
        mainBox.setAlignment(Pos.CENTER);

        firstBox.getChildren().addAll(buttonFilter, buttonUpper, buttonLower);
        firstBox.setAlignment(Pos.CENTER);
        firstBox.setMaxWidth(Double.MAX_VALUE);
        secondBox.getChildren().addAll(buttonRNA, buttonReverse, buttonComplementary);
        thirdBox.getChildren().addAll(buttonReverseComplementary, buttonGCContent, buttonLength);
        fourthBox.getChildren().addAll(buttonClear);

        mainBox.getChildren().addAll(inputArea, buttonFlip, outputArea, firstBox, secondBox, thirdBox, fourthBox, sliderLabel, slider);

        Scene scene = new Scene(mainBox, 600, 800);

        primaryStage.setScene(scene);
        primaryStage.setTitle(myLabels.CAPTION);

        // show scene
        primaryStage.show();

    }

    private String scale(String s){
        String current = new String(s.replace("\n", ""));
        String result = "";
        while (current.length()>sliderPos){
            result += current.subSequence(0, (int)sliderPos);
            result += "\n";
            current = current.substring((int)sliderPos, current.length());
        }
        result += current;
        return result;
    }
}
