package utilities;

public class Unit<T> {
	
	private T value;
	
	public Unit(T value) {
		this.value = value;
	}
	
	public void setAt0(T value) {
		this.value = value;
	}
	
	public T getValue0() {
		return value;
	}
}
