package diaz;

public class GameObjectFactory {
	
	public static GameObject createGameObject (String type, int x, int y) {
		if (type.equalsIgnoreCase("A")) {
			return new Type_A_GameObject (x, y);
		}
		
		if (type.equalsIgnoreCase("B")) {
			return new Type_B_GameObject (x, y);
		}
		
		if (type.equalsIgnoreCase("C")) {
			return new Type_C_GameObject (x, y);
		}
		
		if (type.equalsIgnoreCase("D")) {
			return new Type_D_GameObject (x, y);
		}
		return null;
	}

}
