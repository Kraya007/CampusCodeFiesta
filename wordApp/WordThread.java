package skeletonCodeAssgnmt2;
public class WordThread implements Runnable{

	private WordRecord word;
	private WordControl wControl;

	
	public WordThread(WordRecord word, WordControl wControl)
	{
		super();
		this.word = word;
		this.wControl = wControl;
	}
	
	/**
	Method run starts thread when called and manages functionality of game
	*/
	@Override 
	public void run(){
		while(!wControl.ended()){//when game is still being played
			if (word.dropped()){
				wControl.missedWord();
				word.resetWord();
				wControl.setUpdates();
			}
			else if(wControl.isPaused())
			{
				continue;
			}

			else
			{
				word.drop(1);
				wControl.updateLabels();
			}

			try{
				Thread.sleep(word.getSpeed()/20);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	Method resets word
	*/
	public synchronized void reset(){
		word.resetWord();
	}


}
