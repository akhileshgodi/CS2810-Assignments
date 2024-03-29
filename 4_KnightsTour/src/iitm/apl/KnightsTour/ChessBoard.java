package iitm.apl.KnightsTour;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


public abstract class ChessBoard extends JFrame {
	private static final long serialVersionUID = -5842310284734676048L;

	/**
	 * Starting column and row
	 */
	static private int knightColumn, knightRow;

	/**
	 * the size of the board is set to eight a value lesser than 8 can be used
	 * but not greater
	 */
	static private int boardSize = 6;

	/**
	 * Stack of moves played so far.
	 */
	private Stack<Move> movesStack;
	private int movesCounter;

	/*
	 * Flag variable that determines if next_move and undo_move should draw.
	 */
	static private boolean tourDraw = false;

	static private boolean tourRunning = false;
	/*
	 * Delay after each move
	 */
	private static long delayTime = 100;
	private Thread tourThread;

	// UI Elements

	private JPanel jPanel0;
	private JComboBox jComboBoxSize;
	private JComboBox jComboBoxRow;
	private JComboBox jComboBoxColumn;
	private JButton drawButton;
	private JButton playButton;
	private JButton solutionButton;
	private JButton stopButton;
	private JLabel performanceCounterLabel;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	protected static final long SLOW_DELAY = 500;

	protected static final long FAST_DELAY = 60;

	public ChessBoard() {
		initComponents();
	}

