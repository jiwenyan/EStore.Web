package EStore.Web.Model;

public enum ShoppingCardStatus {
	unknown(1),
	initialized(2),
	pending(3),
	paied(4),
	confirmed(5),
	completed(6),
	refunded(7),
	closed(8),
	onError(9);
	
	
	private int code;
	
	private ShoppingCardStatus(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}

}
