package logic;

public class removeBarricadeFail extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// you CAN add SerialVersionID if eclipse gives you warning
		public String message;
		public removeBarricadeFail(String e)
		{
			this.message=e;
		}

}
