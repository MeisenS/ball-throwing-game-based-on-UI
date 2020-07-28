import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class drive extends JFrame implements ChangeListener, ActionListener{

	Timer timer,subtimer;
	
	Random r=new Random();
	
	int cann=80,ra=40,dis=r.nextInt(800)+100,sco=100,round=5,r1,r2,r3,r4,r5,counter=0;
	double ang=45,rad=Math.toRadians(ang),cos=Math.cos(rad)*cann,sin=Math.sin(rad)*cann,time=0,subtime=0,fx,fy;;
	boolean launch1,regame1;
	int[] rank= {0,0,0,0,0};
	
	JButton launch,regame,start,quit,back,see;
	JSlider speed,angle;
	JLabel instruct,status,score,result,sets,seta,follow,label,rule1,rule2,rule3,rule4,rule5,rule6,rule7,rule8,rule9,outcome,title,rank1,rank2,rank3,rank4,rank5;

	Box page1=Box.createVerticalBox();
	Box page2=Box.createVerticalBox();
	Box page3=Box.createVerticalBox();
    Box board=Box.createVerticalBox();
    
	
	public class paint extends JComponent{
		
		
		public void paintComponent(Graphics g) {
			
			Graphics2D g2=(Graphics2D)g;
			
			ang=angle.getValue();
			rad=Math.toRadians(ang);
			cos=Math.cos(rad)*cann;
			sin=Math.sin(rad)*cann;
			g2.setStroke(new BasicStroke(19.0f));
			g.drawLine(0, getHeight(),(int)cos, (int)(getHeight()-sin));
			g2.setStroke(new BasicStroke(6.0f));
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, getHeight(),(int)cos, (int)(getHeight()-sin));
			g2.setStroke(new BasicStroke(1.0f));
																							//set up launch platform
			
			
			
			
			g.setColor(Color.orange);
			g2.setStroke(new BasicStroke(6.0f));
			
			g.fillOval(dis-ra/2, getHeight()-20-ra/2, ra, ra);
			
			g2.setStroke(new BasicStroke(1.0f));
			
			                                                                              //set up little orange target
			
			
			
			g.setColor(Color.red);
			if(round<=0) {
				if(counter<=4) {
					rank[counter]=sco;
					
				}else {
					if(sco>rank[4]) {
						rank[4]=sco;
					}
				}
				
				int middle=0;
				for(int i=0;i<5;i++) {
					for(int j=i;j<5;j++) {
						if(rank[i]<rank[j]) {
							middle=rank[i];
							
							rank[i]=rank[j];
							
							rank[j]=middle;
			
						}
					}                                                               //use nested for loops to sort cards
				}
				
				rank1.setText("     1. "+rank[0]);
				rank2.setText("     2. "+rank[1]);
				rank3.setText("     3. "+rank[2]);
				rank4.setText("     4. "+rank[3]);
				rank5.setText("     5. "+rank[4]);
				
				counter++;
				
				
				sco=100;
				round=5;
				status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
				score.setText("your score now is:"+sco+"points");
				dis=r.nextInt(800)+100;
				repaint();
			}else if(launch1) {
				follow.setText("     -1");
				launch.disable();
				timer.start();
				
				fx=cos+Math.cos(rad)*speed.getValue()*time;
				fy=(Math.sin(rad)*speed.getValue()*time-0.5*10*time*time)+sin;
				
				g.fillOval((int)(fx)-6, (int)((getHeight()-fy))-6, 12, 12);
				                                                                          //flying projectile
				
				
				
				if(((getHeight()-fy)>=getHeight()-20)) {                                 //stop at the edge of the Frame
					
					
					timer.stop();
					subtimer.start();                                                    //after hitting the ground, another timer to print informatively 
					
					
					if(Math.abs(fx-dis)<=35) {
						result.setText("DIRECT HITTT");
						follow.setText("     -1+5");
						
					}else if(Math.abs(fx-dis)<=65) {
						result.setText("OHHHH nearly miss");
						follow.setText("     -1+1");
						
					}else if(fx-dis>=65) {
						result.setText("went TOOOO long");
						follow.setText("     -1-3");
					
					}else if(fx-dis<=-65) {
						result.setText("fell TOOOO short");
						follow.setText("     -1-3");
						
					}                                                                   //informative display of result
					
					
					if(subtime>=13) {                                               
						
						if(Math.abs(fx-dis)<=35) {
							dis=r.nextInt(getWidth()-150)+100;
							sco+=5;
							score.setText("your score now is:"+sco+"points");
						}else if(Math.abs(fx-dis)<=65) {
							sco+=1;
							score.setText("your score now is:"+sco+"points");
						}else if(fx-dis>=65) {
							sco-=3;
							score.setText("your score now is:"+sco+"points");
						}else if(fx-dis<=-65) {
							sco-=3;
							score.setText("your score now is:"+sco+"points");
						}
						                                                            
						sco-=1;
						score.setText("your score now is:"+sco+"points");
						                                                            //calculate and show score
						
						result.setText("            ");
						follow.setText("            ");
						repaint();
						round--;
						status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
			
						launch1=false;
						time=0;
						subtimer.stop();
						subtime=0;
						launch.enable();                                         //initialize next launch
					}
					
					
				}
				
			}
			
		}
		
	}
	
	public drive() {
		Box tp1=Box.createHorizontalBox();
		Box tp2=Box.createHorizontalBox();
		Box tp3=Box.createHorizontalBox();
		Box tp4=Box.createHorizontalBox();
		Box tp5=Box.createHorizontalBox();
		Box tp6=Box.createHorizontalBox();
		Box tp7=Box.createHorizontalBox();
		Box tp8=Box.createHorizontalBox();
		Box tp9=Box.createHorizontalBox();
		Box tp10=Box.createHorizontalBox();
		Box tp11=Box.createHorizontalBox();                                            //Boxes to arrange Box layout
	   
		  
		JLabel label = new JLabel ("Projectile Game");
		label.setFont(new Font("Comic Sans MS", Font.BOLD,30));
		tp1.add(label);
		
		start = new JButton ("START!");
		start.addActionListener(this);
		start.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		   
		rule1 = new JLabel("Rule 1: In each round, you will be able to set the launch angle and speed in order to hit the yellow target.");
		rule1.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		tp2.add(rule1);
		    
		rule2 = new JLabel("Rule 2: Your default score is 100, and each launch will cost you 1 point.");
		rule2.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp3.add(rule2);
		
		rule3 = new JLabel("Rule 3: You will earn 5 points when you directly hit the target, and earn 1 point when you nearly miss the target.");
		rule3.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp4.add(rule3);
		
		rule4 = new JLabel("Rule 4: You will loss 3 points when you fell too short or too long.");
		rule4.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp5.add(rule4);
		
		rule5 = new JLabel("Rule 5: There will be five rounds for each game.");
		rule5.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp6.add(rule5);
		
		rule6 = new JLabel("Rule 6: The target won't change its position until you directly hit it or the five rounds are over.");
		rule6.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp7.add(rule6);
		
		rule7 = new JLabel("Rule 7: Press 'Exit'to exit and see the highest score or 'New Game' to reset all values and start over.");
		rule7.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp8.add(rule7);
		
		rule9 = new JLabel("Rule 8: After these five rounds,your score will be recorded and placed on the Ranking board.");
		rule9.setFont(new Font("TimesRoman", Font.PLAIN, 20));  
		tp10.add(rule9);
		                                                                           //game rules
		
		
		
		tp11.add(Box.createGlue());
		tp11.add(start);
		tp11.add(Box.createGlue());
		
		page1.add(Box.createGlue());
		page1.add(tp1);
		page1.add(Box.createGlue());
		page1.add(tp2);
		page1.add(Box.createGlue());
		page1.add(tp3);
		page1.add(Box.createGlue());
		page1.add(tp4);
		page1.add(Box.createGlue());
		page1.add(tp5);
		page1.add(Box.createGlue());
		page1.add(tp6);
		page1.add(Box.createGlue());
		page1.add(tp7);
		page1.add(Box.createGlue());
		page1.add(tp8);
		page1.add(Box.createGlue());
		page1.add(tp10);
		page1.add(Box.createGlue());
		page1.add(tp11);
		                                                                                  //instruction page
		
		
		
		
		timer=new Timer(30,new callback());
		subtimer=new Timer(30,new callback());
		
		Box top1=Box.createHorizontalBox();
		Box top2=Box.createHorizontalBox();
		Box top3=Box.createHorizontalBox();
		Box top4=Box.createHorizontalBox();
		Box butt=Box.createHorizontalBox();
			

		speed=new JSlider(JSlider.HORIZONTAL,50,110,60);
		speed.setPaintTicks(true);
		speed.setMajorTickSpacing(10);
		speed.setMinorTickSpacing(2);
		speed.addChangeListener(this);
		speed.setPaintLabels(true);
		
		angle=new JSlider(JSlider.HORIZONTAL,0,90,45);
		angle.setPaintTicks(true);
		angle.setMajorTickSpacing(10);
		angle.setMinorTickSpacing(5);
		angle.addChangeListener(this);
		angle.setPaintLabels(true);
		
		status=new JLabel("remaining rounds: 5     now angle:45degrees, now speed:60m/s        ");
		score=new JLabel("your score now is 100 points");
		sets=new JLabel("set speed:");
		seta=new JLabel("set angle:");
		result=new JLabel("          ");
		follow=new JLabel("          ");
		follow.setFont(new Font("TimesRoman", Font.BOLD, 24)); 
		title=new JLabel("Ranking Board");
		rank1=new JLabel("     1. 0");
		rank2=new JLabel("     2. 0");
		rank3=new JLabel("     3. 0");
		rank4=new JLabel("     4. 0");
		rank5=new JLabel("     5. 0");
		
		launch=new JButton("LAUNCH!");
		launch.addActionListener(this);
		regame=new JButton("New Game");
		regame.addActionListener(this);
		quit=new JButton("Exit");
		quit.addActionListener(this);
		back=new JButton("back");
		back.addActionListener(this);
		see=new JButton("check rules again");
		see.addActionListener(this);
		
		board.add(title);
		board.add(rank1);
		board.add(rank2);
		board.add(rank3);
		board.add(rank4);
		board.add(rank5);
		
		top1.add(see);
		top1.add(Box.createHorizontalGlue());
		top1.add(seta);
		top1.add(angle);
		top1.add(sets);
		top1.add(speed);
		top1.add(Box.createHorizontalGlue());
		top1.add(launch);
		top1.add(regame);
		top1.add(Box.createHorizontalGlue());
		top1.add(quit);
		
		top2.add(Box.createHorizontalGlue());
		top2.add(status);
		top2.add(score);
		top2.add(follow);
		top2.add(Box.createHorizontalGlue());
		
		top3.add(board);
		
		top4.add(result);
		
		paint b=new paint();
		butt.add(b);
		
		page2.add(top1);
		page2.add(top2);
		page2.add(top3);
		page2.add(Box.createVerticalGlue());
		page2.add(top4);
		page2.add(butt);                                                                    //game page
		
		
		
		
		
		Box fin=Box.createHorizontalBox();
		Box tfin=Box.createHorizontalBox();
		outcome=new JLabel("Congratulations! your final scoer is "+sco+"!");
		
		
		tfin.add(back);
		tfin.add(Box.createHorizontalGlue());
		
		fin.add(Box.createGlue());
		fin.add(outcome);
		fin.add(Box.createGlue());
		
		page3.add(tfin);
		page3.add(Box.createGlue());
		page3.add(fin);
		page3.add(Box.createGlue());
		                                                                                    //outcome page
		
		
		
		
		add(page2);
		page2.setVisible(false);
		add(page3);
		page3.setVisible(false);
		
		add(page1);
		                                                                                    //set up switch-able JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==launch) {
			launch1=true;
			status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
			
			repaint();
			                                                                              //set boolean launch1 true to execute codes in paint class.
		}
		if(e.getSource()==regame) {
			dis=r.nextInt(getWidth()-150)+100;
			sco=100;
			round=5;
			
			status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
			score.setText("your score now is:"+sco+"points");
			
			for(int i=0;i<5;i++) {
				rank[i]=0;
			}
			
			rank1.setText("     1. "+rank[0]);
			rank2.setText("     2. "+rank[1]);
			rank3.setText("     3. "+rank[2]);
			rank4.setText("     4. "+rank[3]);
			rank5.setText("     5. "+rank[4]);
			
			
			repaint();
			                                                                                //reset all values
		}
		if(e.getSource()==start) {
			page1.setVisible(false);
			remove(page1);
			
			add(page2);
			page2.setVisible(true);
			                                                                               //switch JFrame
		}
		if(e.getSource()==quit) {
			page2.setVisible(false);
			remove(page2);
			
			if(rank[0]==0) {
				outcome.setFont(new Font("Comic Sans MS", Font.BOLD,30));
				outcome.setText("Sorry, you don't have any recorded score yet.");
			}else{
				outcome.setFont(new Font("Comic Sans MS", Font.BOLD,30));
				outcome.setText("Congratulations! your highest scoer is "+rank[0]+"!");
			}
			
			add(page3);
			page3.setVisible(true);
			                                                                                 //switch JFrame
		}
		if(e.getSource()==back) {
			page3.setVisible(false);
			remove(page3);
			
			dis=r.nextInt(getWidth()-150)+100;
			sco=100;
			round=5;
			
			status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
			score.setText("your score now is:"+sco+"points");
			
			for(int i=0;i<5;i++) {
				rank[i]=0;
			}
			
			rank1.setText("     1. "+rank[0]);
			rank2.setText("     2. "+rank[1]);
			rank3.setText("     3. "+rank[2]);
			rank4.setText("     4. "+rank[3]);
			rank5.setText("     5. "+rank[4]);
			
			
			repaint();
			
			
			add(page2);
			page2.setVisible(true);                                                         //reset all data and switch JFrame
			
		}
		if(e.getSource()==see) {
			page2.setVisible(false);
			remove(page2);
			
			add(page1);
			page1.setVisible(true);                                                             //switch JFrame
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		status.setText("remaining rounds: "+round+"     now angle:"+angle.getValue()+"degrees, now speed:"+speed.getValue()+"m/s        ");
		if(e.getSource()==angle) {
			repaint();
		}                                                                                      //JSlider listener
		
	}
	
	public class callback implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==timer) {
				time+=0.3;
			}
			if(e.getSource()==subtimer) {
				subtime+=0.5;
			}
			repaint();
		}
		                                                                                     //timer call back
	}
	
	
	public static void main(String[] args) {
		drive a=new drive();
		a.setsize(700,800);                                                         //main method setting up JFrame
	}
}
