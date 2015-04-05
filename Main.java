package aOverload;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;


@ScriptManifest(author = "Atex", category = Category.HERBLORE, description = "Makes overload potions. Make sure you have all the ingredients in one bank tab, and the tab is active", name = "aOverload", servers = { "Ikov" }, version = 0.1)
public class Main extends Script implements Paintable {
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	Image background = getImage("http://s9.postimg.org/p2h1ax2xr/image.png");
	public long startTime = System.currentTimeMillis();
	public static int ovlCount = 0;
	@Override
	public boolean onExecute() {
		GUI form = new GUI();
		form.setVisible(true);
		while(form.isVisible()) {
			sleep(50);
		}
		startTime = System.currentTimeMillis();
		strategies.add(new Action());
		provide(strategies);
		return true;
	}
	@Override
	public void paint(Graphics iFace) {
		iFace.setFont(new Font("Impact",Font.PLAIN,12));
		iFace.drawImage(background, 4, 187, null);
		iFace.drawString("Time running: "+runTime(startTime),10,300);
		iFace.drawString("Overloads made: "+ovlCount,10,350);
		iFace.drawString("Overloads/hour "+overloadRate(ovlCount),50,300);
	}
	private Image getImage(String url) {
        try {
                return ImageIO.read(new URL(url));
        } catch (IOException e) {
                return null;
        }
	}
	public int overloadRate(int points) {
        return (int)(((double)(points - 0) * 3600000D) / (double)(System.currentTimeMillis() - startTime));
   }
	public static String runTime(long start) {
		DecimalFormat df = new DecimalFormat("00");
		long currentTime = System.currentTimeMillis() - start;
		long hours = currentTime / (3600000);
		currentTime -= hours * (3600000);
		long minutes = currentTime / (60000);
		currentTime -= minutes * (60000);
		long seconds = currentTime / (1000);
		return df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
	}
}
