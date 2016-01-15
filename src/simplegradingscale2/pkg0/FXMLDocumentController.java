/*
Copyright (c) <2015> <sedj601>

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in 
the Software without restriction, including without limitation the rights to 
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do 
so, subject to the following conditions: 
    The above copyright notice and this permission notice shall be included in 
    all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED
    "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
    NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
    ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package simplegradingscale2.pkg0;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/**
 *
 * @author sedj601
 */
public class FXMLDocumentController implements Initializable
{    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {       
        tbMissed.setSelected(true);
        lblMissedOrCorrect.setText("Missed");
        lvLeft.setFixedCellSize(35);
        
        slNumberOfProblems.setValue(0);
        lblNumberOfProblems.setText(Integer.toString((int) slNumberOfProblems.getValue()));      
        slNumberOfProblems.setMin(1.0);
        slNumberOfProblems.setMax(200.0);                         
        slNumberOfProblems.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
        {
            lblNumberOfProblems.setText(newValue.intValue() + "");            
            if(tgMissedCorrect.getSelectedToggle().equals(tbMissed))
            {                
                populateListViewMissed();
            }
            if(tgMissedCorrect.getSelectedToggle().equals(tbCorrect))
            {                
                populateListViewCorrect();
            }
        });        
        
       
        
        tgMissedCorrect.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle toggle, Toggle new_toggle) ->
        {
            if(new_toggle.equals(tbMissed))
            {
                lblMissedOrCorrect.setText("Missed");
                populateListViewMissed();
            }
            else if(new_toggle.equals(tbCorrect))
            {
                lblMissedOrCorrect.setText("Correct");
                populateListViewCorrect();
            }
        });
    }        
    
    public void populateListViewMissed()
    {
        ObservableList<Text> olMissed = FXCollections.observableArrayList();
        
        int numberOfProblems = Integer.parseInt(lblNumberOfProblems.getText());
        for(int i = 0; i < numberOfProblems;i++)
        {
            double grade = (100 - ((i+1)/(double)numberOfProblems) * 100);
            String stringBuilder = String.format("%42d%90.2f",(i + 1), grade);
            final Text t = new Text(stringBuilder);
            
            if(grade >= 90)
            {
                t.setFill(Color.BLUE);
                olMissed.add(t);     
            }
            else if(grade >=80)
            {
                t.setFill(Color.BLUEVIOLET);
                olMissed.add(t);          
            }
            else if(grade >= 70)
            {
                t.setFill(Color.GREEN);
                olMissed.add(t);        
            }
            else if(grade >= 60)
            {
                t.setFill(Color.ORANGE);
                olMissed.add(t);        
            }
            else
            {
                t.setFill(Color.RED);
                olMissed.add(t);
            }
        }               
        lvLeft.setItems(olMissed);       
    }
    
    public void populateListViewCorrect()
    {
        ObservableList<Text> olCorrect = FXCollections.observableArrayList(); 
        int numberOfProblems = Integer.parseInt(lblNumberOfProblems.getText());
        

        for(int i = 0; i < numberOfProblems;i++)
        {
            double grade = ((i+1)/(double)numberOfProblems) * 100;
            String stringBuilder = String.format("%42d%90.2f",(i + 1), grade);
            final Text t = new Text(stringBuilder);
            
            if(grade >= 90)
            {
                t.setFill(Color.BLUE);
                olCorrect.add(t);     
            }
            else if(grade >=80)
            {
                t.setFill(Color.BLUEVIOLET);
                olCorrect.add(t);          
            }
            else if(grade >= 70)
            {
                t.setFill(Color.GREEN);
                olCorrect.add(t);        
            }
            else if(grade >= 60)
            {
                t.setFill(Color.ORANGE);
                olCorrect.add(t);        
            }
            else
            {
                t.setFill(Color.RED);
                olCorrect.add(t);
            }
        }                
        lvLeft.setItems(olCorrect);       
    }
    
    @FXML private Slider slNumberOfProblems;
    @FXML private Label lblNumberOfProblems; 
    @FXML private Label lblMissedOrCorrect;
    @FXML private ListView lvLeft;  
    @FXML private ToggleButton tbMissed;
    @FXML private ToggleButton tbCorrect;
    @FXML private ToggleGroup tgMissedCorrect;    
}

