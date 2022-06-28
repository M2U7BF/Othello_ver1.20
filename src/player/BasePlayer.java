package player;

public class BasePlayer {
	private boolean isFirst;
	
	public void Pass() {}
	
	 public void turnOver() {
		
	}
	 
	public void placing() {
		
	}
	
	private boolean isMyTurn() {
		return false;
	}
	
	public boolean canTurnOver() {
		return false;
	}
	
	public boolean isDifferent() {
		return false;
	}
	
	public boolean canPlacing() {
		return false;
	}
	
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
}
