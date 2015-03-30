import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FlagViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private JList<String> flagList;
    private static final String FLAGS_DIR = "flags";
    private JLabel flagLabel;
    
    public FlagViewer() {
        super("Flag Viewer");
        Image globe = new ImageIcon("globe.png").getImage();
        super.setIconImage(globe);
        try {
            populateList();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(
                    this,
                    ioe.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(
                    this,
                    npe.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        flagLabel = new JLabel();
        
        flagList.setVisibleRowCount(6);
        flagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flagList.setSelectedIndex(0);
        flagLabel.setIcon(new ImageIcon(FLAGS_DIR+ "/" + flagList.getSelectedValue() + ".png"));
        flagList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                flagLabel.setIcon(new ImageIcon(FLAGS_DIR+ "/" + flagList.getSelectedValue() + ".png"));
                System.out.println(flagList.getSelectedValue());
            }
        });
        final JPanel listPanel = new JPanel();
        listPanel.setBorder(new TitledBorder(new EtchedBorder(), "Flag List"));
        listPanel.add(new JScrollPane(flagList));
        
        final JPanel flagPanel = new JPanel();
        flagPanel.add(flagLabel);
        flagPanel.setBorder(new TitledBorder(new EtchedBorder(), "Country Flag"));

        
        final JPanel imageNumberPanel = new JPanel();
        int numOfImages = flagList.getModel().getSize();
        String strNumOfImages = Integer.toString(numOfImages);
        imageNumberPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        imageNumberPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        imageNumberPanel.add(new JLabel(strNumOfImages + " images loaded."));
        
        final Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.PAGE_START);
        mainPanel.add(flagPanel, BorderLayout.CENTER);
        mainPanel.add(imageNumberPanel, BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);        
        setVisible(true);
        
    }
    private void populateList() throws IOException, NullPointerException {
        File file = new File(FLAGS_DIR);
        String[] flagsInDir = file.list();
        String flag;
        StringBuilder flagNameModifier = new StringBuilder();
        
        
        if(!(file.exists())) {
            throw new NullPointerException("Directory does not exist.");
        }
        int numOfFlags = flagsInDir.length;
        for (int i = 0; i < numOfFlags; i++) {
            flag = flagsInDir[i];//examines one flag
            char character;
            for (int j = 0; j < flag.length(); j++) {
                character = flag.charAt(j);//examines one character of flag
                if (character != '.') {
                    flagNameModifier.append(character);
                }
                
                else {
                    if(flag.charAt(j + 1) != 'p'){
                        throw new IOException("File must be of type 'png'");
                    }
                    else {
                        if (flag.charAt(j + 2) != 'n') {
                            throw new IOException("File must be of type 'png'");
                        }
                        else {
                            if (flag.charAt(j + 3) != 'g') {
                                throw new IOException("File must be of type 'png'");
                            }
                        }
                    }
                break;
                }
                
            }
            flagsInDir[i] = flagNameModifier.toString();
            flagNameModifier = new StringBuilder();
            
        }
        Arrays.sort(flagsInDir, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        flagList = new JList<String>(flagsInDir);
    }

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getName());
            }
        } catch (Exception e) {
            
        }
        FlagViewer flaggers = new FlagViewer();
        
    }
    
}
