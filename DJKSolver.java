import java.awt.Color;
import java.util.PriorityQueue;

public class DJKSolver {

	Labirint l;
	PriorityQueue<PrioCel> q;
	Celula target;
	boolean[][] mat;
	boolean isSolved = false;
	
	public DJKSolver(Labirint l,Celula c,Celula target) {

		this.l = l;
		q = new PriorityQueue<>();
		q.add(new PrioCel(0, c));
		
		mat = new boolean[target.x][target.y];
		this.target = target;
	}
	
	public boolean nextStep() {
		
		if(q.isEmpty()) {
			return false;
		}
		
		PrioCel curr = q.poll();
		Celula cel = curr.c;
		
//		System.out.println(cel.x + " " + cel.y + " " + curr.cost);
//		
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		cel.c = Color.green;
		if(cel.x == target.x && cel.y == target.y) {
			cel.c = Color.green;
			
			Celula ant = cel.prev;
			
			for(int i=0;i<l.noCel;i++) {
				for(int j=0;j<l.noCel;j++) {
					l.celule[i][j].c = Color.BLUE;
				}
			}
			
			while(ant!=null) {
				ant.c = Color.RED;
				ant = ant.prev;
			}
			
			isSolved = true;
			return false;
		}
		
		for(int i=0;i<l.noCel;i++) {
			for(int j=0;j<l.noCel;j++) {
				l.celule[i][j].c = Color.BLUE;
			}
		}
		
		l.celule[cel.x/l.size][cel.y/l.size].c = Color.GREEN;
		mat[cel.x/l.size][cel.y/l.size] = true;
		
		Celula ant = cel.prev;
		while(ant!=null) {

			ant.c = Color.RED;
			ant = ant.prev;
		}
		
		for(int i=0;i<cel.vecini.length;i++) {
//			System.out.println(cel.vecini.length);
			if(cel.vecini[i]!=null && !mat[cel.vecini[i].x/l.size][cel.vecini[i].y/l.size] && cel.vecini[i].prev==null) {
				
				if(!checkIfWall(cel, cel.vecini[i])) {
					cel.vecini[i].prev = cel;
					q.add(new PrioCel(curr.cost+1, cel.vecini[i]));
				}
			}
		}
		

		return true;
	}
	
	private boolean checkIfWall(Celula cel, Celula vec) {
//		System.out.println("checkWall x:" + cel.x + " " + vec.x + " y:"+ cel.y + " " + vec.y);
		if(cel.x == vec.x) {
			if(cel.y > vec.y) {
				if(cel.pereti[0] == true) {
					return true;
				}
			} else {
				if(cel.pereti[2] == true) {
					return true;
				}
			}
		}
		
		if(cel.y == vec.y) {
			if(cel.x > vec.x) {
				if(cel.pereti[3] == true) {
					return true;
				}
			} else {
				if(cel.pereti[1] == true) {
					return true;
				}
			}
		}
		return false;
	}
	
}
