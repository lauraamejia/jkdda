package hw1;

/**
 * 
 * @author lauram1 Model of the channel and volume controls for a television. A
 *         TV has a current channel and volume level. It has methods such as
 *         volumeUp, volumeDown, channelUp, channelDown, goToPreviousChannel,
 *         setChannel, getChannel, getVolume, resetStart, resertNumChannels,
 *         display
 *
 */
public class TV {
	/**
	 * Incremental increase or decrease in volume when the volumeUp or volumeDown
	 * methods are called.
	 */
	public static final double VOLUME_INCREMENT = 0.07;
	/**
	 * Variable to hold the value of volume
	 */
	private double volume;
	/**
	 * Variable to hold the value of start channel
	 */
	private int start;
	/**
	 * Variable to hold the value of current channel
	 */
	private int channel;
	/**
	 * Variable to hold the value of maximum number of channels
	 */
	private int numChannels;
	/**
	 * Variable to hold the value of maximum number of channels This one it's
	 * initialized in the constructor as givenStart+givenNumChannel-1
	 */
	private int maxChannels;
	/**
	 * Variable to hold the value of previous channel
	 */
	private int previousChannel;

	/**
	 * Constructs a new TV with givenNumChannels available channels, numbered
	 * consecutively beginning with givenStart
	 * 
	 * @param givenStart       the fixed start of TV
	 * @param givenNumChannels the fixed maximum of channels of TV
	 */
	public TV(int givenStart, int givenNumChannels) {
		start = givenStart;
		channel = givenStart;
		previousChannel = givenStart;
		volume = 0.5;
		numChannels = givenStart + givenNumChannels - 1;
		maxChannels = givenNumChannels;
	}

	/**
	 * Changes the channel down by 1, wrapping around to start + numChannels - 1 if
	 * the current channel is equal to start
	 */
	public void channelDown() {
		previousChannel = channel;
		channel = (channel - 1 - start + maxChannels) % maxChannels + start;
	}

	/**
	 * Changes the channel up by 1, wrapping around to start if the current channel
	 * can't go any higher.
	 */
	public void channelUp() {
		previousChannel = channel;
		channel = (channel + 1 - start) % maxChannels + start;
	}

	/**
	 * Returns a string representing the current channel and volume, where the
	 * volume is expressed as a percentage rounded to the nearest integer.
	 * 
	 * @return disp
	 */
	public String display() {
		String disp = "Channel " + channel + " Volume " + Math.round(100 * volume) + "%";
		return disp;

	}

	/**
	 * Returns the current channel for this TV.
	 * 
	 * @return channel
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * Returns the current volume for this TV.
	 * 
	 * @return volume
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Sets the current channel to the most recent previous channel.
	 */
	public void goToPreviousChannel() {
		channel = previousChannel;
	}

	/**
	 * Resets the value of the start channel.
	 * 
	 * @param givenStart new start of channels
	 */
	public void resetStart(int givenStart) {
		//channel = Math.min(givenStart, start + numChannels - 1);
		start =givenStart;
		previousChannel=givenStart;
		int newRange = Math.min(givenStart, givenStart + numChannels - 1);
		channel = Math.max(givenStart, newRange);
	}

	/**
	 * Resets the number of channels.
	 * 
	 * @param givenNumChannels new maximum of channels
	 */
	public void resetNumChannels(int givenNumChannels) {
		//previousChannel = channel;
		maxChannels=givenNumChannels + start -1;
		numChannels=givenNumChannels;
		//int newRange = Math.max(start, start + numChannels - 1);
		channel = Math.min(channel,start + givenNumChannels -1);
		previousChannel = Math.min(channel,start + givenNumChannels -1);
		 //numChannels = Math.min(start, start + givenNumChannels - 1);

	}

	/**
	 * Sets the channel to the given channel number.
	 * 
	 * @param channelNumber current channel
	 */
	public void setChannel(int channelNumber) {
		previousChannel = channel;
		int currentChannel = Math.max(start, channelNumber);
		channel = Math.min(currentChannel, numChannels);
	}

	/**
	 * Lowers the volume by VOLUME_INCREMENT, but not below 0.0.
	 */
	public void volumeDown() {
		double currentVolume = volume - VOLUME_INCREMENT;
		volume = Math.abs(Math.max(0.0, currentVolume));
	}

	/**
	 * Raises the volume by VOLUME_INCREMENT, but not above 1.0.
	 */
	public void volumeUp() {
		double currentVolume = volume + VOLUME_INCREMENT;
		volume = Math.min(currentVolume, 1.0);
	}

}
