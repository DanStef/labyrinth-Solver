import java.awt.Color;
import java.awt.Graphics2D;

public class Celula {

	boolean[] pereti;
	int x;
	int y;
	Color c;
	boolean isVisited;
	boolean isCurrent;
	boolean isGood;
	boolean isOnTheWay;
	Celula prev;
	Celula[] vecini;
	
	
	Celula(int x, int y) {
		pereti = new boolean[4];
		initPereti();
		this.x = x;
		this.y = y;
		c = Color.black;
		isVisited = false;
		isGood = false;
		isOnTheWay = false;
		prev = null;
	}

	private void initPereti() {
		pereti[0] = true;
		pereti[1] = true;
		pereti[2] = true;
		pereti[3] = true;
	}

	public void draw(Graphics2D g2d, int size) {
		g2d.setColor(c);
		
		g2d.fillRect(x, y, size, size);
		
		g2d.setColor(Color.WHITE);
		if (pereti[0]) {
			g2d.drawLine(x, y, x + size, y);
		}

		if (pereti[1]) {
			g2d.drawLine(x + size, y, x + size, y + size);
		}

		if (pereti[2]) {
			g2d.drawLine(x, y + size, x + size, y + size);
		}

		if (pereti[3]) {
			g2d.drawLine(x, y, x, y + size);
		}
	}
	
	public void update(int nr) {
		pereti[nr] = false;
	}
}
