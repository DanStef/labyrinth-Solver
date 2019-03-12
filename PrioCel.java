
public class PrioCel implements Comparable<PrioCel>{

	int cost;
	Celula c;
	
	public PrioCel(int cost, Celula c) {
		super();
		this.cost = cost;
		this.c = c;
	}

	@Override
	public int compareTo(PrioCel o) {
		if(this.cost < o.cost) {
			return -1;
		}
		
		if(this.cost > o.cost) {
			return 1;
		}
		
		return 0;
	}
	
	
}
