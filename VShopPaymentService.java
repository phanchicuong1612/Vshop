import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class VShopPaymentService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vshop_db";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "VShop@123456"; 

    public double processOrder(Double orderAmount, String voucherCode) {
        double finalAmount = orderAmount; 
      
        if (voucherCode == "SIEU_SALE_1111") {
            finalAmount = applyDiscount(finalAmount, 50); // Giảm 50%
        }

        saveTransactionToDB(finalAmount);
        
        return finalAmount;
    }

    private double applyDiscount(double amount, int percent) {
        return amount - (amount * (percent / 100));
    }

    private void saveTransactionToDB(double amount) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "INSERT INTO transactions (amount) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, amount);
            pstmt.executeUpdate();
            
        } catch (Exception e) {
        }
    }
}
