package battledev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.vafada.swtcalendar.SWTCalendarEvent;
import org.vafada.swtcalendar.SWTCalendarListener;

import demo.SWTCalendarDialog;

public class Calendar {
	public  Calendar() {
		
	}
	public void calendar() {
		
		final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");

        final Display display = new Display();

        Shell shell = new Shell(display);

        FillLayout layout = new FillLayout();

        shell.setLayout(layout);

        final Text t = new Text(shell, SWT.BORDER);

        Button b = new Button(shell, SWT.PUSH);



        b.setText("Change Date");

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

                        Date d = formatter.parse(t.getText());

                        cal.setDate(d);

                    } catch (ParseException pe) {



                    }

                }

                cal.open();



            }

        });



        shell.open();

        shell.pack();



        while (!shell.isDisposed()) {

            if (!display.readAndDispatch()) display.sleep();

        }

        display.dispose();





    }
	}


