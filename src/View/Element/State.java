package View.Element;

public abstract class State {
    private int positionX;
    private int positionY;
    
    public State(int startX, int startY) {
    	this.positionX = startX;
    	this.positionY = startY;
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
    
    public abstract void updateState();
}


