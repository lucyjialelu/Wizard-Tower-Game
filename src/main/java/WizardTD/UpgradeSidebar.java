package WizardTD;

import processing.core.PApplet;

public class UpgradeSidebar {

    boolean rangeClick, speedClick, damageClick, rangeHovered=false, speedHovered=false, damageHovered=false;
    
    public UpgradeSidebar(){
        this.rangeClick=false;
        this.speedClick=false;
        this.damageClick=false;
    }

    /**
     * Detecting whether mouse is hovering over Upgrade Range button.
     * @param hover, Boolean: True=hovering, False=not hovering.
     */
    public void rangeHovered(boolean hover){
        rangeHovered=hover;
    }

    /**
     * Detecting whether mouse has clicked the Upgrade Range button.
     * @param clicked, Boolean: True=clicked, False=not clicked.
     */
    public void rangeClicked(boolean clicked){
        rangeClick = clicked;
    }
    public boolean getRangeClicked(){
        return rangeClick;
    }

    /**
     * Detecting whether mouse is hovering over Upgrad Speed button.
     * @param hover, Boolean: True=hovering, False=not hovering.
     */
    public void speedHovered(boolean hover){
        speedHovered=hover;
    }

    /**
     * Detecting whether mouse has clicked the Upgrade Speed button.
     * @param clicked, Boolean: True=clicked, False=not clicked.
     */
    public void speedClicked(boolean clicked){
        speedClick = clicked;
    }
    public boolean getSpeedClicked(){
        return speedClick;
    }

    /**
     * Detecting whether mouse is hovering over Upgrad Damage button.
     * @param hover, Boolean: True=hovering, False=not hovering.
     */
    public void damageHovered(boolean hover){
        damageHovered=hover;
    }
    /**
     * Detecting whether mouse has clicked the Upgrade Damage button.
     * @param clicked, Boolean: True=clicked, False=not clicked.
     */
    public void damageClicked(boolean clicked){
        damageClick= clicked;
    }
    public boolean getDamageClicked(){
        return damageClick;
    }

    /**
     * Get the Y coordinate of the Upgrade Range button. 
     * @return int, Y coordinate of 260.
     * @see The y coordinates for other upgrade buttons are set in App by adding 70 onto 260.
     */
    public int getRY(){
        return 260;
    }

    /**
     * Draw all elements in the game by current frame.
     * @param app
     */
    public void draw(PApplet app){
        app.stroke(0);
        if(rangeClick){
            app.fill(255, 255, 0);
        } else if (rangeHovered){
            app.fill(200);
        } else {app.noFill();}
        app.rect(645, 260, 50, 50);
        app.fill(0);
        app.textSize(35);
        app.text("U1", 650, 300);

        app.textSize(10);
        app.fill(0);
        app.text("Upgrade", 700, 270);
        app.text("range", 700, 290);

        app.stroke(0);
        if(speedClick){
            app.fill(255, 255, 0);
        } 
        else if (speedHovered){
            app.fill(200);
        }
        else {app.noFill();}
        app.rect(645, 330, 50, 50);
        app.textSize(35);
        app.fill(0);
        app.text("U2", 650, 370);

        app.textSize(10);
        app.fill(0);
        app.text("Upgrade", 700, 340);
        app.text("speed", 700, 360);

        app.stroke(0);

        if(damageClick){
            app.fill(255, 255, 0);
        }
        else if (damageHovered){
            app.fill(200);
        } 
        else {app.noFill();}
        app.rect(645, 400, 50, 50);
        app.textSize(35);
        app.fill(0);
        app.text("U3", 650, 440);

        app.textSize(10);
        app.fill(0);
        app.text("Upgrade", 700, 410);
        app.text("damage", 700, 430);
    }
}
