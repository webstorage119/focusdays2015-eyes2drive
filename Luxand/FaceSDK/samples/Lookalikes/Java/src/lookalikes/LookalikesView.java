/*
 * LookalikesView.java
 * 
 * To edit GUI in visual editor of Netbeans 7.2+ you can install Swing Application Framework plugin:
 * http://plugins.netbeans.org/plugin/43853/swing-application-framework-support
 * Do not forget to restart Netbeans after installing the plugin!
 */

package lookalikes;

import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import Luxand.*;
import Luxand.FSDK.*;

/**
 * The application's main frame.
 */
public class LookalikesView extends FrameView {
    public final java.util.List<TFaceRecord> FaceList = new ArrayList<TFaceRecord>();
    public int FaceDetectionThreshold = 3;
    public int FARValue = 100;
    public static final int width = 640;
    public static final int height = 480;
    
    public class TFaceRecord
    {
        public FSDK.FSDK_FaceTemplate.ByReference FaceTemplate;
        public FSDK.TFacePosition.ByReference FacePosition;
        public FSDK.FSDK_Features.ByReference FacialFeatures;
        public String ImageFileName;
        public FSDK.HImage image;
        public FSDK.HImage faceImage;
    }
    
    private OptionsDialog optionsDialog = null;
    
    private ResultsDialog resultsDialog = null;
    
    
    private final ImagePanel imagePanel = new ImagePanel();
    private ImagePanel oldImagePanel = null;
    
    private final DefaultListModel listModel = new DefaultListModel();
    private final java.util.List<ImageIcon> listImages = new ArrayList<ImageIcon>();
    private final java.util.List<String> listStrings = new ArrayList<String>();
    private final MyJListCellRenderer listRenderer = new MyJListCellRenderer();
    private boolean cleaning = false;
    
    

