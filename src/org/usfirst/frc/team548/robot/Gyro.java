package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class Gyro {
	
	private SPI spi;
	private Timer updateTime;
	
	public Gyro(SPI.Port port) {
		spi = new SPI(port);
		spi.setClockRate(4000000);
		spi.setClockActiveHigh();
		spi.setChipSelectActiveLow();
		spi.setMSBFirst();
	}
	
	public void update() {
		byte[] command = { 0x20, 0, 0, 0 };
		byte[] data = { 0, 0, 0, 0 };
		spi.transaction(command, data, 4);
		
		System.out.println(assembleData(data));
	}
	
	private short assembleData(byte[] data) {
		return (short) (((short) (data[0] & 0x3)) << 14 | ((short) data[1]) << 6 | ((short) (data[2] & 0xFC)) >> 2);
	}
}
