package task1.pack;

public class MainClass {
	public static void main(String[] args) {
	System.out.println("Some text");
		
		Point p1 = new Point(1,2);
		System.out.println("Первая точка с координатами - (" + p1.x + ", " + p1.y + ")");
		
		Point p2 = new Point(1, 3);
		System.out.println("Вторая точка с координатами - (" + p2.x + ", " + p2.y + ")");
		
		System.out.println("Расстояние между точками - " + p1.distance(p2));
	}
	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
}
