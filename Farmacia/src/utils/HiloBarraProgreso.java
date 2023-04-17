package utils;

import java.util.Iterator;

import vista.FrmPreLoader;

public class HiloBarraProgreso extends Thread {
	@Override
	public void run() {
		for(int i=0;i<=100;i++) {
			FrmPreLoader.prbCarga.setValue(i);
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}