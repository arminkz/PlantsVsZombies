package View.Element;

public class SunState {

    private int positionX;
    private int positionY;
    private int endPositionY;
    
    private int destructTime = 200;
    
    public SunState(int startX, int startY, int endY) {
    	this.positionX = startX;
    	this.positionY = startY;
    	this.endPositionY = endY;
    }
    
    public void setPositionX(int startX) {
    	this.positionX = startX;
    }
    
    public int getPositionX() {
    	return this.positionX;
    }
    
    public void setPositionY(int startY) {
    	this.positionY = startY;
    }
    
    public int getPositionY() {
    	return this.positionY;
    }
    
    public void setEndPositionY(int endY) {
    	this.endPositionY = endY;
    }
    
    public int getEndPositionY() {
    	return this.endPositionY;
    }
    
    public int getDestructionTime() {
    	return this.destructTime;
    }
    
    public void updateState() {
		if (positionY < endPositionY) {
            positionY += 4;
        } else {
            destructTime--;
        }
	}
}
