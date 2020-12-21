package battledev;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Scrollable;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.vafada.swtcalendar.SWTCalendarEvent;
import org.vafada.swtcalendar.SWTCalendarListener;

import demo.SWTCalendarDialog;
import ecriture.Ecrire;
import lecture.Lire;
import mail.B;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CLabel;


public class demo {
	
	Date d;
	protected Shell shell;
	private Display display = Display.getDefault();
	private Text t;
	private Text text;
	private String date, debut,fin,aFaire;  
	private boolean debutAM,debutPM,finAM,finPM;
	private String relaisDebut="";
	private  String relaisFin, message="";
	private int id;
	private 	String[] idtab;
	private Text text_1;
	
	private static String USER_NAME ="clotikriss@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "Yxrhyrup1!"; // GMail password
    private static String RECIPIENT ;

	
	 
	
	   private static demo instance ;
	   private Text text_2;
	
	 
	
	   private demo() {
	
	   }
	
	 
	
	   public static demo getInstance() {
	
	      if (instance == null) {
	
	         instance = new demo() ;
	
	      }
	
	      return instance ;
	
	   }

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			demo window = new demo();
			window.open();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	
	
	
	public void open() {
		 
		createContents();

		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	 private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);
	        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        MimeMessage message = new MimeMessage(session);

	        try {
	        	
	            message.setFrom(new InternetAddress(from));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            // To get the array of addresses
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }

