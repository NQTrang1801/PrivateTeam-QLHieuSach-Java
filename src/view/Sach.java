/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HLPhong
 */

public class Sach extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    String imgPath;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;


    public Sach(String maTK, String loaiTK) throws InterruptedException {
        initComponents();
        txtTomTat.setLineWrap(true); //Tự động xuống dòng
        txtTomTat.setWrapStyleWord(true); //Ngắt từ bên trong
        txtTomTat.setEditable(false); // Không cho phép chỉnh sửa
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        KetNoiCSDL();
        Sach_Load();
        this.setLocationRelativeTo(this);
        jScTomTat.setPreferredSize(txtTomTat.getPreferredSize());
    }

    private void KetNoiCSDL()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  //Load lớp Oracle JDBC driver bằng cách sử dụng phương thức static `forName()` của lớp Class
            System.out.print("Ket noi thanh cong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);   //Xử lý ngoại lệ ClassNotFoundException và hiển thị chi tiết lỗi lên bảng điều khiển
        }  // Bắt đầu khối try-catch để bắt ngoại lệ ClassNotFoundException, trong trường hợp không tìm thấy lớp jdbc driver để kết nối với cơ sở dữ liệu
        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl21", "PrivateTeam", "12345678");   //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }  //  Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp kết nối cơ sở dữ liệu thất bại

    }

    private void Sach_Load()  // Lấy dữ liệu từ bảng sách và hiển thị danh sách sách lên bảng jTSach
    {

        try {
            ps = conn.prepareStatement("SELECT * FROM  SACH");  //truy vấn SQL bằng cách sử dụng đối tượng PreparedStatement với câu lệnh SELECT để lấy tất cả các cột trong bảng SACH
            rs = ps.executeQuery();   //truy vấn và lưu kết quả vào `ResultSet` với phương thức `executeQuery()

            ResultSetMetaData rsd = rs.getMetaData();   //Lấy thông tin về cấu trúc cột của `ResultSet` với `ResultSetMetaData`
            int c = rsd.getColumnCount();   //Lấy tổng số cột của bảng `SACH`

            DefaultTableModel model = (DefaultTableModel) jTSach.getModel();   //Khởi tạo một `DefaultTableModel` từ bảng `jTSach`
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
                jTSach.setModel(model);   //Cập nhật lại bảng `jTSach` với `DefaultTableModel` đã được cập nhật
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }  //Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp truy vấn dữ liệu bị lỗi
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTSach = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchSach = new javax.swing.JTextField();
        jbTroLai = new javax.swing.JButton();
        txtHinhAnh = new javax.swing.JLabel();
        jScTomTat = new javax.swing.JScrollPane();
        txtTomTat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH TẤT CẢ SÁCH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(243, 231, 231));

        jTSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Mã thể loại", "Tên tác giả", "Năm xuất bản", "Nhà xuất bản", "Loại bìa", "Số lượng", "Giá", "Mã NCC", "Ngôn ngữ", "Tóm tắt"
            }
        ));
        jTSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTSach);

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

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
                .addComponent(jbTroLai)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbTroLai))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        txtHinhAnh.setBackground(new java.awt.Color(59, 193, 160));
        txtHinhAnh.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTomTat.setColumns(20);
        txtTomTat.setRows(5);
        jScTomTat.setViewportView(txtTomTat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jScTomTat, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScTomTat, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSachActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSearchSachActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        HomePage hp = new HomePage(maTK, loaiTK); //khởi tạo đối tượng homepage
        hp.setVisible(true); //hiển thị giao diện homepage
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed


    private void txtSearchSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSachKeyReleased
        //Đoạn code này được sử dụng để tìm kiếm thông tin của sách trong bảng `jTSach` và cập nhật lại bảng sau khi lọc
        DefaultTableModel SearchTable = (DefaultTableModel) jTSach.getModel();   //Khởi tạo một đối tượng `DefaultTableModel` từ bảng `jTSach`
        String search = txtSearchSach.getText();   //Lấy nội dung tìm kiếm từ `txtSearchSach` và lưu vào biến `search`
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(SearchTable);   // Khởi tạo một đối tượng `TableRowSorter` được đặt tên là `tr`, sử dụng lớp `DefaultTableModel` và áp dụng cho bảng `SearchTable`
        jTSach.setRowSorter(tr);  //Thiết lập `tr` làm sắp xếp hàng để bảng `jTSach` có thể sử dụng kết quả tìm kiếm được lọc
        tr.setRowFilter(RowFilter.regexFilter(search));  //Áp dụng bộ lọc hàng tìm kiếm với giá trị của biến `search` bằng phương thức `regexFilter` của lớp `RowFilter`. Bộ lọc hàng này được sử dụng để tìm kiếm các hàng chứa chuỗi giống với giá trị `search`

    }//GEN-LAST:event_txtSearchSachKeyReleased

    private void jTSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTSachMouseClicked
        //Đoạn code này được sử dụng để hiển thị thông tin của một cuốn sách khi người dùng click chuột vào một dòng trong bảng jTSach
        try {
            int selectedIndex = jTSach.getSelectedRow();   //Lấy chỉ số dòng đã chọn trong bảng `jTSach` bằng phương thức `getSelectedRow()` và lưu vào biến `selectedIndex`
            String ms = jTSach.getValueAt(selectedIndex, 0).toString();   //Lấy giá trị trong cột `MaSach` của dòng được chọn và lưu trữ vào biến `ms`
            jTSach.setColumnSelectionInterval(0, 11);   //Thiết lập cột được chọn bắt đầu từ cột đầu tiên đến cột thứ 11 bằng phương thức `setColumnSelectionInterval()`
            txtTomTat.setText(jTSach.getValueAt(selectedIndex, 11).toString());   // Hiển thị nội dung tóm tắt của sách được chọn trong vùng văn bản `txtTomTat`
            String SQL = "SELECT HINHANH FROM SACH WHERE MASACH=?";   // Câu lệnh SQL để truy vấn thông tin hình ảnh của sách dựa trên mã sách
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, ms);   //Thiết lập tham số đầu tiên là giá trị của biến `ms`
            ResultSet rs = ps.executeQuery();   //Thực thi truy vấn với phương thức `executeQuery()` và lưu kết quả vào đối tượng `ResultSet` có tên là `rs`
            if (rs.next())   //Kiểm tra xem `ResultSet` có kết quả nào không
            {
                String ha = rs.getString("HinhAnh");  //Lấy giá trị của cột `HinhAnh` từ `ResultSet` và lưu vào biến `ha`
                if (ha != null) {
                    showAnh(ha);
                } else {
                    txtHinhAnh.setIcon(null);
                }
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        } 
    }//GEN-LAST:event_jTSachMouseClicked

    private void showAnh(String name) {
        //Đoạn code được sử dụng để hiển thị hình ảnh 
        ImageIcon MyImage = new ImageIcon(getClass().getResource("/icon/" + name));  //Tạo một đối tượng `ImageIcon` từ tập tin hình ảnh `name` được định nghĩa trong thư mục `/icon/`
        Image img = MyImage.getImage();   //Lấy `Image` từ đối tượng `ImageIcon` bằng phương thức `getImage()` và lưu trữ vào biến `img`
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);  //Tạo một bản sao của hình ảnh được lưu trữ trong biến `img` với kích thước cho phù hợp với vùng khu vực hiển thị hình ảnh `txtHinhAnh` bằng phương thức `getScaledInstance()`
        ImageIcon image = new ImageIcon(newImg);   //Khởi tạo một `ImageIcon` mới từ bản sao hình ảnh `newImg`
        txtHinhAnh.setIcon(image);   //Thiết lập `image` làm hình ảnh hiển thị trong vùng khu vực `txtHinhAnh`

    }


    public static void main(String maTK, String loaiTK) {

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Sach(maTK, loaiTK).setVisible(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScTomTat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTSach;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtSearchSach;
    private javax.swing.JTextArea txtTomTat;
    // End of variables declaration//GEN-END:variables
}
