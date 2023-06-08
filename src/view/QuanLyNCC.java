/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhaCungCapClass;
import model.NhaCungCapCtr;

/**
 *
 * @author DLHa
 */
public class QuanLyNCC extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    /**
     * Creates new form QuanLyNCC
     */
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public QuanLyNCC(String maTK, String loaiTK) {
        initComponents();
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        this.setLocationRelativeTo(this);
        KetNoiCSDL();
        Load_data_NCC();
    }

   public void KetNoiCSDL() {
        try {
            conn = dao.ConnectionOracle.getOracleConnection();
            //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(CTPN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CTPN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Load_data_NCC() {

        try {
            ps = conn.prepareStatement("SELECT * FROM NHACUNGCAP "); //thực hiện truy vấn SQL để lấy toàn bộ thông tin nhà cung cấp
            rs = ps.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int c = rsd.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTListNCC.getModel();    //Lấy mô hình bảng danh sách nhà cung cấp từ giao diện `QuanLyNCC`
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách nhà cung cấp 

            while (rs.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một nhà cung cấp
                for (int i = 1; i <= c; i++)   //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    v1.add(rs.getInt("MaNCC"));
                    v1.add(rs.getString("TenNCC"));
                    v1.add(rs.getString("DiaChi"));
                    v1.add(rs.getString("SDT"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTListNCC.setModel(model);  // Cập nhật lại bảng danh sách phiếu nhập sách
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNCC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListNCC = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBTimKiem = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBLamMoi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(197, 110, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTListNCC.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên NCC", "Địa chỉ", "SĐT"
            }
        ));
        jTListNCC.setGridColor(new java.awt.Color(204, 204, 204));
        jTListNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListNCC);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã NCC");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Tên NCC ");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Địa chỉ");

        txtMaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNCCActionPerformed(evt);
            }
        });

        jBThem.setBackground(new java.awt.Color(103, 242, 158));
        jBThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBThem.setText("INSERT");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        jBXoa.setBackground(new java.awt.Color(241, 72, 72));
        jBXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBXoa.setText("DELETE");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        jBSua.setBackground(new java.awt.Color(220, 220, 85));
        jBSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBSua.setText("UPDATE");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        jBTimKiem.setBackground(new java.awt.Color(196, 101, 38));
        jBTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBTimKiem.setText("Search");
        jBTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTimKiemActionPerformed(evt);
            }
        });

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Số ĐT");

        jBLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        jBLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBLamMoi.setText("REFRESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbTroLai))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel7))
                                            .addGap(74, 74, 74)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtTenNCC)
                                                .addComponent(txtDiaChi))))
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jBThem)
                                .addGap(43, 43, 43)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBSua)
                                        .addGap(30, 30, 30)))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jbTroLai)
                .addGap(7, 7, 7))
        );

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTListNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListNCCMouseClicked
        // đoạn code này dùng để hiển thị thông tin nhà cung cấp khi người dùng click chuột vào một dòng trong bảng jTListNCC

        LamMoi(); //làm mới các trường nhập liệu và các nút trên giao diện `QuanLyPN`
        int selectedIndex = jTListNCC.getSelectedRow();   // Lấy chỉ số của dòng được chọn trong bảng `jTListNCC'
        jTListNCC.setColumnSelectionInterval(0, 3);  //Thiết lập cột được chọn trong bảng `jTListNCC` từ cột 0 đến cột 3
        //hiển thị các giá trị 
        txtMaNCC.setText(jTListNCC.getValueAt(selectedIndex, 0).toString());
        txtTenNCC.setText(jTListNCC.getValueAt(selectedIndex, 1).toString());
        txtDiaChi.setText(jTListNCC.getValueAt(selectedIndex, 2).toString());
        txtSDT.setText(jTListNCC.getValueAt(selectedIndex, 3).toString());
    }//GEN-LAST:event_jTListNCCMouseClicked

    private void txtMaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNCCActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một nhà cung cấp mới vào cơ sở dữ liệu 
        StringBuilder sb = new StringBuilder(); //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaNCC.getText().equals(""))  //Kiểm tra nếu mã nhà cung cấp để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("Mã nhà cung cấp không được để trống!!!");
            txtMaNCC.setBackground(Color.red);
        } else {
            txtMaNCC.setBackground(Color.white);
        }
        if (sb.length() > 0) //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó và không thực hiện thêm nhà cung cấp mới vào cơ sở dữ liệu
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NhaCungCapClass NCC = new NhaCungCapClass();
            //thiết lập các giá trị 
            NCC.setMaNCC(txtMaNCC.getText());
            NCC.setTenNCC(txtTenNCC.getText());
            NCC.setDiaChi(txtDiaChi.getText());
            NCC.setSdt(txtSDT.getText());

            NhaCungCapCtr dao = new NhaCungCapCtr();
            dao.insert(NCC); //Gọi phương thức `insert()` trong lớp `NhaCungCapCtr` để thêm đối tượng `NhaCungCapClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Nhà cung cấp được thêm vào thành công!");  //Hiển thị thông báo xác nhận nếu thêm nhà cung cấp mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm nhà cung cấp mới

        }

    }//GEN-LAST:event_jBThemActionPerformed

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một nhà cung cấp trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaNCC.getText().equals("")) {
            sb.append("Mã nhà cung cấp không được để trống!");
            txtMaNCC.setBackground(Color.red);
        } else {
            txtMaNCC.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            NhaCungCapCtr dao = new NhaCungCapCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhà cung cấp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa nhà cung cấp hay không
            {
                dao.delete(txtMaNCC.getText());  //gọi phương thức `delete()` trong lớp `NhaCungCapCtr` để xóa nhà cung cấp tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, " đã xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, " chưa được xóa!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
       // Đoạn code này được sử dụng để cập nhật một nhà cung cấp trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaNCC.getText().equals("")) {
            sb.append("Mã nhà cung cấp không được để trống!");
            txtMaNCC.setBackground(Color.red);
        } else {
            txtMaNCC.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NhaCungCapClass NCC = new NhaCungCapClass();

            NCC.setMaNCC(txtMaNCC.getText());
            NCC.setTenNCC(txtTenNCC.getText());
            NCC.setDiaChi(txtDiaChi.getText());
            NCC.setSdt(txtSDT.getText());

            NhaCungCapCtr dao = new NhaCungCapCtr();
            dao.update(NCC); //Gọi phương thức `update()` trong lớp `NhaCungCapCtr` để cập nhật đối tượng `NhaCungCapClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Nhà cung cấp được cập nhật thành công!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
        // Đoạn code này dùng để tìm kiếm một nhà cung cấp trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaNCC.getText().equals("")) {
            sb.append("Mã nhà cung cấp không được để trống!");
            txtMaNCC.setBackground(Color.red);
        } else {
            txtMaNCC.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            NhaCungCapCtr ncc = new NhaCungCapCtr();
            NhaCungCapClass NCC = ncc.find(txtMaNCC.getText()); //sử dụng phương thức `find()` để tìm kiếm nhà cung cấp có mã `txtMaNCC` trong cơ sở dữ liệu

            if (NCC != null) //Kiểm tra xem nhà cung cấp được tìm thấy hay không
            {
                //hiển thị thông tin của nhà cung cấp
                int selectedIndex = jTListNCC.getSelectedRow();

                txtMaNCC.setText(NCC.getMaNCC());
                txtTenNCC.setText(NCC.getTenNCC());
                txtDiaChi.setText(NCC.getDiaChi());
                txtSDT.setText(NCC.getSdt());

            } else {
                JOptionPane.showMessageDialog(this, "Nhà cung cấp tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
        // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách nhà cung cấp và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Load_data_NCC();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã phiếu nhập mới và hiển thị mã đó lên trường nhập liệu "txtMaNCC"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())  //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaNCC() as newMaNCC from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã nhà cung cấp mới
            PreparedStatement ps = con.prepareStatement(SQL);  //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())  //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaNCC.setText(rs.getString("NEWMANCC")); //Lấy giá trị của cột "newMaNCC" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaNCC"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String maTK, String loaiTK) {
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
            java.util.logging.Logger.getLogger(QuanLyNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNCC(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBTimKiem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListNCC;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
