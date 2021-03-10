package utilities;

public class ASTNodeDoesNotBelongHere extends Exception {
	@Override
	public String toString() {
		return "Antipattern does not belong here";
	}
}
