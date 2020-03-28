package logic;

public class addBarricadeFail extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// you CAN add SerialVersionID if eclipse gives you warning
	public String message;

	public addBarricadeFail(String message) {
		this.message = message;
	}
}
