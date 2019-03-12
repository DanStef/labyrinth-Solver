import java.awt.Graphics2D;
import java.util.Stack;

public class LabirintSolver {

	Labirint l;
	Position curPos;
	Stack<Position> s = new Stack<Position>();
	boolean isSolved;

	LabirintSolver(Labirint l) {
		this.l = l;
		curPos = new Position(0, 0);
		// l.setNotCurrent(new Position(l.noCel - 1, l.noCel - 1));
		l.setGood(curPos);
		l.setCurrent(curPos);
		l.setIsOnWay(curPos);
		isSolved = false;

	}

	public void draw(Graphics2D g2d) {
		int i, j;
		for (i = 0; i < l.celule.length; i++) {
			for (j = 0; j < l.celule.length; j++) {
				l.celule[i][j].draw(g2d, l.size);
				// break;
			}
			// break;
		}
	}

	public void update() {

		if (curPos.x == l.noCel - 1 && curPos.y == l.noCel - 1) {
			isSolved = true;
			return;
		}

		// System.out.println(curPos.x);
		// System.out.println(curPos.y);
		try {
			// Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (canGoDown(curPos)) {
			goDown(curPos);
			return;
		}

		if (canGoUp(curPos)) {
			goUp(curPos);
			return;
		}

		if (canGoLeft(curPos)) {
			goLeft(curPos);
			return;
		}

		if (canGoRight(curPos)) {
			goRight(curPos);
			return;
		}

		l.setNotGood(curPos);
		l.setNotCurrent(curPos);

		curPos = s.pop();
		l.setGood(curPos);
		l.setCurrent(curPos);

		// l.celule[0][0].c = Color.cyan;
	}

	public boolean canGoRight(Position p) {
		if (!l.celule[p.x][p.y].pereti[1] && !l.celule[p.x + 1][p.y].isOnTheWay) {
			return true;
		}

		return false;
	}

	public boolean canGoLeft(Position p) {
		if (!l.celule[p.x][p.y].pereti[3] && !l.celule[p.x - 1][p.y].isOnTheWay) {
			return true;
		}

		return false;
	}

	public boolean canGoUp(Position p) {
		if (!l.celule[p.x][p.y].pereti[0] && !l.celule[p.x][p.y - 1].isOnTheWay) {
			return true;
		}

		return false;
	}

	public boolean canGoDown(Position p) {

		if (!l.celule[p.x][p.y].pereti[2] && !l.celule[p.x][p.y + 1].isOnTheWay) {
			return true;
		}

		return false;
	}

	public void goRight(Position p) {
		Position aux = new Position(p.x + 1, p.y);
		s.push(curPos);
		l.setNotCurrentSolve(p);
		curPos = aux;
		l.setGood(curPos);
		l.setIsOnWay(curPos);
		l.setCurrent(curPos);
	}

	public void goLeft(Position p) {
		Position aux = new Position(p.x - 1, p.y);
		s.push(curPos);
		l.setNotCurrentSolve(p);
		curPos = aux;
		l.setGood(curPos);
		l.setCurrent(curPos);
		l.setIsOnWay(curPos);
	}

	public void goUp(Position p) {
		Position aux = new Position(p.x, p.y - 1);
		s.push(curPos);
		l.setNotCurrentSolve(p);
		curPos = aux;
		l.setGood(curPos);
		l.setCurrent(curPos);
		l.setIsOnWay(curPos);
	}

	public void goDown(Position p) {
		Position aux = new Position(p.x, p.y + 1);
		s.push(curPos);
		l.setNotCurrentSolve(p);
		curPos = aux;
		l.setGood(curPos);
		l.setCurrent(curPos);
		l.setIsOnWay(curPos);
	}
}
