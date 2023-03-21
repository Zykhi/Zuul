package pkg_UI;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SmoothProgressBarManager {
 
	private final Object OBJ = new Object();
	private SmoothProgressBarAnimation thread;
	private final JProgressBar progressbar;
	private int value;
	private int currentValue;
	private final Runnable progressRunnable;
	private boolean disposed; 
	private final long time;
 
	public SmoothProgressBarManager(final JProgressBar progressbar, int pCurrentHP, int pMaxHP) { 
		this(progressbar, 100, pCurrentHP, pMaxHP);
	}
 
        /**
         * 
         * @param progressbar la {@link JProgressBar} 
         * @param time la durée entre chaque incrémentation
         */
	public SmoothProgressBarManager(final JProgressBar progressbar, long time, int pCurrentHP, int pMaxHP) { 
		this.time=time;
		this.progressbar=progressbar;
		this.currentValue= pCurrentHP;
		this.progressRunnable = new Runnable() {
			public void run() {
				progressbar.setValue(currentValue);
			}
		};
		this.progressbar.setValue(pMaxHP);
	}
 
	public void setValue(int value) {
		if ( disposed ) throw new IllegalStateException();
		this.value=value;
		if ( currentValue!=value ) {
			synchronized (OBJ) {
				if ( thread==null || !thread.running ) {
					thread = new SmoothProgressBarAnimation();
					thread.start();
				}
				else {
					OBJ.notify(); // on relance le thread mis en wait le cas échéant
				}
			}
		}
	}
 
	public void dispose() {
		if ( !disposed ) {
			disposed=true;
			if ( thread!=null ) {
				thread.running=false;
				thread.interrupt();
			}
		}
	}
 
	public boolean isDisposed() {
		return disposed;
	}
 
	public boolean isValueReached() {
		return currentValue==value;
	}
 
	private final class SmoothProgressBarAnimation extends Thread {
 
		private volatile boolean running;
 
		@Override
		public void run() {
			running = true;
			while(running) {
				while( running && currentValue!=value ) {
					if ( currentValue<value ) {
						currentValue++;
					}
					else if ( currentValue>value ) {
						currentValue--;
					}
					updateProgressBar();
					if ( time>0 ) {
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							running=false;
						}
					}
					else if ( interrupted() ) {
						running=false;
					}
				}
				synchronized (OBJ) {
					try {
						OBJ.wait(); // on met le thread en état d'attente pour éviter qu'il ne consomme du cpu pour rien
					} catch (InterruptedException e) {
						running=false;
					}
				}
			}
		}
 
	}
 
	protected void updateProgressBar() {
		SwingUtilities.invokeLater(progressRunnable);
	}
 
}
