/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.HoaDonClass;
import model.HoaDonCtr;
import model.SachClass;
import model.SachCtr;

/**
 *
 * @author HLPhong
 */
public class QuanLySach extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    String imgPath;
    File file = null;

    /**
     * Creates new form QuanLySach
     */
    public QuanLySach(String maTK, String loaiTK) {
        initComponents();
        txtTomTat.setLineWrap(true); //Tự động xuống dòng
        txtTomTat.setWrapStyleWord(true); //Ngắt từ bên trong
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        KetNoiCSDL();
        Sach_Load();
        this.setLocationRelativeTo(this);
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

    private void Sach_Load()  // Lấy dữ liệu từ bảng sách và hiển thị danh sách sách lên bảng jTSach
    {

        try {
            ps = conn.prepareStatement("SELECT * FROM  SACH");  //truy vấn SQL bằng cách sử dụng đối tượng PreparedStatement với câu lệnh SELECT để lấy tất cả các cột trong bảng SACH
            rs = ps.executeQuery();   //truy vấn và lưu kết quả vào `ResultSet` với phương thức `executeQuery()

            ResultSetMetaData rsd = rs.getMetaData();   //Lấy thông tin về cấu trúc cột của `ResultSet` với `ResultSetMetaData`
            int c = rsd.getColumnCount();   //Lấy tổng số cột của bảng `SACH`

            DefaultTableModel model = (DefaultTableModel) jTListSach.getModel();   //Khởi tạo một `DefaultTableModel` từ bảng `jTSach`
            model.setRowCount(0);   //Xóa toàn bộ các hàng của bảng `jTSach`

            while (rs.next())  //Lặp lại việc lấy từng bản ghi từ `ResultSet` với phương thức `next()`
            {
                Vector v1 = new Vector();   //Khởi tạo một đối tượng `Vector` để lưu giá trị của từng cột trong một hàng
                for (int i = 1; i <= c; i++)   //Lặp lại việc lấy giá trị của từng cột của một bản ghi bằng cách sử dụng phương thức `getString()` hoặc `getDate()` với chỉ số cột tương ứng
                {
                    v1.add(rs.getString("MaSach"));
                    v1.add(rs.getString("TenSach"));
                    v1.add(rs.getString("MaTL"));
                    v1.add(rs.getString("TenTG"));
                    v1.add(rs.getDate("NamXB"));
                    v1.add(rs.getString("NXB"));
                    v1.add(rs.getString("HinhThucBia"));
                    v1.add(rs.getString("SL"));
                    v1.add(rs.getString("Gia"));
                    v1.add(rs.getString("MaNCC"));
                    v1.add(rs.getString("NgonNgu"));
                    v1.add(rs.getString("TomTat"));
                }   
                model.addRow(v1);   //Thêm một hàng vào `DefaultTableModel` với đối tượng `Vector` chứa giá trị của từng cột
                jTListSach.setModel(model);   //Cập nhật lại bảng `jTSach` với `DefaultTableModel` đã được cập nhật
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }  //Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp truy vấn dữ liệu bị lỗi
    }

    public void LamMoi() {
        //làm mới các trường dữ liệu
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtMaTL.setText("");
        txtTacGia.setText("");
        jDNamXB.setDate(null);
        txtNhaXB.setText("");
        txtTomTat.setText("");
        txtSL.setText("");
        txtGia.setText("");
        txtMaNCC.setText("");
        txtLB.setText("");
        txtNgonNgu.setText("");
        txtHinhAnh.setText("");
        txtHinhAnh.setIcon(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListSach = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchSach = new javax.swing.JTextField();
        txtHinhAnh = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtMaSach = new javax.swing.JTextField();
        txtMaTL = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jDNamXB = new com.toedter.calendar.JDateChooser();
        txtMaNCC = new javax.swing.JTextField();
        jBDelete = new javax.swing.JButton();
        jBRefresh = new javax.swing.JButton();
        jBInsert = new javax.swing.JButton();
        jBUpdate = new javax.swing.JButton();
        txtSearchMaSach = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtNhaXB = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtLB = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNgonNgu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTomTat = new javax.swing.JTextArea();
        jBBrowser = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(184, 177, 177));

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ SÁCH");

        jPanel2.setBackground(new java.awt.Color(243, 231, 231));

        jTListSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Mã TL", "Tên TG", "Năm XB", "Nhà XB", "Loại bìa", "SL", "Giá", "Mã NCC", "Ngôn ngữ", "TomTat"
            }
        ));
        jTListSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListSachMouseClicked(evt);
            }
        });
        jTListSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTListSachKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTListSach);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Nhập thông tin cần tìm");

        txtSearchSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSachActionPerformed(evt);
            }
        });
        txtSearchSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSachKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtHinhAnh.setBackground(new java.awt.Color(59, 193, 160));
        txtHinhAnh.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtHinhAnh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSachActionPerformed(evt);
            }
        });

        txtMaTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTLActionPerformed(evt);
            }
        });

        txtTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSachActionPerformed(evt);
            }
        });

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã sách");

        jLabel4.setText("Tên sách");

        jLabel5.setText("Mã thể loại");

        jLabel6.setText("Tên tác giả");

        jLabel7.setText("Năm xuất bản");

        jLabel8.setText("Số lượng");

        jLabel9.setText("Giá");

        jLabel10.setText("Mã nhà cung cấp");

        txtSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLActionPerformed(evt);
            }
        });

        jBDelete.setText("DELETE");
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });

        jBRefresh.setText("REFRESH");
        jBRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRefreshActionPerformed(evt);
            }
        });

        jBInsert.setText("INSERT");
        jBInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInsertActionPerformed(evt);
            }
        });

        jBUpdate.setText("UPDATE");
        jBUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUpdateActionPerformed(evt);
            }
        });

        txtSearchMaSach.setText("Search");
        txtSearchMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMaSachActionPerformed(evt);
            }
        });

        jLabel11.setText("Nhà xuất bản");

        txtNhaXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhaXBActionPerformed(evt);
            }
        });

        jLabel12.setText("Loại bìa:");

        txtLB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLBActionPerformed(evt);
            }
        });

        jLabel13.setText("Ngôn ngữ:");

        txtNgonNgu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgonNguActionPerformed(evt);
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
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(txtMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtTacGia))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgonNgu)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSearchMaSach))
                                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGia)
                                    .addComponent(txtLB)
                                    .addComponent(jDNamXB, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel8))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNhaXB, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jBInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jBUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 480, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDNamXB, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhaXB, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );

        txtTomTat.setColumns(20);
        txtTomTat.setRows(5);
        txtTomTat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        jScrollPane3.setViewportView(txtTomTat);

        jBBrowser.setText("Browser");
        jBBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBrowserActionPerformed(evt);
            }
        });

        jbTroLai.setBackground(new java.awt.Color(195, 105, 75));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jbTroLai)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBBrowser))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbTroLai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK);  //khởi tạo đối tượng quản lý 
        ql.setVisible(true);  // hiển thị giao diện quản lý
        this.dispose();  //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void txtSearchSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSachActionPerformed

    private void txtSearchSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSachKeyReleased
        //Đoạn code này được sử dụng để tìm kiếm thông tin của sách trong bảng `jTSach` và cập nhật lại bảng sau khi lọc
        
        DefaultTableModel SearchTable = (DefaultTableModel) jTListSach.getModel();   //Khởi tạo một đối tượng `DefaultTableModel` từ bảng `jTSach`
        String search = txtSearchSach.getText();   //Lấy nội dung tìm kiếm từ `txtSearchSach` và lưu vào biến `search`
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(SearchTable);   // Khởi tạo một đối tượng `TableRowSorter` được đặt tên là `tr`, sử dụng lớp `DefaultTableModel` và áp dụng cho bảng `SearchTable`
        jTListSach.setRowSorter(tr);  //Thiết lập `tr` làm sắp xếp hàng để bảng `jTSach` có thể sử dụng kết quả tìm kiếm được lọc
        tr.setRowFilter(RowFilter.regexFilter(search));  //Áp dụng bộ lọc hàng tìm kiếm với giá trị của biến `search` bằng phương thức `regexFilter` của lớp `RowFilter`. Bộ lọc hàng này được sử dụng để tìm kiếm các hàng chứa chuỗi giống với giá trị `search`

    }//GEN-LAST:event_txtSearchSachKeyReleased

    private void txtMaTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTLActionPerformed

    private void txtMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSachActionPerformed

    private void txtSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLActionPerformed

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSachActionPerformed

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed
        // Đoạn code này được sử dụng để xóa một quyển sách trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();   //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaSach.getText().equals(""))  //Kiểm tra nếu mã sách để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("Mã Sách không được để trống!!!");
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0)  //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó và không thực hiện thêm tài khoản mới vào cơ sở dữ liệu
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            SachCtr sctr = new SachCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa Sách này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa sách hay không

            {
                sctr.delete(txtMaSach.getText()); //gọi phương thức `delete()` trong lớp `SachCtr` để xóa sách tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Sách đã xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sách chưa được xóa!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBDeleteActionPerformed

    private void jBRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRefreshActionPerformed
        // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách các quyển sách và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Sach_Load();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBRefreshActionPerformed

    private void jBInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInsertActionPerformed
        // Đoạn code này được sử dụng để thêm một sách mới vào cơ sở dữ liệu 
        StringBuilder sb = new StringBuilder();
        if (txtMaSach.getText().equals("")) {
            sb.append("Mã sách không được để trống!!!");
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            SachClass s = new SachClass();
            //thiết lập các giá trị  
            s.setMaSach(txtMaSach.getText());
            s.setTenSach(txtTenSach.getText());
            s.setMaTL(txtMaTL.getText());
            s.setTenTG(txtTacGia.getText());
            s.setNhaXB(txtNhaXB.getText());

            if (jDNamXB.getDate() != null)  //Kiểm tra xem trường "jDNamXB" có được nhập hay không
            {
                java.util.Date utilStartDate = jDNamXB.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());  //chuyển đổi giá trị của trường "jDNamXB" từ định dạng `java.util.Date` sang định dạng `java.sql.Date`
                s.setNamXB(sqlStartDate);
            }

            s.setHinhThucBia(txtLB.getText());
            s.setGia(txtGia.getText());
            s.setSoLuong(txtSL.getText());
            s.setTomTat(txtTomTat.getText());
            s.setMaNCC(txtMaNCC.getText());
            s.setNgonNgu(txtNgonNgu.getText());
            s.setHinhAnh(txtHinhAnh.getText());
            if (file != null)  //Kiểm tra xem có tệp tin được chọn để tải lên hay không
            {
                String filename = file.getName();
                s.setHinhAnh(filename);
            }

            SachCtr scr = new SachCtr();
            scr.insert(s);  //Gọi phương thức `insert()` trong lớp `SachCtr` để thêm đối tượng `SachClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Sách được thêm vào thành công!");  //Hiển thị thông báo xác nhận nếu thêm sách mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());  //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm sách mới

        }
    }//GEN-LAST:event_jBInsertActionPerformed

    private void jBUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUpdateActionPerformed
        // Đoạn code này được sử dụng để cập nhật một quyển sách vào cơ sở dữ liệu 
        StringBuilder sb = new StringBuilder();
        if (txtMaSach.getText().equals("")) {
            sb.append("Mã sách không được để trống!!!");
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            SachClass s = new SachClass();
            s.setMaSach(txtMaSach.getText());
            s.setTenSach(txtTenSach.getText());
            s.setMaTL(txtMaTL.getText());
            s.setTenTG(txtTacGia.getText());
            s.setNhaXB(txtNhaXB.getText());

            if (jDNamXB.getDate() != null) {
                java.util.Date utilStartDate = jDNamXB.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                s.setNamXB(sqlStartDate);
            }

            s.setHinhThucBia(txtLB.getText());
            s.setGia(txtGia.getText());
            s.setSoLuong(txtSL.getText());
            s.setTomTat(txtTomTat.getText());
            s.setMaNCC(txtMaNCC.getText());
            s.setNgonNgu(txtNgonNgu.getText());
            s.setHinhAnh(txtHinhAnh.getText());
            if (file != null) {
                String filename = file.getName();
                s.setHinhAnh(filename);
            }
            SachCtr sctr = new SachCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                sctr.update(s); //Gọi phương thức `update()` trong lớp `SachCtr` để thêm đối tượng `SachClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Sách được cập nhật vào thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sách chưa được cập nhật!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }//GEN-LAST:event_jBUpdateActionPerformed

    private void txtNhaXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhaXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhaXBActionPerformed

    private void txtLBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLBActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void jTListSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListSachMouseClicked
        // Đoạn code này được sử dụng để hiển thị thông tin của một cuốn sách được chọn từ bảng danh sách sách
        try {
            LamMoi();  //Gọi phương thức `LamMoi()` để làm mới các trường nhập liệu 
            int selectedIndex = jTListSach.getSelectedRow();  // Lấy chỉ số hàng được chọn trong bảng danh sách sách
            String ms = jTListSach.getValueAt(selectedIndex, 0).toString();   //Lấy giá trị mã sách tương ứng của hàng được chọn.
            jTListSach.setColumnSelectionInterval(0, 11);  //Thiết lập khu vực được chọn trong bảng danh sách sách
            //lấy các giá trị từ hàng được chọn hiển thị lên các trường nhập liệu
            txtMaSach.setText(ms);
            txtTenSach.setText(jTListSach.getValueAt(selectedIndex, 1).toString());
            txtMaTL.setText(jTListSach.getValueAt(selectedIndex, 2).toString());
            txtTacGia.setText(jTListSach.getValueAt(selectedIndex, 3).toString());
            jDNamXB.setDate((java.util.Date) jTListSach.getValueAt(selectedIndex, 4));
            txtNhaXB.setText(jTListSach.getValueAt(selectedIndex, 5).toString());
            txtLB.setText(jTListSach.getValueAt(selectedIndex, 6).toString());
            txtSL.setText(jTListSach.getValueAt(selectedIndex, 7).toString());
            txtGia.setText(jTListSach.getValueAt(selectedIndex, 8).toString());
            txtMaNCC.setText(jTListSach.getValueAt(selectedIndex, 9).toString());
            txtNgonNgu.setText(jTListSach.getValueAt(selectedIndex, 10).toString());
            txtTomTat.setText(jTListSach.getValueAt(selectedIndex, 11).toString());
            String SQL = "SELECT HINHANH FROM SACH WHERE MASACH=?";   //Tạo một câu truy vấn SQL để lấy dữ liệu hình ảnh của sách tương ứng từ cơ sở dữ liệu
            PreparedStatement ps = conn.prepareStatement(SQL);    //Tạo đối tượng `PreparedStatement` để chuẩn bị cho câu truy vấn SQL
            ps.setString(1, ms);
            ResultSet rs = ps.executeQuery();    //Thực thi câu truy vấn SQL và lưu kết quả vào đối tượng `ResultSet`
            if (rs.next())  //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                String ha = rs.getString("HinhAnh");   // Lấy giá trị hình ảnh của sách tương ứng từ kết quả của câu truy vấn SQL
                if (ha != null)  
                {
                    showAnh(ha);
                } else {
                    txtHinhAnh.setIcon(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListSachMouseClicked

    private void txtNgonNguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgonNguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgonNguActionPerformed

    private void jBBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBrowserActionPerformed
        // Đoạn code này được sử dụng để chọn tệp tin hình ảnh từ thiết bị và hiển thị hình ảnh
        JFileChooser fc = new JFileChooser();  //Khởi tạo một đối tượng `JFileChooser` để cho phép người dùng chọn tệp tin hình ản
        try {
            fc.setCurrentDirectory(new File(getClass().getResource("/icon/").toURI()));  //Thiết lập thư mục mặc định cho hộp thoại chọn tệp tin là thư mục  icon trong dự án
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Tạo một bộ lọc định dạng tệp tin để chỉ cho phép người dùng chọn các tệp tin hình ảnh có định dạng là JPG, GIF hoặc PNG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        fc.addChoosableFileFilter(filter);  //Thêm bộ lọc định dạng tệp tin vào hộp thoại chọn tệp tin
        int result = fc.showSaveDialog(fc);   //Mở hộp thoại chọn tệp tin và lấy kết quả trả về từ người dùng
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();  //Lấy tệp tin hình ảnh được chọn
            String path = file.getAbsolutePath();   //Lấy đường dẫn đến tệp tin hình ảnh được chọn
            txtHinhAnh.setIcon(ResizeCover(path, null));  // Hiển thị hình ảnh được chọn lên trường "txtHinhAnh"
            imgPath = path;  // Lưu đường dẫn đến tệp tin hình ảnh được chọn vào biến `imgPath
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "No File Selected");   //Nếu người dùng không chọn tệp tin hình ảnh, hiển thị thông báo cho người dùng
        }
    }//GEN-LAST:event_jBBrowserActionPerformed

    private void txtSearchMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMaSachActionPerformed
        // Đoạn code này dùng để tìm kiếm một quyển sách trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaSach.getText().equals("")) {
            sb.append("Mã sách không được để trống!");
            txtMaSach.setBackground(Color.red);
        } else {
            txtMaSach.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            SachCtr hdc = new SachCtr();
            SachClass s = hdc.find(txtMaSach.getText()); //sử dụng phương thức `find()` để tìm kiếm sách có mã `txtMaSach` trong cơ sở dữ liệu
            if (s != null)  //Kiểm tra xem sách được tìm thấy hay không
            {
                //hiển thị thông tin của sách
                int selectedIndex = jTListSach.getSelectedRow();
                txtMaSach.setText(s.getMaSach());
                txtTenSach.setText(s.getTenSach());
                txtMaTL.setText(s.getMaTL());
                txtTacGia.setText(s.getTenTG());
                txtNhaXB.setText(s.getNhaXB());
                jDNamXB.setDate(s.getNamXB());
                txtTomTat.setText(s.getTomTat());
                txtLB.setText(s.getHinhThucBia());
                txtSL.setText(s.getSoLuong());
                txtGia.setText(s.getGia());
                txtMaNCC.setText(s.getMaNCC());
                txtNgonNgu.setText(s.getNgonNgu());
                String ha = s.getHinhAnh();
                if (ha.equals("") == false) {
                    showAnh(ha);
                } else {
                    txtHinhAnh.setIcon(null);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Sách tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchMaSachActionPerformed

    private void jTListSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTListSachKeyReleased
        // Đoạn code này được sử dụng để xử lý sự kiện khi người dùng chọn một cuốn sách trong bảng danh sách sách trên giao diện `QuanLySach` bằng cách sử dụng bàn phím
        try {
            LamMoi();
            int selectedIndex = jTListSach.getSelectedRow();
            String ms = jTListSach.getValueAt(selectedIndex, 0).toString();
            jTListSach.setColumnSelectionInterval(0, 11);
            txtMaSach.setText(ms);
            txtTenSach.setText(jTListSach.getValueAt(selectedIndex, 1).toString());
            txtMaTL.setText(jTListSach.getValueAt(selectedIndex, 2).toString());
            txtTacGia.setText(jTListSach.getValueAt(selectedIndex, 3).toString());
            jDNamXB.setDate((java.util.Date) jTListSach.getValueAt(selectedIndex, 4));
            txtNhaXB.setText(jTListSach.getValueAt(selectedIndex, 5).toString());
            txtLB.setText(jTListSach.getValueAt(selectedIndex, 6).toString());
            txtSL.setText(jTListSach.getValueAt(selectedIndex, 7).toString());
            txtGia.setText(jTListSach.getValueAt(selectedIndex, 8).toString());
            txtMaNCC.setText(jTListSach.getValueAt(selectedIndex, 9).toString());
            txtNgonNgu.setText(jTListSach.getValueAt(selectedIndex, 10).toString());
            txtTomTat.setText(jTListSach.getValueAt(selectedIndex, 11).toString());
            String SQL = "SELECT HINHANH FROM SACH WHERE MASACH=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, ms);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ha = rs.getString("HinhAnh");
                if (ha != null)   //Kiểm tra xem hình ảnh của sách tương ứng có tồn tại hay không
                {
                    showAnh(ha);
                } else {
                    txtHinhAnh.setIcon(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListSachKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // Đoạn code này được sử dụng để tạo một mã sách mới và hiển thị mã đó lên trường nhập liệu "txtMaSach"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())  //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaSach() as newMaSach from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã sách mới
            PreparedStatement ps = con.prepareStatement(SQL);  //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())   //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaSach.setText(rs.getString("NEWMASACH"));  //Lấy giá trị của cột "newMaSach" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaSach"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   private void showAnh(File file)
    {
        //đoạn code này được dùng để hiển thị một hình ảnh từ đường dẫn tệp được chọn bởi người dùng
        ImageIcon MyImage = new ImageIcon(file.getAbsolutePath());  //Tạo một đối tượng ImageIcon có chứa hình ảnh được chọn từ đường dẫn tệp được truyền vào
        Image img = MyImage.getImage();   
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        txtHinhAnh.setIcon(image);
    }

    private void showAnh(String name) 
    {
        //Đoạn code được sử dụng để hiển thị hình ảnh 
        ImageIcon MyImage = new ImageIcon(getClass().getResource("/icon/" + name));  //Tạo một đối tượng `ImageIcon` từ tập tin hình ảnh `name` được định nghĩa trong thư mục `/icon/`
        Image img = MyImage.getImage();   //Lấy `Image` từ đối tượng `ImageIcon` bằng phương thức `getImage()` và lưu trữ vào biến `img`
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);  //Tạo một bản sao của hình ảnh được lưu trữ trong biến `img` với kích thước cho phù hợp với vùng khu vực hiển thị hình ảnh `txtHinhAnh` bằng phương thức `getScaledInstance()`
        ImageIcon image = new ImageIcon(newImg);   //Khởi tạo một `ImageIcon` mới từ bản sao hình ảnh `newImg`
        txtHinhAnh.setIcon(image);   //Thiết lập `image` làm hình ảnh hiển thị trong vùng khu vực `txtHinhAnh`
    }

    public ImageIcon ResizeCover(String ImagePath, byte[] pic)  //có hai đối số đầu vào: `ImagePath` và `pic` để truyền đường dẫn tới file hình ảnh và một mảng byte chứa dữ liệu hình ản
    {
        
        ImageIcon MyImage = null;
        if (ImagePath != null)
        {
            MyImage = new ImageIcon(ImagePath);  //phương thức sử dụng đường dẫn tương ứng để tạo một đối tượng ImageIcon
        } else
        {
            MyImage = new ImageIcon(pic);  //phương thức sử dụng mảng byte chứa dữ liệu hình ảnh để tạo đối tượng ImageIcon.
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }


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
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySach(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBrowser;
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBInsert;
    private javax.swing.JButton jBRefresh;
    private javax.swing.JButton jBUpdate;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDNamXB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTListSach;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtGia;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtLB;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMaTL;
    private javax.swing.JTextField txtNgonNgu;
    private javax.swing.JTextField txtNhaXB;
    private javax.swing.JTextField txtSL;
    private javax.swing.JButton txtSearchMaSach;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextArea txtTomTat;
    // End of variables declaration//GEN-END:variables
}
