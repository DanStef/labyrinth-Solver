import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Stack;

public class Labirint {
	int size;
	Celula[][] celule;
	int noCel;
	Stack<Position> s = new Stack<Position>();
	Position currentPosition;
	int visited;

	Labirint(int noCel, int x) {
		this.noCel = noCel;
		size = x / noCel;
		celule = new Celula[noCel][noCel];
		initCelule();
		currentPosition = new Position(0, 0);
		setVisited(currentPosition);
		visited = 0;
	}

	public void draw(Graphics2D g2d) {
		int i, j;
		for (i = 0; i < celule.length; i++) {
			for (j = 0; j < celule.length; j++) {
				celule[i][j].draw(g2d, size);
				// break;
			}
			// break;
		}
	}

	private void initCelule() {
		for (int i = 0; i < celule.length; i++) {
			for (int j = 0; j < celule.length; j++) {
				celule[i][j] = new Celula(i * size, j * size);
			}
		}

		for (int i = 0; i < celule.length; i++) {
			for (int j = 0; j < celule.length; j++) {

				if (i == 0 && j == 0) {
					celule[i][j].vecini = new Celula[2];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i][j + 1];
				} else if (i == celule.length - 1 && j == celule.length - 1) {
					celule[i][j].vecini = new Celula[2];
					celule[i][j].vecini[0] = celule[i - 1][j];
					celule[i][j].vecini[1] = celule[i][j - 1];
				} else if (i == celule.length - 1 && j == 0) {
					celule[i][j].vecini = new Celula[2];
					celule[i][j].vecini[0] = celule[i - 1][j];
					celule[i][j].vecini[1] = celule[i][j + 1];
				} else if (i == 0 && j == celule.length - 1) {
					celule[i][j].vecini = new Celula[2];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i][j - 1];
				} else if (i == 0) {
					celule[i][j].vecini = new Celula[3];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i][j - 1];
					celule[i][j].vecini[2] = celule[i][j + 1];
				} else if (j == 0) {
					celule[i][j].vecini = new Celula[3];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i][j + 1];
					celule[i][j].vecini[2] = celule[i - 1][j];
				} else if (i == celule.length - 1) {
					celule[i][j].vecini = new Celula[3];
					celule[i][j].vecini[0] = celule[i][j + 1];
					celule[i][j].vecini[1] = celule[i][j - 1];
					celule[i][j].vecini[2] = celule[i - 1][j];
				} else if (j == celule.length - 1) {
					celule[i][j].vecini = new Celula[3];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i - 1][j];
					celule[i][j].vecini[2] = celule[i][j - 1];
				} else {
					celule[i][j].vecini = new Celula[4];
					celule[i][j].vecini[0] = celule[i + 1][j];
					celule[i][j].vecini[1] = celule[i - 1][j];
					celule[i][j].vecini[2] = celule[i][j - 1];
					celule[i][j].vecini[3] = celule[i][j + 1];
				}
			}
		}
	}

	public boolean checkFinish() {
		// for (int i = 0; i < celule.length; i++) {
		// for (int j = 0; j < celule.length; j++) {
		// if (!celule[i][j].isVisited) {
		// return false;
		// }
		// }
		// }

		//System.out.println(visited);
		if (visited != ((celule.length) * (celule.length))-1) {
			return false;
		}

		return true;
	}

	public boolean checkNeighbours(int x, int y) {

		for (int i = 0; i < celule[x][y].vecini.length; i++) {
			if (!celule[x][y].vecini[i].isVisited) {
				return true;
			}
		}

		return false;
	}

	public Position getNext(int x, int y) {
		Random r = new Random();
		int index;

		do {
			index = Math.abs(r.nextInt()) % celule[x][y].vecini.length;
		} while (celule[x][y].vecini[index].isVisited);

		Position aux = new Position(celule[x][y].vecini[index].x / size, celule[x][y].vecini[index].y / size);

		return aux;

	}

	public void setVisited(Position p) {
		celule[p.x][p.y].isVisited = true;
		celule[p.x][p.y].c = Color.BLUE;
		visited++;
	}
	
	public void setGood(Position p) {
		celule[p.x][p.y].isGood = true;
		celule[p.x][p.y].c = Color.RED;
	}
	
	public void setNotGood(Position p) {
		celule[p.x][p.y].isGood = false;
		celule[p.x][p.y].c = Color.BLUE;
	}

	public void setCurrent(Position p) {
		celule[p.x][p.y].isCurrent = true;
		celule[p.x][p.y].c = Color.GREEN;
	}

	public void setNotCurrent(Position p) {
		celule[p.x][p.y].isCurrent = false;
		celule[p.x][p.y].c = Color.BLUE;
	}
	
	public void setNotCurrentSolve(Position p) {
		celule[p.x][p.y].isCurrent = false;
		celule[p.x][p.y].c = Color.RED;
	}
	
	public void setIsOnWay(Position p) {
		celule[p.x][p.y].isOnTheWay = true;
		celule[p.x][p.y].c = Color.BLUE;
	}

	public void update() {
		if (checkFinish()) {
			setNotCurrent(currentPosition);
			currentPosition.x = noCel - 1;
			currentPosition.y = noCel - 1;
			//setCurrent(currentPosition);
			return;
		}

		if (checkNeighbours(currentPosition.x, currentPosition.y)) {

			Position nextPosition = getNext(currentPosition.x, currentPosition.y);
			s.push(currentPosition);
			if (nextPosition.x - currentPosition.x == 1) {
				celule[currentPosition.x][currentPosition.y].update(1);
				celule[nextPosition.x][nextPosition.y].update(3);
			}

			if (nextPosition.x - currentPosition.x == -1) {
				celule[currentPosition.x][currentPosition.y].update(3);
				celule[nextPosition.x][nextPosition.y].update(1);
			}

			if (nextPosition.y - currentPosition.y == 1) {
				celule[currentPosition.x][currentPosition.y].update(2);
				celule[nextPosition.x][nextPosition.y].update(0);
			}

			if (nextPosition.y - currentPosition.y == -1) {
				celule[currentPosition.x][currentPosition.y].update(0);
				celule[nextPosition.x][nextPosition.y].update(2);
			}

			setNotCurrent(currentPosition);
			currentPosition = nextPosition;

			try {
//				 Thread.sleep(10);
			} catch (Exception e) {

			}

			setVisited(currentPosition);
			setCurrent(currentPosition);

		} else {

			setNotCurrent(currentPosition);
			currentPosition = s.pop();
			setCurrent(currentPosition);
		}
	}
}
