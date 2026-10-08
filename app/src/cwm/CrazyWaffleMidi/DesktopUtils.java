package app.src.cwm.CrazyWaffleMidi;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DesktopUtils {
    private static Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    public static boolean openWebpage(URI uri) {
        if (desktop != null && desktop.isSupported(Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
