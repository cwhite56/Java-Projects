import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageScaler {
    public static ImageIcon scaleImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        Image temp = icon.getImage();
        Image scaledTemp = temp.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledTemp);
        return icon;
        
    }
}
