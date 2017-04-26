package bot.scripts;

import org.sikuli.script.Region;

public class RegionHandler {
	static Region temp;

	public static Region createTempRegion(int x, int y, int w, int h, Region janela) {

		janela = new Region(temp.getX() + x, temp.getY() + y, w, h);
		return janela;
	}

	public static void restoreRegion(Region janela) {
		janela = temp;
	}

	public static void setTemp(Region janela) {
		temp = janela;
	}
}