    class MyJListCellRenderer extends JLabel implements ListCellRenderer  
    {
        public MyJListCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setHorizontalTextPosition(CENTER);
            setVerticalTextPosition(BOTTOM);
        }
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)  
        {
            //Get the selected index. (The index param isn't
            //always valid, so just using the value as index.)
            int selectedIndex = ((Integer)value).intValue();
            
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            
            ImageIcon icon = listImages.get(selectedIndex);
            String str = listStrings.get(selectedIndex);
            setIcon(icon);
            if (icon != null) {
                setText(str);
            } else {
                setText("(no image available) " + str);
            }
            return this;  
        }  
    }
    
    
    public LookalikesView(SingleFrameApplication app) {
        super(app);

        initComponents();
        
        getFrame().setJMenuBar(jMenuBar1);
        getFrame().setPreferredSize(new Dimension(width, height + 100));
        getFrame().setResizable(true);
                
        try {
            int r = FSDK.ActivateLibrary("Jl3R1DBC1qVQonaiBAq8gK7KzetXbFb4r+OF1DLzInT3KyXHvgHNLyk2Tymk5G6GBv58/Oqn+SQeOWCQfQASTV1Mcd7RQAsrmW02oOa9lhZsMockPLoEnpsH4W1I0+zmxmUwecWKEep9j4BrYhQWuiA3QcNeQO+tfyLOHASk3+M=");
            if (r != FSDK.FSDKE_OK){
               JOptionPane.showMessageDialog(mainPanel, "Please run the License Key Wizard (Start - Luxand - FaceSDK - License Key Wizard)", "Error activating FaceSDK", JOptionPane.ERROR_MESSAGE); 
               System.exit(r);
            }
        } 
        catch(java.lang.UnsatisfiedLinkError e) {
            JOptionPane.showMessageDialog(mainPanel, e.toString(), "Link Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }    
            
        FSDK.Initialize();
        jTextArea1.setText(jTextArea1.getText() + "FaceSDK initialized\n");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList1.setModel(listModel);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCellRenderer(listRenderer);
        jList1.setFixedCellHeight(160);
        jList1.setFixedCellWidth(160);
        jList1.setName("jList1"); // NOI18N
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lookalikes.LookalikesApp.class).getContext().getResourceMap(LookalikesView.class);
        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(lookalikes.LookalikesApp.class).getContext().getActionMap(LookalikesView.class, this);
        jMenuItem1.setAction(actionMap.get("menuEnrollFace")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAction(actionMap.get("menuMatchFace")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenu1.add(jMenuItem2);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu1.add(jSeparator1);

        jMenuItem3.setAction(actionMap.get("quit")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem4.setAction(actionMap.get("menuOptions")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenu2.add(jMenuItem4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu2.add(jSeparator2);

        jMenuItem5.setAction(actionMap.get("menuClearDatabase")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jMenuItem6.setAction(actionMap.get("menuAbout")); // NOI18N
        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        int selectedIndex = jList1.getSelectedIndex();
        if (!evt.getValueIsAdjusting() && !cleaning && selectedIndex >= 0){
            //jTextArea1.setText(jTextArea1.getText() + "selected index = " + selectedIndex + "; size = " + FaceList.size() + "\n");
            
            TFaceRecord fr = FaceList.get(selectedIndex);
            
            // resize image to fit the window width/height
            int imageWidthByReference[] = new int[1];
            int imageHeightByReference[] = new int[1];
            FSDK.GetImageWidth(fr.image, imageWidthByReference);
            FSDK.GetImageHeight(fr.image, imageHeightByReference);
            int imageWidth = imageWidthByReference[0];
            int imageHeight = imageHeightByReference[0];
            double ratio = java.lang.Math.min((width + 0.4) / imageWidth,
                (height + 0.4) / imageHeight);
            HImage tempImage = new HImage();
            FSDK.CreateEmptyImage(tempImage);
            FSDK.ResizeImage(fr.image, ratio, tempImage);
            
            
            // save image to awt.Image
            Image awtImage[] = new Image[1];
            int res = FSDK.SaveImageToAWTImage(tempImage, awtImage, FSDK.FSDK_IMAGEMODE.FSDK_IMAGE_COLOR_24BIT);
            FSDK.FreeImage(tempImage);
            if (res != FSDK.FSDKE_OK){
                JOptionPane.showMessageDialog(mainPanel, "Error displaying picture");
                return;
            } 
            BufferedImage bimg = new BufferedImage(awtImage[0].getWidth(null), awtImage[0].getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics gr = bimg.getGraphics(); 
            gr.drawImage(awtImage[0], 0, 0, null);
            
            // mark face
            gr.setColor(Color.green);
            int left = (int)(ratio * (fr.FacePosition.xc - fr.FacePosition.w / 2));
            int top = (int)(ratio * fr.FacePosition.yc - fr.FacePosition.w / 2);
            int w = (int)(ratio * fr.FacePosition.w);
            int h = (int)(ratio * fr.FacePosition.w);
            gr.drawRect(left, top, w, h);

            // mark features 
            for (int i = 0; i < 2 /*FSDK.FSDK_FACIAL_FEATURE_COUNT*/; ++i){
                if (i<2)
                    gr.setColor(Color.blue);
                else if (i==2)
                    gr.setColor(Color.green);

                gr.drawOval((int)(ratio * fr.FacialFeatures.features[i].x), 
                        (int)(ratio * fr.FacialFeatures.features[i].y), 3, 3);
            }

            gr.dispose();
            
            // draw image on window
            imagePanel.setImage((bimg != null) ? bimg : awtImage[0]);

            GroupLayout layout = (GroupLayout)mainPanel.getLayout();
            if (oldImagePanel == null) {
                layout.replace(jPanel1, imagePanel);
            } else {
                layout.replace(oldImagePanel, imagePanel);
            }
            oldImagePanel = imagePanel;
            
        }
    }//GEN-LAST:event_jList1ValueChanged

    
    public void closeFrame(){
        FSDK.Finalize();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    
    // Component to draw image on
    private class ImagePanel extends JPanel {
        Image image;
        public void setImage(Image image) {
            this.image = image;
        }
        @Override
        public void paintComponent(Graphics gr) {
            if(image != null) {
                gr.clearRect(0, 0, width, height);
                gr.drawImage(image, 0, 0, this);
            }
        }
        @Override
        public Dimension getPreferredSize() {
            int w, h;
            if(image == null) {
                return new Dimension(0, 0);
            }
            w = image.getWidth(null);
            h = image.getHeight(null);
            return new Dimension((w > 0) ? w : 0, (h > 0) ? h : 0);
        }
    }

    @Action
    public void menuAbout() {
        JOptionPane.showMessageDialog(mainPanel, "Luxand Face Recognition Demo \n\n(C) 2013 Luxand, Inc.\nhttp://www.luxand.com", "About", JOptionPane.INFORMATION_MESSAGE); 
    }

    @Action
    public void menuClearDatabase() {
        cleaning = true;
        
        jList1.clearSelection();
        listModel.clear();
        listStrings.clear();
        listImages.clear();
        for (int i=0; i<FaceList.size(); ++i) {
            TFaceRecord fr = FaceList.get(i);
            FSDK.FreeImage(fr.image);
            FSDK.FreeImage(fr.faceImage);
        }
        FaceList.clear();
        
        GroupLayout layout = (GroupLayout)mainPanel.getLayout();
        if (oldImagePanel != null) {
            imagePanel.setImage(null);
            layout.replace(oldImagePanel, imagePanel);
            oldImagePanel = imagePanel;
        }
        
        cleaning = false;
    }

    @Action
    public void menuOptions() {
        if (optionsDialog == null) optionsDialog = new OptionsDialog(this.getFrame(), true, this);
        optionsDialog.setVisible(true);
    }

    @Action
    public void menuEnrollFace() {
        //Assuming that faces are vertical (HandleArbitraryRotations = false) to speed up face detection
        FSDK.SetFaceDetectionParameters(false, true, 384);
        FSDK.SetFaceDetectionThreshold(FaceDetectionThreshold);
        
        FileDialog dlg = new FileDialog((java.awt.Frame)null, "Open File", FileDialog.LOAD);
        dlg.setFile("*.jpg");
        dlg.setVisible(true);
        String fileName = dlg.getDirectory() + dlg.getFile();
        
        TFaceRecord fr = new TFaceRecord();
        fr.ImageFileName = dlg.getFile();
        fr.image = new HImage();
       
        int res = FSDK.LoadImageFromFileW(fr.image, fileName); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "Cannot load " + fileName + " with error " + res, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jTextArea1.setText(jTextArea1.getText() + "Enrolling '" + fileName + "'\n");
        
        fr.FacePosition = new TFacePosition.ByReference();
        res = FSDK.DetectFace(fr.image, fr.FacePosition); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "No faces found. Try to lower the Minimal Face Quality parameter in the Options dialog box.", "Error", JOptionPane.ERROR_MESSAGE);
            FSDK.FreeImage(fr.image);
            return;
        }
        
        fr.faceImage = new HImage();
        FSDK.CreateEmptyImage(fr.faceImage);
        FSDK.CopyRect(fr.image,
                (int)(fr.FacePosition.xc - fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.yc - fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.xc + fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.yc + fr.FacePosition.w * 0.5), fr.faceImage);
        
        fr.FacialFeatures = new FSDK.FSDK_Features.ByReference();
        res = FSDK.DetectEyesInRegion(fr.image, fr.FacePosition, fr.FacialFeatures); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "Error detecting facial features.", "Error", JOptionPane.ERROR_MESSAGE);
            FSDK.FreeImage(fr.image);
            FSDK.FreeImage(fr.faceImage);
            return;
        }
        
        fr.FaceTemplate = new FSDK.FSDK_FaceTemplate.ByReference();
        res = FSDK.GetFaceTemplateInRegion(fr.image, fr.FacePosition, fr.FaceTemplate); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "Error retrieving face template.", "Error", JOptionPane.ERROR_MESSAGE);
            FSDK.FreeImage(fr.image);
            FSDK.FreeImage(fr.faceImage);
            return;
        }
        
        FaceList.add(fr);
        
        // Adding item to jList1 via its Model
        HImage tempImage = new HImage();
        FSDK.CreateEmptyImage(tempImage);
        int widthByReference[] = new int[1];
        FSDK.GetImageWidth(fr.faceImage, widthByReference);
        double ratio = 100.0 / widthByReference[0]; 
        FSDK.ResizeImage(fr.faceImage, ratio, tempImage);
        java.awt.Image thumbnail[] = new java.awt.Image[1];
        FSDK.SaveImageToAWTImage(tempImage, thumbnail, FSDK.FSDK_IMAGEMODE.FSDK_IMAGE_COLOR_24BIT);
        FSDK.FreeImage(tempImage);
        listImages.add(new ImageIcon(thumbnail[0]));
        listStrings.add(fr.ImageFileName);
        listModel.addElement(FaceList.size()-1);
        
        jTextArea1.setText(jTextArea1.getText() + "File '" + fileName + "' enrolled\n");
        
        //Draw big image on selection change
        jList1.setSelectedIndex(listModel.getSize()-1);
        
        
    }
    
    public class Sortable {
        public float similarity;
        public int index;
    }

    @Action
    public void menuMatchFace() {
        if (FaceList.isEmpty()){
            JOptionPane.showMessageDialog(mainPanel, "Please enroll faces first");
            return;
        }

        FileDialog dlg = new FileDialog((java.awt.Frame)null, "Open File", FileDialog.LOAD);
        dlg.setFile("*.jpg");
        dlg.setVisible(true);
        String fileName = dlg.getDirectory() + dlg.getFile();
        
        TFaceRecord fr = new TFaceRecord();
        fr.ImageFileName = dlg.getFile();
        fr.image = new HImage();
       
        int res = FSDK.LoadImageFromFileW(fr.image, fileName); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "Cannot load " + fileName + " with error " + res, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        fr.FacePosition = new TFacePosition.ByReference();
        res = FSDK.DetectFace(fr.image, fr.FacePosition); 
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "No faces found. Try to lower the Minimal Face Quality parameter in the Options dialog box.", "Error", JOptionPane.ERROR_MESSAGE);
            FSDK.FreeImage(fr.image);
            return;
        }
        
        fr.faceImage = new HImage();
        FSDK.CreateEmptyImage(fr.faceImage);
        FSDK.CopyRect(fr.image,
                (int)(fr.FacePosition.xc - fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.yc - fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.xc + fr.FacePosition.w * 0.5),
                (int)(fr.FacePosition.yc + fr.FacePosition.w * 0.5), fr.faceImage);
        
        fr.FaceTemplate = new FSDK.FSDK_FaceTemplate.ByReference();
        res = FSDK.GetFaceTemplateInRegion(fr.image, fr.FacePosition, fr.FaceTemplate); 
        
        if (res != FSDK.FSDKE_OK){
            JOptionPane.showMessageDialog(mainPanel, "Error retrieving face template.", "Error", JOptionPane.ERROR_MESSAGE);
            FSDK.FreeImage(fr.image);
            FSDK.FreeImage(fr.faceImage);
            return;
        }

        if (resultsDialog == null) resultsDialog = new ResultsDialog(this.getFrame(), true);
        resultsDialog.DrawFaceImage(fr);
        resultsDialog.listModel.clear();
        resultsDialog.listImages.clear();
        resultsDialog.listStrings.clear();
        
        
        // Matching
        float ThresholdByReference[] = new float[1];
        FSDK.GetMatchingThresholdAtFAR(FARValue/100.0f, ThresholdByReference);
        float Threshold = ThresholdByReference[0];
        int MatchedCount = 0;
        float SimilarityByReference[] = new float[1];
        
        java.util.List<Sortable> sim_ind = new ArrayList<Sortable>();
        for (int i=0; i<FaceList.size(); ++i){
            FSDK.MatchFaces(fr.FaceTemplate, FaceList.get(i).FaceTemplate, SimilarityByReference);
            float Similarity = SimilarityByReference[0];
            if (Similarity >= Threshold){
                Sortable s = new Sortable();
                s.index = i;
                s.similarity = Similarity;
                sim_ind.add(s);
                ++MatchedCount;
            }
        }
        
        if (MatchedCount == 0){
            JOptionPane.showMessageDialog(mainPanel, "No matches found. You can try to increase the FAR parameter in the Options dialog box.", "No matches", JOptionPane.INFORMATION_MESSAGE);
            FSDK.FreeImage(fr.image);
            FSDK.FreeImage(fr.faceImage);
            return;
        }
        
        Collections.sort(sim_ind, new Comparator<Sortable>() {
            @Override
            public int compare(Sortable obj1, Sortable obj2) {
                return ((Float)obj2.similarity).compareTo(obj1.similarity);
            }
        }); 
        
        // Adding JList elements to resultsDialog via its Model
        for (int i=0; i<sim_ind.size(); ++i){
            TFaceRecord cur_fr = FaceList.get(sim_ind.get(i).index);
            HImage tempImage = new HImage();
            FSDK.CreateEmptyImage(tempImage);
            int widthByReference[] = new int[1];
            FSDK.GetImageWidth(cur_fr.faceImage, widthByReference);
            double ratio = 100.0 / widthByReference[0]; 
            FSDK.ResizeImage(cur_fr.faceImage, ratio, tempImage);
            java.awt.Image thumbnail[] = new java.awt.Image[1];
            FSDK.SaveImageToAWTImage(tempImage, thumbnail, FSDK.FSDK_IMAGEMODE.FSDK_IMAGE_COLOR_24BIT);
            FSDK.FreeImage(tempImage);
            resultsDialog.listImages.add(new ImageIcon(thumbnail[0]));
            resultsDialog.listStrings.add(cur_fr.ImageFileName + "; similarity " + sim_ind.get(i).similarity*100.0f + "%");
            resultsDialog.listModel.addElement(i);
        }
        
        resultsDialog.setVisible(true);
    }
    
    
    
}
