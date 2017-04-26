package pacote.principal;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sikuli.script.Region;

public class SSRobot {

	private static String tmpdir = System.getProperty("java.io.tmpdir")+File.separator+"print.jpg";
	
	public static String takeSS(Region janela) throws IOException, AWTException{
		Robot robot = new Robot();
        BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); 
        ImageIO.write(bi, "jpg", new File(tmpdir));
        return tmpdir;
		
	}
}
