/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.hotel;

import bkap.hotel.DangNhap;
//import bkap.internalframe.DangNhap;
import bkap.internalframe.DoiMatKhau;
import bkap.internalframe.User;
import bkap.projectHotel.internalFrame.Service_JInternalFrame;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author admi
 */
public class MainFrame extends javax.swing.JFrame {
    boolean isLogin = false; 
    String user,pass;
    public static JDesktopPane actionJPanel;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        Login_Jdialog dangNhap = new Login_Jdialog(this, true);
        dangNhap.setVisible(true);
        if(dangNhap.isCheckDN() == true) {
            dangNhap.dispose();
            user = Login_Jdialog.txtTenDN.getText();
            pass = Login_Jdialog.txtMatKhau.getText();
            initComponents();
            this.setLocationRelativeTo(null);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            setConfig();
            setTitle("Quản lý khách sạn - " + user);
            setRoleGUI(dangNhap.getLevel());
        }
    }
    
    private void setRoleGUI(int role) {
        if (role == 1) {
            // mCaiDat.setEnabled(true);
        } else if (role == 2) {
            jmiDichVu.setEnabled(false);
            jmiUser.setEnabled(false);
        }
    }
    
    public void setConfig(){
//        Xet toa do hien thi form o giua man hin 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
    }
    
    private boolean checkOnly(JInternalFrame innerFrame) {
        JInternalFrame[] arrFrame = desktopPaneMain.getAllFrames();
        for (JInternalFrame frame : arrFrame) {
            if(frame.getClass().getName() == innerFrame.getClass().getName()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     Hàm căn giữa Jinternalframe ra giữa màn hình
     */
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = desktopPaneMain.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        desktopPaneMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mItemDoiMK = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiUser = new javax.swing.JMenuItem();
        jmiDichVu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BKAP - Phần mềm quản lý khách sạn");

        jToolBar1.setRollover(true);

        javax.swing.GroupLayout desktopPaneMainLayout = new javax.swing.GroupLayout(desktopPaneMain);
        desktopPaneMain.setLayout(desktopPaneMainLayout);
        desktopPaneMainLayout.setHorizontalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneMainLayout.setVerticalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/settings.png"))); // NOI18N
        jMenu2.setText("Tài khoản");

        mItemDoiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/lock.png"))); // NOI18N
        mItemDoiMK.setText("Đổi mật khẩu");
        mItemDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemDoiMKActionPerformed(evt);
            }
        });
        jMenu2.add(mItemDoiMK);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/log-out-29775.jpg"))); // NOI18N
        jMenuItem1.setText("Đăng Xuất");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/exit.png"))); // NOI18N
        jMenuItem2.setText("Thoát");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Quản lý");

        jmiUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkap/icon/nhanvien.png"))); // NOI18N
        jmiUser.setText("Nhân Viên");
        jmiUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUserActionPerformed(evt);
            }
        });
        jMenu1.add(jmiUser);

        jmiDichVu.setText("Dịch Vụ");
        jmiDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDichVuActionPerformed(evt);
            }
        });
        jMenu1.add(jmiDichVu);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
            .addComponent(desktopPaneMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPaneMain))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mItemDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemDoiMKActionPerformed
        // TODO add your handling code here:
        DoiMatKhau dmk = new DoiMatKhau();
        if(!checkOnly(dmk)) {
            centerJIF(dmk);
            desktopPaneMain.add(dmk);
        }
    }//GEN-LAST:event_mItemDoiMKActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jmiUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUserActionPerformed
        // TODO add your handling code here:
        User user = new User();
        if(!checkOnly(user)) {
            centerJIF(user);
            desktopPaneMain.add(user);
        }
        
    }//GEN-LAST:event_jmiUserActionPerformed

    private void jmiDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDichVuActionPerformed
        // TODO add your handling code here:
        Service_JInternalFrame jInternalFrame = new Service_JInternalFrame();
        if(!checkOnly(jInternalFrame)) {
            centerJIF(jInternalFrame);
            desktopPaneMain.add(jInternalFrame);
        }
    }//GEN-LAST:event_jmiDichVuActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
//                MainFrame trangchu = new MainFrame();
//
//                Runnable sh = new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(1000);
//                            // Hiển thị dialog đăng nhập
//                            trangchu.showLogin();
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                };
//                Thread task = new Thread(sh);
//                task.start();
//                trangchu.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPaneMain;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmiDichVu;
    private javax.swing.JMenuItem jmiUser;
    private javax.swing.JMenuItem mItemDoiMK;
    // End of variables declaration//GEN-END:variables
}
