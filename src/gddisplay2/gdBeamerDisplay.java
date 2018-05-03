package gddisplay2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gdBeamerDisplay {

    private Image imageVooricon;
    private int frameHoogte;
    private int frameBreedte;
    JFrame diaFrame;
    public File keuzeFile ;
    
  
     

    public void gdBeamerDisplay() {
//        System.err.println("IOn gdsiaplsy");
//        \Users\G Doets\Pictures\oudeMotorToertocht2012\DSC_6615_2012-05-12_11-13-38.jpg
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (gs.length > 1) {
//            System.err.println("dus er is nog een scherm en die gaan we gebruikne voor de diaas");
            GraphicsDevice gd = gs[1];
            gd.setFullScreenWindow(null);
            diaFrame = new JFrame(gd.getDefaultConfiguration());
            frameHoogte = gd.getDisplayMode().getHeight();
            frameBreedte = gd.getDisplayMode().getWidth();
            diaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            diaFrame.setUndecorated(true);


//            diaFrame.setSize(frameBreedte, frameHoogte);

        } else {
//            System.err.println("er is mar 1 scherm");

            GraphicsDevice gd = gs[0];
            diaFrame = new JFrame();
            diaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            diaFrame.setUndecorated(true);
            frameHoogte = gd.getDisplayMode().getHeight();
            frameBreedte = gd.getDisplayMode().getWidth();
        }

//        diaFrame.setSize(frameBreedte, frameHoogte);
        diaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                diaFrame.setBackground(Color.BLACK);
        diaFrame.getContentPane().setBackground(Color.BLACK);
        diaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        diaFrame.addMouseListener(new clickListener());

//        System.out.println(frameBreedte);
//        System.out.println(frameHoogte);
//        keuzeFile = new File(stringKeuzeFile);

    }
    
    
 public void gdBeamerDisplayRun(){
        try {
            Image imageToBeDisplayed = ImageIO.read(keuzeFile);
            int widthImageToBeDisplayed = imageToBeDisplayed.getWidth(null);
            int heightImageToBeDisplayed = imageToBeDisplayed.getHeight(null);
//            System.out.print(widthImageToBeDisplayed + " ");
//            System.out.println(heightImageToBeDisplayed);

            // portrait
            if (heightImageToBeDisplayed > widthImageToBeDisplayed) {

                imageVooricon = imageToBeDisplayed.getScaledInstance(-1, frameHoogte, Image.SCALE_DEFAULT);
//                System.out.println(" geschaald op de vaste hoogte");

            } else {
                double factorPlaatje = widthImageToBeDisplayed / heightImageToBeDisplayed;
                double factorScherm = frameBreedte * 1.0 / frameHoogte * 1.0;
                // landscape
                if (factorPlaatje > factorScherm) {
                    // schaal op breddte
                    imageVooricon = imageToBeDisplayed.getScaledInstance((int) frameBreedte, -1, Image.SCALE_DEFAULT);
//                    System.out.println(" geschaald op de vaste breedte " + factorPlaatje + " " + factorScherm);
                } else {
                    imageVooricon = imageToBeDisplayed.getScaledInstance(-1, frameHoogte, Image.SCALE_DEFAULT);
//                    System.out.println(" geschaald op de vaste hoogte " + factorPlaatje + " " + factorScherm);
                }

            }
           
            diaFrame.getContentPane().removeAll();
//                diaFrame.pack();
//                        removeAll();
            BufferedImage bi = new BufferedImage(frameBreedte, frameHoogte, BufferedImage.TYPE_3BYTE_BGR);
            diaFrame.getContentPane().add(new JLabel(new ImageIcon(bi)));
            diaFrame.getContentPane().add(new JLabel(new ImageIcon(imageVooricon)), BorderLayout.PAGE_END);
//                diaFrame.pack();
            diaFrame.setVisible(true);
            diaFrame.setFocusable(Boolean.FALSE);
//            diaFrame.set   setfullscreenFrame(keuzeFile);
//            Object[] keuze = keuzelijst.getSelectedValues();
//            for (int i = 0; i < keuze.length; i++) {
//            }
//            lijst.addElement("water");
        } catch (IOException ex) {
            System.out.println(" IOExeption op lezen file  " + keuzeFile);

        }

    }

    public class clickListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
//            myButton jButtonPressed = (myButton) event.getSource();

//        setVisible(false); //you can't see me!
//        skipnumaardez = Boolean.FALSE;
//            System.out.println(" Disposing Diaframe");
            diaFrame.dispose(); //Destroy the JFrame object
        }
    }
}