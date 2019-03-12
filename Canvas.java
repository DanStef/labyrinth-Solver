import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements ActionListener {

	Timer timer;
	Labirint lab;
	int noCel;
	int heightWidth;
	LabirintSolver lbs;
	boolean solve;
	boolean djkSolve;
	DJKSolver dj;

	Canvas() {
		timer = new Timer(10, this);
		timer.start();
		noCel = 30;
		heightWidth = 900;
		lab = new Labirint(noCel, heightWidth);
		setFocusable(true);
		setBackground(Color.BLACK);
		lbs = new LabirintSolver(lab);
		solve = true;
		djkSolve = true;
		dj = new DJKSolver(lab, lab.celule[0][0], lab.celule[noCel-1][noCel-1]);
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if (lab.checkFinish()) {

			if (solve) {

				if (djkSolve == true) {
					//
					if(dj.isSolved) {
						for(int i=0;i<lab.noCel;i++) {
							for(int j=0;j<lab.noCel;j++) {
								lab.celule[i][j].c = Color.BLUE;
							}
						}
						djkSolve = false;
					}
//						dj.nextStep();
					if(!dj.isSolved)
						dj.nextStep();
					paint2(g);
				} else {
					lab.setNotCurrent(lab.currentPosition);
					paint2(g);
				}
				return;
			} else {
				lab.setNotCurrent(lab.currentPosition);
				lab.setCurrent(new Position(lab.noCel - 1, lab.noCel - 1));
				lab.draw(g2d);
			}

			// lab.draw(g2d);
			// lab.setNotCurrent(lab.currentPosition);
			// lab.setCurrent(new Position(lab.noCel - 1, lab.noCel - 1));
			// lab.draw(g2d);
			// paint2(g);
			// return;
		}
		lab.draw(g2d);
		// System.out.println("Updating");
		// System.out.println(lbs.curPos.y);

		lab.update();

		// lab.update();

		// test.draw(g2d, 100);

	}

	public void paint2(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if (lbs.isSolved || dj.isSolved) {
			lab.setNotCurrentSolve(new Position(lab.noCel - 1, lab.noCel - 1));
//			return;
		}

		lab.setNotCurrent(lab.currentPosition);
		lab.draw(g2d);
		
		if(djkSolve == false)
			lbs.update();           //PUNE ASTA CA SA MEARGA DFS ul!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();

	}

}
