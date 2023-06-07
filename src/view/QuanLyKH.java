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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHangCLass;
import model.KhachHangCtr;

/**
 *
 * @author NTVy
 */
public class QuanLyKH extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;

    /**
     * Creates new form QuanLyKH
     */
    Connection conn; //Biến kết nối cơ sở dữ liệu.
    PreparedStatement ps;  //Biến truy vấn chuẩn bị để thực thi.
    ResultSet rs;  //Biến lưu trữ kết quả trả về từ cơ sở dữ liệu.
    public QuanLyKH(String maTK, String loaiTK) {
        initComponents();
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        this.setLocationRelativeTo(this);
        KetNoiCSDL();
        KhachHang_Load();
    }
    public QuanLyKH() {
        initComponents();
        this.setLocationRelativeTo(this);
        KetNoiCSDL();
        KhachHang_Load();
    }
    
    public void KetNoiCSDL() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //Load lớp Oracle JDBC driver bằng cách sử dụng phương thức static `forName()` của lớp Class
            System.out.print("Ket noi thanh cong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyKH.class.getName()).log(Level.SEVERE, null, ex);    //Xử lý ngoại lệ ClassNotFoundException và hiển thị chi tiết lỗi lên bảng điều khiển
        }   // Bắt đầu khối try-catch để bắt ngoại lệ ClassNotFoundException, trong trường hợp không tìm thấy lớp jdbc driver để kết nối với cơ sở dữ liệu
        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl21", "PrivateTeam", "12345678");
            //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKH.class.getName()).log(Level.SEVERE, null, ex);
        }   ////  Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp kết nối cơ sở dữ liệu thất bại

    }

    public void KhachHang_Load() {
        //đoạn code sử dụng để tải dữ liệu từ cơ sở dữ liệu và hiển thị chúng trong bảng `jTListKH
        try {
            ps = conn.prepareStatement("SELECT * FROM KHACHHANG ");  //thực hiện câu truy vấn SQL lấy tất cả thông tin trong Khách hàng 
            rs = ps.executeQuery();  //thực hiện câu truy vấn lưu kết quả vào rs


            ResultSetMetaData rsd = rs.getMetaData();  //lấy thông tin về các cột trong kết quả truy vấn
            int c = rsd.getColumnCount();  //lưu trữ số lượng cột vào biến `c`

            DefaultTableModel model = (DefaultTableModel) jTListKH.getModel();  // Lấy một tham chiếu đến mô hình dữ liệu của bảng `jTListNV`
            model.setRowCount(0);  // xóa tất cả các dòng trong bảng để chuẩn bị cho việc tải dữ liệu mới


            while (rs.next()) {
                Vector v1 = new Vector(); //tạo một đối tượng `Vector` và thêm các giá trị của các cột trong bản ghi vào đó
                for (int i = 1; i <= c; i++) {
                    v1.add(rs.getString("MaKH"));
                    v1.add(rs.getString("Ho"));
                    v1.add(rs.getString("Ten"));
                    v1.add(rs.getDate("NgaySinh"));
                    v1.add(rs.getString("GioiTinh"));
                    v1.add(rs.getString("SoDT"));
                    v1.add(rs.getString("LoaiKH"));
                    v1.add(rs.getString("DiemTichLuy"));
                }
                model.addRow(v1);  //Thêm đối tượng `Vector` vừa tạo vào `DefaultTableModel`
                jTListKH.setModel(model);  //cập nhật lại bảng `jTListKH` để hiển thị tất cả các bản ghi được tải
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaKH.setText("");
        txtHoKH.setText("");
        txtTenKH.setText("");
        jDNgaySinh.setDate(null);
        gtNam.setSelected(false);
        gtNu.setSelected(false);
        txtSoDT.setText("");
        jRThanThiet.setSelected(false);
        jRThuong.setSelected(false);
        jRVIP.setSelected(false);
        txtDiemTichLuy.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListKH = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jDNgaySinh = new com.toedter.calendar.JDateChooser();
        txtHoKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        gtNam = new javax.swing.JRadioButton();
        gtNu = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jRThuong = new javax.swing.JRadioButton();
        jRThanThiet = new javax.swing.JRadioButton();
        jbTroLai = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtDiemTichLuy = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBLamMoi = new javax.swing.JButton();
        jBTimKiem = new javax.swing.JButton();
        jRVIP = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ  KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN KHÁCH HÀNG ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel3.setText("DANH SÁCH KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTListKH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MAKH", "Họ", "Tên", "Ngày sinh", "Giới tính", "Số ĐT", "Loại KH", "Điểm TL"
            }
        ));
        jTListKH.setGridColor(new java.awt.Color(204, 204, 204));
        jTListKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListKH);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã khách hàng");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày sinh");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Họ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Giới tính");

        buttonGroup1.add(gtNam);
        gtNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        gtNam.setSelected(true);
        gtNam.setText("Nam");
        gtNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gtNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(gtNu);
        gtNu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        gtNu.setText("Nữ");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Loại khách hàng");

        buttonGroup2.add(jRThuong);
        jRThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRThuong.setSelected(true);
        jRThuong.setText("Thường");

        buttonGroup2.add(jRThanThiet);
        jRThanThiet.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRThanThiet.setText("Thân thiết");

        jbTroLai.setBackground(new java.awt.Color(195, 105, 75));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Điểm tích lũy");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Tên");

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
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

        jBLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        jBLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBLamMoi.setText("REFRESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
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

        buttonGroup2.add(jRVIP);
        jRVIP.setText("VIP");
        jRVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRVIPActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Số ĐT");

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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(gtNu))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoKH, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(gtNam))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jBThem)
                        .addGap(36, 36, 36)
                        .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jBSua))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(62, 62, 62)
                                .addComponent(txtSoDT))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jRThanThiet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRThuong))
                                    .addComponent(txtDiemTichLuy))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRVIP))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbTroLai)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbTroLai)
                            .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gtNam)
                            .addComponent(gtNu)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jRThuong)
                            .addComponent(jRThanThiet)
                            .addComponent(jRVIP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiemTichLuy))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gtNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gtNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gtNamActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một khách hàng mới vào cơ sở dữ liệu 
        StringBuilder sb = new StringBuilder();  //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaKH.getText().equals(""))  //Kiểm tra nếu mã khách hàng để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("Mã khách hàng không được để trống!!!");
            txtMaKH.setBackground(Color.red);
        } else {
            txtMaKH.setBackground(Color.white);
        }
        if (sb.length() > 0) //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó và không thực hiện thêm khách hàng mới vào cơ sở dữ liệu
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            KhachHangCLass KH = new KhachHangCLass();
            //thiết lập các giá trị 
            KH.setMaKH(txtMaKH.getText());
            KH.setHo(txtHoKH.getText());
            KH.setTen(txtTenKH.getText());
            if (jDNgaySinh.getDate() != null) {
                java.util.Date utilStartDate = jDNgaySinh.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                KH.setNgaySinh(sqlStartDate);
            }
            if(gtNam.isSelected()){
                KH.setGioiTinh(gtNam.getText());
            }
            if (gtNu.isSelected()) {
                KH.setGioiTinh(gtNu.getText());
            }
            KH.setSoDT(txtSoDT.getText());
            if(jRThanThiet.isSelected()){
                KH.setLoaiKH(jRThanThiet.getText());
            }
            if(jRThuong.isSelected()){
                KH.setLoaiKH(jRThuong.getText());
            }
            if(jRVIP.isSelected()){
                KH.setLoaiKH(jRVIP.getText());
            }
            KH.setDiemTichLuy(txtDiemTichLuy.getText());

            KhachHangCtr  khctr = new KhachHangCtr();
            khctr.insert(KH);  //Gọi phương thức `insert()` trong lớp `KhachHangCtr` để thêm đối tượng `KhachHangClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Khách hàng được thêm vào thành công!");  //Hiển thị thông báo xác nhận nếu thêm khách hàng mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());  //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm khách hàng mới

        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một khách hàng trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaKH.getText().equals("")) {
            sb.append("Mã khách hàng không được để trống!");
            txtMaKH.setBackground(Color.red);
        } else {
            txtMaKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            KhachHangCtr khctr = new KhachHangCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)  //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa nhà cung cấp hay không
            {
                khctr.delete(txtMaKH.getText());  //gọi phương thức `delete()` trong lớp `KhachHangCtr` để xóa nhà cung cấp tương ứng khỏi cơ sở dữ liệu
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
        // Đoạn code này được sử dụng để cập nhật một khách hàng trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaKH.getText().equals("")) {
            sb.append("Mã khách hàng không được để trống!!!");
            txtMaKH.setBackground(Color.red);
        } else {
            txtMaKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            KhachHangCLass KH = new KhachHangCLass();
            KH.setMaKH(txtMaKH.getText());
            KH.setHo(txtHoKH.getText());
            KH.setTen(txtTenKH.getText());
            if (jDNgaySinh.getDate() != null) {
                java.util.Date utilStartDate = jDNgaySinh.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                KH.setNgaySinh(sqlStartDate);
            }
            if(gtNam.isSelected()){
                KH.setGioiTinh(gtNam.getText());
            }
            if (gtNu.isSelected()) {
                KH.setGioiTinh(gtNu.getText());
            }
            KH.setSoDT(txtSoDT.getText());
            if(jRThanThiet.isSelected()){
                KH.setLoaiKH(jRThanThiet.getText());
            }
            if(jRThuong.isSelected()){
                KH.setLoaiKH(jRThuong.getText());
            }
            if(jRVIP.isSelected()){
                KH.setLoaiKH(jRVIP.getText());
            }
            KH.setDiemTichLuy(txtDiemTichLuy.getText());

            KhachHangCtr khctr = new KhachHangCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                khctr.update(KH);  //Gọi phương thức `update()` trong lớp `NhaCungCapCtr` để cập nhật đối tượng `NhaCungCapClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Khách hàng được cập nhật vào thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Khách hàng chưa được cập nhật!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
         // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách nhà cung cấp và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        KhachHang_Load();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
       // Đoạn code này dùng để tìm kiếm một nhà cung cấp trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaKH.getText().equals("")) {
            sb.append("Mã khách hàng không được để trống!");
            txtMaKH.setBackground(Color.red);
        } else {
            txtMaKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            KhachHangCtr khctr = new KhachHangCtr();
            KhachHangCLass KH = khctr.find(txtMaKH.getText());  //sử dụng phương thức `find()` để tìm kiếm khách hàng có mã `txtMaKH` trong cơ sở dữ liệu

            if (KH != null) //Kiểm tra xem khách hàng được tìm thấy hay không
            {
                //hiển thị thông tin của khách hàng
                int selectedIndex = jTListKH.getSelectedRow();

                txtMaKH.setText(KH.getMaKH());
                txtHoKH.setText(KH.getHo());
                txtTenKH.setText(KH.getTen());
                jDNgaySinh.setDate(KH.getNgaySinh());
                if (KH.getGioiTinh().equals("Nam")) {
                    gtNu.setSelected(false);
                    gtNam.setSelected(true);
                } else {
                    gtNu.setSelected(true);
                    gtNam.setSelected(false);
                }
                txtSoDT.setText(KH.getSoDT());
                if (KH.getLoaiKH().equals("Thân Thiết")) {
                    jRThanThiet.setSelected(true);
                    jRThuong.setSelected(false);
                    jRVIP.setSelected(false);
                } else if (KH.getLoaiKH().equals("Thường")) {
                    jRThanThiet.setSelected(false);
                    jRThuong.setSelected(true);
                    jRVIP.setSelected(false);
                }
                else if (KH.getLoaiKH().equals("VIP")) {
                    jRThanThiet.setSelected(false);
                    jRThuong.setSelected(false);
                    jRVIP.setSelected(true);
                }
                txtDiemTichLuy.setText(KH.getDiemTichLuy());
               

            } else {
                JOptionPane.showMessageDialog(this, "khách hàng tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jRVIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRVIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRVIPActionPerformed

    private void jTListKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListKHMouseClicked
        // đoạn code này dùng để hiển thị thông tin khách hàng khi người dùng click chuột vào một dòng trong bảng jTListKH

        try {
            LamMoi(); //làm mới các trường nhập liệu và các nút trên giao diện `QuanLyKH
            int selectedIndex = jTListKH.getSelectedRow();  // Lấy chỉ số của dòng được chọn trong bảng `jTListKH
            jTListKH.setColumnSelectionInterval(0, 7);  //Thiết lập cột được chọn trong bảng `jTListKH` từ cột 0 đến cột 7
            //hiển thị các giá trị 
            txtMaKH.setText(jTListKH.getValueAt(selectedIndex, 0).toString());
            txtHoKH.setText(jTListKH.getValueAt(selectedIndex, 1).toString());
            txtTenKH.setText(jTListKH.getValueAt(selectedIndex, 2).toString());
            jDNgaySinh.setDate((java.util.Date) jTListKH.getValueAt(selectedIndex, 3));
            String sex = jTListKH.getValueAt(selectedIndex, 4).toString();
            if (sex.equals("Nữ")) {
                gtNu.setSelected(true);
                gtNam.setSelected(false);
            } else if (sex.equals("Nam")) {
                gtNam.setSelected(true);
                gtNu.setSelected(false);
            }
            txtSoDT.setText(jTListKH.getValueAt(selectedIndex, 5).toString());
            String loaikh = jTListKH.getValueAt(selectedIndex, 6).toString();
            if (loaikh.equals("Thân Thiết")) {
                jRThanThiet.setSelected(true);
                jRThuong.setSelected(false);
                jRVIP.setSelected(false);
            } else if (loaikh.equals("Thường")) {
                jRThanThiet.setSelected(false);
                jRThuong.setSelected(true);
                jRVIP.setSelected(false);
            } else if (loaikh.equals("VIP")) {
                jRThanThiet.setSelected(false);
                jRThuong.setSelected(false);
                jRVIP.setSelected(true);
            }

            txtDiemTichLuy.setText(jTListKH.getValueAt(selectedIndex, 7).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
       
    }//GEN-LAST:event_jTListKHMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã khách hàng mới và hiển thị mã đó lên trường nhập liệu "txtMaKH"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaKH() as newMaKH from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã khách hàng mới
            PreparedStatement ps = con.prepareStatement(SQL);   //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())  //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaKH.setText(rs.getString("NEWMAKH"));  //Lấy giá trị của cột "newMaKH" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaKH"
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
            java.util.logging.Logger.getLogger(QuanLyKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKH(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton gtNam;
    private javax.swing.JRadioButton gtNu;
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBTimKiem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRThanThiet;
    private javax.swing.JRadioButton jRThuong;
    private javax.swing.JRadioButton jRVIP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListKH;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtHoKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
