package gui;



public class Item {
	private String itemName;
	private String url;
	
	Item(String itemName){
		switch(itemName) {
			case "HorizontalBarricade" :		url = "HorizontalBarricade.png";break;
			case "VerticalBarricade" : 	url = "VerticalBarricade.png";break;
			case "Cancel" : 	url = "Cancel.png"; 	break;
			case "Home" : url = "Home.png"; break;
			case "Bomb" : 	url = "Bomb.png"; break;
			case "Move" : 	url = "Move.png"; break;
			case "RemoveBarricade" : 	url = "RemoveBarricade.png"; break;
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
