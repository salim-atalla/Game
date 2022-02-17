import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game extends JPanel implements KeyListener {

	
	// Attributs
	private JFrame frame;
	private int dx, dy;
	private double random;
	private int rx, ry;
	private int key;
	private boolean firstApple;
	private int score = -1;
	private JPanel info = new JPanel();
	private JLabel fin = new JLabel("GAME OVER!");
	private JLabel finScore = new JLabel();
	private JLabel s = new JLabel();
	private JLabel time = new JLabel();
	private long start = System.currentTimeMillis();
	private long end = System.currentTimeMillis();

	
	// Constructeur
	public Game() {
		super();
		frame = new JFrame();
		frame.setSize(500, 525);
		frame.setTitle("Snake");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setLayout(null);
		this.setLayout(null);
		info.setLayout(null);
		info.setBounds(0, 0, 500, 25);
		this.add(info);
		
		
		
		time.setBounds(110, 0, 100, 25);
		info.add(time);
		
		s.setBounds(325, 0, 100, 25);
		info.add(s);
		
		fin.setFont(fin.getFont().deriveFont(20.0f));
		fin.setForeground(Color.WHITE);
		fin.setBounds(175, 210, 150, 50);
		
		finScore.setFont(fin.getFont().deriveFont(20.0f));
		finScore.setForeground(Color.WHITE);
		finScore.setBounds(160, 250, 200, 50);
		
		random = Math.random();
		dx = (int) (random*430)+25;
		random = Math.random()+25;
		dy = (int) (random*430);
		
		firstApple = true;
	}
	
	// M�thodes
	
	private void getApple(Graphics g, int rx, int ry) {
		
	    g.setColor(Color.RED);
	    g.fillOval(rx, ry, 15, 15);
	}
	
	
	
	
	public void paintComponent(Graphics g) {
		
		
	if(end-start <= 30000 ) {	

			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 500, 500);
			
			
			s.setText("Score : " + score);
			time.setText("Time left : " + (30-(end-start)/1000) + " s");
			
			g.setColor(Color.BLUE);
			g.fillRect(dx, dy, 15, 15);
			
			
			try { Thread.sleep(3); } catch(Exception e) {}
			
			
		    if (key == KeyEvent.VK_LEFT) {
		        dx--;
		    }
	
		    if (key == KeyEvent.VK_RIGHT) {
		        dx++;
		    }
	
		    if (key == KeyEvent.VK_UP) {
		        dy--;
		    }
	
		    if (key == KeyEvent.VK_DOWN) {
		        dy++;
		    }
		    
		    // pour d�blocker les barres.
		    if(dx >460) {
		    	dx = dx - 460;
		    }
		    if(dy >460) {
		    	dy = dy - (460-35);
		    }
		    if(dx <10) {
		    	dx = dx + 460;
		    }
		    if(dy <35) {
		    	dy = dy + (460-35);
		    }
		    
		    /*
		     // pour blocker les barres.
		    if(dx > 460) {
		    	dx = 460;
		    }
		    if(dy > 460) {
		    	dy = 460;
		    }
		    if(dx < 10) {
		    	dx = 10;
		    }
		    if(dy < 35) {
		    	dy = 35;
		    }
		    */
		     
		    
		    getApple(g, this.rx, this.ry);
		    
		    if((this.dx < this.rx+15 && this.dx > this.rx-15) && (this.dy < this.ry+15 && this.dy > this.ry-15) || firstApple) {	
		    	
				random = Math.random();
			    rx = (int) (random*400)+25;
			    random = Math.random();
			    ry = (int) (random*400)+25;
		    	
		    	firstApple = false;
		    	score++;
		    }
		    g.setColor(Color.WHITE);
		    g.fillRect(0, 0, 500, 25);
		    
		    
		    end = System.currentTimeMillis();
		    System.out.println((end-start)/1000);
			repaint();

		}else {
			
			this.setBackground(Color.gray);
			this.add(fin);
			finScore.setText("Your Score is : " + score);
			this.add(finScore);
			time.setText("Time left : " + 0 + " s");
			
			
			System.out.println("GAME OVER!");
			System.out.println("Your score is: " + score);
		}
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		key = e.getKeyCode();
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
