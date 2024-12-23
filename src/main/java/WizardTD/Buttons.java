package WizardTD;

import processing.core.PApplet;

public class Buttons extends App{

    int xBox, yBox;
    float x, y;
    int length;// square, so length = width
    String string1, string2;
    int buttonX, buttonY, descriptX, descriptY;
    boolean clicked;

    public Buttons(float x, float y, int xBox, int yBox, String string1, String string2, int buttonX, int buttonY, int descriptX, int descriptY){
        this.x=x;
        this.y=y;
        this.xBox = xBox;
        this.yBox = yBox;
        this.length = 50;
        this.string1 = string1;
        this.string2 = string2;
        this.buttonX = buttonX;
        this.buttonY = buttonY;
        this.descriptX = descriptX;
        this.descriptY = descriptY;
        this.clicked = false;
    }

    /**
     * Sets the click status of the button
     * @return boolean, clicked(true) or not clicked(false)
     */
    public boolean getSetClicked(){
        return clicked;
    }

    /**
     * Gets the click status of the button
     */
    public void setClicked(boolean setclick){
        clicked = setclick;
    }

    /**
     * Gets the x coordinate of the box outline of button
     * @return int, x coordinate
     */
    public int getX(){
        return xBox;
    }
    /**
     * Gets the y coordinate of the box outline of button
     * @return int, y coordinate
     */
    public int getY(){
        return yBox;
    }

    /**
     * Gets length of box of button
     * @return int, length of box
     */
    public int getlength(){
        return length;
    }

    /**
     * Draw button elements in the game by current frame.
     * @param app
     * @param hovering, boolean status of whether the mouse is hovering over the button
     */
    public void draw(PApplet app, boolean hovering){
        app.stroke(0);
        if (clicked) {
            app.fill(255, 255, 0);
        } 
        else if (hovering){
            app.fill(200);
        }
        else{app.noFill();}

        app.rect(xBox, yBox, length, length);
        
        app.fill(0);
        app.textSize(35);
        app.textAlign(LEFT);
        app.text(string1, buttonX, buttonY);

        app.textSize(10);
        app.fill(0);
        app.textAlign(LEFT);
        app.text(string2, descriptX, descriptY);
    }
}
