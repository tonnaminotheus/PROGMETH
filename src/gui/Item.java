package gui;



public class Item {
	private String itemName;
	private String url;
	
	Item(String itemName){
		switch(itemName) {
			case "HorizontalBarricade" :		url = "file:res/HorizontalBarricade.png";break;
			case "VerticalBarricade" : 	url = "file:res/VerticalBarricade.png";break;
			case "Cancel" : 	url = "file:res/Cancel.png"; 	break;
			case "Home" : url = "file:res/Home.png"; break;
			case "Bomb" : 	url = "file:res/Bomb.png"; break;
			case "Move" : 	url = "file:res/Move.png"; break;
			case "RemoveBarricade" : 	url = "file:res/RemoveBarricade.png"; break;
		}
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getUrl() {
		return url;
	}
	
	
}
