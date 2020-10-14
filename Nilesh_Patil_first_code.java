class ACheckpoint 
{ 

	static int INF = 10000; 

	static class Checkpoint 
	{ 
		int x; 
		int y; 

		public Checkpoint(int x, int y) 
		{ 
			this.x = x; 
			this.y = y; 
		} 
	}; 

	static boolean onSegment(Checkpoint p, Checkpoint q, Checkpoint r) 
	{ 
		if (q.x <= Math.max(p.x, r.x) && 
			q.x >= Math.min(p.x, r.x) && 
			q.y <= Math.max(p.y, r.y) && 
			q.y >= Math.min(p.y, r.y)) 
		{ 
			return true; 
		} 
		return false; 
	} 

	static int checkOrientation(Checkpoint p, Checkpoint q, Checkpoint r) 
	{ 
		int val = (q.y - p.y) * (r.x - q.x) 
				- (q.x - p.x) * (r.y - q.y); 

		if (val == 0) 
		{ 
			return 0; // colinear 
		} 
		return (val > 0) ? 1 : 2; // clock or counterclock wise 
	} 


	static boolean checkIntersect(Checkpoint p1, Checkpoint q1, 
							Checkpoint p2, Checkpoint q2) 
	{ 
 
		int o1 = checkOrientation(p1, q1, p2); 
		int o2 = checkOrientation(p1, q1, q2); 
		int o3 = checkOrientation(p2, q2, p1); 
		int o4 = checkOrientation(p2, q2, q1); 

	
		if (o1 != o2 && o3 != o4) 
		{ 
			return true; 
		} 


		if (o1 == 0 && onSegment(p1, p2, q1)) 
		{ 
			return true; 
		} 

		if (o2 == 0 && onSegment(p1, q2, q1)) 
		{ 
			return true; 
		} 

		if (o3 == 0 && onSegment(p2, p1, q2)) 
		{ 
			return true; 
		} 

 
		if (o4 == 0 && onSegment(p2, q1, q2)) 
		{ 
			return true; 
		} 

		return false; 
	} 

	static boolean checkInside(Checkpoint polygon[], int n, Checkpoint p) 
	{ 
		
		if (n < 3) 
		{ 
			return false; 
		} 

		
		Checkpoint extreme = new Checkpoint(INF, p.y); 

		int count = 0, i = 0; 
		do
		{ 
			int next = (i + 1) % n; 

			if (checkIntersect(polygon[i], polygon[next], p, extreme)) 
			{ 

				if (checkOrientation(polygon[i], p, polygon[next]) == 0) 
				{ 
					return onSegment(polygon[i], p, 
									polygon[next]); 
				} 

				count++; 
			} 
			i = next; 
		} while (i != 0); 

		
		return (count % 2 == 1); 
	} 

	
	public static void main(String[] args) 
	{ 
		Checkpoint polygon1[] = {new Checkpoint(0, 0), 
							new Checkpoint(10, 0), 
							new Checkpoint(10, 10), 
							new Checkpoint(0, 10)}; 
		int n = polygon1.length; 
		Checkpoint p = new Checkpoint(20, 20); 
		if (checkInside(polygon1, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
		p = new Checkpoint(5, 5); 
		if (checkInside(polygon1, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
		Checkpoint polygon2[] = {new Checkpoint(0, 0), 
			new Checkpoint(5, 5), new Checkpoint(5, 0)}; 
		p = new Checkpoint(3, 3); 
		n = polygon2.length; 
		if (checkInside(polygon2, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
		p = new Checkpoint(5, 1); 
		if (checkInside(polygon2, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
		p = new Checkpoint(8, 1); 
		if (checkInside(polygon2, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
		Checkpoint polygon3[] = {new Checkpoint(0, 0), 
							new Checkpoint(10, 0), 
							new Checkpoint(10, 10), 
							new Checkpoint(0, 10)}; 
		p = new Checkpoint(-1, 10); 
		n = polygon3.length; 
		if (checkInside(polygon3, n, p)) 
		{ 
			System.out.println("True"); 
		} 
		else
		{ 
			System.out.println("False"); 
		} 
	} 
} 