	            message.setSubject(subject);
	            message.setText(body);
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	    }
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shell = new Shell();
		shell.setSize(911, 489);
		shell.setText("Demo");
		shell.setLayout(null);

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmCalendrier = new MenuItem(menu, SWT.NONE);
		mntmCalendrier.setText("Calendrier");

		Group group = new Group(shell, SWT.NONE);
		group.setBounds(10, 24, 400, 93);
		
			t= new Text(group, SWT.BORDER);
		t.setLocation(20, 20);
		t.setSize(200, 21);
		 final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Button b = new Button(group, SWT.NONE);
		b.setImage(SWTResourceManager.getImage("C:\\Users\\Clotide\\Pictures\\Google_Calendar_icon-icons.com_75710.png"));
		b.setBounds(230, 20, 150, 70);
		b.setText("New Button");

		
		  b.setText("Change Date");
		  
		  Group group_1 = new Group(shell, SWT.NONE);
		  group_1.setBounds(218, 112, 203, 33);
		  text_1 = new Text(shell, SWT.BORDER);
			text_1.setBounds(554, 59, 76, 21);
		  Button btnAm = new Button(group_1, SWT.RADIO);
		  btnAm.setBounds(10, 10, 61, 16);
		  btnAm.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent e) {
		  		 Button btn = (Button) e.getSource();
		  		if(btn.getSelection()) {
		  			
		  			debutAM=true;
		  		}
		  		else {
		  			debutAM=false;
		  			
		  		}
		  	}
		  });
		  btnAm.setText("AM");
		  
		  Button btnPm = new Button(group_1, SWT.RADIO);
		  btnPm.setBounds(77, 10, 90, 16);
		  btnPm.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent e) {
		  		 Button btn = (Button) e.getSource();
		  		
		  		
		  		
		  		if(btn.getSelection()) {
		  			
		  			debutPM=true;
		  		}
		  		else {
		  			debutPM=false;
		  			
		  		}
		  	}
		  
		  });
		  btnPm.setText("PM");
		  
		  Label lblTacheFaire = new Label(shell, SWT.NONE);
		  lblTacheFaire.setBounds(8, 123, 75, 22);
		  lblTacheFaire.setText("Heure Debut");
		  
		  CCombo combo = new CCombo(shell, SWT.BORDER);
		  combo.setBounds(89, 123, 123, 22);
		  combo.setItems (new String [] {"0", "1", "2", "3","4", "5", "6","7","8","9","10", "11","12"});
		  
		  Label lblHeureDeFin = new Label(shell, SWT.NONE);
		  lblHeureDeFin.setBounds(10, 161, 73, 15);
		  lblHeureDeFin.setText("Heure de fin");
		  
		  CCombo combo_1 = new CCombo(shell, SWT.BORDER);
		  combo_1.setBounds(89, 155, 123, 22);
		  combo_1.setItems (new String [] {"0", "1", "2", "3","4", "5", "6","7","8","9","10", "11","12"});
		  

		  Button btnAm_1 = new Button(shell, SWT.RADIO);
		  btnAm_1.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent e) {
		  		 Button btn = (Button) e.getSource();
		  		
		  		if(btn.getSelection()) {
		  			
		  			finAM=true;
		  		}
		  		else {
		  			finAM=false;
		  			
		  		}
		  	}
		  		
		  
		  });
		  btnAm_1.setBounds(228, 160, 52, 16);
		  btnAm_1.setText("AM");
		  
		  Button btnPm_1 = new Button(shell, SWT.RADIO);
		  btnPm_1.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent e) {
		  		 Button btn = (Button) e.getSource();
	if(btn.getSelection()) {
		  			
		  			finPM=true;
		  		}
		  		else {
		  			finPM=false;
		  			
		  		}
		  	}
		  		
		  	
		  });
		  btnPm_1.setBounds(298, 160, 90, 16);
		  btnPm_1.setText("PM");
		  
		  text = new Text(shell, SWT.BORDER);
		  text.setBounds(32, 203, 324, 170);
		  Label lblNewLabel = new Label(shell, SWT.NONE);
		  lblNewLabel.setBounds(10, 182, 110, 15);
		  lblNewLabel.setText("  A faire");
		  Lire lir=new Lire("D:\\kri\\emploidutemps.txt");
		  
		  CLabel lblNewLabel_1 = new CLabel(shell, SWT.NONE);
		  lblNewLabel_1.setBounds(385, 203, 409, 170);
		  lblNewLabel_1.setText("");

		  
		  
		  
		  
		  
		  text_2 = new Text(shell, SWT.BORDER);
			text_2.setBounds(471, 123, 248, 21);
		  Button btnSoumettre = new Button(shell, SWT.NONE);
		  btnSoumettre.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent e) {
		  		String bilan="";
		  		date=t.getText();
		  		debut=combo.getText();
		  		if(debutAM) {
		  			debut=debut+" AM";
		  			relaisDebut="AM";
		  		}
		  		else if(debutPM) {
		  			debut=debut+" PM";
		  			relaisDebut="PM";
		  		}
		  		fin=combo_1.getText();
		  		if(finAM) {
		  		fin=fin+" AM";
		  		relaisFin="AM";
		  		}
		  		else if(finPM) {
		  			fin=fin+" PM";
		  			relaisFin="PM";
		  		}
		  		aFaire=text.getText();
		  		Lire lir=new Lire("D:\\kri\\emploidutemps.txt");
		  		bilan=lir.lecture();
		  		if(bilan.equals("")) {
		  			bilan=bilan+"A la date du "+date+" Au début de cette heure " + debut+ " jusqu'à "+fin+" A faire: "+aFaire+"\n";
		  		}else {
		  			bilan=bilan+"\n"+"A la date du "+date+" Au début de cette heure " + debut+ " jusqu'à "+fin+" A faire: "+aFaire+"\n";
		  		}
		  	   	String from = USER_NAME;
			        String pass = PASSWORD;
		  		
		        if(text_2.getText()!=null && text_2.getText()!="") {
		     
		        RECIPIENT=text_2.getText();
		         // list of recipient email addresses
		 

		
		        
		        if(!RECIPIENT.contains(",")) {
		        	String[] to = {RECIPIENT};
		        	String subject = "Java send mail";
			        String body = aFaire;
			        sendFromGMail(from, pass, to, subject, body);
		        }
		        else {
		         String[] to = RECIPIENT.split("[,]"); // list of recipient email addresses
		        String subject = "Java send mail";
		        String body = aFaire;

		        sendFromGMail(from, pass, to, subject, body);}
		    

		        }
		  		
		  		Ecrire ecr=new Ecrire("D:\\kri\\emploidutemps.txt");
		  		ecr.enregistrement();
		  		ecr.acquisition(bilan);
		  		
		  		 SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		         String dateInString = date;
		         	Date date2=null;
		         try {

		             date2= formatter.parse(dateInString);
		      

		         } catch (ParseException m) {
		             m.printStackTrace();
		         }
	
		  		  
		  		
		        
		  		Desktop desk = Desktop.getDesktop();
		  		try {
					desk.open(new File("D:\\kri\\emploidutemps.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  		try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Aricad2");
					
//					Statement statement =con.createStatement();
//					String sql="INSERT INTO EMPLOI_DU_TEMPS (DEBUT, FIN, DATE_JOUR, DEBUTAMOUPM, FINAMOUPM, AFAIRE)" + " VALUES" + " ("+debut+", "+fin+", "+d+","+relaisDebut+", "+relaisFin+", "+aFaire+")" ;
					
				//ResultSet rs=statement.executeQuery(sql);
//					while(rs.next()) 
//						
				String query = "INSERT INTO EMPLOI_DU_TEMPS (DEBUTAMOUPM, FINAMOUPM, AFAIRE, DEBUT, FIN, DATEJOUR, ID ) VALUES (?,?,?,?,?,?,seq_person.nextval)";
				PreparedStatement ps = con.prepareStatement(query);
			
				
				ps.setString(1, relaisDebut);
				ps.setString(2, relaisFin);
				ps.setString(3, aFaire);
				// etc - there is a setter for each basic datatype
				ps.setString(4, debut);
				ps.setString(5, fin);
				ps.setDate(6, (java.sql.Date) new java.sql.Date(date2.getTime()));
				
				
				
				ps.execute();
				ps.close();
//				
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Aricad2");
				Statement statement =con.createStatement();
				String sql="Select * from EMPLOI_DU_TEMPS";
				
				ResultSet rs=statement.executeQuery(sql);
				while(rs.next()) {
					
					
				
					message+=rs.getDate("DATEJOUR")+" Debut: "+rs.getString("DEBUT")+" Fin: "+rs.getString("FIN")+" "+rs.getString("AFAIRE")+" "+rs.getString("ID")+"\n";
					id=rs.getInt("ID");}
				
					con.close();
					idtab=new String[id];
					for(int i=0; i<id; i++) {
						idtab[0]=""+(i+1);
						
						idtab[i]=""+(i+1);
						
						
					}
					System.out.println(id);
					
				
				
		
				
			
				
//						System.out.println(rs.getString("Prenom")+"");
						
		
					
					
					
						bilan="";
						relaisDebut="";
						relaisFin="";
					
				}
				catch(Exception j) {
					System.out.println(j);
					
				}
				lblNewLabel_1.setText(message);
				message="";
				// TODO Auto-generated method stub

			}
		  		
		  		
		  		
		  		
		  		
		  	
		  });
		  btnSoumettre.setBounds(101, 379, 128, 31);
		  btnSoumettre.setText("Soumettre");
		  
		 
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con;
			
			try {
				
				
				
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Aricad2");
				Statement statement =con.createStatement();
				String sql="Select * from EMPLOI_DU_TEMPS";
				
				ResultSet rs=statement.executeQuery(sql);
				while(rs.next()) {
					
					message+=rs.getDate("DATEJOUR")+" Debut: "+rs.getString("DEBUT")+" Fin: "+rs.getString("FIN")+" "+rs.getString("AFAIRE")+" "+" "+rs.getInt("ID")+"\n";
				id=rs.getInt("ID");
				}
					con.close();
					idtab=new String[id];
					for(int i=0; i<id; i++) {
						idtab[0]=""+(i+1);
						
						idtab[i]=""+(i+1);
						
						
					}
					System.out.println(id);
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			lblNewLabel_1.setText(message);
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					 try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con;
							
							try {
								id=Integer.parseInt(text_1.getText());
								
								
								
								con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Aricad2");
								Statement statement =con.createStatement();
								String sql="DELETE  FROM EMPLOI_DU_TEMPS WHERE ID = "+id;
								
								ResultSet rs=statement.executeQuery(sql);
								
									
									
									sql="Select * from EMPLOI_DU_TEMPS";
									
									 rs=statement.executeQuery(sql);
									while(rs.next()) {
										
										message+=rs.getDate("DATEJOUR")+" Debut: "+rs.getString("DEBUT")+" Fin: "+rs.getString("FIN")+" "+rs.getString("AFAIRE")+" "+" "+rs.getInt("ID")+"\n";
									id=rs.getInt("ID");
									}
										con.close();
							
									
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
							catch(Exception e3) {
								
							}
							
					
					
					lblNewLabel_1.setText(message);
					
					message="";
					
					
					 
					 
					 }
			});
			btnNewButton.setBounds(692, 57, 75, 25);
			btnNewButton.setText("Delete");
			
			
			
			Label lblIdDelete = new Label(shell, SWT.NONE);
			lblIdDelete.setBounds(458, 62, 90, 15);
			lblIdDelete.setText("ID \u00E0 delete");
			
			
			
			Label lblMail = new Label(shell, SWT.NONE);
			lblMail.setBounds(471, 102, 55, 15);
			lblMail.setText("mail");
			  message="";
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	
	

	        b.addListener(SWT.Selection, new Listener() {

	            public void handleEvent(Event event) {

	                final SWTCalendarDialog cal = new SWTCalendarDialog(display);

	                cal.addDateChangedListener(new SWTCalendarListener() {

	                    public void dateChanged(SWTCalendarEvent calendarEvent) {

	                        t.setText(formatter.format(calendarEvent.getCalendar().getTime()));

	                    }

	                });



	                if (t.getText() != null && t.getText().length() > 0) {

	                    try {

	                        d = formatter.parse(t.getText());

	                        cal.setDate(d);

	                    } catch (ParseException pe) {



	                    }

	                }

	                cal.open();



	            }

	        });

		mntmCalendrier.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub

				System.out.println("ok");

				
				group.setVisible(true);
				new SWTCalendarDialog(display);

			}
		});
	}
}
