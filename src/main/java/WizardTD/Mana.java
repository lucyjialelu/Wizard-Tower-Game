package WizardTD;

import processing.core.PApplet;

public class Mana extends PApplet{
    float length;
    int initial;
    int cap;
    int gained;
    int progress; 

    float lastUpdateTime;       
    float interval = (float) 1.0;    
    int frameCount;
    boolean gameEnd;
    boolean monsterEnd;
    int hpLeft;
    int deduct;

    public Mana(int initial, int cap, int gained){
        this.initial = initial;
        this.cap = cap;
        this.gained = gained;
        this.progress = initial;
        this.length=0;
        this.gameEnd = false;
        this.monsterEnd = false;
        this.deduct = 0;
    }

    /**
     * Handles logic of mana in topbar by current frame
     * @param timeMultiplier
     */
    public void tick(int timeMultiplier){
        float currentTime = (float) (millis()/1000.0);
        if (timeMultiplier==2){
            interval = (float) 0.5;
        } else if (timeMultiplier == 1){
            interval = (float) 1.0;
        }

        if(progress <= 0){
            gameEnd = true;
        } 

        if (monsterEnd && !gameEnd){
            progress -= hpLeft;
        }

        if (currentTime - lastUpdateTime >= interval){
            if(progress<cap && timeMultiplier!=0){
                progress += gained;
            } 
            lastUpdateTime = currentTime;
        }
    }

    /**
     * Sets cap of the Mana 
     * @param num, Changed when Mana pool spell is cast
     */
    public void setCap(float num){
        cap= (int)((int)cap*num);
    }

    /**
     * Draws all elements of the Mana top bar at current frame.
     * @param app
     * @param timeMultiplier, int changed when 2x speed or pause is clicked
     */
    public void draw(PApplet app, int timeMultiplier){
        frameCount++;

        app.textSize(25);
        app.fill(0);
        app.textAlign(LEFT);
        app.text("MANA:", 300, 30);

        app.fill(255);
        app.rect(385,5, 325, 30);

        if(timeMultiplier != 0 && progress<cap){
            length = setProgress(progress); 
        }

        app.fill(204, 255, 255);
        app.rect(385, 5, length, 30);

        app.fill(0);
        app.textAlign(LEFT);
        app.text(progress+" / " +cap, 475, 30);
        tick(timeMultiplier);

    }

    /**
     * Gets current mana in the game
     * @return int, Mana
     */
    public int getMana(){
        return progress;
    }

    /**
     * Sets the progress of the mana (how much)
     * @param progress, The actual number of mana left
     * @return float, The amount of mana displayed on screen based off calculations of the whole bar
     */
    public float setProgress(int progress){
        Double d = new Double(0.325);
        float multiplier = d.floatValue();
        float length = progress * multiplier;
        return length;
    }

    /**
     * Gets status of game
     * @return boolean, game playing(false), game ended(true)
     */
    public boolean getGameEnd(){
        return gameEnd;
    }

    /**
     * Crucial in deducting mana (eg. when monster is hit or tower is bought)
     * @param toDeduct, The amount of mana to deduct from action
     */
    public void setDeduct(int toDeduct){
        progress -= toDeduct;
    }

    /**
     * Sets the mana gained
     * @param toGain, The amount of mana gained from action
     */
    public void setGain(int toGain){
        if((progress+=toGain)<=cap){
            progress += toGain;
        }
    }

    /**
     * Resets necessray elements related the mana when game is restarted
     */
    public void reset(){
        progress = initial; // Reset progress to its initial value
        length = 0; // Reset the length of the progress bar
        gameEnd = false; // Reset the gameEnd flag
        monsterEnd = false; // Reset the monsterEnd flag
        deduct = 0; // Reset the deduction amount
        lastUpdateTime = (float) (millis() / 1000.0); // Reset the lastUpdateTime
        frameCount = 0; // Reset the frameCount
    }
}
