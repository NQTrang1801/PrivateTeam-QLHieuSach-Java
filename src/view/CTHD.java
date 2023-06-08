/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.CTHDClass;
import model.CTHDCtr;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author NQTrang
 */
public class CTHD extends javax.swing.JFrame {

    private String maNV;
    private String maTK;
    private String loaiTK;
    private String maHD;

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form CTHD
     */
    public CTHD(String maNV, String maTK, String loaiTK, String maHD) {
        initComponents();
        this.maNV = maNV;
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        this.maHD = maHD;
        KetNoiCSDL();
        Load_data_HD();
        this.setLocationRelativeTo(this);
        txtTenSach.setEditable(false); // không cho phép chỉnh sửa
        txtTenSach.setEnabled(false); // không cho phép nhập liệu
        txtGia.setEditable(false); // không cho phép chỉnh sửa
        txtGia.setEnabled(false); // không cho phép nhập liệu
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

    public void Load_data_HD() {
        try {
            if (this.maHD.isEmpty()) // Kiểm tra xem một mã hóa đơn đã được truyền vào hay không
            {
                if (maNV.isEmpty() == false) {
                    ps = conn.prepareStatement("SELECT CTHD.MaHD, CTHD.MaSach, S.TenSach, CTHD.SL, S.Gia \n"
                            + "FROM (select MaHD from HOADON where MaNV = " + maNV + ") HD \n"
                            + "        JOIN CTHD ON HD.MaHD = CTHD.MaHD\n"
                            + "        JOIN (select MaSach, TenSach, Gia from SACH) S ON CTHD.MaSach = S.MaSach"); // Kiểm tra xem một mã phiếu nhập đã được truyền vào hay không
                } else {
                    ps = conn.prepareStatement("SELECT CTHD.MaHD, CTHD.MaSach, S.TenSach, CTHD.SL, S.Gia \n"
                            + "FROM CTHD JOIN (select MaSach, TenSach, Gia from SACH) S ON CTHD.MaSach = S.MaSach");
                }
            } else {
                txtMaHD.setText(maHD);
                txtMaHD.setEditable(false); // không cho phép chỉnh sửa
                txtMaHD.setEnabled(false); // không cho phép nhập liệu
                ps = conn.prepareStatement("SELECT MaHD, CT.MaSach, S.TenSach, CT.SL, S.Gia \n"
                        + "FROM (select MaHD, MaSach, SL from CTHD where MaHD = " + maHD + ") CT \n"
                        + "        JOIN (select MaSach, TenSach, Gia from SACH) S ON CT.MaSach = S.MaSach");
            }
            rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả trả về trong đối tượng `ResultSet` (`rs`)

            ResultSetMetaData rsd = rs.getMetaData();
            int c = rsd.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) jTListCTHD.getModel();  //Khởi tạo mô hình bảng `DefaultTableModel` và lấy mô hình của bảng `jTListCTHD` để sử dụng
            model.setRowCount(0);  //Đặt lại số lượng hàng của bảng là 0 để xóa các hàng hiện có.
            while (rs.next()) {
                Vector v1 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v1.add(rs.getString("MaHD"));
                    v1.add(rs.getString("MaSach"));
                    v1.add(rs.getString("TenSach"));
                    v1.add(rs.getString("SL"));
                    v1.add(rs.getString("Gia"));
                }
                model.addRow(v1); //Thêm các giá trị của vector `v1` vào mô hình bảng
                jTListCTHD.setModel(model);  //Thiết lập mô hình bảng của bảng `jTListCTHD` bằng mô hình bảng mới
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaHD.setText("");
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
    }

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
        txtMaHD = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jBThem = new javax.swing.JButton();
        jBLamMoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListCTHD = new javax.swing.JTable();
        jBtnPrint = new javax.swing.JButton();
        txtSearchSach = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 202, 164));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CHI TIẾT  HÓA ĐƠN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel2.setBackground(new java.awt.Color(243, 231, 231));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn");

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

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
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

        jTListCTHD.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sách", "Tên sách", "Số lượng", "Giá"
            }
        ));
        jTListCTHD.setGridColor(new java.awt.Color(204, 204, 204));
        jTListCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListCTHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListCTHD);

        jBtnPrint.setBackground(new java.awt.Color(255, 204, 204));
        jBtnPrint.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jBtnPrint.setText("PRINT");
        jBtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPrintActionPerformed(evt);
            }
        });

        txtSearchSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSachActionPerformed(evt);
            }
        });

        jLabel1.setText("SearchMaSach >>");

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("S");
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
                    .addComponent(txtTenSach)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBThem)
                        .addGap(41, 41, 41)
                        .addComponent(jBXoa)
                        .addGap(39, 39, 39)
                        .addComponent(jBSua)
                        .addGap(34, 34, 34)
                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jBThem, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbTroLai)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbTroLai)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm mới chi tiết phiếu nhập
        StringBuilder sb = new StringBuilder();  //Khai báo một đối tượng `StringBuilder` để xây dựng thông báo lỗi.
        if (txtMaHD.getText().equals("") && txtMaSach.getText().equals(""))  //Kiểm tra xem người dùng đã nhập mã hóa đơn và mã sách để xóa hay chưa
        {
            sb.append("!!Mã hóa đơn và Mã sách không được để trống!!");
            txtMaHD.setBackground(Color.red);
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0)   //Kiểm tra xem `StringBuilder` có chứa thông báo lỗi không. Nếu có, hiển thị các thông báo lỗi cho người dùng.
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            CTHDClass cthd = new CTHDClass();
            cthd.setMaHD(txtMaHD.getText());
            cthd.setMaSach(txtMaSach.getText());
            cthd.setsL(txtSoLuong.getText());

            CTHDCtr.InsertCTHD(cthd);  //Gọi phương thức `InsertCTHD` từ lớp `CTHDCtr` để thêm mới chi tiết hóa đơn
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.");  //hiển thị thông báo đã thêm thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa chi tiết hóa đơn
        StringBuilder sb = new StringBuilder();
        if (txtMaHD.getText().equals("") && txtMaSach.getText().equals("")) {
            sb.append("!!Mã hóa đơn và Mã sách không được để trống!!");
            txtMaHD.setBackground(Color.red);
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hóa đơn không?", "Xóa", JOptionPane.YES_NO_OPTION);
            //Hiển thị hộp thoại `JOptionPane` để xác nhận xóa hóa đơn 
            if (result == JOptionPane.YES_OPTION)
            {
                CTHDCtr.DeleteCTHD(txtMaHD.getText(), txtMaSach.getText());  //Gọi phương thức `DeleteCTPN` từ lớp `CTHDCtr` để xóa chi tiết hóa đơn được chọn
                JOptionPane.showMessageDialog(this, "Đã xóa  thành công");  // Hiển thị thông báo xóa thành công.
            } else {
                JOptionPane.showMessageDialog(this, "hóa đơn chưa được xóa");  //Hiển thị thông báo lỗi nếu xóa không thành công.
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật chi tiết hóa đơn
        StringBuilder sb = new StringBuilder();
        if (txtMaHD.getText().equals("") && txtMaSach.getText().equals("")) {
            sb.append("!!Mã hóa đơn hoặc Mã sách không được để trống!!");
            txtMaHD.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            CTHDClass cthd = new CTHDClass();
            //thiết lập các giá trị
            cthd.setMaHD(txtMaHD.getText());
            cthd.setMaSach(txtMaSach.getText());
            cthd.setsL(txtSoLuong.getText());
 
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật CTHD không?", "cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                CTHDCtr.UpdateCTHD(cthd);  //Gọi phương thức `UpdateCTHD` từ lớp `CTHDCtr` để cập nhật chi tiết hóa đơn được chọn
                JOptionPane.showMessageDialog(this, "Đã Cập nhật thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "hóa đơn chưa được cập nhật");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed

         // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách phiếu nhập và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Load_data_HD();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon(maTK, loaiTK);
        hd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void txtMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSachActionPerformed

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenSachActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void jTListCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListCTHDMouseClicked
        // đoạn code này dùng để hiển thị thông tin chi tiết hóa đơn khi người dùng click chuột vào một dòng trong bảng jTListCTHD
        try {
            LamMoi(); //làm mới các trường nhập liệu và các nút trên giao diện `CTHD
            int selectedIndex = jTListCTHD.getSelectedRow();  // Lấy chỉ số của dòng được chọn trong bảng `jTListCTHD`
            jTListCTHD.setColumnSelectionInterval(0, 4);  //Thiết lập cột được chọn trong bảng `jTListCTHD` từ cột 0 đến cột 4
            txtMaHD.setText(jTListCTHD.getValueAt(selectedIndex, 0).toString());
            txtMaSach.setText(jTListCTHD.getValueAt(selectedIndex, 1).toString());
            txtTenSach.setText(jTListCTHD.getValueAt(selectedIndex, 2).toString());
            txtSoLuong.setText(jTListCTHD.getValueAt(selectedIndex, 3).toString());
            txtGia.setText(jTListCTHD.getValueAt(selectedIndex, 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListCTHDMouseClicked

    private void jBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrintActionPerformed
        // TODO add your handling code here:
        if (txtMaHD.getText().equals("") == false) {
            try {

                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\21521\\OneDrive\\Desktop\\DoAnJavaHQT\\src\\report\\rptHoaDonKhachHang.jrxml");
                String query = "select HD.MAHD, NGAYHD, NV.HOTENNV, KH.HOTENKH, KH.SoDT, KH.LoaiKH, CT.MaSach, S.TenSach, CT.SL, S.Gia, HD.ThanhTien\n"
                        + "from (select MAHD, NGAYHD, MAKH, MANV, THANHTIEN from HOADON where MAHD = " + txtMaHD.getText() + ") HD\n"
                        + "        join (select MANV, HO || ' ' || TEN as HOTENNV from NHANVIEN) NV on NV.MANV = HD.MANV\n"
                        + "        join (select MAKH, HO ||' '|| TEN as HOTENKH, SoDT, LOAIKH from KHACHHANG) KH on HD.MAKH = KH.MAKH\n"
                        + "        join CTHD CT on CT.MAHD = HD.MAHD\n"
                        + "        join (select MaSach, TenSach, GIA from SACH) S on CT.MaSACH = S.MASACH";
                JRDesignQuery updateQuery = new JRDesignQuery();
                updateQuery.setText(query);
                jdesign.setQuery(updateQuery);
                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(jreport, null, conn);
                JasperViewer.viewReport(jprint);
            } catch (JRException ex) {
                Logger.getLogger(CTHD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBtnPrintActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tìm kiếm sách theo mã và hiển thị thông tin sách lên giao diện người dùng.
        
        if (txtSearchSach.getText().equals("") == false)  //Kiểm tra xem người dùng đã nhập mã sách để tìm kiếm hay chưa.
        {
            txtMaSach.setText(txtSearchSach.getText()); //Thiết lập mã sách được nhập cho trường mã sách trên giao diện người dùng.
            try (java.sql.Connection con = ConnectionUtils.getMyConnection())  //Mở kết nối đến cơ sở dữ liệu và thực hiện truy vấn SQL để lấy thông tin sách.
            {
                String SQL = "SELECT TenSach, Gia FROM SACH WHERE MASACH = " + txtMaSach.getText();    //Khai báo câu truy vấn SQL để lấy tên sách theo mã sách được nhập
                PreparedStatement ps = con.prepareStatement(SQL);  //Tạo một đối tượng `PreparedStatement` để thực thi câu truy vấn
                ResultSet rs = ps.executeQuery(); //Thực thi câu truy vấn và lưu kết quả trả về vào đối tượng `ResultSet`
                if (rs.next()) {
                    txtTenSach.setText(rs.getString("TenSach"));  //lấy tên sách và thiết lập cho trường tên sách trên giao diện người dùng
                    txtGia.setText(rs.getString("Gia"));    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSachActionPerformed

    /**
     * @param args the command line arguments
     */
    /**
     *
     * @param maTK
     * @param args the command line arguments
     */
    public static void main(String maNV, String maTK, String loaiTK, String maHD) {
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
            java.util.logging.Logger.getLogger(CTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CTHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CTHD(maNV, maTK, loaiTK, maHD).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jBtnPrint;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListCTHD;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
