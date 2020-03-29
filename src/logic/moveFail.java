package logic;

public class moveFail extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// you CAN add SerialVersionID if eclipse gives you warning
	public String message;

	public moveFail(String message) {
		this.message=message;
	}
}
