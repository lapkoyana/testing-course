package task1.pack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
	@Test
	public void distanceTest() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(1, 3);
		
		Assert.assertEquals(p1.distance(p2), 1.0);
	}
}
