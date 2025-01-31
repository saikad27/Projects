package modules;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Marked extends Pieces {
    String color;
    String name = "Blank";
    
    Circle circle = new Circle();
  

    public Circle getCircle(){
        
        circle.setRadius(10);
		circle.setFill(Color.TEAL);
        circle.setOpacity(opVal);
        //circle.setStroke(Color.RED);
        
        return circle;
        
    }
   
    
}
    
