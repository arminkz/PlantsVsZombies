package View.Element;

public class SunState extends State {
	private int endPositionY;
    private int destructTime;
    
    public SunState(int startX, int startY, int endY) {
		super(startX, startY);
		this.endPositionY = endY;
		this.destructTime = 200;
	}
    
    public void setEndPositionY(int endY) {
    	this.endPositionY = endY;
    }
    
    public int getEndPositionY() {
    	return this.endPositionY;
    }
    
    public void setDestructionTime(int destructionTime) {
    	this.destructTime = destructionTime;
    }
    
    public int getDestructionTime() {
    	return this.destructTime;
    }
    
    @Override
    public void updateState() {
		if (this.getPositionY() < this.endPositionY) {
            this.setPositionY(this.getPositionY() + 4);
        } else {
            this.setDestructionTime(this.getDestructionTime() - 1);
        }
	}
}
