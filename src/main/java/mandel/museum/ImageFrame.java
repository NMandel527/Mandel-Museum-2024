package mandel.museum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageFrame extends JFrame
{
    public ImageFrame(ArtObject ao) throws IOException
    {
        setTitle(ao.title + " by " + ao.principalOrFirstMaker);
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        URL url = new URL(ao.webImage.url);
        Image image = ImageIO.read(url);
        Image scaledImage = image.getScaledInstance(800, -1, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(icon);

        JScrollPane scrollPane = new JScrollPane(imageLabel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
    }
}
