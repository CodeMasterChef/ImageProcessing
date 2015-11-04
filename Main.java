/*
 * Update: 21:55 24/9/2015
 * 
 * 
 */
package imageprocessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Dao Trung Duyet
 */
public class Main extends javax.swing.JFrame {

    BufferedImage inputImage;
    BufferedImage outputImage;

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        scrollPane = new javax.swing.JScrollPane();
        lblMain = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mn_file = new javax.swing.JMenu();
        menuItem_load = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuItemGrayscale = new javax.swing.JMenuItem();
        BinaryMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                form_load(evt);
            }
        });

        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new java.awt.Dimension(1200, 900));
        scrollPane.setMinimumSize(new java.awt.Dimension(400, 300));
        scrollPane.setName(""); // NOI18N

        lblMain.setMaximumSize(new java.awt.Dimension(1200, 900));
        scrollPane.setViewportView(lblMain);

        menuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        mn_file.setText("File");

        menuItem_load.setText("Load");
        menuItem_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_loadActionPerformed(evt);
            }
        });
        mn_file.add(menuItem_load);

        menuItemSave.setText("Save");
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        mn_file.add(menuItemSave);

        menuBar.add(mn_file);

        jMenu1.setText("Histogram");
        menuBar.add(jMenu1);

        jMenu2.setText("Point Processing");

        menuItemGrayscale.setText("Grayscale");
        menuItemGrayscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGrayscaleActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemGrayscale);

        BinaryMenuItem.setText("Binary");
        BinaryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BinaryMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(BinaryMenuItem);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(954, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1370, 669);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_loadActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Mở tâp tin ảnh:");
        // ImageFileFilter là lớp mình tạo ra để lọc các file ảnh
        chooser.setFileFilter(new locFileAnh());
        chooser.showOpenDialog(null);
        String location;
        if (chooser.getSelectedFile() != null) {
            File f = chooser.getSelectedFile();
            location = f.getAbsolutePath();

            try {
                inputImage = ImageIO.read(new File(location));
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image i = inputImage.getScaledInstance(inputImage.getWidth(), inputImage.getHeight(), Image.SCALE_AREA_AVERAGING);
            ImageIcon icon = new ImageIcon(i);
            lblMain.setIcon(icon);

        }


    }//GEN-LAST:event_menuItem_loadActionPerformed

    private void form_load(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_form_load
        // full màn hình
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }//GEN-LAST:event_form_load

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed
        // TODO add your handling code here:
        if (lblMain == null) {
            JOptionPane.showMessageDialog(null, "Chưa có thông tin để lưu ảnh!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        } else {
            JFileChooser save = new JFileChooser();
            save.setFileFilter(new locFileAnh());
            save.setDialogTitle("Lưu ảnh");

            int result = save.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = save.getSelectedFile();

                String fileName = file.getName();
                // nếu không có phần mở rộng thì thêm vào đuôi png
                if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".bmp"))) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                try {
                    ImageIO.write(outputImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_menuItemSaveActionPerformed

    private void loadImageToLabel() {
        // scale lại kích thước cho phù hợp với label
        Image image = outputImage.getScaledInstance(inputImage.getWidth(), inputImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        lblMain.setIcon(imageIcon);
    }

    private void menuItemGrayscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGrayscaleActionPerformed
        // TODO add your handling code here:
        if (lblMain.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Chưa có ảnh để xử lí!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        } else {
            Grayscale grayscale = new Grayscale(inputImage);
            grayscale.processing();
            outputImage = grayscale.getOutputImage();
            // load to label 
            loadImageToLabel();
        }
    }//GEN-LAST:event_menuItemGrayscaleActionPerformed

    private void BinaryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BinaryMenuItemActionPerformed
        // TODO add your handling code here:
        if (lblMain.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Chưa có ảnh để xử lí!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        } else {
            // xử lí grayscale trước 
            Grayscale grayscale = new Grayscale(inputImage);
            grayscale.processing();
            // xử lí nhị phân
            Binary binary = new Binary(grayscale.getOutputImage());
            binary.processing();
            outputImage = binary.getOutputImage();
            loadImageToLabel();
        }
    }//GEN-LAST:event_BinaryMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BinaryMenuItem;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel lblMain;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemGrayscale;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItem_load;
    private javax.swing.JMenu mn_file;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