	/**
	 * Initialises the graphical components of the chess board
	 */
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Initialise components
		Component jPanel0 = getJPanel0();
		Component jComboBoxSize = getJComboBoxSize();
		Component jComboBoxRow = getJComboBoxRow();
		Component jComboBoxColumn = getJComboBoxColumn();
		Component drawButton = getDrawButton();
		Component playButton = getPlayButton();
		Component solutionButton = getSolutionButton();
		Component stopButton = getStopButton();
		Component performanceCounterLabel = getPerformanceCounterLabel();

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addComponent(jPanel0)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(jComboBoxSize, 10, 50, 100)
								.addComponent(jComboBoxColumn, 10, 50, 100)
								.addComponent(jComboBoxRow, 10, 50, 100)
								.addComponent(drawButton, 10, 50, 100)
								.addComponent(playButton, 10, 50, 100)
								.addComponent(solutionButton, 10, 50, 100)
								.addComponent(stopButton, 10, 50, 100)
								.addComponent(performanceCounterLabel, 30, 50,
										100)));

		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(jPanel0)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jComboBoxSize, 5, 15, 20)
								.addComponent(jComboBoxColumn, 5, 15, 20)
								.addComponent(jComboBoxRow, 5, 15, 20)
								.addComponent(drawButton, 5, 15, 20)
								.addComponent(playButton, 5, 15, 20)
								.addComponent(solutionButton, 5, 15, 20)
								.addComponent(stopButton, 5, 15, 20)
								.addComponent(performanceCounterLabel, 5, 15,
										20).addContainerGap()));

		setSize(800, 600);
	}

	private JButton getSolutionButton() {
		if (solutionButton == null) {
			solutionButton = new JButton();
			solutionButton.setText("Solution");
			solutionButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					solutionButtonActionActionPerformed(event);
				}
			});
		}
		return solutionButton;
	}

	private JButton getPlayButton() {
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("Play");
			playButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					playButtonActionActionPerformed(event);
				}
			});
		}
		return playButton;
	}

	private JButton getStopButton() {
		if (stopButton == null) {
			stopButton = new JButton("Stop ");
			stopButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					stopTour();
				}
			});
		}
		return stopButton;
	}

	private JComboBox getJComboBoxSize() {
		if (jComboBoxSize == null) {
			jComboBoxSize = new JComboBox();
			jComboBoxSize.setModel(new DefaultComboBoxModel(
					new Integer[] { 5, 6, 7, 8 }));
			jComboBoxSize.setDoubleBuffered(false);
			jComboBoxSize.setBorder(null);
			jComboBoxSize.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					boardSize = (Integer) jComboBoxSize.getItemAt(jComboBoxSize
							.getSelectedIndex());

				}
			});
		}
		return jComboBoxSize;
	}

	private JComboBox getJComboBoxColumn() {
		if (jComboBoxColumn == null) {
			jComboBoxColumn = new JComboBox();
			jComboBoxColumn.setModel(new DefaultComboBoxModel(
					new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }));
			jComboBoxColumn.setDoubleBuffered(false);
			jComboBoxColumn.setBorder(null);
			jComboBoxColumn.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					knightColumn = jComboBoxColumn.getSelectedIndex();

				}
			});
		}
		return jComboBoxColumn;
	}

	private JComboBox getJComboBoxRow() {
		if (jComboBoxRow == null) {
			jComboBoxRow = new JComboBox();
			jComboBoxRow.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
			jComboBoxRow.setDoubleBuffered(false);
			jComboBoxRow.setBorder(null);
			jComboBoxRow.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					knightRow = jComboBoxRow.getSelectedIndex();

				}
			});
		}
		return jComboBoxRow;
	}

	private JLabel getPerformanceCounterLabel() {
		if (performanceCounterLabel == null) {
			performanceCounterLabel = new JLabel("Steps: Run first");
		}
		return performanceCounterLabel;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setMinimumSize(new Dimension(450, 450));
		}
		return jPanel0;
	}

	private JButton getDrawButton() {
		if (drawButton == null) {
			drawButton = new JButton();
			drawButton.setText("Draw");
			drawButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					drawButtonActionActionPerformed(event);
				}
			});
		}
		return drawButton;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	// Callbacks
	private void drawButtonActionActionPerformed(ActionEvent event) {
		drawChessPattern();
		drawKnight(new Move(knightRow, knightColumn));
	}

	private void playButtonActionActionPerformed(ActionEvent event) {
		stopTour();

		tourRunning = true;
		tourThread = new Thread(new Runnable() {
			@Override
			public void run() {

				drawChessPattern();
				movesStack = new Stack<Move>();
				movesCounter = 0;

				tourDraw = true;
				delayTime = FAST_DELAY;
				List<Move> path = tour(new Move(knightRow, knightColumn));
				if (path == null) {
					updatePerformanceCounter(-1);
				}
				;
				updatePerformanceCounter(movesCounter);
			}
		}, "tourThread");
		tourThread.start();

	}

	private void solutionButtonActionActionPerformed(ActionEvent event) {
		stopTour();

		tourRunning = true;
		Thread tourThread = new Thread(new Runnable() {
			@Override
			public void run() {
				movesStack = new Stack<Move>();
				movesCounter = 0;
				tourDraw = false;
				Move initPos = new Move(knightRow, knightColumn);
				List<Move> path = tour(initPos);

				if (path == null) {
					updatePerformanceCounter(-1);
					return;
				}

				tourDraw = true;
				delayTime = SLOW_DELAY;

				drawChessPattern();
				drawKnight(initPos);
				Move currentPos = initPos;
				for( int i = 0; i< path.size(); i++)
				{
					// Draw the number of the last position
					colorSquare(currentPos, Color.yellow);
					drawNumber(currentPos, i);
					
					Move nextPos = path.get(i);
					drawKnight(nextPos);
					currentPos = nextPos;

					delay(delayTime);

				}
				updatePerformanceCounter(movesCounter);
			}
		}, "tourThread");
		tourThread.start();

	}

	// Drawing functions
	/**
	 * Fetch the starting row/column of the knight and draw the chess board
	 */
	private int marginTop = 0;
	private int marginLeft = 50;
	private int gridSize = 50;

	/**
	 * Clear the entire board
	 */
	private void clearBoard() {
		Graphics g = jPanel0.getGraphics();
		Rectangle bounds = jPanel0.getBounds();
		g.clearRect(0, 0, bounds.width, bounds.height);
	}

	/**
	 * Color a particular square
	 */
	private void colorSquare(int row, int col, Color color) {
		Graphics g = jPanel0.getGraphics();
		g.setColor(color);
		g.fillRect(marginLeft + gridSize * col, marginTop + gridSize * row,
				gridSize, gridSize);
		g.setColor(Color.black);
		g.drawRect(marginLeft + gridSize * col, marginTop + gridSize * row,
				gridSize, gridSize);
	}

	private void colorSquare(Move m, Color color) {
		colorSquare(m.row, m.col, color);
	}

	private void drawKnight(int row, int col) {
		Graphics g = jPanel0.getGraphics();
		g.setColor(Color.red);
		g.fillOval(marginLeft + gridSize * col + gridSize / 2 - 10, marginTop
				+ gridSize * row + gridSize / 2 - 10, 20, 20);
	}

	private void drawKnight(Move position) {
		drawKnight(position.row, position.col);
	}

	private void drawNumber(int row, int col, int num) {
		Graphics g = jPanel0.getGraphics();
		g.setColor(Color.black);
		// drawString draws at the baseline of the specified coordinate.
		// Consider center - a little.
		g.drawString(Integer.toString(num), marginLeft + gridSize * col
				+ gridSize / 2 - 5, marginTop + gridSize * row + gridSize / 2);
	}

	private void drawNumber(Move m, int num) {
		drawNumber(m.row, m.col, num);
	}

	private void drawChessPattern() {
		clearBoard();
		for (int i = 0; i < getBoardSize(); i++) {
			for (int j = 0; j < getBoardSize(); j++) {
				if ((i + j) % 2 == 0)
					colorSquare(i, j, Color.white);
				else
					colorSquare(i, j, Color.black);
			}
		}
	}

	private void updatePerformanceCounter(int moves) {
		if (moves < 0) {
			performanceCounterLabel.setText("No Solution");
		} else {
			performanceCounterLabel.setText("Steps: " + movesCounter);
		}
		performanceCounterLabel.updateUI();
	}

	private void delay(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	// ----- protected moves that can be accessed by child classes ------

	/**
	 * Sets up the screen. Must be called before using next_move and undo_move
	 * 
	 * @param frame
	 *            : The graphics frame
	 */
	protected static void initialise(final ChessBoard frame) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Knight's Tour");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * This function simulates a move of the current knight.
	 * 
	 * @param nextPos
	 *            : Next position of the knight
	 * @return: True if position is legal; false otherwise
	 */
	protected boolean makeMove(Move nextPos)// -----need
	{
		if (movesStack.size() >= boardSize * boardSize
				|| !isNextMoveValid(nextPos))
			return false;

		Move currentPos = (!movesStack.empty()) ? movesStack.peek() : null;
		movesStack.push(nextPos);
		movesCounter++;

		updatePerformanceCounter(movesCounter);
		if (!tourDraw || !tourRunning)
			return true;

		if (currentPos != null) {
			// Draw the number of the last position
			colorSquare(currentPos, Color.yellow);
			drawNumber(currentPos, movesStack.size() - 1);
		}
		drawKnight(nextPos);

		delay(delayTime);

		return true;
	}

	/*
	 * returns false if no move has been made previously
	 * 
	 * it undo's the previous move
	 */
	protected boolean undoMove() {
		if (movesStack.size() <= 1)
			return false;

		Move currentPos = movesStack.pop();
		Move prevPos = movesStack.peek();

		if (!tourDraw || !tourRunning)
			return true;

		// Undo the current position
		if ((currentPos.row + currentPos.col) % 2 == 0)
			colorSquare(currentPos, Color.white);
		else
			colorSquare(currentPos, Color.black);

		if ((prevPos.row + prevPos.col) % 2 == 0)
			colorSquare(prevPos, Color.white);
		else
			colorSquare(prevPos, Color.black);
		drawKnight(prevPos);

		delay(delayTime);
		return true;
	}

	static protected int getBoardSize() {
		return boardSize;
	}
	
	protected boolean isRunning() {
		return tourRunning;
	}

	private void stopTour() {
		tourRunning = false;
		if (tourThread != null && tourThread.isAlive())
			tourThread.interrupt();
	}

	/**
	 * Verifies if m can be a valid next move in the given board position
	 */
	protected boolean isNextMoveValid(Move m) {
		if (!m.isValid())
			return false;

		if (movesStack != null && !movesStack.empty()) {

			// checking if it is a knight's move
			Move prev = movesStack.peek();
			if ((Math.abs(prev.row - m.row) == 1 && Math.abs(prev.col - m.col) != 2)
					|| (Math.abs(prev.row - m.row) == 2 && Math.abs(prev.col
							- m.col) != 1))
				return false;

			// checking whether it is visiting a previously visited position

			if (movesStack.contains(m))
				return false;
		}

		return true;
	}

	/**
	 * This method should implement the logic of finding a knight's tour.
	 * 
	 * It should make calls - next_move(m1) whenever it chooses m1 as the next
	 * move, including the first position - undo_move() whenever it wants to
	 * revert back by 1 move
	 * 
	 * @param m
	 *            : Initial position of the knight as parameter m
	 * @return: The sequence of moves the knight should take if a solution is
	 *          found else null
	 * 
	 */
	abstract List<Move> tour(Move m);

}
