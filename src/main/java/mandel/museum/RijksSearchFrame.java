package mandel.museum;

import com.andrewoid.ApiKey;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class RijksSearchFrame extends JFrame {
    ApiKey apiKey = new ApiKey();
    RijksService service = new RijksServiceFactory().getService();
    private final JTextField search;
    private JLabel[] lbl;
    private final JPanel subPanel;
    private int page = 1;

    public RijksSearchFrame() throws IOException {
        setTitle("Rijks");
        setSize(750, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        search = new JTextField();
        search.setPreferredSize(new Dimension(400, 25));
        JButton prev = new JButton("Previous Page");
        JButton next = new JButton("Next Page");

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(prev);
        panel.add(search);
        panel.add(next);
        main.add(panel, BorderLayout.NORTH);

        subPanel = new JPanel(new FlowLayout());
        main.add(subPanel, BorderLayout.CENTER);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page++;
                getPage();
            }
        });

        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page--;
                getPage();
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSearchContent();
            }
        });

        getPage();
        setContentPane(main);
    }

    private void getImages(Art query) throws IOException {
        subPanel.removeAll();

        int totalLbls = Math.min(query.artObjects.length, 10);
        lbl = new JLabel[totalLbls];

        for (int i = 0; i < totalLbls; i++) {
            URL url = new URL(query.artObjects[i].webImage.url);
            Image image = ImageIO.read(url);
            lbl[i] = new JLabel();
            Image scaledImage = image.getScaledInstance(150, -1, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            lbl[i].setIcon(imageIcon);

            subPanel.add(lbl[i]);

            ArtObject currObject = query.artObjects[i];
            lbl[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    imageInformation(query);
                    try {
                        new ImageFrame(currObject).setVisible(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

    private void getPage() {
        Disposable disposable = service.pageNumber(
                        apiKey.get(),
                        page)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        this::getImages,
                        Throwable::printStackTrace);
    }

    private void getSearchContent() {
        Disposable disposable = service.queryAndPage(
                        apiKey.get(),
                        page,
                        search.getText())
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        this::getImages,
                        Throwable::printStackTrace);
    }

    private void imageInformation(Art info) {
        for (int i = 0; i < lbl.length; i++) {
            lbl[i].setToolTipText(info.artObjects[i].title + " by " + info.artObjects[i].principalOrFirstMaker);
        }
    }
}