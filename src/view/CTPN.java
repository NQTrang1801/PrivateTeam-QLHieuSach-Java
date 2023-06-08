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
import model.CTHDClass;
import model.CTHDCtr;
import model.CTPNClass;
import model.CTPNCtr;

/**
 *
 * @author NQTrang
 */
public class CTPN extends javax.swing.JFrame {

    /**
     * Creates new form CTPN
     */
    private String maTK;
    private String loaiTK;
    private String maPN;
    private String maNV;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public CTPN() {
        initComponents();
    }

    public CTPN(String maNV, String maTK, String loaiTK, String maPN) {
        initComponents();
        this.maNV = maNV;
        this.loaiTK = loaiTK;
        this.maTK = maTK;
        this.maPN = maPN;
        KetNoiCSDL();
        Load_data_PN();
        this.setLocationRelativeTo(this);
        txtTenSach.setEditable(false); // không cho phép chỉnh sửa
        txtTenSach.setEnabled(false); // không cho phép nhập liệutxtTenSach.setEditable(false); // không cho phép chỉnh sửa
        txtTenSach.setEnabled(false); // không cho phép nhập liệu
        txtMaSach.setEditable(false); // không cho phép chỉnh sửa
        txtMaSach.setEnabled(false); // không cho phép nhập liệu
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

    public void Load_data_PN() {
        try {
            if (this.maPN.isEmpty())  // Kiểm tra xem một mã phiếu nhập đã được truyền vào hay không
            {
                if (maNV.isEmpty() == false) {
                    ps = conn.prepareStatement("SELECT CTPN.MaPN, CTPN.MaSach, S.TenSach, CTHD.SL, CTPN.Gia \n"
                            + "FROM (select MaPN from PHIEUNHAP where MaNV = " + maNV + ") PN \n"
                            + "        JOIN CTPN ON PN.MaPN = CTPN.MaPN\n"
                            + "        JOIN (select MaSach, TenSach from SACH) S ON CTPN.MaSach = S.MaSach");  //Khai báo câu truy vấn SQL để lấy dữ liệu từ cơ sở dữ liệu, được lưu trữ trong biến `ps`
                } else {
                    ps = conn.prepareStatement("SELECT CTPN.MaPN, CTPN.MaSach, S.TenSach, CTPN.SL, CTPN.Gia \n"
                            + "FROM CTPN JOIN (select MaSach, TenSach from SACH) S ON CTPN.MaSach = S.MaSach");
                }
            } else {
                txtMaPN.setText(maPN);
                txtMaPN.setEditable(false); // không cho phép chỉnh sửa
                txtMaPN.setEnabled(false); // không cho phép nhập liệu
                ps = conn.prepareStatement("SELECT MaPN, CT.MaSach, S.TenSach, CT.SL, CT.Gia \n"
                        + "FROM (select MaPN, MaSach, SL, Gia from CTPN where MaPN = " + maPN + ") CT \n"
                        + "        JOIN (select MaSach, TenSach from SACH) S ON CT.MaSach = S.MaSach");
            }
            rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả trả về trong đối tượng `ResultSet` (`rs`)

            ResultSetMetaData rsd = rs.getMetaData();
            int c = rsd.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) jTListCTPN.getModel();  //Khởi tạo mô hình bảng `DefaultTableModel` và lấy mô hình của bảng `jTListCTPN` để sử dụng
            model.setRowCount(0);  //Đặt lại số lượng hàng của bảng là 0 để xóa các hàng hiện có.
            while (rs.next()) {
                Vector v1 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v1.add(rs.getString("MaPN"));
                    v1.add(rs.getString("MaSach"));
                    v1.add(rs.getString("TenSach"));
                    v1.add(rs.getString("SL"));
                    v1.add(rs.getString("Gia"));
                }
                model.addRow(v1);  //Thêm các giá trị của vector `v1` vào mô hình bảng
                jTListCTPN.setModel(model);  //Thiết lập mô hình bảng của bảng `jTListCTPN` bằng mô hình bảng mới
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyPN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        txtMaPN = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jBThem = new javax.swing.JButton();
        jBLamMoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListCTPN = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtSearchSach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 202, 164));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CHI TIẾT PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel2.setBackground(new java.awt.Color(243, 231, 231));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã phiếu nhập");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Mã sách");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Giá");

        jBXoa.setBackground(new java.awt.Color(196, 101, 38));
        jBXoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBXoa.setText("DELETE");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        jBSua.setBackground(new java.awt.Color(196, 101, 38));
        jBSua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBSua.setText("UPDATE");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        txtMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSachActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Số lượng");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jBThem.setBackground(new java.awt.Color(196, 101, 38));
        jBThem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBThem.setText("INSERT");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        jBLamMoi.setBackground(new java.awt.Color(196, 101, 38));
        jBLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBLamMoi.setText("REFESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Tên sách");

        txtTenSach.setActionCommand("<Not Set>");
        txtTenSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTenSach.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTenSach.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtTenSach.setVerifyInputWhenFocusTarget(false);
        txtTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSachActionPerformed(evt);
            }
        });

        jTListCTPN.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Mã sách", "Tên sách", "Số lượng", "Giá"
            }
        ));
        jTListCTPN.setGridColor(new java.awt.Color(204, 204, 204));
        jTListCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListCTPNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListCTPN);

        jLabel9.setText("<< SearchMaSach :");

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(195, 195, 195)
                        .addComponent(jBThem)
                        .addGap(44, 44, 44)
                        .addComponent(jBXoa)
                        .addGap(36, 36, 36)
                        .addComponent(jBSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenSach)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchSach)))
                        .addGap(616, 616, 616))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbTroLai))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbTroLai)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa chi tiết phiếu nhập
        StringBuilder sb = new StringBuilder();  //Khai báo một đối tượng `StringBuilder` để xây dựng thông báo lỗi.
        if (txtMaPN.getText().equals("") && txtMaSach.getText().equals(""))  //Kiểm tra xem người dùng đã nhập mã phiếu nhập và mã sách để xóa hay chưa
        {
            sb.append("!!Mã phiếu nhập và Mã sách không được để trống!!");
            txtMaPN.setBackground(Color.red);
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0)  //Kiểm tra xem `StringBuilder` có chứa thông báo lỗi không. Nếu có, hiển thị các thông báo lỗi cho người dùng.
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu nhập không?", "Xóa", JOptionPane.YES_NO_OPTION);  //Hiển thị hộp thoại `JOptionPane` để xác nhận xóa phiếu nhập
            if (result == JOptionPane.YES_OPTION) {
                CTPNCtr.DeleteCTPN(txtMaPN.getText(), txtMaSach.getText());  //Gọi phương thức `DeleteCTPN` từ lớp `CTPNCtr` để xóa chi tiết phiếu nhập được chọn
                JOptionPane.showMessageDialog(this, "Đã xóa  thành công");  // Hiển thị thông báo xóa thành công.
            } else {
                JOptionPane.showMessageDialog(this, "phiếu nhập chưa được xóa");  //Hiển thị thông báo lỗi nếu xóa không thành công.
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaPN.setText("");
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
    }
    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật chi tiết phiếu nhập
        StringBuilder sb = new StringBuilder();
        if (txtMaPN.getText().equals("") && txtMaSach.getText().equals("")) {
            sb.append("!!Mã phiếu nhập hoặc Mã sách không được để trống!!");
            txtMaPN.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            CTPNClass pn = new CTPNClass();
            //thiết lập các giá trị
            pn.setMaPN(txtMaPN.getText());
            pn.setMaSach(txtMaSach.getText());
            pn.setsL(txtSoLuong.getText());
            pn.setGia(txtGia.getText());
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật CTPN không?", "cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                CTPNCtr.UpdateCTPN(pn);  //Gọi phương thức `UpdateCTPN` từ lớp `CTPNCtr` để cập nhật chi tiết phiếu nhập được chọn
                JOptionPane.showMessageDialog(this, "Đã Cập nhật thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Phiếu nhập chưa được cập nhật");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void txtMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSachActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm mới chi tiết phiếu nhập
        StringBuilder sb = new StringBuilder();
        if (txtMaPN.getText().equals("") && txtMaSach.getText().equals("")) {
            sb.append("!!Mã phiếu nhập và Mã sách không được để trống!!");
            txtMaPN.setBackground(Color.red);
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            CTPNClass pn = new CTPNClass();
            pn.setMaPN(txtMaPN.getText());
            pn.setMaSach(txtMaSach.getText());
            pn.setsL(txtSoLuong.getText());

            pn.setGia(txtGia.getText());
            CTPNCtr.InsertCTPN(pn);  //Gọi phương thức `InsertCTPN` từ lớp `CTPNCtr` để thêm mới chi tiết phiếu nhập 
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
        // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách phiếu nhập và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Load_data_PN();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSachActionPerformed

    private void jTListCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListCTPNMouseClicked
        // đoạn code này dùng để hiển thị thông tin chi tiết phiếu nhập khi người dùng click chuột vào một dòng trong bảng jTListCTPN
        try {
            LamMoi();  //làm mới các trường nhập liệu và các nút trên giao diện `CTPN`
            int selectedIndex = jTListCTPN.getSelectedRow();  // Lấy chỉ số của dòng được chọn trong bảng `jTListCTPN`
            jTListCTPN.setColumnSelectionInterval(0, 4);  //Thiết lập cột được chọn trong bảng `jTListCTPN` từ cột 0 đến cột 4
            //hiển thị các giá trị 
            txtMaPN.setText(jTListCTPN.getValueAt(selectedIndex, 0).toString());
            txtMaSach.setText(jTListCTPN.getValueAt(selectedIndex, 1).toString());
            txtTenSach.setText(jTListCTPN.getValueAt(selectedIndex, 2).toString());
            txtSoLuong.setText(jTListCTPN.getValueAt(selectedIndex, 3).toString());
            txtGia.setText(jTListCTPN.getValueAt(selectedIndex, 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListCTPNMouseClicked

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLyPN pn = new QuanLyPN(maTK, loaiTK); //khởi tạo đối tượng QuanLyPN
        pn.setVisible(true); // hiển thị giao diện quản lý phiếu nhập
        this.dispose(); // đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tìm kiếm sách theo mã và hiển thị thông tin sách lên giao diện người dùng.
        if (txtSearchSach.getText().equals("") == false)  //Kiểm tra xem người dùng đã nhập mã sách để tìm kiếm hay chưa.
        {
            try (java.sql.Connection con = ConnectionUtils.getMyConnection()) //Mở kết nối đến cơ sở dữ liệu và thực hiện truy vấn SQL để lấy thông tin sách.
            {
                txtMaSach.setText(txtSearchSach.getText());  //Thiết lập mã sách được nhập cho trường mã sách trên giao diện người dùng.
                String SQL = "SELECT TenSach FROM SACH WHERE MASACH = " + txtSearchSach.getText();  //Khai báo câu truy vấn SQL để lấy tên sách theo mã sách được nhập
                PreparedStatement ps = con.prepareStatement(SQL);   //Tạo một đối tượng `PreparedStatement` để thực thi câu truy vấn
                ResultSet rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả trả về vào đối tượng `ResultSet`
                if (rs.next()) 
                {
                    txtTenSach.setText(rs.getString("TenSach"));  //lấy tên sách và thiết lập cho trường tên sách trên giao diện người dùng
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CTPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CTPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CTPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CTPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CTPN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListCTPN;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
