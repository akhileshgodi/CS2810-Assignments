/**@Author : Akhilesh Godi (CS10B037) 
 * Assignment 10 - Basic Wave Player
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class WavePlayer extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368341207285606728L;
	AudioFormat audioFormat;
	AudioInputStream audioIn;
	SourceDataLine sourceDataLine;
	boolean stopPlayback = false;
	final JButton stopButton = new JButton("Stop");
	final JButton playButton = new JButton("Play");
	final JTextField textField = new JTextField("Karz.wav");

	public WavePlayer()
	{
		stopButton.setEnabled(false);
		playButton.setEnabled(true);

		playButton.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopButton.setEnabled(true);
				playButton.setEnabled(false);
				playAudio();//Play the file
			}
		}
		);
	
		stopButton.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopPlayback = true;
			}
		}
		);

		getContentPane().add(playButton,"West");
		getContentPane().add(stopButton,"East");
		getContentPane().add(textField,"North");

		setTitle("Akhilesh's Wave Player");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,70);
		setVisible(true);
	}

	private void playAudio()
	{
		try
		{
			File soundFile = new File(textField.getText());
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioIn.getFormat();
			System.out.println(audioFormat);

			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);

			sourceDataLine =(SourceDataLine)AudioSystem.getLine(dataLineInfo);

			new PlayThread().start();	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}


	public static void main(String args[])
	{
		new WavePlayer();
	}
	
	//Below is an Inner Class
	class PlayThread extends Thread
	{
		byte tempBuffer[] = new byte[10000];

		public void run()
		{
			try
			{
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				int count;
				while((count = audioIn.read(tempBuffer,0,tempBuffer.length)) != -1&& stopPlayback == false)
				{
					if(count > 0)
					{
						sourceDataLine.write(tempBuffer, 0, count);
					}
				}
				
				sourceDataLine.drain();
				sourceDataLine.close();

				stopButton.setEnabled(false);
				playButton.setEnabled(true);
				stopPlayback = false;
			} catch (Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}