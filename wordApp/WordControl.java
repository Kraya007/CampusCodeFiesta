package skeletonCodeAssgnmt2;
import javax.swing.*;
import java.util.*;

public class WordControl{

	private WordThread[] wordThreads;

	private volatile boolean end,pause,update,playing;
	private final Object locked = new Object();

	public WordControl(){
		super();
		this.wordThreads = new WordThread[WordApp.words.length];
		end = true;
		pause = false;
		playing = false;
	}
	
	/**
	*Method wordStart produces different threads 
	*for all the words in WordRecord
	*/
	public void wordStart()
	{
		end = false;
		playing =true;
		int i = 0;

		for (WordRecord word : WordApp.words){
			wordThreads[i] = new WordThread(word, this);
			new Thread(wordThreads[i]).start();
			i++;
		}
	}

	/**
	Method resetScore resets score objects score
	*/
	public void resetScore(){
		WordApp.score.resetScore();
		updateLabels();
	}
	/**
	Method restartGame resets the game
	*/
	public void restartGame()
	{
		for (WordThread wt: wordThreads){
			wt.reset();
		}

		end = true;
		playing = false;
	}
	/**
	Method endGame ends current game
	*/
	public void endGame(){
		restartGame();
		resetScore();
		WordApp.w.repaintOnce();
	}
	/**
	Method ended checks whether game has ended
	@return boolean true if ended, false if ongoing
	*/
	public boolean ended(){
		return end;
	}
	/**
	*Method winGame shows pop up message if user wins game
	*and the points collected
	*/
	public void winGame(){
		restartGame();
		setUpdates();
		JOptionPane.showMessageDialog(WordApp.w, "Winner!\n" + 
				"Words Caught:" + WordApp.score.getCaught() +
				"\nScore:" + WordApp.score.getScore());
		resetScore();
		WordApp.w.repaintOnce();

	}
	/**
	Method setPaused sets paused variable to opposite state
	*/
	public void setPaused(){
		pause  =! pause;
	}
	/**
	*Method isPaused checks if game is paused or not paused
	*/
	public boolean isPaused(){
		return pause;
	}
	/**
	*Method updated checks if our games 
	*is updated or not
	*/
	public boolean updates()
	{
		return update;
	}
	
	/**
	Method setNotUpdated sets updated
	* variable to be false
	*/
	public synchronized void setNotUpdated()
	{
		update = false;
	}
	/**
	*Method setUpdates sets the updated 
	* variables to be true
	*/
	public synchronized void setUpdates()
	{
		update = true;
	}
	/**
	*Method play returns boolean that 
	*assures us if our game is playing 
	*or not playing
	*/
	public boolean playing(){
		return playing;
	}
	
	/**
	Method missedWord increments the missed word counter
	*/
	public synchronized void missedWord()
	{
		WordApp.score.missedWord();
		updateLabels();
		if (WordApp.score.getMissed() >= 10){
			restartGame();
			setUpdates();
			JOptionPane.showMessageDialog(WordApp.w, "Game Over\n" + 
				"Words Caught:" + WordApp.score.getCaught() +
				"\nScore:" + WordApp.score.getScore());

			resetScore();
			WordApp.w.repaintOnce();
		}
	}
	
	/**
	Method wordMatch checks to see if the word entered by the user matched the actual word falling
	@param text String
	@return boolean true if matches, false if word does not match
	*/
	public synchronized boolean wordMatch(String text)
	{
		Arrays.sort(WordApp.words, new Comparator<WordRecord>() {
			@Override
			public  int compare(WordRecord w1, WordRecord w2){
				if (w1.equals(w2)){
					return 0;
				}

				if (w1.getY() > w2.getY()){
					return -1;
				}

				return -1;
			}
		});

		for (WordRecord word : WordApp.words){
			if (word.matchWord(text)) {
				WordApp.score.caughtWord(text.length());
				updateLabels();
				if(WordApp.score.getCaught() >= WordApp.totalWords){
					winGame();
				}

				return true;
			}
		}

		return false;
	}
	
	/**
	Method updateLabels updates the labels on the GUI when it changes
	*/
	public void updateLabels(){
		synchronized (locked) {
			WordApp.labels[0].setText("Caught: " + WordApp.score.getCaught() + "    ");
			WordApp.labels[1].setText("Missed:" + WordApp.score.getMissed()+ "    ");
			WordApp.labels[2].setText("Incorrect:" + WordApp.score.getIncorrect() + "    ");
			WordApp.labels[3].setText("Score:" + WordApp.score.getScore()+ "    ");
			setUpdates();
		}
	}
	


}
